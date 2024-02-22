package com.example.devproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Welcome extends AppCompatActivity  {
    private LinearLayout dataLayout;
    EditText etCity, etCountry;
    TextView tvResult;
    private final String url = "http://api.openweathermap.org/data/2.5/weather";
    private final String appid = "e53301e27efa0b66d05045d91b2742d3";
    DecimalFormat df = new DecimalFormat(  "#.##");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

          etCity=findViewById(R.id.etCity);
          etCountry=findViewById(R.id.etCountry);
          tvResult=  findViewById(R.id.tvResult);
        // Obtenir la date actuelle
        String currentDate = new SimpleDateFormat("EEEE d MMMM yyyy", Locale.getDefault()).format(new Date());

        // Mettre à jour la vue tvDate avec la date actuelle
        TextView tvDate = findViewById(R.id.tvDate);
        tvDate.setText(currentDate);
        }
    public void getweatherDetails(View view) {
        String tempUrl = "";
        String city=etCity.getText().toString().trim();
        String country = etCountry.getText().toString().trim();
        if(city.equals("")){
            tvResult.setText("City field can not be empty!");}
        else{
            if(!country.equals("")){
                tempUrl = url + "?q=" + city + "," + country + "&appid=" + appid;
                System.out.println(tempUrl);
            }
            else{
                tempUrl = url + "?q=" + city + "&appid=" + appid;
                System.out.println(tempUrl);
            }

            StringRequest stringRequest = new StringRequest (Request.Method. POST, tempUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    // Log.d("response", response);
                    String output="";
                    try {
                        JSONObject jsonResponse=new JSONObject(response);
                        JSONArray jsonArray=jsonResponse.getJSONArray("weather");
                        JSONObject jsonObjectWeather=jsonArray.getJSONObject(0);
                        String description =jsonObjectWeather.getString("description");
                        JSONObject jsonObjectMain =jsonResponse.getJSONObject("main");
                        double temp=jsonObjectMain.getDouble("temp");
                        double temp1=temp-273.15;
                        double feelsLike=jsonObjectMain.getDouble("feels_like");
                        double feelsLike1=feelsLike-273.15;
                        float pressure=jsonObjectMain.getInt("pressure");
                        int humidity=jsonObjectMain.getInt("humidity");
                        JSONObject jsonObjectWind=jsonResponse.getJSONObject("wind");
                        String wind=jsonObjectWind.getString("speed");
                        JSONObject jsonObjectClouds=jsonResponse.getJSONObject("clouds");
                        String clouds =jsonObjectClouds.getString("all");
                        JSONObject jsonObjectSys=jsonResponse.getJSONObject("sys");
                        String countryName =jsonObjectSys.getString("country");
                        String cityName=jsonResponse.getString("name");

                        String output1= df.format(temp1) + " °C\n";
                        String output2= humidity + "%\n";
                        String output3= df.format(feelsLike1) + " °C\n";
                        String output4= pressure + " hPa";
                        // Créer un Intent pour démarrer la nouvelle activité
                        Intent intent = new Intent(Welcome.this, WeatherDetailsActivity.class);
                        // Ajouter les données météorologiques à l'intention
                        intent.putExtra("city", cityName);
                        intent.putExtra("country", countryName);
                        intent.putExtra("description", description);
                        intent.putExtra("temperature", output1);
                        intent.putExtra("feelsLike", output3);
                        intent.putExtra("pressure", output4);
                        intent.putExtra("humidity", output2);
                        intent.putExtra("wind", wind);
                        intent.putExtra("clouds", clouds);
                        startActivity(intent);

                    }
                    catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse (VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            });
            RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);

        }
    }




}

























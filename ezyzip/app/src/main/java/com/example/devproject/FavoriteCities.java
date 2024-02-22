package com.example.devproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class FavoriteCities extends AppCompatActivity {
    private final String url = "http://api.openweathermap.org/data/2.5/weather";
    private final String appid = "e53301e27efa0b66d05045d91b2742d3";
    DecimalFormat df = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite_cities);

        // Récupérer les données transmises via l'intent
        Intent intent = getIntent();
        final String city;
        final String country;

        if (intent.hasExtra("cityAdd")) {
            city = intent.getStringExtra("cityAdd");
        } else {
            city = "";
        }

        if (intent.hasExtra("countryAdd")) {
            country = intent.getStringExtra("countryAdd");
        } else {
            country = "";
        }

        // Afficher le nom de la ville si les données sont disponibles
        Button btnCity = findViewById(R.id.tvCityAdd);
        if (!city.isEmpty()) {
            btnCity.setText(city);
            btnCity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Appeler la fonction getweatherDetails
                    getWeatherDetails(city, country);
                }
            });
        } else {
            btnCity.setText("City not provided");
        }
    }

    // Méthode pour récupérer les détails de la météo
    private void getWeatherDetails(String city, String country) {
        String tempUrl = "";
        if (city.isEmpty()) {
            Toast.makeText(getApplicationContext(), "City field can not be empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!country.isEmpty()) {
            tempUrl = url + "?q=" + city + "," + country + "&appid=" + appid;
        } else {
            tempUrl = url + "?q=" + city + "&appid=" + appid;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, tempUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            JSONArray jsonArray = jsonResponse.getJSONArray("weather");
                            JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);
                            String description = jsonObjectWeather.getString("description");
                            JSONObject jsonObjectMain = jsonResponse.getJSONObject("main");
                            double temp = jsonObjectMain.getDouble("temp");
                            double temp1 = temp - 273.15;
                            double feelsLike = jsonObjectMain.getDouble("feels_like");
                            double feelsLike1 = feelsLike - 273.15;
                            float pressure = jsonObjectMain.getInt("pressure");
                            int humidity = jsonObjectMain.getInt("humidity");
                            JSONObject jsonObjectWind = jsonResponse.getJSONObject("wind");
                            String wind = jsonObjectWind.getString("speed");
                            JSONObject jsonObjectClouds = jsonResponse.getJSONObject("clouds");
                            String clouds = jsonObjectClouds.getString("all");
                            JSONObject jsonObjectSys = jsonResponse.getJSONObject("sys");
                            String countryName = jsonObjectSys.getString("country");
                            String cityName = jsonResponse.getString("name");

                            String output1 = df.format(temp1) + " °C\n";
                            String output2 = humidity + "%\n";
                            String output3 = df.format(feelsLike1) + " °C\n";
                            String output4 = pressure + " hPa";

                            // Créer un Intent pour démarrer la nouvelle activité
                            Intent intent = new Intent(FavoriteCities.this, WeatherDetailsActivity.class);
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

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}

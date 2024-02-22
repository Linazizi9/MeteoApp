package com.example.devproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WeatherDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_details_activity);

        // Récupérer les données météorologiques de l'intention
        Intent intent = getIntent();
        String city = intent.getStringExtra("city");
        String country = intent.getStringExtra("country");
        String description = intent.getStringExtra("description");
        String temperature = intent.getStringExtra("temperature");
        String feelsLike = intent.getStringExtra("feelsLike");
        String pressure = intent.getStringExtra("pressure");
        String humidity = intent.getStringExtra("humidity");
        String wind = intent.getStringExtra("wind");
        String clouds = intent.getStringExtra("clouds");

        // Récupérez d'autres données météorologiques de l'intention si nécessaire
        // Afficher les données météorologiques dans votre layout
        TextView tvCity = findViewById(R.id.tvCity);
        TextView tvCountry = findViewById(R.id.tvCountry);
        //TextView tvDescription = findViewById(R.id.tvDescription);
        TextView tvTemp = findViewById(R.id.tvTemp);
        TextView tvfeelsLike = findViewById(R.id.tvfeelsLike);
        TextView tvPression = findViewById(R.id.tvPression);
        TextView tvHumidity = findViewById(R.id.tvHumidity);
        TextView tvWind = findViewById(R.id.tvWind);
        TextView tvClouds = findViewById(R.id.tvClouds);

        String currentDate = new SimpleDateFormat("EEEE d MMMM yyyy", Locale.getDefault()).format(new Date());

        // Mettre à jour la vue tvDate avec la date actuelle
        TextView tvDate = findViewById(R.id.tvDate);
        tvDate.setText(currentDate);

        tvCity.setText(city);
        tvCountry.setText(country + " ,");
       // tvDescription.setText("Description : " + description);
        tvTemp.setText("Temperature : " + temperature);
        tvfeelsLike.setText("Ressenti : " + feelsLike);
        tvPression.setText("Pression : " + pressure);
        tvHumidity.setText("Humidité : " + humidity);
        tvWind.setText("Vent : " + wind);
        tvClouds.setText("Nuages : " + clouds);


        Button btnAddFavoriteCity = findViewById(R.id.btnAddFavoriteCity);
        btnAddFavoriteCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeatherDetailsActivity.this, AddfavoriteCity.class);
                startActivity(intent);
            }
        });


    }

}
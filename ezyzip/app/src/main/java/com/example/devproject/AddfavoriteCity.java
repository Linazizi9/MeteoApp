package com.example.devproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddfavoriteCity extends AppCompatActivity {
    private EditText etCity;
    private EditText etCountry;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_favorite_city);

        etCity = findViewById(R.id.etCity);
        etCountry = findViewById(R.id.etCountry);
        btnAdd = findViewById(R.id.btnadd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = etCity.getText().toString();
                String country = etCountry.getText().toString();

                // Vérifiez si les champs ne sont pas vides
                if (!city.isEmpty() && !country.isEmpty()) {
                    // Créer une intention pour ouvrir WeatherDetailsActivity
                    Intent intent = new Intent(AddfavoriteCity.this,FavoriteCities.class);

                    // Ajouter le nom de la ville et du pays à l'intention
                    intent.putExtra("cityAdd", city);
                    intent.putExtra("countryAdd", country);

                    // Ouvrir WeatherDetailsActivity
                    startActivity(intent);
                } else {
                    Toast.makeText(AddfavoriteCity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

package com.example.devproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.devproject.DbUtils.LocalDb;
import com.example.devproject.DbUtils.User;

public class Connexion extends AppCompatActivity {

    private Button btn_login_to_application;
    private EditText edmail;
    private EditText edpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        btn_login_to_application = findViewById(R.id.btn_login_To_Application);
        edmail = findViewById(R.id.ed_mail);
        edpassword = findViewById(R.id.ed_password);

        btn_login_to_application.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail_from_ed = edmail.getText().toString();
                String password_from_ed = edpassword.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        LocalDb dbInstance = RoomImplementation.getmInstance().getDbInstance();
                        User user = dbInstance.UserDao().getUserByMail(mail_from_ed);
                        if (user != null && user.getMail() != null && user.getMail().equalsIgnoreCase(mail_from_ed)) {
                            if (user.getPassword() != null && user.getPassword().equalsIgnoreCase(password_from_ed)) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        // Afficher un message de connexion réussie
                                        Toast.makeText(Connexion.this, "Connexion réussie", Toast.LENGTH_SHORT).show();
                                        // Rediriger vers l'activité Welcome
                                        Intent intent = new Intent(Connexion.this, Welcome.class);
                                        startActivity(intent);
                                        finish(); // Optionnel : fermer l'activité de connexion pour empêcher de revenir en arrière avec le bouton "Retour"
                                    }
                                });
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        // Afficher un message d'échec de connexion (mot de passe incorrect)
                                        Toast.makeText(Connexion.this, "Mot de passe incorrect", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    // Afficher un message d'échec de connexion (adresse e-mail incorrecte)
                                    Toast.makeText(Connexion.this, "Adresse e-mail incorrecte", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();
            }
        });
    }
}
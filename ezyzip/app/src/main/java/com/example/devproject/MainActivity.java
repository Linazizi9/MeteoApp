package com.example.devproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.devproject.Connexion;
import com.example.devproject.DbUtils.LocalDb;
import com.example.devproject.DbUtils.User;
import com.example.devproject.RoomImplementation;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity  {
    private EditText edname;
    private EditText edlastname;
    private EditText edmail;
    private EditText edpassword;
    private EditText edrepassword;
    private Button btnlogin;
    private Button btnSignUp;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        edname = findViewById(R.id.ed_name);
        edlastname = findViewById(R.id.ed_lastname);
        edmail = findViewById(R.id.ed_mail);
        edpassword = findViewById(R.id.ed_password);
        edrepassword = findViewById(R.id.ed_repassword);
        btnlogin = findViewById(R.id.btn_login);
        btnSignUp = findViewById(R.id.btn_signup);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Connexion.class);
                startActivity(intent);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strPassword = edpassword.getText().toString();
                String strConfirmPassword = edrepassword.getText().toString();
                String strmail = edmail.getText().toString();
                String strname = edname.getText().toString();
                String strLastname = edlastname.getText().toString();

                if (strPassword.equals(strConfirmPassword)) {
                    // Utilisez une nouvelle méthode pour sauvegarder les informations de l'utilisateur
                    saveUserInformation(strname, strLastname, strmail, strPassword);
                } else {
                    // Affichez un message d'erreur si les mots de passe ne correspondent pas
                    Toast.makeText(MainActivity.this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveUserInformation(String name, String lastname, String mail, String password) {
        User user = new User();
        user.setName(name);
        user.setLastname(lastname);
        user.setMail(mail);
        user.setPassword(password);

        new Thread(new Runnable() {
            @Override
            public void run() {
                LocalDb dbInstance = RoomImplementation.getmInstance().getDbInstance();
                dbInstance.UserDao().creatUser(user);
            }
        }).start();

        // Une fois l'inscription terminée, redirigez vers la page de connexion
        Intent intent = new Intent(MainActivity.this, Connexion.class);
        startActivity(intent);


    }
}
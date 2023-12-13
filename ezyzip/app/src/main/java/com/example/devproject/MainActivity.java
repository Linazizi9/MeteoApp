package com.example.devproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText edname;
    private EditText edlastname;
    private EditText edmail;
    private EditText edpassword;
    private EditText edrepassword;
    private Button btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //return the view qui correspond à cet id
        edname=findViewById(R.id.ed_name);
        edlastname=findViewById(R.id.ed_lastname);
        edmail=findViewById(R.id.ed_mail);
        edpassword=findViewById(R.id.ed_password);
        edrepassword=findViewById(R.id.ed_repassword);
        btnlogin=(Button) findViewById(R.id.btn_login);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strname=edname.getText().toString();
                Intent intent=new Intent(MainActivity.this,Connexion.class);
                intent.putExtra("name",strname);
                System.out.println("this is ok");
                startActivity(intent);

            }
        });
    }
}

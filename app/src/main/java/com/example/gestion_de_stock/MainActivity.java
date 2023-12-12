package com.example.gestion_de_stock;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    View btnlogin, _btnCreer;
    DBHelper DB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.NonUser);
        password = findViewById(R.id.password);
        btnlogin = findViewById(R.id.btnConnecte);
        _btnCreer = findViewById(R.id.btnCreer);
        DB = new DBHelper(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(MainActivity.this, "Remplir Tous Les Champs", Toast.LENGTH_SHORT).show();
                } else {
                    if (user.equals("admin") && pass.equals("123")) {
                        Intent adminIntent = new Intent(MainActivity.this, Mes_Produits.class);
                        startActivity(adminIntent);
                    } else {
                        Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                        if (checkuserpass) {
                            Toast.makeText(MainActivity.this, "Bienvenue", Toast.LENGTH_SHORT).show();
                            Intent userIntent = new Intent(MainActivity.this, Formulaire.class);
                            startActivity(userIntent);
                        } else {
                            Toast.makeText(MainActivity.this, "Echec de connexion", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        _btnCreer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Creation_Compte.class);
                startActivity(i);
            }
        });
    }
}

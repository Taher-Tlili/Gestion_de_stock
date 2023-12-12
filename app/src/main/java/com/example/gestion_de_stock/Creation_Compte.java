package com.example.gestion_de_stock;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class Creation_Compte extends AppCompatActivity {
    EditText username, password, repassword, email;
    View _btnCreation, _btnconnecte;
    DBHelper db;

    //@SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_compte);
        username = (EditText) findViewById(R.id.NonUser);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.confirmer);
        email = (EditText) findViewById(R.id.adresse);
        _btnCreation = (View) findViewById(R.id.btnCreation);
        _btnconnecte = (View) findViewById(R.id.btnConnecte);
        db = new DBHelper(this);
        _btnCreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String User = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if (User.equals("") || pass.equals(""))
                    Toast.makeText(Creation_Compte.this, "les mots passes incompatibles", Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(repass)) {
                        Boolean checkUser = db.checkusername(User);
                        if (checkUser == false) {
                            Boolean insert = db.insertData(User,pass);
                            if (insert == true) {
                                Toast.makeText(Creation_Compte.this, "Insertion Avec  Succée", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(Creation_Compte.this, "Insertion Non Validée", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Creation_Compte.this, "User déja existe", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
        _btnconnecte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }

}


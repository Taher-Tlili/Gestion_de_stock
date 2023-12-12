package com.example.gestion_de_stock;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivitySauvgarder extends AppCompatActivity {

    private Button Enregistrer,Annuler;
    private SQLiteDatabase db;
    private EditText efamille, ecomposant,edate,equantite;

    @SuppressLint({"Range", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sauvgarder);
        Annuler=findViewById(R.id.btnAnn);
        Enregistrer=(Button)findViewById(R.id.btnenv);
        ecomposant=(EditText)findViewById(R.id.ed2);
        efamille=(EditText)findViewById(R.id.ed1);
        edate=(EditText)findViewById(R.id.ed3);
        equantite=(EditText)findViewById(R.id.ed4);
        db = openOrCreateDatabase("stock", MODE_PRIVATE, null);


        Enregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // insertion
                db.execSQL("insert into composants (nom,famille,date_acq,quantite) values (?,?,?,?);", new String[] {ecomposant.getText().toString(), efamille.getText().toString(),edate.getText().toString(),equantite.getText().toString()});

                Toast.makeText(getApplicationContext(),"Insertion Terminer",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivitySauvgarder.this, Mes_Produits.class);
                startActivity( intent);
            }
        });
        Annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Mes_Produits.class);
                startActivity(intent);
            }});
        Intent intent = getIntent();
    }
}
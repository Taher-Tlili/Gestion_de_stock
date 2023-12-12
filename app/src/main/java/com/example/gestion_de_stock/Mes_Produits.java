package com.example.gestion_de_stock;

import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.database.sqlite.SQLiteStatement;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.sql.PreparedStatement;

public class Mes_Produits extends AppCompatActivity {
    Cursor cur;
    SQLiteDatabase db;
    LinearLayout layNaviguer, layRecherche;
    EditText _txtcompsant, _txtquantite,_txtRecherche;
    ImageButton _btnRecherche;
    Button _btnAdd,_btnUpdate,_btnDelete;
    int op = 0;
    String x;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_produits);
        //Intent intent = getIntent();
        _txtcompsant = (EditText) findViewById(R.id.txtcomposant);
        _txtquantite = (EditText) findViewById(R.id.txtqt);
        _txtRecherche=(EditText) findViewById(R.id.txtRechercheComposant);
        _btnAdd = (Button) findViewById(R.id.btnAdd);
        _btnUpdate = (Button) findViewById(R.id.btnUpdate);
        _btnDelete = (Button) findViewById(R.id.btnDelete);
        _btnRecherche = (ImageButton) findViewById(R.id.btnRecherche);
        db = openOrCreateDatabase("stock", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS composants (nom TEXT primary key , famille VARCHAR, date_acq TEXT, quantite TEXT);");
        SQLiteStatement s = db.compileStatement("select count(*) from composants;");
        long c = s.simpleQueryForLong();
        Log.d("TAG", "onCreate: " + String.valueOf(c));
        if (c == 0) {
            db.execSQL("INSERT INTO composants (nom, famille, date_acq, quantite) VALUES (?, ?, ?, ?)",
                    new String[]{"esp32", "electronique", "2017-08-01", "20"});
            db.execSQL("INSERT INTO composants (nom, famille, date_acq, quantite) VALUES (?, ?, ?, ?)",
                    new String[]{"arduino", "electronique", "2017-01-18", "30"});
           db.execSQL("INSERT INTO composants (nom, famille, date_acq, quantite) VALUES (?, ?, ?, ?)",
                    new String[]{"stm32", "electronique", "2017-01-18", "60"});
        }

            _btnRecherche.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Cursor cur = db.rawQuery("select * from composants where nom like ?", new String[]{ _txtRecherche.getText().toString().trim() });
                    try {
                        cur.moveToFirst();
                        _txtcompsant.setText(cur.getString(0));
                        _txtquantite .setText(cur.getString(3));
                    } catch (Exception e) {
                        e.printStackTrace();
                        _txtcompsant.setText("");
                        _txtquantite .setText("");
                    }}
                });
                 _btnAdd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getApplicationContext(), MainActivitySauvgarder.class);
                            startActivity(intent);
                        }
                    });

       _btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),update.class);
                startActivity(intent);
            }
        });
        _btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.execSQL("delete  from composants where nom like ?", new String[]{ _txtRecherche.getText().toString().trim() });
                  Toast.makeText(getApplicationContext(), "Suppresion terminer", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Mes_Produits.class);
                startActivity(intent);
            }
        });



    }}




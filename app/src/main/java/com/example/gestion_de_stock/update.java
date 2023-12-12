package com.example.gestion_de_stock;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class update extends AppCompatActivity {
    private Button envoyer;
    private SQLiteDatabase db;
    private EditText _famille, _nom, _date, _quantite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        envoyer = findViewById(R.id.btnenv);
        _nom = findViewById(R.id.ed2);
        _famille = findViewById(R.id.ed1);
        _date = findViewById(R.id.ed3);
        _quantite = findViewById(R.id.ed4);
        db = openOrCreateDatabase("stock", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS composants (nom TEXT PRIMARY KEY, famille TEXT,quantite INTEGER,date_acq TEXT)");
        envoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Récupérer les valeurs des champs de texte
                String nom = _nom.getText().toString();
                String famille = _famille.getText().toString();
               String quantitee = _quantite.getText().toString();
                String date = _date.getText().toString();
               int quantite = Integer.parseInt(_quantite.getText().toString());
                ContentValues values = new ContentValues();
                values.put("nom", nom);
                values.put("famille", famille);
                values.put("quantite", quantite);
                values.put("date_acq", date);
                int rowsAffected = db.update("composants", values, "nom=?", new String[]{nom});
                if (rowsAffected > 0) {
                    Toast.makeText(getApplicationContext(), "Mise à jour avec succès", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Aucune mise à jour effectuée", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

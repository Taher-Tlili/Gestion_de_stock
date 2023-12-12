package com.example.gestion_de_stock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gestion_de_stock.DBHelper;
import com.example.gestion_de_stock.R;
import com.example.gestion_de_stock.Userlist;
import com.itextpdf.text.DocWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Formulaire extends AppCompatActivity {

   // Calendar calendar;
    EditText famille, nom, quantite,dp1;

    Button insert ,Annuler;
    DBHelper DB;
    private static final int STORAGE_CODE = 1000;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire);
        Annuler=findViewById(R.id.btnAnn);
        famille=findViewById(R.id.famille);
        nom=findViewById(R.id.nom);
        quantite=findViewById(R.id.quantite);
        dp1=findViewById(R.id.dp);
        insert=findViewById(R.id.btnInsert);
        DB = new DBHelper(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String familleTXT = famille.getText().toString();
                String nomTXT = nom.getText().toString();
                String quantiteTXT = quantite.getText().toString();
                String dpTXT = dp1.getText().toString();
                int quantitee = Integer.parseInt(quantite.getText().toString());

                Boolean checkinsertdata = DB.insertuserdata(familleTXT , nomTXT , quantiteTXT, dpTXT);
                if (checkinsertdata==true)
                {
                    Toast.makeText(getApplicationContext(), "Nouvelle Carte Insérée", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Userlist.class));
                    //we need to handle runtime permission for devices with marshmallow and above
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                        //system OS >= Marshmallow(6.0), check if permission is enabled or not
                        if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                                PackageManager.PERMISSION_DENIED) {
                            //permission was not granted, request it
                            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                            requestPermissions(permissions, STORAGE_CODE);
                        } else {
                            //permission already granted, call save pdf method
                            savePdf();
                        }
                    } else {
                        //system OS < Marshmallow, call save pdf method
                        savePdf();
                    }
                }
                else {
                    Toast.makeText(Formulaire.this, "la carte n a pas inserée", Toast.LENGTH_SHORT).show();
                }
            }

    });
        Annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                famille.equals("") ;
                nom.equals("");
                quantite.equals("") ;
                dp1.equals("") ;
                Intent intent = new Intent(getApplicationContext(),Formulaire.class);
                startActivity(intent);
            }});}
    private void savePdf() {
        //create object of Document class
        com.itextpdf.text.Document mDoc = new Document();
        //pdf file name
        String mFileName = new SimpleDateFormat("yyyyMMdd_HHmmss",Locale.getDefault()).format(System.currentTimeMillis());
        //pdf file path
        String mFilePath = Environment.getExternalStorageDirectory() + "/" + mFileName + ".pdf";
        try {
            //create instance of PdfWriter class
            PdfWriter.getInstance(mDoc, new FileOutputStream(mFilePath));
            //open the document for writing
            mDoc.open();
            //get text from EditText i.e. mTextEt
            String mText = famille.getText().toString();
            String mText1 = nom.getText().toString();
            String mText2 = quantite.getText().toString();
            String mText3 = dp1.getText().toString();
            //add author of the document (optional)
            mDoc.addAuthor("admin");
            //add paragraph to the document
            mDoc.add(new Paragraph(mText));
            mDoc.add(new Paragraph(mText1));
            mDoc.add(new Paragraph(mText2));
            mDoc.add(new Paragraph(mText3));
            //close the document
            mDoc.close();
            //show message that file is saved, it will show file name and file path too
            Toast.makeText(this, mFileName + ".pdf\nis Enregistrer to\n" + mFilePath, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            //if any thing goes wrong causing exception, get and show exception message
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    //handle permission result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case STORAGE_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permission was granted from popup, call savepdf method
                    savePdf();
                } else {
                    //permission was denied from popup, show error message
                    Toast.makeText(this, "Permission denied...!", Toast.LENGTH_SHORT).show();
                }
            }
        }


    }
}


package com.example.gestion_de_stock;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class Userlist extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> famille, nom, quantite, dp;
    DBHelper DB;
    MyAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);
        DB = new DBHelper(this);
        famille = new ArrayList<>();
        nom = new ArrayList<>();
        quantite = new ArrayList<>();
        dp = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MyAdapter(this, famille,nom, quantite,dp);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();
    }

    private void displaydata() {
        Cursor cursor = DB.getdata();
        if(cursor.getCount()==0)
        {
            Toast.makeText(Userlist.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            while (cursor.moveToNext())
            {
                famille.add(cursor.getString(0));
                nom.add(cursor.getString(1));
                quantite.add(cursor.getString(2));
                dp.add(cursor.getString(3));
            }
        }


    }
}
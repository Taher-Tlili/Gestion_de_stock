package com.example.gestion_de_stock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private Context context;
    private ArrayList famille_id, nom_id, quantite_id , dp_id;

    public MyAdapter(Context context, ArrayList famille_id, ArrayList nom_id, ArrayList quantite_id, ArrayList dp_id) {
        this.context = context;
        this.famille_id = famille_id;
        this.nom_id = nom_id;
        this.quantite_id = quantite_id;
        this.dp_id = dp_id;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.userentry,parent,false);


        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

        holder.famille_id.setText(String.valueOf(famille_id.get(position)));
        holder.nom_id.setText(String.valueOf(nom_id.get(position)));
        holder.quantite_id.setText(String.valueOf(quantite_id.get(position)));
        holder.dp_id.setText(String.valueOf(dp_id.get(position)));

    }

    @Override
    public int getItemCount() {
        return famille_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView famille_id, nom_id,quantite_id,dp_id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            famille_id = itemView.findViewById(R.id.textfamille);
            nom_id = itemView.findViewById(R.id.textnom);
            quantite_id = itemView.findViewById(R.id.textquantite);
            dp_id = itemView.findViewById(R.id.textdp);
        }
    }
}

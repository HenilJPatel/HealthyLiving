package com.example.healthyliving;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_Product_Details extends RecyclerView.Adapter<Adapter_Product_Details.MyViewHolder> {

    Context context;
    ArrayList<Data_Product> list;

    public Adapter_Product_Details(Context context, ArrayList<Data_Product> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Adapter_Product_Details.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.cardview_product_details,parent,false);
        return new Adapter_Product_Details.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Product_Details.MyViewHolder holder, int position) {
        Data_Product pres= list.get(position);
        {
            DatabaseReference db= FirebaseDatabase.getInstance().getReference("Product/"+pres.getpID());
            db.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try {
                        String msg = snapshot.child("pName").getValue(String.class);
                        holder.Product_name.setText(msg);
                        String brand = snapshot.child("pBrand").getValue(String.class);
                        holder.brand.setText(brand);
                        String direction = snapshot.child("pDirections").getValue(String.class);
                        holder.direction.setText(direction);
                        String Availability = snapshot.child("pAvailability").getValue(String.class);
                        holder.availability.setText(Availability);
                        String category = snapshot.child("pCategory").getValue(String.class);
                        holder.category.setText(category);
                        String warning = snapshot.child("pWarning").getValue(String.class);
                        holder.warning.setText(warning);
                        String url=snapshot.child("pURL").getValue(String.class);
                        Picasso.with(context).load(url).into(holder.img);
                        holder.img.setImageResource(R.drawable.noimage);
                    } catch (Exception e){
                        Log.e("Firebase",e.toString());
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("Firebase",error.toString());
                }
            });}//Set Text for Med Name,Brand
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Product_name,brand,direction,warning,availability,category;
        ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.PimageView);
            Product_name =itemView.findViewById(R.id.PName);
            brand=itemView.findViewById(R.id.Pbrand);
            direction=itemView.findViewById(R.id.Pdirection);
            warning=itemView.findViewById(R.id.Pwarning);
            availability=itemView.findViewById(R.id.PAvailability);
            category=itemView.findViewById(R.id.PCategory);
        }
    }
}


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

 public class Adapter_Prescription_Details extends RecyclerView.Adapter<Adapter_Prescription_Details.MyViewHolder> {

     Context context;
     ArrayList<Data_Prescription> list;

     public Adapter_Prescription_Details(Context context, ArrayList<Data_Prescription> list) {
         this.context = context;
         this.list = list;
     }

     @NonNull
     @Override
     public Adapter_Prescription_Details.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View v= LayoutInflater.from(context).inflate(R.layout.cardview_prescription_details,parent,false);
         return new Adapter_Prescription_Details.MyViewHolder(v);
     }

     @Override
     public void onBindViewHolder(@NonNull Adapter_Prescription_Details.MyViewHolder holder, int position) {
         Data_Prescription pres= list.get(position);
         {
             DatabaseReference db= FirebaseDatabase.getInstance().getReference("Product/"+pres.getProductID());
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
         holder.dr_name.setText(pres.getDoctorID());
         holder.day_supply.setText(pres.getDaysofsupply());
         holder.no_of_refill.setText(pres.getRefill());
         holder.last_refill.setText(pres.getLastrefilldate());
         holder.next_refill.setText(pres.getNextrefilldate());
     }

     @Override
     public int getItemCount() {
         return list.size();
     }

     public static class MyViewHolder extends RecyclerView.ViewHolder{
         TextView Product_name,brand, day_supply, no_of_refill, dr_name, last_refill, next_refill,direction,warning;
         ImageView img;
         public MyViewHolder(@NonNull View itemView) {
             super(itemView);
             img=itemView.findViewById(R.id.PDimageView);
             Product_name =itemView.findViewById(R.id.PDName);
             brand=itemView.findViewById(R.id.PDbrand);
             day_supply =itemView.findViewById(R.id.PDsupply);
             no_of_refill =itemView.findViewById(R.id.PDrefill);
             dr_name =itemView.findViewById(R.id.PDdoctor);
             last_refill =itemView.findViewById(R.id.PDrefilldate);
             next_refill =itemView.findViewById(R.id.PDnextrefill);
             direction=itemView.findViewById(R.id.PDdirection);
             warning=itemView.findViewById(R.id.PDwarning);
         }
     }
 }


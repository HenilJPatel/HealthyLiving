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

 public class PrescriptionDetailAdapter extends RecyclerView.Adapter<com.example.healthyliving.PrescriptionDetailAdapter.MyViewHolder> {

     Context context;
     ArrayList<PrescriptionData> list;

     public PrescriptionDetailAdapter(Context context, ArrayList<PrescriptionData> list) {
         this.context = context;
         this.list = list;
     }

     @NonNull
     @Override
     public com.example.healthyliving.PrescriptionDetailAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View v= LayoutInflater.from(context).inflate(R.layout.prescriptiondetails,parent,false);
         return new com.example.healthyliving.PrescriptionDetailAdapter.MyViewHolder(v);
     }

     @Override
     public void onBindViewHolder(@NonNull com.example.healthyliving.PrescriptionDetailAdapter.MyViewHolder holder, int position) {
         PrescriptionData pres= list.get(position);
         {
             DatabaseReference db= FirebaseDatabase.getInstance().getReference("Product/"+pres.getProductID());
             db.addListenerForSingleValueEvent(new ValueEventListener() {
                 @Override
                 public void onDataChange(@NonNull DataSnapshot snapshot) {
                     try {
                         String msg = snapshot.child("pName").getValue(String.class);
                         holder.Productname.setText(msg);
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
         holder.drname.setText(pres.getDoctorID());
         holder.daysupply.setText(pres.getDaysofsupply());
         holder.noofrefill.setText(pres.getRefill());
         holder.lastrefill.setText(pres.getLastrefilldate());
         holder.nextrefill.setText(pres.getNextrefilldate());
     }

     @Override
     public int getItemCount() {
         return list.size();
     }

     public static class MyViewHolder extends RecyclerView.ViewHolder{
         TextView Productname,brand,daysupply,noofrefill,drname,lastrefill,nextrefill,direction,warning;
         ImageView img;
         public MyViewHolder(@NonNull View itemView) {
             super(itemView);
             img=itemView.findViewById(R.id.PDimageView);
             Productname=itemView.findViewById(R.id.PDName);
             brand=itemView.findViewById(R.id.PDbrand);
             daysupply=itemView.findViewById(R.id.PDsupply);
             noofrefill=itemView.findViewById(R.id.PDrefill);
             drname=itemView.findViewById(R.id.PDdoctor);
             lastrefill=itemView.findViewById(R.id.PDrefilldate);
             nextrefill=itemView.findViewById(R.id.PDnextrefill);
             direction=itemView.findViewById(R.id.PDdirection);
             warning=itemView.findViewById(R.id.PDwarning);
         }
     }
 }


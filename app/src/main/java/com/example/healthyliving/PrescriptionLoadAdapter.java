package com.example.healthyliving;

import android.content.Context;
import android.content.Intent;
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

public class PrescriptionLoadAdapter extends RecyclerView.Adapter<PrescriptionLoadAdapter.MyViewHolder> {

    Context context;
    ArrayList<PrescriptionData> list;

    public PrescriptionLoadAdapter(Context context, ArrayList<PrescriptionData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.items,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PrescriptionData pres= list.get(position);
        {DatabaseReference db= FirebaseDatabase.getInstance().getReference("Product/"+pres.getProductID());
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    String msg = snapshot.child("pName").getValue(String.class);
                    holder.Productname.setText(msg);
                    String brand = snapshot.child("pBrand").getValue(String.class);
                    holder.brand.setText(brand);
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
        holder.img.setMaxWidth(130);
        holder.img.setMaxHeight(150);
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

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Productname,brand,daysupply,noofrefill,drname,lastrefill,nextrefill;
        ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.imageView123);
            Productname=itemView.findViewById(R.id.productname);
            brand=itemView.findViewById(R.id.brandname);
            daysupply=itemView.findViewById(R.id.daySupply);
            noofrefill=itemView.findViewById(R.id.Refill);
            drname=itemView.findViewById(R.id.drName);
            lastrefill=itemView.findViewById(R.id.refillDate);
            nextrefill=itemView.findViewById(R.id.NextRefill);

            context=itemView.getContext();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int itemPosition  = getLayoutPosition();
                    //Toast.makeText(context, "" + list.get(itemPosition).getPresID(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context,PrescriptionDetails_Activity.class);
                    intent.putExtra("presID",list.get(itemPosition).getPresID().toString());
                    context.startActivity(intent);
                }
            });
        }
    }
}

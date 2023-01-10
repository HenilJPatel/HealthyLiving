package com.example.healthyliving;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_Prescription_List extends RecyclerView.Adapter<Adapter_Prescription_List.MyViewHolder> {

    Context context;
    ArrayList<Data_Prescription> list;

    public Adapter_Prescription_List(Context context, ArrayList<Data_Prescription> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.cardview_prescription_list,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Data_Prescription pres= list.get(position);
        {DatabaseReference db= FirebaseDatabase.getInstance().getReference("Product/"+pres.getProductID());
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    String msg = snapshot.child("pName").getValue(String.class);
                    holder.Product_name.setText(msg);
                    String brand = snapshot.child("pBrand").getValue(String.class);
                    holder.brand.setText(brand);
                    String url=snapshot.child("pURL").getValue(String.class);
                    holder.img.setImageResource(R.drawable.noimage);
                    Picasso.with(context).load(url).into(holder.img);

                } catch (Exception e){
                    Log.e("Firebase",e.toString());
                    Toast.makeText(context, "Bugs are bad.", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase",error.toString());
            }
        });}//Set Text for Med Name,Brand
        holder.img.setMaxWidth(130);
        holder.img.setMaxHeight(150);
        holder.dr_name.setText(pres.getDoctorID());
        holder.day_supply.setText(pres.getDaysofsupply());
        holder.no_of_refill.setText(pres.getRefill());
        holder.last_refill.setText(pres.getLastrefilldate());
        holder.next_refill.setText(pres.getNextrefilldate());
        try {
            DatabaseReference dbr = FirebaseDatabase.getInstance().getReference("Doctor").child(pres.getDoctorID());
            dbr.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String drname = snapshot.child("DoctorName").getValue(String.class);
                    holder.dr_name.setText(drname);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }catch(Exception e){
            Toast.makeText(context, "Failed to retrieve name for "+pres.getDoctorID(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Product_name,brand, day_supply, no_of_refill, dr_name, last_refill, next_refill;
        ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.imageView123);
            Product_name =itemView.findViewById(R.id.productname);
            brand=itemView.findViewById(R.id.brandname);
            day_supply =itemView.findViewById(R.id.daySupply);
            no_of_refill =itemView.findViewById(R.id.Refill);
            dr_name =itemView.findViewById(R.id.drName);
            last_refill =itemView.findViewById(R.id.refillDate);
            next_refill =itemView.findViewById(R.id.NextRefill);

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

package com.example.healthyliving;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class Adapter_Coupon_List extends RecyclerView.Adapter<Adapter_Coupon_List.MyViewHolder> {

    Context context;
    ArrayList<String> list;
    String reference;
    Class activity;

    public Adapter_Coupon_List(Context context, ArrayList<String> list,String reference,Class activity) {
        this.context = context;
        this.list = list;
        this.reference=reference;
        this.activity=activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.cardview_coupon_list,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String key= list.get(position);
        DatabaseReference db= FirebaseDatabase.getInstance().getReference(reference+"/"+key);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    String msg;
                    if(reference.equals("Product")) {
                        msg = snapshot.child("pName").getValue().toString();
                        DatabaseReference dba=FirebaseDatabase.getInstance().getReference("Coupon/"+key);
                        dba.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String str=String.valueOf(snapshot.getChildrenCount());
                                holder.count_promo.setText(str);
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {}
                        });
                    } else{
                        msg = Objects.requireNonNull(snapshot).getKey();
                        String promo= Objects.requireNonNull(snapshot).child("Promo_Offer").getValue().toString();
                        holder.count_promo.setText(promo);
                    }
                    holder.Product_name.setText(msg);
                } catch (Exception e){
                    Log.e("Firebase",e.toString());
                    Toast.makeText(context, "Bugs are bad.", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase",error.toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Product_name,count_promo;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Product_name=itemView.findViewById(R.id.h1);
            count_promo=itemView.findViewById(R.id.h2);
            context=itemView.getContext();
            itemView.setOnClickListener(v -> {
                int itemPosition  = getLayoutPosition();
                Toast.makeText(context, "" + list.get(itemPosition), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,activity);
                intent.putExtra("reference",reference);
                intent.putExtra("couponID",list.get(itemPosition).toString());
                context.startActivity(intent);
            });
        }
    }
}

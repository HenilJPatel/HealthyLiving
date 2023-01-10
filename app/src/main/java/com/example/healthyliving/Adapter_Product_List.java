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

public class Adapter_Product_List extends RecyclerView.Adapter<Adapter_Product_List.MyViewHolder> {
    Context context;
    ArrayList<Data_Product> list;

    public Adapter_Product_List(Context context, ArrayList<Data_Product> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Adapter_Product_List.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.cardview_product_list,parent,false);
        return new Adapter_Product_List.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Product_List.MyViewHolder holder, int position) {
        Data_Product prod= list.get(position);
        {
            DatabaseReference db= FirebaseDatabase.getInstance().getReference("Product/"+prod.getpID());
            db.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try
                    {
                    String msg = snapshot.child("pName").getValue(String.class);
                    holder.product_name.setText(msg);
                        String brand = snapshot.child("pBrand").getValue(String.class);
                        holder.brand.setText(brand);
                    String url=snapshot.child("pURL").getValue(String.class);
                    Picasso.with(context).load(url).into(holder.img);
                    } catch (Exception e){
                        Toast.makeText(context,"Failed "+prod.getpID(),Toast.LENGTH_LONG).show();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("Firebase",error.toString());
                }
            });}//Set Text for Med Name,Brand
        holder.img.setMaxWidth(130);
        holder.img.setMaxHeight(150);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView product_name,brand;
        ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.Prod_Image);
            product_name=itemView.findViewById(R.id.Prod_ProductName);
            brand=itemView.findViewById(R.id.Prod_BrandName);
            context=itemView.getContext();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int itemPosition = getLayoutPosition();
                    Toast.makeText(context, "" + list.get(itemPosition).getpID(), Toast.LENGTH_SHORT).show();
                    try {
                        Intent intent = new Intent(context, ProductDetails_Activity.class);
                        intent.putExtra("prodID", list.get(itemPosition).getpID());
                        context.startActivity(intent);
                    } catch (Exception e) {
                        Toast.makeText(context, "Fuck, this isnt working boss", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}

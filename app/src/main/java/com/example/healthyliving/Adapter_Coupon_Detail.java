package com.example.healthyliving;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class Adapter_Coupon_Detail extends RecyclerView.Adapter<Adapter_Coupon_Detail.MyViewHolder> {

    Context context;
    ArrayList<String> list;
    String reference;
    Class activity;

    public Adapter_Coupon_Detail(Context context, ArrayList<String> list, String reference, Class activity) {
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
        String ref= list.get(position);
        DatabaseReference dr= FirebaseDatabase.getInstance().getReference(ref);
        String[] prod=ref.split("/");
        DatabaseReference db= FirebaseDatabase.getInstance().getReference("Product/"+prod[1]);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                holder.Product_name.setText(Objects.requireNonNull(snapshot.child("pName").getValue()).toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        dr.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                holder.count_promo.setText(Objects.requireNonNull(snapshot.child("Promo_Offer").getValue().toString()));
                holder.tt1.setText(Objects.requireNonNull(snapshot.child("BIN").getValue().toString()));
                holder.tt1.setText(Objects.requireNonNull(snapshot.child("BIN").getValue().toString()));
                holder.tt2.setText(Objects.requireNonNull(snapshot.child("PCN").getValue().toString()));
                holder.tt3.setText(Objects.requireNonNull(snapshot.child("Group").getValue().toString()));
                holder.tt4.setText(Objects.requireNonNull(snapshot.child("MemberID").getValue().toString()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        holder.ll1.setVisibility(View.VISIBLE);
        holder.ll2.setVisibility(View.VISIBLE);
        holder.ll3.setVisibility(View.VISIBLE);
        holder.ll4.setVisibility(View.VISIBLE);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Product_name,count_promo,tt1,tt2,tt3,tt4;
        LinearLayout ll1,ll2,ll3,ll4;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Product_name=itemView.findViewById(R.id.h1);
            count_promo=itemView.findViewById(R.id.h2);
            ll1=itemView.findViewById(R.id.llt1);
            ll2=itemView.findViewById(R.id.llt2);
            ll3=itemView.findViewById(R.id.llt3);
            ll4=itemView.findViewById(R.id.llt4);
            tt1=itemView.findViewById(R.id.t1);
            tt2=itemView.findViewById(R.id.t2);
            tt3=itemView.findViewById(R.id.t3);
            tt4=itemView.findViewById(R.id.t4);

            context=itemView.getContext();
            itemView.setOnClickListener(v -> {
                int itemPosition  = getLayoutPosition();
                Intent intent = new Intent(context,activity);
                intent.putExtra("couponID",list.get(itemPosition).toString());
                context.startActivity(intent);
            });
        }
    }
}

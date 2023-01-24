package com.example.healthyliving;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CouponPharmaListActivity extends AppCompatActivity {
    final private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    Adapter_Coupon_List adapter_coupon_list;
    ArrayList<String> list,list2;
    long count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_list);
        recyclerView=findViewById(R.id.CouponListViewer);
        Bundle extras = getIntent().getExtras();
        String couponID=null;
        if (extras != null) {
            couponID = extras.getString("couponID");
        }
        databaseReference= FirebaseDatabase.getInstance().getReference("Coupon/"+couponID);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();
        adapter_coupon_list = new Adapter_Coupon_List(this,list,"Coupon/"+couponID,CouponDetailActivity.class);
        recyclerView.setAdapter(adapter_coupon_list);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    try {
                        String data = dataSnapshot.getKey();
                        list.add(data);
                    } catch (Exception e){
                        System.out.println(e.toString());
                    }
                }
                if(list.size()>0){
                    adapter_coupon_list.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
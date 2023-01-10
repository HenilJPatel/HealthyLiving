package com.example.healthyliving;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

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

public class ProductDetails_Activity extends AppCompatActivity {
    final private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Bundle extras = getIntent().getExtras();
        String prodID=null;
        if (extras != null) {
            prodID = extras.getString("prodID");
        }
        Toast.makeText(this,prodID,Toast.LENGTH_SHORT).show();
        RecyclerView recyclerView;
        recyclerView=findViewById(R.id.ProductDetail);
        DatabaseReference databaseReference;
        Adapter_Product_Details PDAdapter;
        ArrayList<Data_Product> list;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list=new ArrayList<>();
        PDAdapter = new Adapter_Product_Details(this,list);
        recyclerView.setAdapter(PDAdapter);
        databaseReference= FirebaseDatabase.getInstance().getReference("Product/"+prodID);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    Data_Product data = snapshot.getValue(Data_Product.class);
                    list.add(data);
                } catch (Exception e){
                    System.out.println(e.toString());
                }
                PDAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
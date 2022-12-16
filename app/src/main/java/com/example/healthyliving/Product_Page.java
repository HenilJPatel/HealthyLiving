package com.example.healthyliving;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Product_Page extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    Adapter_Product_List product_list;
    ArrayList<Data_Product> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        recyclerView=findViewById(R.id.ProductViewer);
        databaseReference= FirebaseDatabase.getInstance().getReference("Product");
        recyclerView.setHasFixedSize(true);
        int columns=2;
        recyclerView.setLayoutManager(new GridLayoutManager(this,columns));
        list=new ArrayList<>();
        product_list = new Adapter_Product_List(this,list);
        recyclerView.setAdapter(product_list);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    try {
                        Data_Product data = dataSnapshot.getValue(Data_Product.class);
                        list.add(data);
                    } catch (Exception e){
                        System.out.println(e.toString());
                    }
                }
                if(list.size()>0){
                    product_list.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
package com.example.healthyliving;

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
    ProductListLoad productlist;
    ArrayList<ProductData> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_list);
        recyclerView=findViewById(R.id.PrescriptionViewer);
        databaseReference= FirebaseDatabase.getInstance().getReference("Product");
        recyclerView.setHasFixedSize(true);
        int columns=2;
        recyclerView.setLayoutManager(new GridLayoutManager(this,columns));
        list=new ArrayList<>();
        productlist = new ProductListLoad(this,list);
        recyclerView.setAdapter(productlist);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    try {
                        ProductData data = dataSnapshot.getValue(ProductData.class);
                        list.add(data);
                    } catch (Exception e){
                        System.out.println(e.toString());
                    }
                }
                if(list.size()>0){
                    productlist.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
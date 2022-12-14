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

public class PrescriptionDetails_Activity extends AppCompatActivity {
    final private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_details);
        Bundle extras = getIntent().getExtras();
        String presID=null;
        if (extras != null) {
            presID = extras.getString("presID");
        }
        Toast.makeText(this,presID,Toast.LENGTH_SHORT).show();
        RecyclerView recyclerView;
        recyclerView=findViewById(R.id.PrescriptionDetail);
        DatabaseReference databaseReference;
        Adapter_Prescription_Details PDAdapter;
        ArrayList<Data_Prescription> list;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list=new ArrayList<>();
        PDAdapter = new Adapter_Prescription_Details(this,list);
        recyclerView.setAdapter(PDAdapter);
        databaseReference= FirebaseDatabase.getInstance().getReference("Users/"+mAuth.getUid()+"/Prescriptions/"+presID);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    Data_Prescription data = snapshot.getValue(Data_Prescription.class);
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
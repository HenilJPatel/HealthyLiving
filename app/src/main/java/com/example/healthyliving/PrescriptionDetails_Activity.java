package com.example.healthyliving;

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
        PrescriptionDetailAdapter PDAdapter;
        ArrayList<PrescriptionData> list;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list=new ArrayList<>();
        PDAdapter = new PrescriptionDetailAdapter(this,list);
        recyclerView.setAdapter(PDAdapter);
        databaseReference= FirebaseDatabase.getInstance().getReference("Users/"+mAuth.getUid()+"/Prescriptions/"+presID);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    PrescriptionData data = snapshot.getValue(PrescriptionData.class);
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
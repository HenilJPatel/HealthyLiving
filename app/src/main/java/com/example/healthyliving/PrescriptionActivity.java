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

public class PrescriptionActivity extends AppCompatActivity {
    final private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    PrescriptionLoadAdapter prescriptionLoadAdapter;
    ArrayList<PrescriptionData> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_list);
        recyclerView=findViewById(R.id.PrescriptionViewer);
        databaseReference= FirebaseDatabase.getInstance().getReference("Users/"+mAuth.getUid()+"/Prescriptions/");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();
        prescriptionLoadAdapter = new PrescriptionLoadAdapter(this,list);
        recyclerView.setAdapter(prescriptionLoadAdapter);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Toast.makeText(PrescriptionActivity.this,String.valueOf(snapshot.getChildrenCount()),Toast.LENGTH_LONG).show();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    try {
                        PrescriptionData data = dataSnapshot.getValue(PrescriptionData.class);
                        list.add(data);
                    } catch (Exception e){
                        System.out.println(e.toString());
                    }
                }
                if(list.size()>0){
                    prescriptionLoadAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
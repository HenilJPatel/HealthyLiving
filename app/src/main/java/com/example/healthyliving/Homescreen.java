package com.example.healthyliving;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class Homescreen extends AppCompatActivity {
    private Button prescription;
    private Button appointment;
    private Button products;
    private Button logout;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        mAuth=FirebaseAuth.getInstance();
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser user=mAuth.getCurrentUser();
        if(user==null){
            Intent intent=new Intent(Homescreen.this,LoginActivity.class);
            startActivity(intent);
        }else
        {
            getUsername();
            Home();
        }
    }
    private void Home(){
        FirebaseUser user=mAuth.getCurrentUser();
        TextView s=(TextView)findViewById(R.id.textView11);
        s.setText(user.getEmail());
        //Toast.makeText(this,"Signed in as "+user.getEmail(),Toast.LENGTH_SHORT).show();
        Button btnProfile = findViewById(R.id.profile);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homescreen.this, ViewProfile.class);
                startActivity(intent);
            }
        });
        prescription = (Button) findViewById(R.id.prescription);
        prescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homescreen.this, PrescriptionActivity.class);
                startActivity(intent);
            }
        });
        appointment = (Button) findViewById(R.id.appointments);
        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homescreen.this,Appointments.class);
                startActivity(intent);
            }
        });
        products = (Button) findViewById(R.id.products);
        products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homescreen.this, Catalog_Page.class);
                startActivity(intent);
            }
        });
        logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(Homescreen.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    private void getUsername(){
        DatabaseReference db= FirebaseDatabase.getInstance().getReference("Users");
        Query query=db.child(mAuth.getUid()).child("LoginDetails").child("name");
        long date=new Date().getTime();
        int hour=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                double rand_dub1 = ThreadLocalRandom.current().nextDouble();
                String s;
                if(hour>=0&&hour<12){
                    s="Good Morning, ";
                }else if(hour>=12&&hour<16){
                    s="Good Afternoon, ";
                }else if(hour>=16&&hour<24){
                    s="Good Evening, ";
                }else{
                    s="Hello, ";
                }
                setTitle(s+(snapshot.getValue().toString()));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
       // Toast.makeText(Homescreen.this,String.valueOf(mAuth.getUid()),Toast.LENGTH_SHORT).show();
    }
}
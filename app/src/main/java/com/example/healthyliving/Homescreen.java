package com.example.healthyliving;

import android.content.Intent;
import android.os.Bundle;
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
import java.util.Objects;

public class Homescreen extends AppCompatActivity {
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
        TextView s= findViewById(R.id.textView11);
        assert user != null;
        s.setText(user.getEmail());
        Button btnProfile = findViewById(R.id.profile);
        btnProfile.setOnClickListener(v -> {
            Intent intent = new Intent(Homescreen.this, ViewProfile.class);
            startActivity(intent);
        });
        Button prescription = findViewById(R.id.prescription);
        prescription.setOnClickListener(v -> {
            Intent intent = new Intent(Homescreen.this, PrescriptionActivity.class);
            startActivity(intent);
        });
        Button appointment = findViewById(R.id.appointments);
        appointment.setOnClickListener(v -> {
            Intent intent = new Intent(Homescreen.this,Appointments.class);
            startActivity(intent);
        });
        Button products = findViewById(R.id.products);
        products.setOnClickListener(v -> {
            Intent intent = new Intent(Homescreen.this, Product_Page.class);
            startActivity(intent);
        });
        Button coupons = findViewById(R.id.home_coupons);
        coupons.setOnClickListener(v -> {
            Intent intent = new Intent(Homescreen.this, CouponListActivity.class);
            startActivity(intent);
        });
        Button code_trail = findViewById(R.id.code_trial);
        code_trail.setOnClickListener(v -> {
            Intent intent = new Intent(Homescreen.this, map_api_trial.class);
            startActivity(intent);
        });
        Button code_trail2 = findViewById(R.id.code_trial_2);
        code_trail2.setOnClickListener(v -> {
            Intent intent = new Intent(Homescreen.this, Codetrial.class);
            startActivity(intent);
        });
        Button logout = findViewById(R.id.logout);
        logout.setOnClickListener(v -> {
            mAuth.signOut();
            Intent intent = new Intent(Homescreen.this, LoginActivity.class);
            startActivity(intent);
        });
    }
    private void getUsername(){
        DatabaseReference db= FirebaseDatabase.getInstance().getReference("Users");
        Query query=db.child(Objects.requireNonNull(mAuth.getUid())).child("LoginDetails").child("name");
        int hour=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String s;
                if(hour<12){
                    s="Good Morning, ";
                }else if(hour<16){
                    s="Good Afternoon, ";
                }else {
                    s="Good Evening, ";
                }
                setTitle(s+(Objects.requireNonNull(snapshot.getValue()).toString()));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
       // Toast.makeText(Homescreen.this,String.valueOf(mAuth.getUid()),Toast.LENGTH_SHORT).show();
    }
}
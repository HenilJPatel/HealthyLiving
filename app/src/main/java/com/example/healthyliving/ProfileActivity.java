package com.example.healthyliving;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    final private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    EditText profName, profDOB, profInsurance, profDoc, profSSN, profAddress;
    TextView tName, tDOB, tHI, tDoc, tSSN, tAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getUid());
        Query query = dbref.child("Profile");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    UserProfile uP = new UserProfile();
                    uP = (snapshot.getValue(UserProfile.class));
                    CheckFields(uP);
                } else {
                    UserProfile empty = new UserProfile();
                    CheckFields(empty);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Button Submit = findViewById(R.id.ProfileSubmitButton);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadProfile();
            }
        });
    }
    public void CheckFields(UserProfile uP)
    {
        profName=findViewById(R.id.profileName);
        profDOB=findViewById(R.id.profileDOB);
        profInsurance=findViewById(R.id.profileInsurance);
        profDoc=findViewById(R.id.primaryDoc);
        profSSN=findViewById(R.id.profileSSN);
        profAddress=findViewById(R.id.profileAddress);
        Exist_NotNull(profName,uP.getName());
        Exist_NotNull(profDOB,uP.getDateOfBirth());
        Exist_NotNull(profInsurance,uP.getHealthInsuranceNo());
        Exist_NotNull(profDoc,uP.getPrimaryDoctor());
        Exist_NotNull(profSSN,uP.getSSN());
        Exist_NotNull(profAddress,uP.getAddress());
    }
    public void Exist_NotNull(EditText et,String p)
    {
        if (p != null) {
            et.setText(p);
            et.setClickable(false);
        } else {
            et.setText(null);
            et.setClickable(true);
        }
    }

    public void uploadProfile()
    {
        profName=findViewById(R.id.profileName);
        profDOB=findViewById(R.id.profileDOB);
        profInsurance=findViewById(R.id.profileInsurance);
        profDoc=findViewById(R.id.primaryDoc);
        profSSN=findViewById(R.id.profileSSN);
        profAddress=findViewById(R.id.profileAddress);
        UserProfile ProfileData=new UserProfile();
        ProfileData.setName(profName.getText().toString().trim());
        ProfileData.setDateOfBirth(profDOB.getText().toString().trim());
        ProfileData.setHealthInsuranceNo(profInsurance.getText().toString().trim());
        ProfileData.setPrimaryDoctor(profDoc.getText().toString().trim());
        ProfileData.setSSN(profSSN.getText().toString().trim());
        ProfileData.setAddress(profAddress.getText().toString().trim());
        DatabaseReference dr=FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getUid()).child("Profile");
        dr.setValue(ProfileData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Intent intent=new Intent(ProfileActivity.this,ViewProfile.class);
                        startActivity(intent);
                    }
                });
    }
}
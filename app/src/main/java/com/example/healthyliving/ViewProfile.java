package com.example.healthyliving;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ViewProfile extends AppCompatActivity {

    final private FirebaseAuth mAuth=FirebaseAuth.getInstance();
    EditText profName,profDOB,profInsurance,profDoc,profSSN,profAddress;
    TextView tName,tDOB,tHI,tDoc,tSSN,tAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        DatabaseReference dbref= FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getUid());
        Query query=dbref.child("Profile");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue()!=null){
                    UserProfile uP=new UserProfile();
                    uP=(snapshot.getValue(UserProfile.class));
                    CheckFields(uP);
                }else{
                    UserProfile empty=new UserProfile();
                    CheckFields(empty);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Button Submit=findViewById(R.id.ViewProfile);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ViewProfile.this,ProfileActivity.class);
                startActivity(intent);
            }
        });
    }
    public void CheckFields(UserProfile uP){
        profName=findViewById(R.id.profName);
        profDOB=findViewById(R.id.profDOB);
        profInsurance=findViewById(R.id.profHI);
        profDoc=findViewById(R.id.profDoc);
        profSSN=findViewById(R.id.profSSN);
        profAddress=findViewById(R.id.profAddress);
        Exist_NotNull(profName,"",uP.getName());
        Exist_NotNull(profDOB,"",uP.getDateOfBirth());
        Exist_NotNull(profInsurance,"",uP.getHealthInsuranceNo());
        Exist_NotNull(profDoc,"",uP.getPrimaryDoctor());
        Exist_NotNullSSN(profSSN,"",uP.getSSN());
        Exist_NotNull(profAddress,"",uP.getAddress());
    }
    public void Exist_NotNull(EditText et,String title,String p) {
        {
            if (p != null) {
                et.setText(title + p);
                et.setEnabled(false);
                et.setTextColor(Color.BLACK);
                et.setClickable(false);
            } else {
                et.setText(title + "Not Set");
                et.setEnabled(false);
                et.setTextColor(Color.BLACK);
                et.setClickable(true);
            }
        }
    }
    public void Exist_NotNullSSN (EditText et, String title, String p) {
        if (p != null) {
            char[] ch = new char[p.length()];

            // Copy character by character into array
            for (int i = 0; i < p.length(); i++) {
                ch[i] = p.charAt(i);
            }
            String str="***-**-**"+ch[7]+ch[8];
            et.setText(title + str);
            et.setEnabled(false);
            et.setTextColor(Color.BLACK);
            et.setClickable(false);
        } else {
            et.setText(title + "Not Set");
            et.setEnabled(false);
            et.setTextColor(Color.BLACK);
            et.setClickable(true);
        }
    }
}
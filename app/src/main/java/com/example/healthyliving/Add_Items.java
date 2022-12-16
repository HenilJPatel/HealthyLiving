package com.example.healthyliving;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Add_Items extends AppCompatActivity {
    EditText txt_name,txtContact,txtEmail,txtUsername;
    DatabaseReference dbref;
    Products product;
    long max_id =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);
        txt_name = findViewById(R.id.editName);
        txtContact = findViewById(R.id.editDescription);
        txtEmail = findViewById(R.id.editQty);
        txtUsername = findViewById(R.id.editPrice);
        Button signup = findViewById(R.id.signup);
        product = new Products();
        dbref = FirebaseDatabase.getInstance().getReference().child("User").child("Userinfo");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                    max_id = (snapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        signup.setOnClickListener(v -> {
            product.setName(txt_name.getText().toString().trim());
            product.setDescription(txtContact.getText().toString().trim());
            product.setQuantity(txtEmail.getText().toString().trim());
            product.setPrice(txtUsername.getText().toString().trim());
            dbref.child("P"+ max_id + 1).setValue(product);
            Toast.makeText(Add_Items.this, "Product Created", Toast.LENGTH_LONG).show();
        });
    }
}
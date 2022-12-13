package com.example.healthyliving;

import android.os.Bundle;
import android.view.View;
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
    EditText txtname,txtContact,txtEmail,txtUsername;
    private Button signup;
    DatabaseReference dbref;
    Products product;
    long maxid=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);
        txtname = (EditText) findViewById(R.id.editName);
        txtContact = (EditText) findViewById(R.id.editDescription);
        txtEmail = (EditText) findViewById(R.id.editQty);
        txtUsername = (EditText) findViewById(R.id.editPrice);
        signup = (Button) findViewById(R.id.signup);
        product = new Products();
        dbref = FirebaseDatabase.getInstance().getReference().child("User").child("Userinfo");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                    maxid = (snapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.setName(txtname.getText().toString().trim());
                product.setDescription(txtContact.getText().toString().trim());
                product.setQuantity(txtEmail.getText().toString().trim());
                product.setPrice(txtUsername.getText().toString().trim());
                dbref.child(String.valueOf("P"+maxid + 1)).setValue(product);
                Toast.makeText(Add_Items.this, "Product Created", Toast.LENGTH_LONG).show();
            }
        });
    }
}
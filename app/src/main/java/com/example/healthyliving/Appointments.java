package com.example.healthyliving;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Objects;

public class Appointments extends AppCompatActivity {
    Button btnNew;
    ListView listView;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    String[] sid=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);
        btnNew = findViewById(R.id.btnNewAppointment);
        Button btncancel= findViewById(R.id.btnCancelAppointment);
        btnNew.setOnClickListener(v -> {
            Intent intent = new Intent(Appointments.this, NewAppointment.class);
            startActivity(intent);
        });
        listView=findViewById(R.id.AppointmentList);
        arrayAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            sid=((String)parent.getItemAtPosition(position)).split(" ");
            //Toast.makeText(Appointments.this,sid[1], Toast.LENGTH_SHORT).show();
            btncancel.setEnabled(true);
            btncancel.setText(getString(R.string.cancel_appointment) + sid[1]);
        });
        DatabaseReference dr= FirebaseDatabase.getInstance().getReference("Users/"+ Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()+"/Appointments");
        Query qr=dr.orderByChild("status").equalTo("active");
        qr.addChildEventListener(new ChildEventListener(){
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String value= Objects.requireNonNull(snapshot.getValue(AppointmentData.class)).toString();
                arrayList.add(value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                arrayList.clear();
                String value= Objects.requireNonNull(snapshot.getValue(AppointmentData.class)).toString();
                arrayList.add(value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                String value= Objects.requireNonNull(snapshot.getValue(AppointmentData.class)).toString();
                arrayList.add(value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                arrayList.clear();
                String value= Objects.requireNonNull(snapshot.getValue(AppointmentData.class)).toString();
                arrayList.add(value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btncancel.setOnClickListener(v -> {
            if(btncancel.getText()!="Cancel Appointment"){
                dr.child(sid[1]).child("status").setValue("cancel")
                        .addOnSuccessListener(aVoid -> {
                            Toast.makeText(Appointments.this, "Appointment Cancelled Successfully",Toast.LENGTH_LONG).show();
                            arrayList.clear();
                            qr.addChildEventListener(new ChildEventListener(){
                                @Override
                                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                    String value= Objects.requireNonNull(snapshot.getValue(AppointmentData.class)).toString();
                                    arrayList.add(value);
                                    arrayAdapter.notifyDataSetChanged();

                                }

                                @Override
                                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }
                                @Override
                                public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                                    arrayAdapter.notifyDataSetChanged();
                                }
                                @Override
                                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }
                                @Override
                                public void onCancelled(@NonNull DatabaseError error) { }
                            });
                            arrayAdapter.notifyDataSetChanged();
                        }).addOnFailureListener(e -> Toast.makeText(Appointments.this, "Cancel Appointment Failed",Toast.LENGTH_LONG).show());
            }
            else{
                Toast.makeText(Appointments.this, "Cant process",Toast.LENGTH_LONG).show();
            }
        });
    }
}
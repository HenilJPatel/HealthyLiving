package com.example.healthyliving;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class NewAppointment extends AppCompatActivity  {
    private final String[] DoctorName ={"Select Doctor","Dr.Selena Singh","Dr.Parth Patel", "Dr. John Rodriguez"};
    private final String[] DocAddress={"Doctor not Selected","1200 W University Blvd, Winterpark","2nd Stuart St, Orlando","2200, 52nd South Dr, Sanford"};
    //String docName,Address = new ArrayList<>();
    EditText date_time;
    EditText etAddress;
    final private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private long max_id =0;
    ArrayList<String> Name;
    ArrayList<String> Address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_appointment);
        FirebaseUser user= mAuth.getCurrentUser();
        DatabaseReference db=FirebaseDatabase.getInstance().getReference("Users").child(Objects.requireNonNull(user).getUid()).child("Appointments");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    max_id = snapshot.getChildrenCount() ;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}});
        /*Appointment Date*/{
            date_time = findViewById(R.id.editTime);
            final Calendar cal = Calendar.getInstance();
            @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
            date_time.setText(formatter.format(cal.getTime()));
            date_time.setInputType(InputType.TYPE_NULL);
            date_time.setOnClickListener(v -> DatePick(date_time));
        }
        TextView tv=findViewById(R.id.textView10);
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Doctor");
        Name=new ArrayList<>();
        Name.add("No Doctor Selected");
        Address=new ArrayList<>();
        Address.add("No Doctor Selected");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        databaseReference.child(ds.getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                Name.add(snapshot.child("DoctorName").getValue().toString());
                                Address.add(snapshot.child("Address").getValue().toString());
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "DB doesn't work", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ArrayAdapter doctorAdapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,Name);
        Spinner spin = findViewById(R.id.spinnerDoctorName);
        spin.setAdapter(doctorAdapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                etAddress.setText(spin.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                tv.setText("None");
            }
        });
        /*New Appointment Button*/{

            Button btnNew = findViewById(R.id.btnAppointment);
            btnNew.setOnClickListener(v -> {
                if(spin.getSelectedItemPosition()!=0) {
                    AppointmentData aptData = new AppointmentData();
                    //spin = ((Spinner) findViewById(R.id.spinnerDoctorName)).getSelectedItem().toString().trim();
                    //EditText address = findViewById(R.id.editAddress);
                    //address = address.getText().toString();
                    String Date = ((EditText) findViewById(R.id.editTime)).getText().toString().trim();
                    aptData.setPatientName("null");
                    aptData.setDoctorName(null);
                    aptData.setAddress(null);
                    aptData.setTime(Date);
                    aptData.setStatus("active");
                    aptData.setId(max_id);
                    DatabaseReference dbref;
                    dbref = FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid()).child("Appointments");
                    dbref.child(String.valueOf(max_id)).setValue(aptData);
                    Toast.makeText(NewAppointment.this, "Appointment Created", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(NewAppointment.this,"Please Select a Doctor",Toast.LENGTH_SHORT).show();
                }
            });
        }
        /*Back Button*/{
            Button back = findViewById(R.id.btnBack);
            back.setOnClickListener(v -> {
                Intent intent = new Intent(NewAppointment.this, Appointments.class);
                startActivity(intent);
            });
        }
    }

    public void DatePick(final EditText date_time) {
        final Calendar calender = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            calender.set(Calendar.YEAR, year);
            calender.set(Calendar.MONTH, month);
            calender.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            TimePickerDialog.OnTimeSetListener timeSetListener = (view1, hourOfDay, minute) -> {
                calender.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calender.set(Calendar.MINUTE, minute);

                @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");

                date_time.setText(simpleDateFormat.format(calender.getTime()));
            };

            new TimePickerDialog(NewAppointment.this, timeSetListener, calender.get(Calendar.HOUR_OF_DAY), calender.get(Calendar.MINUTE), true).show();
        };
        new DatePickerDialog(NewAppointment.this, dateSetListener,calender.get(Calendar.YEAR), calender.get(Calendar.MONTH), calender.get(Calendar.DAY_OF_MONTH)).show();
    }
}

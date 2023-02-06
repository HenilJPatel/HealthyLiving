package com.example.healthyliving;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.HandlerThread;
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

import com.example.healthyliving.mapclass.Location;
import com.example.healthyliving.mapclass.MapData;
import com.example.healthyliving.mapclass.Result;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class NewAppointment extends AppCompatActivity implements OnMapReadyCallback {
    final OkHttpClient client = new OkHttpClient();
    EditText date_time;
    final private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private long max_id =0;
    ArrayList<String> Name;
    ArrayList<String> Address;
    Location l;
    LatLng location;
    GoogleMap mMap;
    String addresserrortext="Error Retriving address for this Doctor";
    String activedoc,activeaddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_appointment);
        try {
            FirebaseUser user = mAuth.getCurrentUser();
            DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(Objects.requireNonNull(user).getUid()).child("Appointments");
            db.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        max_id = snapshot.getChildrenCount();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
            /*Appointment Date*/
            final Calendar cal = Calendar.getInstance();
            @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
            date_time = findViewById(R.id.editTime);
            date_time.setText(formatter.format(cal.getTime()));
            date_time.setInputType(InputType.TYPE_NULL);
            date_time.setOnClickListener(v -> DatePick(date_time));
            //End appointment date
            TextView tv = findViewById(R.id.createappointmentdoctoraddress);
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Doctor");
            Name = new ArrayList<>();
            Name.add("No Doctor Selected");
            Address = new ArrayList<>();
            Address.add(null);
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            databaseReference.child(ds.getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        Name.add(Objects.requireNonNull(snapshot.child("DoctorName").getValue()).toString());
                                    try {
                                        Address.add(snapshot.child("Address").getValue().toString());
                                    } catch (NullPointerException e) {
                                        Address.add("Error Retrieving address for this Doctor");
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                }
                            });
                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "DB doesn't work", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.new_appointment_map);
            ArrayAdapter<String> doctorAdapter = new ArrayAdapter<>(NewAppointment.this, android.R.layout.simple_spinner_dropdown_item, Name);
            Spinner spin = findViewById(R.id.spinnerDoctorName);
            spin.setAdapter(doctorAdapter);
            spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (spin.getSelectedItem() != addresserrortext && spin.getSelectedItem() != null) {
                        tv.setText(Address.get(position));
                        activedoc=Name.get(position);
                        activeaddress=Address.get(position);
                        mapFragment.getMapAsync(NewAppointment.this);
                    } else {
                        //Toast.makeText(NewAppointment.this,"Nothing",Toast.LENGTH_LONG).show();
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    tv.setText("None");
                }
            });
            /*New Appointment Button*/
            Button btnNew = findViewById(R.id.btnAppointment);
            btnNew.setOnClickListener(v -> {
                if (spin.getSelectedItemPosition() != 0) {
                    AppointmentData aptData = new AppointmentData();
                    String Date = ((EditText) findViewById(R.id.editTime)).getText().toString().trim();
                    aptData.setPatientName("null");
                    aptData.setDoctorName(activedoc);
                    aptData.setAddress(activeaddress);
                    aptData.setTime(Date);
                    aptData.setStatus("active");
                    aptData.setId(max_id);
                    DatabaseReference dbref;
                    dbref = FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid()).child("Appointments");
                    dbref.child(String.valueOf(max_id)).setValue(aptData);
                    Toast.makeText(NewAppointment.this, "Appointment Created", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(NewAppointment.this, "Please Select a Doctor", Toast.LENGTH_SHORT).show();
                }
            });
            /*Back Button*/
            Button back = findViewById(R.id.btnBack);
            back.setOnClickListener(v -> {
                Intent intent = new Intent(NewAppointment.this, Appointments.class);
                startActivity(intent);
            });
        }catch (Exception e){
            //Toast.makeText(this,"error",Toast.LENGTH_LONG).show();
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
    @Override
    public void onMapReady(GoogleMap googleMap){
        try {
            Thread t= new HandlerThread("UIHandler"){
                @Override
                public void run() {
                    try {
                        TextView input=findViewById(R.id.createappointmentdoctoraddress);
                        URL myUrl = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" + input.getText() + "&key=" + getString(R.string.google_maps_key));
                        Request request = new Request.Builder()
                                .url(myUrl)
                                .build();
                        Response response = client.newCall(request).execute();
                        String str = response.body().string();
                        Gson gson = new Gson();
                        MapData mapData = gson.fromJson(str, MapData.class);
                        List<Result> res = mapData.getResults();
                        if (mapData.getStatus().equals("OK")) {
                            l=res.get(0).getGeometry().getLocation();
                        }
                        /*Code if address has some problems
                        else{

                        }
                        */
                    } catch (Exception e) {
                        //e.getStackTrace();
                    }
                }
            };
            t.start();
            t.join();
            mMap = googleMap;
            try{
                Spinner spinner=findViewById(R.id.spinnerDoctorName);
                location=new LatLng(l.getLat(),l.getLng());
                if(spinner.getSelectedItemPosition()!=0) {
                    mMap.addMarker(new MarkerOptions().position(location).title(spinner.getSelectedItem().toString()));
                    CameraPosition cameraPosition = new CameraPosition(location, 14, 0, 0);
                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }
            }catch (Exception e){

            }
        }catch (Exception e){

        }
    }
}

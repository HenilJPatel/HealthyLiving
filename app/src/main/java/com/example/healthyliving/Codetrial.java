package com.example.healthyliving;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.HandlerThread;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthyliving.openfda.LabelData;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.net.URL;

public class Codetrial extends AppCompatActivity //implements OnMapReadyCallback
{
    final OkHttpClient client = new OkHttpClient();
    EditText input;
    TextView output;
    Button clicker;
    String str;
    /*double getLat,getLng;
    GoogleMap mMap;
    Location l;*/

    private LatLng location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codetrial);
        clicker = findViewById(R.id.codetrialbutton);
        clicker.setOnClickListener(v -> {
            try {
                input = findViewById(R.id.codetrialinput);
                output = findViewById(R.id.codetrialOutput);
                final int[] value = new int[1];
                Thread t = new HandlerThread("UIHandler") {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {
                        try {
                            //URL myUrl = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" + input.getText() + "&key=" + getString(R.string.google_maps_key));
                            URL myUrl = new URL("https://api.fda.gov/drug/label.json?limit=10");
                            Request request = new Request.Builder()
                                    .url(myUrl)
                                    .build();
                            Response response = client.newCall(request).execute();
                            str = response.body().string();
                            output.setText(str);
                            Gson gson = new Gson();
                            LabelData mapData = gson.fromJson(str, LabelData.class);
                            //List<Label_Results> res = mapData.getResults();
                            output.setText(str);
                        } catch (Exception e) {
                           output.setText(e.toString());
                        }
                    }
                };
                try {
                    t.start();
                    t.join();
                    //SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    //        .findFragmentById(R.id.code_trial_map);
                    //assert mapFragment != null;
                    //mapFragment.getMapAsync(Codetrial.this);
                } catch (Exception e) {
                    clicker.setText(String.valueOf(value[0]));
                }
            } catch (Exception exception) {
                clicker.setText(exception.toString());
            }
        });
    }
   // @NonNull
   // @Override
    /*public void onMapReady(GoogleMap googleMap) {
        try {
            mMap = googleMap;
            // Add a marker in Sydney and move the camera
            location=new LatLng(l.getLat(),l.getLng());
            mMap.addMarker(new MarkerOptions().position(location).title("The location"));
            CameraPosition cameraPosition= new CameraPosition(location,15,0,0);
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        }catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }*/
}
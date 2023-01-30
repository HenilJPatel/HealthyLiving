package com.example.healthyliving;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.net.URL;

public class Codetrial extends AppCompatActivity {
    final OkHttpClient client = new OkHttpClient();
    EditText input;
    TextView output;
    Button clicker;
    String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codetrial);
        clicker=findViewById(R.id.codetrialbutton);
        clicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    input=findViewById(R.id.codetrialinput);
                    output =findViewById(R.id.codetrialOutput);
                    Thread thread = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            try  {
                                URL myUrl=new URL("https://maps.googleapis.com/maps/api/geocode/json?address="+input.getText()+"&key="+getString(R.string.google_maps_key));
                                Request request=new Request.Builder()
                                        .url(myUrl)
                                        .build();
                                Response response = client.newCall(request).execute();
                                str= response.body().string();
                                output.setText(str);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    thread.start();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }
    public String run(String address){

        try {
            URL myUrl=new URL("https://maps.googleapis.com/maps/api/geocode/xml?address="+address+"&key="+R.string.google_maps_key);
            Request request=new Request.Builder()
                .url(myUrl)
                .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }catch (Exception exception){
            return exception.toString();
        }
    }
}
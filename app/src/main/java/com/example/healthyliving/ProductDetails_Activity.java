package com.example.healthyliving;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.HandlerThread;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthyliving.openfda.LabelData;
import com.example.healthyliving.openfda.Label_Results;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.net.URL;
import java.util.List;

public class ProductDetails_Activity extends AppCompatActivity {
    final private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    final OkHttpClient client=new OkHttpClient();
    String str,img;
    List<Label_Results> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Bundle extras = getIntent().getExtras();
        str= extras.getString("Data",null);
        img= extras.getString("img");
        Toast.makeText(this,String.valueOf(img), Toast.LENGTH_SHORT).show();
        RecyclerView recyclerView;
        recyclerView=findViewById(R.id.ProductDetail);
        DatabaseReference databaseReference;
        Adapter_openFDA_ProductDetail PDAdapter;
        recyclerView.setHasFixedSize(true);
        if(str!=null) {
            list=Threadset();
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            PDAdapter = new Adapter_openFDA_ProductDetail(this, list,img);
            recyclerView.setAdapter(PDAdapter);
        }
    }
    public List<Label_Results> Threadset(){
        final List<Label_Results>[] results1 = new List[]{null};
        TextView textView=findViewById(R.id.textView4);
        Thread t = new HandlerThread("UIHandler") {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                try {
                    URL myUrl = new URL("https://api.fda.gov/drug/label.json?limit=100&search=openfda.id.like.%22"+str+"%22");
                    Request request = new Request.Builder()
                            .url(myUrl)
                            .build();
                    Response response = client.newCall(request).execute();
                    String str = response.body().string();
                    Gson gson = new Gson();
                    LabelData mapData = gson.fromJson(str, LabelData.class);
                    List<Label_Results> res = mapData.getResults();
                    results1[0] =res;
                } catch (Exception e) {
                    e.getStackTrace();

                }
            }
        };
        try {
            t.start();
            t.join();
        } catch (Exception e) {
            textView.setText(e.toString());
        }
        return results1[0];
    }
}
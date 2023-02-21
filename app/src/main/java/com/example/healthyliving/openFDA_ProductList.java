package com.example.healthyliving;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.HandlerThread;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthyliving.openfda.NDCData;
import com.example.healthyliving.openfda.NDC_Results;
import com.google.firebase.database.DatabaseReference;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.net.URL;
import java.util.List;

public class openFDA_ProductList extends AppCompatActivity {
    OkHttpClient client=new OkHttpClient();
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    Adapter_openFDA_ProductList1 product_list;
    List<NDC_Results> label_results;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        recyclerView=findViewById(R.id.ProductViewer);
        recyclerView.setHasFixedSize(true);
        int columns=2;
        recyclerView.setLayoutManager(new GridLayoutManager(this,columns));
        TextView textView1=findViewById(R.id.textView4);
        try {
            label_results = Threadset();
            if (label_results == null) {
                Toast.makeText(this, "Failed to retrieve Data", Toast.LENGTH_SHORT).show();
            } else {
                product_list = new Adapter_openFDA_ProductList1(this, label_results);
                recyclerView.setAdapter(product_list);
                product_list.notifyDataSetChanged();
            }
        }catch (Exception e){
            textView1.setText(e.toString());
        }
    }
    /*public List<Label_Results> Threadset(){
        final List<Label_Results>[] results1 = new List[]{null};
        TextView textView=findViewById(R.id.textView4);
        Thread t = new HandlerThread("UIHandler") {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                try {
                    URL myUrl = new URL("https://api.fda.gov/drug/label.json?search=(_exists_:openfda)+AND+openfda.product_type:%22OTC%22&limit=100");
                    Request request = new Request.Builder()
                            .url(myUrl)
                            .build();
                    Response response = client.newCall(request).execute();
                    String str = response.body().string();
                    Gson gson = new Gson();
                    LabelData labelData = gson.fromJson(str, LabelData.class);
                    List<Label_Results> res = labelData.getResults();
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
    }*/
    public List<NDC_Results> Threadset(){
        final List<NDC_Results>[] results1 = new List[]{null};
        TextView textView=findViewById(R.id.textView4);
        Thread t = new HandlerThread("UIHandler") {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                try {
                    URL myUrl = new URL("https://api.fda.gov/drug/ndc.json?search=product_type:%22OTC%22&limit=100");
                    Request request = new Request.Builder()
                            .url(myUrl)
                            .build();
                    Response response = client.newCall(request).execute();
                    String str = response.body().string();
                    Gson gson = new Gson();
                    NDCData labelData = gson.fromJson(str, NDCData.class);
                    List<NDC_Results> res = labelData.getResults();
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

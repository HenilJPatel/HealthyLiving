package com.example.healthyliving;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.HandlerThread;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class openFDA_ProductList extends AppCompatActivity {
    OkHttpClient client=new OkHttpClient();
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    Adapter_openFDA_ProductList1 product_list,product_update;
    List<NDC_Results> label_results,label_results1;
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
        Button listview = (Button) findViewById(R.id.list_item);
        listview.setVisibility(View.GONE);
        final CharSequence[] items = {"Proctor","Rite Aid"};
        final ArrayList selectedItems=new ArrayList();
        boolean[] selected=new boolean[items.length];
        Arrays.fill(selected,true);
        listview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(openFDA_ProductList.this);
                builder.setTitle("This is a title");
                builder.setMultiChoiceItems(items,selected, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        //Here you add or remove the items from the list selectedItems. That list will be the result of the user selection.
                        if(isChecked){
                            selected[which]=true;
                        }else {
                            selected[which]=false;
                        }
                    }
                });

                builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int i=0;
                        selectedItems.clear();
                        for (boolean b:selected
                             ) {
                            if(b){
                                selectedItems.add(items[i]);
                            }else{
                                selectedItems.remove(items[i]);
                            }
                            i++;
                        }
                        Thread t=new HandlerThread("HANDLER") {
                            @Override
                            public void run() {
                                String str=update(selectedItems);
                                if(label_results1!=null) {
                                    label_results1.clear();
                                }
                                refresh(str);
                            }
                        };
                        t.start();
                        //Do something when the user closes the dialog by pressing the Done button
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //Do something else if you want
                    }
                });

                builder.create();
                builder.show();
            }
        });
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
    public List<NDC_Results> Threadset() {
        String str="labeler_name.like.%22Procter%22+OR+labeler_name.like.%22Vicks%22+OR+labeler_name.like.%22Rite%22";
        return Threadset(str);
    }
    public List<NDC_Results> Threadset(String str){
        final List<NDC_Results>[] results1 = new List[]{null};
        TextView textView=findViewById(R.id.textView4);
        Thread t = new HandlerThread("UIHandler") {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                try {
                    URL myUrl = new URL("https://api.fda.gov/drug/ndc.json?search=product_type:%22OTC%22+AND+("+str+")&limit=100");
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
    public String update(ArrayList<String> selecteditems){
        boolean test=false;
        String str="";
        for (String s:selecteditems){
            if(test) {
                str = str + "+OR+";
            }else {

            }
            str=str+"labeler_name.like.%22"+s+"%22";
            test=true;
        }
        return str;
    }

    public void refresh(String s) {
        //TextView textView1 = findViewById(R.id.textView4);
        try {
            label_results1 = Threadset(s);
            //textView1.setText(label_results.toString());
            product_update = new Adapter_openFDA_ProductList1(openFDA_ProductList.this, label_results1);
            recyclerView.setAdapter(product_update);
            runOnUiThread(() -> {
                Toast.makeText(openFDA_ProductList.this, label_results1.toString(), Toast.LENGTH_SHORT).show();
            });
            /*if (label_results == null) {
                Toast.makeText(openFDA_ProductList.this, "Failed to retrieve Data", Toast.LENGTH_SHORT).show();
            } else {
                product_list = new Adapter_openFDA_ProductList1(openFDA_ProductList.this, label_results);
                recyclerView.setAdapter(product_list);
                product_list.notifyAll();
            }*/
        } catch (Exception e) {
            //System.out.println(e.toString());
        }
    }
}

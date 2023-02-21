package com.example.healthyliving;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthyliving.imageclass.imagesearch;
import com.example.healthyliving.openfda.NDC_Results;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.net.URL;
import java.util.List;

public class Adapter_openFDA_ProductList1 extends RecyclerView.Adapter<Adapter_openFDA_ProductList1.MyViewHolder> {
    NDC_Results lResults;
    Context context;
    List<NDC_Results> list;
    final String[] strr = {""};

    public Adapter_openFDA_ProductList1(Context context, List<NDC_Results> list) {
            this.context = context;
            this.list = list;
        }

        @NonNull
        @Override
        public Adapter_openFDA_ProductList1.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v= LayoutInflater.from(context).inflate(R.layout.cardview_product_list,parent,false);
            return new Adapter_openFDA_ProductList1.MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull Adapter_openFDA_ProductList1.MyViewHolder holder, int position) {
            lResults =list.get(position);

            NDC_Results Results=lResults;
            String productname= Results.getBrand_name_base()+(Results.getBrand_name_suffix()!=null?" "+ Results.getBrand_name_suffix():"");
            //String productname=Results.getBrand_name().get(0);
            holder.product_name.setText(productname);
            //String manufacturer=Results.getManufacturer_name().get(0);
            String labeler=Results.getLabeler_name();
            holder.brand.setText(labeler);
            holder.img.setImageResource(R.drawable.noimage);
            {Thread t=new Thread(() -> {
                try {
                    Context threadcontext=context;
                    OkHttpClient client=new OkHttpClient();
                    URL myUrl = new URL("https://customsearch.googleapis.com/customsearch/v1?key=AIzaSyAwL-gYCuyPPP9fghAPPUlhVJdOfI4E7qM&cx=b50c011fe291c4c87&q="+productname+" "+labeler);//12df352a260bc480e
                    Request request = new Request.Builder()
                            .url(myUrl)
                            .build();
                    Response response = client.newCall(request).execute();
                    String str = response.body().string();
                    Gson gson = new Gson();
                    imagesearch img=gson.fromJson(str,imagesearch.class);

                    int i=0;
                    while(img.getItems().get(0).getPagemap().getCse_image().iterator().hasNext()){
                        strr[0] =img.getItems().get(0).getPagemap().getCse_image().get(i).getSrc();
                        if(strr[0].contains("https")){
                            break;
                        }
                        i++;
                    }
                    //holder.product_name.setText(strr[0]);
                    URL url = new URL(strr[0]);
                    Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    holder.img.setImageBitmap(bitmap);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            });
            t.start();

            }
            //holder.product_name.setText(list.get(position).getGeneric_name());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{

            TextView product_name,brand;
            ImageView img;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                img=itemView.findViewById(R.id.Prod_Image);
                product_name=itemView.findViewById(R.id.Prod_ProductName);
                brand=itemView.findViewById(R.id.Prod_BrandName);
                //context=itemView.getContext();
                itemView.setOnClickListener(v-> {
                        int itemPosition = getLayoutPosition();
                        //Toast.makeText(context, "" + list.get(itemPosition).getOpenfda().getSpl_id(), Toast.LENGTH_SHORT).show();
                        try {
                            Intent intent = new Intent(context, ProductDetails_Activity.class);
                            intent.putExtra("Data",list.get(itemPosition).getSpl_id());
                            //Toast.makeText(context, strr[0], Toast.LENGTH_SHORT).show();
                            intent.putExtra("img",strr[0]);
                            context.startActivity(intent);
                        } catch (Exception e) {
                            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                });
            }
        }
}

package com.example.healthyliving;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthyliving.openfda.Label_Results;
import com.example.healthyliving.openfda.Label_openfda;

import java.util.List;

public class Adapter_openFDA_ProductList extends RecyclerView.Adapter<Adapter_openFDA_ProductList.MyViewHolder> {
    Label_Results lResults;
        Context context;
        List<Label_Results> list;

    public Adapter_openFDA_ProductList(Context context, List<Label_Results> list) {
            this.context = context;
            this.list = list;
        }

        @NonNull
        @Override
        public Adapter_openFDA_ProductList.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v= LayoutInflater.from(context).inflate(R.layout.cardview_product_list,parent,false);
            return new Adapter_openFDA_ProductList.MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull Adapter_openFDA_ProductList.MyViewHolder holder, int position) {
            lResults =list.get(position);
            Label_openfda Results=lResults.getOpenfda();
            //String productname= Results.getBrand_name_base()+(Results.getBrand_name_suffix()!=null?" "+ NDCResults.getBrand_name_suffix():"");
            String productname=Results.getBrand_name().get(0);
            holder.product_name.setText(productname);
            holder.brand.setText(Results.getManufacturer_name().get(0));
            holder.img.setImageResource(R.drawable.noimage);
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
                            intent.putExtra("Data",list.get(itemPosition).getOpenfda().getSpl_id().get(0));
                            context.startActivity(intent);
                        } catch (Exception e) {
                            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                });
            }
        }
}

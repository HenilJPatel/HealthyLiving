package com.example.healthyliving;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthyliving.openfda.Label_Results;

import java.net.URL;
import java.util.List;

public class Adapter_openFDA_ProductDetail extends RecyclerView.Adapter<Adapter_openFDA_ProductDetail.MyViewHolder> {
        Context context;
        List<Label_Results> list;
        String img;

    public Adapter_openFDA_ProductDetail(Context context, List<Label_Results> list,String img) {
            this.context = context;
            this.list = list;
            this.img=img;
        }

        @NonNull
        @Override
        public Adapter_openFDA_ProductDetail.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v= LayoutInflater.from(context).inflate(R.layout.cardview_product_details,parent,false);
            return new Adapter_openFDA_ProductDetail.MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull Adapter_openFDA_ProductDetail.MyViewHolder holder, int position) {
            Label_Results label_results=list.get(position);
            holder.Product_name.setText(label_results.getOpenfda().getBrand_name().get(0));
            holder.brand.setText(label_results.getOpenfda().getManufacturer_name().get(0));
            holder.direction.setText(label_results.getDosage_and_administration().get(0));
            //holder.availability.setText(list.get(position).getOpenfda().getBrand_name().get(0));
            //holder.category.setText(list.get(position).getOpenfda().getBrand_name().get(0));
            holder.warning.setText(label_results.getWarnings().get(0));
            holder.img.setImageResource(R.drawable.noimage);
            try{
                URL url=new URL(img);
                Bitmap bitmap= BitmapFactory.decodeStream(url.openConnection().getInputStream());
                holder.img.setImageBitmap(bitmap);
            }catch (Exception e){

            }
            //Picasso.with(context).load(url).into(holder.img);
        }

        @Override
        public int getItemCount() {
            return (list!=null?1:0);
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{

            TextView Product_name,brand,direction,warning,availability,category;
            ImageView img;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                img=itemView.findViewById(R.id.PimageView);
                Product_name =itemView.findViewById(R.id.PName);
                brand=itemView.findViewById(R.id.Pbrand);
                direction=itemView.findViewById(R.id.Pdirection);
                warning=itemView.findViewById(R.id.Pwarning);
                availability=itemView.findViewById(R.id.PAvailability);
                //category=itemView.findViewById(R.id.PCategory);
                //context=itemView.getContext();
                itemView.setOnClickListener(v-> {
                        //int itemPosition = getLayoutPosition();
                        //Toast.makeText(context, "" + list.get(itemPosition).getSpl_id(), Toast.LENGTH_SHORT).show();
                        /*try {
                            Intent intent = new Intent(context, ProductDetails_Activity.class);
                            intent.putExtra("prodID", list.get(itemPosition).getpID());
                            context.startActivity(intent);
                        } catch (Exception e) {
                            Toast.makeText(context, "Fuck, this isnt working boss", Toast.LENGTH_SHORT).show();
                        }*/
                });
            }
        }
}

package com.example.healthyliving;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Catalog extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] maintitle;
    private final String[] subtitle;
    private final String[] catalogprice;
    private final Integer[] imgid;

    public Catalog(Activity context, String[] maintitle,String[] subtitle,String[] catalogprice, Integer[] imgid) {
        super(context, R.layout.activity_catalog, maintitle);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.maintitle=maintitle;
        this.subtitle=subtitle;
        this.catalogprice=catalogprice;
        this.imgid=imgid;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView= ((LayoutInflater) inflater).inflate(R.layout.activity_catalog, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.subtitle);
        subtitleText.setTextColor(Color.RED);

        TextView catprice = (TextView) rowView.findViewById(R.id.catalogprice);
        catprice.setTextColor(Color.RED);
        titleText.setText(maintitle[position]);
        imageView.setImageResource(imgid[position]);
        subtitleText.setText(subtitle[position]);
        catprice.setText(catalogprice[position]);

        return rowView;

    };
}
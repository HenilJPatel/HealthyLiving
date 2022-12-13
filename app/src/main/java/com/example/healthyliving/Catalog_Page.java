package com.example.healthyliving;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Catalog_Page extends AppCompatActivity {

    ListView list;

    String[] maintitle ={
            "DayQuil","NyQuil","KneePad",
            "Advil Ibuprofen Tablet 100ct","Benadryl Children's Allergy Liquid 4oz","Accu-Chek Aviva Plus Diabetic Test Strips - 50 Strips",
            "Accu-Chek Guide Me Meter Care Kit - 1ct","Mason Natural Vitamin D 1000 Iu Softgels 60ct","Natures Bounty Your Life Multivitamin Adult Gummies, 75ct",
            "Sundown Naturals Milk Thistle XTRA Herbal Supplement 240mg Capsules- 60ct","Gillette 3X Protection Anti-Perspirant/Deodorant Clear Gel Cool Wave 4oz","Old Spice High Endurance Deodorant Long Lasting Stick Pure Sport 2.25 oz"
    };

    String[] subtitle ={
            "Pain & Fever Medicine","Pain & Fever Medicine","Pain Accessories",
            "Pain & Fever Medicine", "Allergy & Sinus Medicine","Diabetic Supplies",
            "Diabetic Supplies","Diet&Nutrition"," Diet & Nutrition",
            "Diet&Nutrition","Deodorant and Antiperspirants","Deodorant and Antiperspirants"
    };
    String[] catalogprice ={
            "$9.99","$9.99", "$19.99",
            "$14.99", "$8.99","$89.99",
            "$16.99","$8.50","$11.99",
            "$15.49","$7.59","$9.49"
    };

    Integer[] imgid={
            R.drawable.img0,R.drawable.img2,R.drawable.img1,
            R.drawable.img4,R.drawable.img3,R.drawable.img5,
            R.drawable.img6,R.drawable.img7,R.drawable.img8,
            R.drawable.img10,R.drawable.img11,R.drawable.img12,
                };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog_page);
        Catalog adapter=new Catalog(this, maintitle, subtitle, catalogprice,imgid);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub

                    //code specific to first list item
                    Toast.makeText(getApplicationContext(),"Selected " + maintitle[position],Toast.LENGTH_SHORT).show();

            }
        });
    }
}
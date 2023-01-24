package com.example.healthyliving;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CouponDetailActivity extends AppCompatActivity {
    final private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    Adapter_Coupon_Detail adapter_coupon_detail;
    ArrayList<String> list;
    long count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_list);
        recyclerView=findViewById(R.id.CouponListViewer);
        Bundle extras = getIntent().getExtras();
        String couponID=null;
        String reference=null;
        if (extras != null) {
            couponID = extras.getString("couponID");
            reference = extras.getString("reference");
        }
        String ref=reference+"/"+couponID;
        databaseReference= FirebaseDatabase.getInstance().getReference(reference+"/"+couponID);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();
        adapter_coupon_detail = new Adapter_Coupon_Detail(this,list,reference+"/"+couponID,null);
        recyclerView.setAdapter(adapter_coupon_detail);
        list.add(ref);
        adapter_coupon_detail.notifyDataSetChanged();
        /*databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                TextView couponslist=findViewById(R.id.Coupon_List_Title);
               // couponslist.setText(snapshot.toString());
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    try {
                        Data_Coupon data_coupon=dataSnapshot.getValue(Data_Coupon.class);
                        couponslist.setText(data_coupon.getMemberID());
                        data_coupon.setKey(dataSnapshot.getKey());

                        //list.add(data_coupon);
                    } catch (Exception e){
                        Toast.makeText(getApplicationContext(),"This is not working",Toast.LENGTH_SHORT).show();
                    }
                }
                if(list.size()>0){
                    adapter_coupon_detail.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
    }
}
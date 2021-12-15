package com.sameetasadullah.i180479_i180531.presentationLayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sameetasadullah.i180479_i180531.R;
import com.sameetasadullah.i180479_i180531.logicLayer.Customer;
import com.sameetasadullah.i180479_i180531.logicLayer.HRS;
import com.sameetasadullah.i180479_i180531.logicLayer.Vendor;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Vendor_Choose_Option_Screen extends AppCompatActivity {
    RelativeLayout register_hotel, view_registered_hotels;
    CircleImageView dp;
    HRS hrs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_choose_option_screen);

        register_hotel = findViewById(R.id.rl_register_hotel_button);
        view_registered_hotels = findViewById(R.id.rl_view_registered_hotels);
        dp = findViewById(R.id.display_pic);
        hrs = HRS.getInstance(Vendor_Choose_Option_Screen.this);

        String email = getIntent().getStringExtra("email");
        for(int i = 0; i < hrs.getVendors().size(); ++i) {
            if (hrs.getVendors().get(i).getEmail().equals(email)) {
                Picasso.get().load(hrs.getVendors().get(i).getDp()).into(dp);
            }
        }
        register_hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Vendor_Choose_Option_Screen.this, Hotel_Registration_Screen.class);
                startActivity(intent);
            }
        });
        view_registered_hotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Vendor_Choose_Option_Screen.this, Registered_Hotels_Screen.class);
                startActivity(intent);
            }
        });
    }
}
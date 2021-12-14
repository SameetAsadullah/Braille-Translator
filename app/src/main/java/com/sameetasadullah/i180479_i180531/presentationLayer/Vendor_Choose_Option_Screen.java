package com.sameetasadullah.i180479_i180531.presentationLayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sameetasadullah.i180479_i180531.R;

public class Vendor_Choose_Option_Screen extends AppCompatActivity {
    RelativeLayout register_hotel, view_registered_hotels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_choose_option_screen);

        register_hotel = findViewById(R.id.rl_register_hotel_button);
        view_registered_hotels = findViewById(R.id.rl_view_registered_hotels);

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
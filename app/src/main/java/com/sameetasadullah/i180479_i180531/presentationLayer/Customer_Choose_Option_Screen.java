package com.sameetasadullah.i180479_i180531.presentationLayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.sameetasadullah.i180479_i180531.R;

public class Customer_Choose_Option_Screen extends AppCompatActivity {
    RelativeLayout reserve_hotel, view_old_reservations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_choose_option_screen);

        reserve_hotel = findViewById(R.id.rl_reserve_hotel_button);
        view_old_reservations = findViewById(R.id.rl_view_old_reservations);

        reserve_hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Customer_Choose_Option_Screen.this, Reserve_Screen.class);
                startActivity(intent);
            }
        });
        view_old_reservations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Customer_Choose_Option_Screen.this, Reservations_Screen.class);
                startActivity(intent);
            }
        });
    }
}
package com.sameetasadullah.i180479_i180531.presentationLayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sameetasadullah.i180479_i180531.R;
import com.sameetasadullah.i180479_i180531.logicLayer.HRS;
import com.sameetasadullah.i180479_i180531.logicLayer.Hotel;
import com.sameetasadullah.i180479_i180531.logicLayer.Reservation;
import com.sameetasadullah.i180479_i180531.logicLayer.Room;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

public class Hotel_Reservation_Screen extends AppCompatActivity {

    RelativeLayout endButton;
    EditText hotelName,rooms,totalPrice,totalRooms;
    HRS hrs;
    String Email,checkInDate,checkOutDate,HotelName,HotelLocation;
    Hotel h1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_reservation_screen);
        hotelName = findViewById(R.id.tv_hotel_name);
        rooms = findViewById(R.id.tv_rooms);
        totalPrice = findViewById(R.id.tv_total_price);
        totalRooms = findViewById(R.id.tv_total_rooms);
        endButton = findViewById(R.id.END_button);
        hrs = HRS.getInstance(Hotel_Reservation_Screen.this);
        Email = getIntent().getStringExtra("Email");

        HotelName = getIntent().getStringExtra("Hotel_name");
        HotelLocation = getIntent().getStringExtra("Hotel_Loc");

        h1 = hrs.searchHotelByNameLoc(HotelName,HotelLocation);

        /////////////

        Toast.makeText(Hotel_Reservation_Screen.this,"Yello::::" + h1.getReservations().size(),Toast.LENGTH_LONG ).show();

        Vector<Reservation> res= h1.getReservations();

        Reservation reservation=res.get(res.size());
        Vector<Room> rooms1=reservation.getReservedRooms();
        hotelName.setText(h1.getName());
        totalRooms.setText(String.valueOf(rooms1.size()));
        int totalPriceCal=0;
        String roomFinal="";
        for (int i=0;i<rooms1.size();i++){
            if (rooms1.get(i).getType().equals("Single")){
                totalPriceCal= totalPriceCal + Integer.parseInt(h1.getSingleRoomPrice());
            }
            else{
                totalPriceCal= totalPriceCal + Integer.parseInt(h1.getDoubleRoomPrice());
            }
            roomFinal+= String.valueOf(rooms1.get(i).getNumber());
            if(i+1!= rooms1.size()){
                roomFinal+=",";
            }
        }
        totalPrice.setText(String.valueOf(totalPriceCal));
        rooms.setText(roomFinal);

        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Hotel_Reservation_Screen.this,Customer_Choose_Option_Screen.class);
                intent.putExtra("email",Email);
                startActivity(intent);
            }
        });


    }
}
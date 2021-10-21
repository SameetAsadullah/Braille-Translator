package com.sameetasadullah.i180479_i180531;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Reservations_Screen extends AppCompatActivity {


    RecyclerView rv;
    List<Hotel_row> ls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations_screen);

        rv = findViewById(R.id.rv);
        ls = new ArrayList<>();
        ls.add(new Hotel_row("The Grand Hotel"));
        ls.add(new Hotel_row("Javson"));
        ls.add(new Hotel_row("Monal"));
        ls.add(new Hotel_row("Grand Leisure Hotel"));
        ls.add(new Hotel_row("Michels Glory"));
        ls.add(new Hotel_row("Grandiour"));
        ls.add(new Hotel_row("The Kings Tower"));
        ls.add(new Hotel_row("Mike's Castle"));

        //Adapter
        Hotel_row_adapter adapter = new Hotel_row_adapter(ls,this);
        RecyclerView.LayoutManager lm =new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);
    }
}
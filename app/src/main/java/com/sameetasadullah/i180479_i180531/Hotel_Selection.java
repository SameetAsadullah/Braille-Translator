package com.sameetasadullah.i180479_i180531;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Hotel_Selection extends AppCompatActivity {

    RecyclerView rv;
    List<Hotel_Selection_row> ls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_selection);

        rv = findViewById(R.id.rv);
        ls = new ArrayList<>();
        ls.add(new Hotel_Selection_row("The Grand Hotel"));
        ls.add(new Hotel_Selection_row("Javson"));
        ls.add(new Hotel_Selection_row("Monal"));
        ls.add(new Hotel_Selection_row("Grand Leisure Hotel"));
        ls.add(new Hotel_Selection_row("Michels Glory"));
        ls.add(new Hotel_Selection_row("Grandiour"));
        ls.add(new Hotel_Selection_row("The Kings Tower"));
        ls.add(new Hotel_Selection_row("Mike's Castle"));

        //Adapter
        Hotel_Selection_adapter adapter = new Hotel_Selection_adapter(ls,this);
        RecyclerView.LayoutManager lm =new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);
    }
}
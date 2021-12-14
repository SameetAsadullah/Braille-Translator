package com.sameetasadullah.i180479_i180531.presentationLayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.sameetasadullah.i180479_i180531.R;

import java.util.ArrayList;
import java.util.List;

public class Registered_Hotels_Screen extends AppCompatActivity {

    RecyclerView rv;
    List<Hotel_Registraion_row> ls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_hotels_screen);

        rv = findViewById(R.id.rv);
        ls = new ArrayList<>();
        ls.add(new Hotel_Registraion_row("The Grand Hotel"));
        ls.add(new Hotel_Registraion_row("Javson"));
        ls.add(new Hotel_Registraion_row("Monal"));
        ls.add(new Hotel_Registraion_row("Grand Leisure Hotel"));
        ls.add(new Hotel_Registraion_row("Michels Glory"));
        ls.add(new Hotel_Registraion_row("Grandiour"));
        ls.add(new Hotel_Registraion_row("The Kings Tower"));
        ls.add(new Hotel_Registraion_row("Mike's Castle"));

        //Adapter
        Hotel_Registration_adapter adapter = new Hotel_Registration_adapter(ls,this);
        RecyclerView.LayoutManager lm =new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);
    }
}
package com.sameetasadullah.i180479_i180531.presentationLayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sameetasadullah.i180479_i180531.R;

import java.util.List;

public class Hotel_Registration_adapter extends RecyclerView.Adapter<Hotel_Registration_adapter.Hotel_Registration_Holder>{
    List<Hotel_Registraion_row> ls;
    Context c;
    public Hotel_Registration_adapter(List<Hotel_Registraion_row> ls, Context c) {
        this.c=c;
        this.ls=ls;
    }

    @NonNull
    @Override
    public Hotel_Registration_adapter.Hotel_Registration_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(c).inflate(R.layout.hotel_registration_row,parent,false);
        return new Hotel_Registration_Holder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Hotel_Registration_adapter.Hotel_Registration_Holder holder, int position) {
        holder.name.setText(ls.get(position).getName());

    }

    @Override
    public int getItemCount() {

        return ls.size();
    }



    public class Hotel_Registration_Holder extends RecyclerView.ViewHolder {
        TextView name;
        public Hotel_Registration_Holder(@NonNull View itemView){
            super(itemView);
            name =itemView.findViewById(R.id.name);
        }
    }
}

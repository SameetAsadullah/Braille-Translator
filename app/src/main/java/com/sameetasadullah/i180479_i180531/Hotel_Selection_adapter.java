package com.sameetasadullah.i180479_i180531;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Hotel_Selection_adapter extends RecyclerView.Adapter<Hotel_Selection_adapter.Hotel_Selection_Holder>{
    List<Hotel_Selection_row> ls;
    Context c;
    public Hotel_Selection_adapter(List<Hotel_Selection_row> ls, Context c) {
        this.c=c;
        this.ls=ls;
    }

    @NonNull
    @Override
    public Hotel_Selection_adapter.Hotel_Selection_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(c).inflate(R.layout.hotel_selection_row,parent,false);
        return new Hotel_Selection_Holder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Hotel_Selection_adapter.Hotel_Selection_Holder holder, int position) {
        holder.name.setText(ls.get(position).getName());

    }

    @Override
    public int getItemCount() {

        return ls.size();
    }



    public class Hotel_Selection_Holder extends RecyclerView.ViewHolder {
        TextView name;
        public Hotel_Selection_Holder(@NonNull View itemView){
            super(itemView);
            name =itemView.findViewById(R.id.name);
        }
    }
}

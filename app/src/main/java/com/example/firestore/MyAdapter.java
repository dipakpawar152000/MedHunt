package com.example.firestore;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<LocationList> locationlist;
    private Context context;
    public String lat[];
    public String lon[];
    public String nam[];
    public String ph[];
    public int dipak;

    public MyAdapter() {


    }

    public MyAdapter(List<LocationList> locationlist, Context context) {
        this.locationlist = locationlist;
        this.context = context;
        dipak = 0;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(context).inflate(R.layout.list,parent,false);

        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        LocationList list = locationlist.get(position);

        holder.listlatitude.setText(list.getLatitide());
        holder.listlongitude.setText(list.getLongitude());
        holder.listname.setText(list.getName());
        holder.listphone.setText(list.getPhone());

        lat[dipak] = list.getLatitide().toString();
        lon[dipak] = list.getLongitude().toString();
        nam[dipak] = list.getName().toString();
        ph[dipak] = list.getPhone().toString();







        dipak = dipak+1;





    }

    @Override
    public int getItemCount() {
        return locationlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView listlatitude,listlongitude,listname,listphone;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lat = new String[getItemCount()];
            lon = new String[getItemCount()];
            nam = new String[getItemCount()];
            ph = new String[getItemCount()];

            listlatitude = (TextView) itemView.findViewById(R.id.listlatitude);
            listlongitude = (TextView) itemView.findViewById(R.id.listlongitude);
            listname = (TextView) itemView.findViewById(R.id.listname);
            listphone = (TextView) itemView.findViewById(R.id.listphone);


        }
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);


        Toast.makeText(context.getApplicationContext(),lat[1],Toast.LENGTH_LONG).show();
    }

}

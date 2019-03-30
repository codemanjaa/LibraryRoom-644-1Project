package com.hevs.codemanja.roomdbdemo.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.hevs.codemanja.roomdbdemo.Database.entity.Spot;
import com.hevs.codemanja.roomdbdemo.R;

import java.util.ArrayList;
import java.util.List;

public class SpotAdapter extends RecyclerView.Adapter<SpotAdapter.SpotHolder> {

    private List<Spot> spots  = new ArrayList<>();


    @NonNull
    @Override
    public SpotHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.spot_item, viewGroup, false);

        return new SpotHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SpotHolder spotHolder, int i) {

        Spot currentSpot = spots.get(i);
        spotHolder.textViewDesc.setText(currentSpot.getDesc());
        spotHolder.textViewCategory.setText(currentSpot.getCategory());

    }

    @Override
    public int getItemCount() {
        return spots.size();
    }

    public void setSpots(List<Spot> spots){
        this.spots = spots;
        notifyDataSetChanged();
    }


    class SpotHolder extends RecyclerView.ViewHolder{
        private TextView textViewDesc;
        private TextView textViewCategory;


        public SpotHolder(View itemView){
            super(itemView);
            textViewDesc = itemView.findViewById(R.id.textDesc);
            textViewCategory = itemView.findViewById(R.id.textViewCategory);

        }
    }
}


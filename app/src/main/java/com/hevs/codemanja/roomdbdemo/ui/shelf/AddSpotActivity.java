package com.hevs.codemanja.roomdbdemo.ui.shelf;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hevs.codemanja.roomdbdemo.Adapter.SpotAdapter;
import com.hevs.codemanja.roomdbdemo.Database.entity.Spot;
import com.hevs.codemanja.roomdbdemo.R;
import com.hevs.codemanja.roomdbdemo.viewmodel.SpotViewModel;

import java.util.List;

public class AddSpotActivity extends AppCompatActivity {
    private SpotViewModel spotViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_spot);



        RecyclerView recyclerView = findViewById(R.id.recyclerSpotView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        final SpotAdapter adapter = new SpotAdapter();
        recyclerView.setAdapter(adapter);

        spotViewModel = ViewModelProviders.of(this).get(SpotViewModel.class);
        spotViewModel.getAllSpots().observe(this, new Observer<List<Spot>>() {

            @Override
            public void onChanged(@Nullable List<Spot> spots) {

                adapter.setSpots(spots);


            }
        });

    }
}

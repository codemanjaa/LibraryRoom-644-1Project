package com.hevs.codemanja.roomdbdemo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.hevs.codemanja.roomdbdemo.Database.entity.Spot;
import com.hevs.codemanja.roomdbdemo.Database.repo.SpotRepository;

import java.util.List;

public class SpotViewModel extends AndroidViewModel {

    private SpotRepository repository;
    private LiveData<List<Spot>> allSpots;

    public SpotViewModel(@NonNull Application application) {
        super(application);

        repository = new SpotRepository(application);
        allSpots = repository.getAllSpots();




    }

    public void insert(Spot spot){
        repository.insert(spot);
    }

    public void update(Spot spot){
        repository.update(spot);
    }

    public void delete(Spot spot){
        repository.delete(spot);
    }

    public LiveData<List<Spot>> getAllSpots(){
        return allSpots;
    }
}

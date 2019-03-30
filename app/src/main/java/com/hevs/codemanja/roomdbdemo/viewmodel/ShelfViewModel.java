package com.hevs.codemanja.roomdbdemo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.hevs.codemanja.roomdbdemo.Database.entity.ShelfEntity;
import com.hevs.codemanja.roomdbdemo.Database.repo.ShelfRepo;

import java.util.List;



public class ShelfViewModel extends AndroidViewModel {

    private ShelfRepo repository;
    private LiveData<List<ShelfEntity>> allSpots;
    private LiveData<List<String>> allCategorySpots;

    private MutableLiveData<String> spotId;







    public ShelfViewModel(Application application){
        super(application);

        repository = new ShelfRepo(application);
        allSpots = repository.getAllSpots();

    }
   // public ShelfViewModel(@NonNull Application application) {

  //  }

    public void insert(ShelfEntity shelf) {
        repository.insert(shelf);
    }

    public void update(ShelfEntity shelf){
        repository.update(shelf);
    }

    public void delete(ShelfEntity shelf){
        repository.delete(shelf);
    }

    public LiveData<List<ShelfEntity>> getAllSpots(){
        return allSpots;
    }

    public LiveData<List<String>> getAllCategorySpots(){return allCategorySpots;}


}

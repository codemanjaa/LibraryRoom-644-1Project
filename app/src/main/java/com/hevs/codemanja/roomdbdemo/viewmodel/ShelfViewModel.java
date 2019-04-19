package com.hevs.codemanja.roomdbdemo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.hevs.codemanja.roomdbdemo.Database.entity.ShelfEntity;
import com.hevs.codemanja.roomdbdemo.Database.repo.ShelfRepo;
import com.hevs.codemanja.roomdbdemo.util.OnAsyncEventListener;

import java.util.List;


public class ShelfViewModel extends AndroidViewModel {

    private ShelfRepo repository;
    private LiveData<List<ShelfEntity>> allSpots;
    private LiveData<List<String>> allCategorySpots;

    private LiveData<ShelfEntity> spots;

    private MutableLiveData<String> spotId;
   private  static LiveData<DataSnapshot> liveData;







    public ShelfViewModel(Application application){
        super(application);
        repository = new ShelfRepo();
        liveData = repository.getDataSnapshotShelfLiveData();

      //repository = new ShelfRepo(application);
      //  allSpots = repository.getAllSpots();



    }
   // public ShelfViewModel(@NonNull Application application) {

  //  }

    public void insert(ShelfEntity shelf, OnAsyncEventListener callback ) {
       repository.insert(shelf , callback);
    }

    public void update(ShelfEntity shelf , OnAsyncEventListener callback ){
        repository.update(shelf, callback);
    }

    public void delete(ShelfEntity shelf, OnAsyncEventListener callback ){
        repository.delete(shelf , callback);
    }

    public LiveData<List<ShelfEntity>> getAllSpots(){
        return allSpots;
    }

    public LiveData<List<String>> getAllCategorySpots(){return allCategorySpots;}

    public LiveData<ShelfEntity> spotsList(){
        spots = repository.getSpot();
        return spots;}

    public LiveData<DataSnapshot> getDatasnapshotShelfLiveData(){
        return liveData;
    }




}

package com.hevs.codemanja.roomdbdemo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.hevs.codemanja.roomdbdemo.Database.entity.ShelfEntity;
import com.hevs.codemanja.roomdbdemo.Database.repo.ShelfRepo;
import com.hevs.codemanja.roomdbdemo.util.OnAsyncEventListener;

import java.util.List;


public class ShelfViewModel extends AndroidViewModel {

    private ShelfRepo repository;

    private final MediatorLiveData<DataSnapshot> mObservableData;
    private  static LiveData<DataSnapshot> liveData;
    private LiveData<List<ShelfEntity>> allSpots;
    private LiveData<List<String>> allCategorySpots;

    private LiveData<ShelfEntity> spots;

    private MutableLiveData<String> spotId;

    private String spotsId[];






    public ShelfViewModel(Application application){
        super(application);
        repository = new ShelfRepo();
        liveData = repository.getDataSnapshotShelfLiveData();
        spotsId = repository.getSpotsId();


        //repository = new ShelfRepo(application);
        //  allSpots = repository.getAllSpots();


        mObservableData =new MediatorLiveData<>();

        // Set null until we get data from the database
        mObservableData.setValue(null);
        if(liveData != null){
            mObservableData.addSource(liveData, mObservableData::setValue);
        }

    }

    // public ShelfViewModel(@NonNull Application application) {

    //  }

    public void insert(ShelfEntity shelf, OnAsyncEventListener callback ) {
        repository.insert(shelf , callback);
        repository.getDataSnapshotShelfLiveData();
    }

    public void update(ShelfEntity shelf , OnAsyncEventListener callback ){
        repository.update(shelf, callback);

    }

    public void delete(ShelfEntity shelf, OnAsyncEventListener callback ){
        repository.delete(shelf , callback);

    }


    public LiveData<List<String>> getAllCategorySpots(){return allCategorySpots;}

    public LiveData<ShelfEntity> spotsList(){
        // spots = repository.getSpot();
        return spots;}

    public LiveData<DataSnapshot> getDatasnapshotShelfLiveData(){
        return mObservableData;
    }

    public String[] getSpotsId(){return spotsId;}




}

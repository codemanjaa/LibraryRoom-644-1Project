package com.hevs.codemanja.roomdbdemo.Database.repo;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.hevs.codemanja.roomdbdemo.async.CreateShelf;
import com.hevs.codemanja.roomdbdemo.util.*;
import com.hevs.codemanja.roomdbdemo.Database.LibraryDB;
import com.hevs.codemanja.roomdbdemo.entity.Shelf;

import java.util.List;

public class ShelfRepo {


    private static ShelfRepo instance;

    private ShelfRepo(){

    }

    public static ShelfRepo getInstance(){
        if(instance == null){
            synchronized (ShelfRepo.class){
                if(instance == null){
                    instance = new ShelfRepo();
                }
            }
        }
        return instance;
    }

    /*
    public LiveData<Shelf> getShelf(final String spotId, Context context){
        return LibraryDB.getInstance(context).shelfDao().getBySpotId(spotId);
    }

    public LiveData<List<Shelf>> getAllShlf(Context context){
        return LibraryDB.getInstance(context).shelfDao().getAll();
    }
*/
    public void insert(final Shelf shelf, OnAsyncEventListener callback, Context context){

        new CreateShelf(context, callback).execute(shelf);
    }










}

package com.hevs.codemanja.roomdbdemo.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import com.hevs.codemanja.roomdbdemo.entity.Shelf;

import java.util.List;

@Dao
public interface ShelfDao {

    /*
    @Query("SELECT * FROM shelf WHERE spotId = :spotId")
    LiveData<Shelf> getBySpotId(String spotId);

    @Query("SELECT * FROM shelf")
    LiveData<List<Shelf>> getAll();

*/
    @Query("SELECT spotId FROM shelf")
    String[] getAllSpots();

    @Insert
    void insert(Shelf shelf);

    @Update
    void update(Shelf shelf);

    @Delete
    void delete(Shelf shelf);

    @Query("DELETE  FROM shelf")
    void deleteAll();


}

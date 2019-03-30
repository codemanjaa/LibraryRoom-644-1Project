package com.hevs.codemanja.roomdbdemo.Database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.hevs.codemanja.roomdbdemo.Database.entity.Spot;

import java.util.List;

@Dao
public interface SpotDao {

    @Insert
    void insert(Spot spot);

    @Update
    void update(Spot spot);

    @Delete
    void delete(Spot spot);

    @Query("SELECT * FROM spot")
    LiveData<List<Spot>> getAllSpots();



}

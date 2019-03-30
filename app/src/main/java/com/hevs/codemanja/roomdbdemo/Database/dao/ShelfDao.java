package com.hevs.codemanja.roomdbdemo.Database.dao;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.hevs.codemanja.roomdbdemo.Database.entity.BookEntity;
import com.hevs.codemanja.roomdbdemo.Database.entity.ShelfEntity;

import java.util.List;

@Dao
public interface ShelfDao {


    @Query("SELECT * FROM shelfentity WHERE spotid = :id")
    LiveData<ShelfEntity> getById(String id);

    @Query("SELECT * FROM shelfentity")
    LiveData<List<ShelfEntity>> getAllSpots();



    @Query ("SELECT spotid FROM shelfentity WHERE category like :category")
    String[] getAllSpotIds(String category);


    @Query ("SELECT spotid FROM shelfentity WHERE category like :category")
    LiveData<List<ShelfEntity>> getCategorySpot(String category);

    @Insert
    void insert(ShelfEntity shelf);

    @Update
    void update(ShelfEntity shelf);

    @Delete
    void delete(ShelfEntity shelf);

    @Query("DELETE FROM shelfEntity")
    void deleteAll();


    @Query ("SELECT spotid FROM shelfentity WHERE category like :category")
    String[] getAllCategorySpots(String category);




}

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

    /*
    @Query("SELECT * FROM shelf WHERE spotId = :spotId")
    LiveData<Shelf> getBySpotId(String spotId);

    @Query("SELECT * FROM shelf")
    LiveData<List<Shelf>> getAll();

*/


    @Query("SELECT spotid FROM shelfEntity")
    LiveData<List<ShelfEntity>> getAllSpots();

    /*

    @Query('SELECT spotid FROM shelf WHERE spotid LIKE "spot%"')
    String[] getAllSpots(String spot);

@Query("SELECT * FROM hamster WHERE name LIKE :arg0")
fun loadHamsters(search: String?): Flowable<List<Hamster>>

 @Query("SELECT * FROM user WHERE first_name LIKE :first AND "
           + "last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);

*/

    @Query("SELECT spotid FROM shelfEntity WHERE spotid like :spot")
    String[] getAllSpotsList(String spot);

    @Query ("SELECT spotid FROM shelfEntity WHERE category like :category")
    String[] getCategorySpot(String category);

    @Insert
    void insert(ShelfEntity shelf);

    @Update
    void update(ShelfEntity shelf);

    @Delete
    void delete(ShelfEntity shelf);

    @Query("DELETE FROM shelfEntity")
    void deleteAll();






}
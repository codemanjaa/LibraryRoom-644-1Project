package com.hevs.codemanja.roomdbdemo.Database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.hevs.codemanja.roomdbdemo.Database.entity.BookEntity;

import java.util.List;

@Dao
public interface BookDao {

    @Insert
    void insert(BookEntity book);

    @Update
    void update(BookEntity book);

    @Delete
    void delete(BookEntity book);

    @Query("SELECT * FROM BookEntity")
    LiveData<List<BookEntity>> getAllBooks();


}

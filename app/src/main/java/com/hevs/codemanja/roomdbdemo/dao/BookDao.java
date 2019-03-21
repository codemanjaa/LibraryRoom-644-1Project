package com.hevs.codemanja.roomdbdemo.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.hevs.codemanja.roomdbdemo.entity.Book;

import java.util.List;

@Dao
public interface BookDao {

    @Insert
     void addBook(Book book);

    @Query("SELECT * FROM books")
     List<Book> getAllBooks();


    @Update
    void update(Book book);

}

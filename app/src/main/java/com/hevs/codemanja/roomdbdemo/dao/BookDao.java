package com.hevs.codemanja.roomdbdemo.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.hevs.codemanja.roomdbdemo.entity.Book;

@Dao
public interface BookDao {

    @Insert
    public void addBook(Book book);

}

package com.hevs.codemanja.roomdbdemo.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.hevs.codemanja.roomdbdemo.dao.BookDao;
import com.hevs.codemanja.roomdbdemo.entity.Book;

@Database(entities = {Book.class}, version = 1, exportSchema = false)

public abstract class LibraryDB extends RoomDatabase {

    public abstract BookDao bookDao();
}

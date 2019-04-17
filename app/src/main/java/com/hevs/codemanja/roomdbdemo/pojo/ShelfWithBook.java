package com.hevs.codemanja.roomdbdemo.pojo;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.hevs.codemanja.roomdbdemo.Database.entity.BookEntity;
import com.hevs.codemanja.roomdbdemo.Database.entity.ShelfEntity;

import java.util.List;

public class ShelfWithBook {


        public ShelfEntity spot;
        public List<BookEntity> books;


}

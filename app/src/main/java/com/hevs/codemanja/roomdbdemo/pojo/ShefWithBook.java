package com.hevs.codemanja.roomdbdemo.pojo;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.hevs.codemanja.roomdbdemo.Database.entity.BookEntity;
import com.hevs.codemanja.roomdbdemo.Database.entity.ShelfEntity;

import java.util.List;

public class ShefWithBook {

    @Embedded
    public ShelfEntity shelf;

    @Relation(parentColumn = "spotid", entityColumn = "f_spotid", entity = BookEntity.class)
    public List<BookEntity> book;

}

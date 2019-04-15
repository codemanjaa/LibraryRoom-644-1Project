package com.hevs.codemanja.roomdbdemo.Database.repo;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hevs.codemanja.roomdbdemo.Database.entity.Book;

public class BookRepository {

    private static BookRepository instance;

    public static BookRepository getInstance(){
        if(instance == null){
            synchronized (BookRepository.class){
                if(instance == null){
                    instance = new BookRepository();
                }
            }
        }
        return instance;
    }



}

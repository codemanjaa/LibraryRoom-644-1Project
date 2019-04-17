package com.hevs.codemanja.roomdbdemo.Database.repo;

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

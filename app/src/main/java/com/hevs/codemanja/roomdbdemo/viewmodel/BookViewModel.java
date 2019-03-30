package com.hevs.codemanja.roomdbdemo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.support.annotation.NonNull;

import com.hevs.codemanja.roomdbdemo.Database.entity.BookEntity;
import com.hevs.codemanja.roomdbdemo.Database.repo.BookRepo;


import java.util.List;

public class BookViewModel extends AndroidViewModel {

   private BookRepo repository;
   private LiveData<List<BookEntity>> allBooks;


    public BookViewModel(@NonNull Application application) {
        super(application);
        repository = new BookRepo(application);
        allBooks = repository.getAllBooks();
    }

    public void insert(BookEntity book) {
        repository.insert(book);
    }

    public void update(BookEntity book){
        repository.update(book);
    }

    public void deleteAllBooks(){
        repository.deleteAllBooks();
    }
    public void delete(BookEntity book){
        repository.delete(book);
    }

    public LiveData<List<BookEntity>> getAllBooks(){
        return allBooks;
    }



    }




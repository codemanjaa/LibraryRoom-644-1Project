package com.hevs.codemanja.roomdbdemo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.hevs.codemanja.roomdbdemo.Database.entity.BookEntity;
import com.hevs.codemanja.roomdbdemo.Database.repo.BookRepo;
import com.hevs.codemanja.roomdbdemo.util.OnAsyncEventListener;

import java.util.List;

public class BookViewModel extends AndroidViewModel {

   private BookRepo repository;
   private LiveData<List<BookEntity>> allBooks;
    private  static LiveData<DataSnapshot> liveData;

    public BookViewModel(@NonNull Application application) {
        super(application);
        repository = new BookRepo();
        liveData = repository.getDataSnapshotShelfLiveData();
       // allBooks = repository.getAllBooks();
    }

    public void insert(BookEntity book , OnAsyncEventListener callback ) {
        repository.insert(book, callback);
    }

    public void update(BookEntity book , OnAsyncEventListener callback){
        repository.update(book,callback);
    }

    public void deleteAllBooks(){
        //repository.deleteAllBooks();
    }
    public void delete(BookEntity book , OnAsyncEventListener callback){
        repository.delete(book, callback);
    }

    public LiveData<List<BookEntity>> getAllBooks(){
        return allBooks;
    }

    public LiveData<DataSnapshot> getDatasnapshotShelfLiveData(){
        return liveData;
    }


    }




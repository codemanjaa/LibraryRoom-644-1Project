package com.hevs.codemanja.roomdbdemo.Database.repo;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hevs.codemanja.roomdbdemo.Database.dao.BookDao;
import com.hevs.codemanja.roomdbdemo.Database.entity.BookEntity;
import com.hevs.codemanja.roomdbdemo.util.OnAsyncEventListener;

import java.util.List;

public class BookRepo {

    private LiveData<List<BookEntity>> allBooks;



    public void insert(final BookEntity book, final OnAsyncEventListener callback){

        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("spots")
                .child(book.getF_spotid())
                .child("book");
        String key = reference.push().getKey();
        FirebaseDatabase.getInstance()
                .getReference("spots")
                .child(book.getF_spotid())
                .child("book")
                .child(key)
                .setValue(book, (databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        callback.onFailure(databaseError.toException());
                    } else {
                        callback.onSuccess();
                    }
                });

    }

    public void update(final BookEntity book, final OnAsyncEventListener callback){
        FirebaseDatabase.getInstance()
                .getReference("spots")
                .child(book.getF_spotid())
                .child("book")
                .child(book.getBid())
                .updateChildren(book.toMap(), (databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        callback.onFailure(databaseError.toException());
                    } else {
                        callback.onSuccess();
                    }
                });

    }
    public void delete(final BookEntity book, final OnAsyncEventListener callback){

        FirebaseDatabase.getInstance()
                .getReference("spots")
                .child(book.getF_spotid())
                .child("book")
                .child(book.getBid())
                .removeValue((databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        callback.onFailure(databaseError.toException());
                    } else {
                        callback.onSuccess();
                    }
                });



    }

    public LiveData<List<BookEntity>> getAllBooks(){
         return allBooks;
    }


   /* public void deleteAllBooks(){
        new DeleteAllBooksAsyncTask(bookDao).execute();
   }
*/




 /*
    private static BookRepo instance;

    private BookRepo() {}

    public static BookRepo getInstance() {
        if (instance == null) {
            synchronized (BookRepo.class) {
                if (instance == null) {
                    instance = new BookRepo();
                }
            }
        }
        return instance;
    }*/

    /*
    public LiveData<Book> getClient(final String email, Context context) {
        return LibraryDB.getInstance(context).bookDao().getByTitle(title);
    }
    */

/**
    public LiveData<List<Book>> getAllBooks(Context context) {
        return (LiveData<List<Book>>) LibraryDB.getInstance(context).bookDao().getAllBooks();
    }**/
/*
    public void insert(final Book book, OnAsyncEventListener callback, Context context) {
        new CreateBook(context, callback).execute(book);
    }
*/
 /*   public void update(final Book book, OnAsyncEventListener callback, Context context) {
        new UpdateBook(context, callback).execute(book);
    }*/
/*
    public void delete(final Book book, OnAsyncEventListener callback, Context context) {
        new DeleteBook(context, callback).execute(book);
    }
*/
}

package com.hevs.codemanja.roomdbdemo.Database.repo;


import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.hevs.codemanja.roomdbdemo.Database.LibraryDB;
import com.hevs.codemanja.roomdbdemo.async.CreateBook;
import com.hevs.codemanja.roomdbdemo.async.UpdateBook;
import com.hevs.codemanja.roomdbdemo.entity.Book;
import com.hevs.codemanja.roomdbdemo.util.OnAsyncEventListener;

import java.util.List;

public class BookRepo {


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
    }




    public LiveData<List<Book>> getAllBooksLive(Context context) {

        return LibraryDB.getInstance(context).bookDao().getAllBookLive();
    }


    public LiveData<Book> getBook(final String title, Context context) {
        return LibraryDB.getInstance(context).bookDao().getByTitle(title);


    }



    public void insert(final Book book, OnAsyncEventListener callback, Context context) {
        new CreateBook(context, callback).execute(book);
    }

    public void update(final Book book, OnAsyncEventListener callback, Context context) {
        new UpdateBook(context, callback).execute(book);
    }

    public void delete(final Book book, OnAsyncEventListener callback, Context context) {
//        new DeleteBook(context, callback).execute(book);
    }

}

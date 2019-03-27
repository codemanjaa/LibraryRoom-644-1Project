package com.hevs.codemanja.roomdbdemo.async;

import android.content.Context;
import android.os.AsyncTask;

import com.hevs.codemanja.roomdbdemo.Database.LibraryDB;
import com.hevs.codemanja.roomdbdemo.entity.Book;
import com.hevs.codemanja.roomdbdemo.util.OnAsyncEventListener;


public class CreateBook extends AsyncTask<Book, Void, Void> {

    private LibraryDB database;
    private OnAsyncEventListener callback;
    private Exception exception;

    public CreateBook(Context context, OnAsyncEventListener callback) {
        database = LibraryDB.getInstance(context);
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(Book... params) {
        try {
            for (Book book : params)
                database.bookDao().addBook(book);
        } catch (Exception e) {
            exception = e;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (callback != null) {
            if (exception == null) {
                callback.onSuccess();
            } else {
                callback.onFailure(exception);
            }
        }
    }
}

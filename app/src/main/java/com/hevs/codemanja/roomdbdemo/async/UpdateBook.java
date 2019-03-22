package com.hevs.codemanja.roomdbdemo.async;

import android.content.Context;
import android.os.AsyncTask;

import com.hevs.codemanja.roomdbdemo.Database.LibraryDB;
import com.hevs.codemanja.roomdbdemo.entity.Book;
import com.hevs.codemanja.roomdbdemo.util.OnAsyncEventListener;

public class UpdateBook extends AsyncTask<Book, Void, Void> {

    private LibraryDB libraryDB;
    private OnAsyncEventListener callback;
    private Exception exception;



    public UpdateBook(Context context, OnAsyncEventListener callback){

        libraryDB = LibraryDB.getInstance(context);
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(Book... books) {
        try{
            for (Book book: books)


                libraryDB.bookDao().update(book);

            } catch(Exception e){
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

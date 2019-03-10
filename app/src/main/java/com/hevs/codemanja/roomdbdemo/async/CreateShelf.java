package com.hevs.codemanja.roomdbdemo.async;

import android.content.Context;
import android.os.AsyncTask;

import com.hevs.codemanja.roomdbdemo.Database.LibraryDB;
import com.hevs.codemanja.roomdbdemo.entity.Shelf;
import com.hevs.codemanja.roomdbdemo.util.OnAsyncEventListener;

public class CreateShelf extends AsyncTask<Shelf, Void, Void> {


    private LibraryDB database;
    private OnAsyncEventListener callback;
    private Exception exception;



    public CreateShelf(Context context, OnAsyncEventListener callback){
        database = LibraryDB.getInstance(context);
        this.callback = callback;
    }


    @Override
    protected Void doInBackground(Shelf... shelves) {
        try {

            for(Shelf shelf : shelves)
                database.shelfDao().insert(shelf);
        } catch (Exception e){
            exception = e;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {

        if(callback != null){
            if(exception == null){
                callback.onSuccess();
            } else {
                callback.onFailure(exception);
            }
        }
       // super.onPostExecute(aVoid);






    }




}








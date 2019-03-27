package com.hevs.codemanja.roomdbdemo.async;

import android.content.Context;
import android.os.AsyncTask;

import com.hevs.codemanja.roomdbdemo.Database.LibraryDB;
import com.hevs.codemanja.roomdbdemo.entity.ClientEntity;
import com.hevs.codemanja.roomdbdemo.util.OnAsyncEventListener;

public class UpdateClient extends AsyncTask<ClientEntity, Void, Void> {

    private LibraryDB database;
    private OnAsyncEventListener callback;
    private Exception exception;

    public UpdateClient(Context context, OnAsyncEventListener callback) {
        database = LibraryDB.getInstance(context);
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(ClientEntity... params) {
        try {
            for (ClientEntity client : params)
                database.clientDao().update(client);
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
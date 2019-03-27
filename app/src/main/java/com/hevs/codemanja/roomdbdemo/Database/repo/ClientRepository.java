package com.hevs.codemanja.roomdbdemo.Database.repo;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.hevs.codemanja.roomdbdemo.Database.LibraryDB;
import com.hevs.codemanja.roomdbdemo.async.CreateClient;
import com.hevs.codemanja.roomdbdemo.async.DeleteClient;
import com.hevs.codemanja.roomdbdemo.async.UpdateClient;
import com.hevs.codemanja.roomdbdemo.entity.ClientEntity;
import com.hevs.codemanja.roomdbdemo.util.OnAsyncEventListener;

import java.util.List;




public class ClientRepository {

    private static ClientRepository instance;

    private ClientRepository() {}

    public static ClientRepository getInstance() {
        if (instance == null) {
            synchronized (ClientRepository.class) {
                if (instance == null) {
                    instance = new ClientRepository();
                }
            }
        }
        return instance;
    }

    public LiveData<ClientEntity> getClient(final String email, Context context) {
        return LibraryDB.getInstance(context).clientDao().getByEmail(email);
    }

    public LiveData<List<ClientEntity>> getAllClients(Context context) {
        return LibraryDB.getInstance(context).clientDao().getAll();
    }

    public void insert(final ClientEntity client, OnAsyncEventListener callback, Context context) {
        new CreateClient(context, callback).execute(client);
    }

    public void update(final ClientEntity client, OnAsyncEventListener callback, Context context) {
        new UpdateClient(context, callback).execute(client);
    }

    public void delete(final ClientEntity client, OnAsyncEventListener callback, Context context) {
        new DeleteClient(context, callback).execute(client);
    }
}

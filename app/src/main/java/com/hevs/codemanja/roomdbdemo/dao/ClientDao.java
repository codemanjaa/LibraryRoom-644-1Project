package com.hevs.codemanja.roomdbdemo.dao;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.sqlite.SQLiteConstraintException;

import com.hevs.codemanja.roomdbdemo.entity.ClientEntity;

import java.util.List;




@Dao
public interface ClientDao {

    @Query("SELECT * FROM clients WHERE email = :email")
    LiveData<ClientEntity> getByEmail(String email);

    @Query("SELECT * FROM clients")
    LiveData<List<ClientEntity>> getAll();

    @Insert
    void insert(ClientEntity client) throws SQLiteConstraintException;

    @Update
    void update(ClientEntity client);

    @Delete
    void delete(ClientEntity client);

    @Query("DELETE FROM clients")
    void deleteAll();
}

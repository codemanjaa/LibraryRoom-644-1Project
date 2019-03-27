package com.hevs.codemanja.roomdbdemo.Database;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import android.support.annotation.NonNull;
import android.util.Log;

import com.hevs.codemanja.roomdbdemo.dao.BookDao;
import com.hevs.codemanja.roomdbdemo.dao.ClientDao;
import com.hevs.codemanja.roomdbdemo.dao.ShelfDao;
import com.hevs.codemanja.roomdbdemo.entity.Book;
import com.hevs.codemanja.roomdbdemo.entity.ClientEntity;
import com.hevs.codemanja.roomdbdemo.entity.Shelf;

import java.util.concurrent.Executors;



import static android.content.ContentValues.TAG;

@Database(entities = {Book.class, Shelf.class, ClientEntity.class}, version = 1, exportSchema = false)

public abstract class LibraryDB extends RoomDatabase {

    public abstract BookDao bookDao();
    public abstract ShelfDao shelfDao();
    public abstract ClientDao clientDao();

    private static LibraryDB instance;
    private static final String DATABASE_NAME = "library-database";

    private final MutableLiveData<Boolean> isDatabaseCreated = new MutableLiveData<>();

    public static LibraryDB getInstance(final Context context){
        if(instance == null){
            synchronized ( (LibraryDB.class)){
                if(instance == null){
                    instance = buildDatabase(context.getApplicationContext());
                    instance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    private static LibraryDB buildDatabase(final Context appContext) {
        Log.i(TAG, "Database will be initialized.");
        return Room.databaseBuilder(appContext, LibraryDB.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadExecutor().execute(() -> {
                            LibraryDB database = LibraryDB.getInstance(appContext);
                            DatabaseInitializer.populateDatabase(database);
                            // notify that the database was created and it's ready to be used
                            database.setDatabaseCreated();
                        });
                    }
                }).build();
    }

    /**
     * Check whether the database already exists and expose it via {@link #getDatabaseCreated()}
     */
    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            Log.i(TAG, "Database initialized.");
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated(){
        isDatabaseCreated.postValue(true);
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return isDatabaseCreated;
    }

}

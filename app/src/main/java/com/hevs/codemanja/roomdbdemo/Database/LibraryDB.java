package com.hevs.codemanja.roomdbdemo.Database;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.util.Log;

import com.hevs.codemanja.roomdbdemo.Database.dao.BookDao;
import com.hevs.codemanja.roomdbdemo.Database.dao.ShelfDao;
import com.hevs.codemanja.roomdbdemo.Database.dao.SpotDao;
import com.hevs.codemanja.roomdbdemo.Database.entity.BookEntity;
import com.hevs.codemanja.roomdbdemo.Database.entity.ShelfEntity;
import com.hevs.codemanja.roomdbdemo.Database.entity.Spot;

import java.util.concurrent.Executors;

import static android.content.ContentValues.TAG;
//, ShelfEntity.class
@Database(entities = {BookEntity.class, ShelfEntity.class, Spot.class},  version = 2, exportSchema = false)

public abstract class LibraryDB extends RoomDatabase {


    private static LibraryDB instance;


    public abstract BookDao bookDao();
    public abstract ShelfDao shelfDao();
    public abstract SpotDao spotDao();

    private static final String DATABASE_NAME = "library-database";

    private final MutableLiveData<Boolean> isDatabaseCreated = new MutableLiveData<>();

    public static synchronized LibraryDB getInstance(final Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    LibraryDB.class,"LibraryDB")
                    //following method avoid the crash of the app if version is changed
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
            new PopulatespotDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private BookDao bookDao;
        private  ShelfDao shelfDao;
       // private SpotDao spotDao;


        private PopulateDbAsyncTask(LibraryDB db){
            bookDao = db.bookDao();
            shelfDao = db.shelfDao();
            //spotDao = db.spotDao();

        }



        @Override
        protected Void doInBackground(Void... voids) {
           //  bookDao.insert(new BookEntity("Android","Arts","A-1-1"));
            // bookDao.insert(new BookEntity("Androide","arts","spot3"));
            //bookDao.insert(new BookEntity("Androidffrrrrrrr","arts","spot2"));

           // shelfDao.insert(new ShelfEntity("A-1-2","Android","VA"));
            return null;
        }


    }

    private static class PopulatespotDbAsyncTask extends AsyncTask<Void,Void,Void>{

        private ShelfDao shelfDao;
        private PopulatespotDbAsyncTask(LibraryDB db){
            shelfDao = db.shelfDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //shelfDao.insert(new ShelfEntity("A-1-1","Android", "Arts"));
            return null;
        }
    }



/**
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
    /**  private void updateDatabaseCreated(final Context context) {
     if (context.getDatabasePath(DATABASE_NAME).exists()) {
     Log.i(TAG, "Database initialized.");
     setDatabaseCreated();
     }
     }

     private void setDatabaseCreated()
     isDatabaseCreated.postValue(true);


     public LiveData<Boolean> getDatabaseCreated() {
     return isDatabaseCreated;
     } **/

}

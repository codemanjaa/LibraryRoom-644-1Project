package com.hevs.codemanja.roomdbdemo.Database.repo;

import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.hevs.codemanja.roomdbdemo.Database.dao.ShelfDao;
import com.hevs.codemanja.roomdbdemo.Database.entity.ShelfEntity;

@Database(entities = {ShelfEntity.class}, version = 1)

public abstract class ShelfDB extends RoomDatabase {

    private static ShelfDB instance;
    public abstract ShelfDao shelfDao();

    private static final String DATABASE_NAME = "shelf-database";

    private final MutableLiveData<Boolean> isDatabaseCreated = new MutableLiveData<>();

    public static synchronized ShelfDB getInstance(final Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ShelfDB.class,"LibraryDB")
                    //following method avoid the crash of the app if version is changed
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

  public static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){

      @Override
      public void onCreate(@NonNull SupportSQLiteDatabase db) {
          super.onCreate(db);
          new PopulateDbAsyncTask(instance).execute();
      }
  };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{

        private ShelfDao shelfDao;

        private PopulateDbAsyncTask(ShelfDB db){
            shelfDao = db.shelfDao();
            shelfDao = db.shelfDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            shelfDao.insert(new ShelfEntity("ffffff","ffffffe","agdgag"));

            return null;
        }


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

     private void setDatabaseCreated(){
     isDatabaseCreated.postValue(true);
     }

     public LiveData<Boolean> getDatabaseCreated() {
     return isDatabaseCreated;
     } **/



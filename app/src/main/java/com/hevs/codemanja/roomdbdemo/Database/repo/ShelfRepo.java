package com.hevs.codemanja.roomdbdemo.Database.repo;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import com.hevs.codemanja.roomdbdemo.Database.LibraryDB;
import com.hevs.codemanja.roomdbdemo.Database.dao.BookDao;
import com.hevs.codemanja.roomdbdemo.Database.dao.ShelfDao;
import com.hevs.codemanja.roomdbdemo.Database.entity.BookEntity;
import com.hevs.codemanja.roomdbdemo.Database.entity.ShelfEntity;
import com.hevs.codemanja.roomdbdemo.util.*;

import java.util.List;


public class ShelfRepo {

    private ShelfDao shelfDao;
    private LiveData<List<ShelfEntity>> allShelf;
    String category;


    public ShelfRepo(Application application){
        LibraryDB databaseshelf = LibraryDB.getInstance(application);
        shelfDao = databaseshelf.shelfDao();
        allShelf =   shelfDao.getAllSpots(); //shelfDao.getCategorySpot(category);
    }

    public void insert(ShelfEntity shelf){
        new ShelfRepo.InsertShelfAsyncTask(shelfDao).execute(shelf);
    }

    public void update(ShelfEntity  shelf){
        new ShelfRepo.UpdateShelfAsyncTask(shelfDao).execute(shelf);

    }
    public void delete(ShelfEntity  shelf){
        new ShelfRepo.DeleteShelfAsyncTask(shelfDao).execute(shelf);
    }
    public LiveData<List<ShelfEntity>> getAllSpots(){
        return allShelf;
    }
   // test
    public LiveData<List<ShelfEntity>> getCategorySpot(String category){

        return allShelf;
    }


    private static class InsertShelfAsyncTask extends AsyncTask<ShelfEntity,Void,Void> {
        private ShelfDao shelfDao;

        private InsertShelfAsyncTask(ShelfDao shelfDao){
            this.shelfDao = shelfDao;
        }
        @Override
        protected Void doInBackground(ShelfEntity... shelfEntities) {
            shelfDao.insert(shelfEntities[0]);
            return null;
        }
    }

    private static class UpdateShelfAsyncTask extends AsyncTask<ShelfEntity,Void,Void>{
        private ShelfDao shelfDao;

        private UpdateShelfAsyncTask(ShelfDao shelfDao){
            this.shelfDao = shelfDao;
        }
        @Override
        protected Void doInBackground(ShelfEntity... shelfEntities) {
           shelfDao.update(shelfEntities[0]);
            return null;
        }
    }

    private static class DeleteShelfAsyncTask extends AsyncTask<ShelfEntity,Void,Void>{
        private ShelfDao shelfDao;


        private DeleteShelfAsyncTask(ShelfDao shelfDao){
            this.shelfDao = shelfDao;
        }
        @Override
        protected Void doInBackground(ShelfEntity... shelfEntities) {
            shelfDao.delete(shelfEntities[0]);
            return null;
        }
    }

/*
    private static ShelfRepo instance;

    private ShelfRepo(){

    }

    public static ShelfRepo getInstance(){
        if(instance == null){
            synchronized (ShelfRepo.class){
                if(instance == null){
                    instance = new ShelfRepo();
                }
            }
        }
        return instance;
    }*/

    /*
    public LiveData<Shelf> getShelf(final String spotId, Context context){
        return LibraryDB.getInstance(context).shelfDao().getBySpotId(spotId);
    }

    public LiveData<List<Shelf>> getAllShlf(Context context){
        return LibraryDB.getInstance(context).shelfDao().getAll();
    }
*/










}

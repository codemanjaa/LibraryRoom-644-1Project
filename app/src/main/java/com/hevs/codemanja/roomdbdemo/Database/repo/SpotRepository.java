package com.hevs.codemanja.roomdbdemo.Database.repo;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.hevs.codemanja.roomdbdemo.Database.LibraryDB;
import com.hevs.codemanja.roomdbdemo.Database.dao.SpotDao;
import com.hevs.codemanja.roomdbdemo.Database.entity.Spot;

import java.util.List;

public class SpotRepository {

    private SpotDao spotDao;
    private LiveData<List<Spot>> allSpots;


    public SpotRepository(Application application){
        LibraryDB database = LibraryDB.getInstance(application);
        spotDao= database.spotDao();
        allSpots = spotDao.getAllSpots();
    }

    public void insert(Spot spot){

        new InsertSpotAsyncTask(spotDao).execute(spot);
    }

    public void update(Spot spot){

        new UpdateSpotAsyncTask(spotDao).execute(spot);
    }


    public void delete(Spot spot){
        new DeleteSpotAsyncTask(spotDao).execute(spot);
    }

    public LiveData<List<Spot>> getAllSpots(){

        return allSpots;

    }

    private static class InsertSpotAsyncTask extends AsyncTask<Spot, Void, Void>{
        private SpotDao spotDao;

        private InsertSpotAsyncTask(SpotDao spotDao){
            this.spotDao = spotDao;
        }


        @Override
        protected Void doInBackground(Spot... spots) {

            spotDao.insert(spots[0]);
            return null;
        }
    }



    private static class UpdateSpotAsyncTask extends AsyncTask<Spot, Void, Void>{
        private SpotDao spotDao;

        private  UpdateSpotAsyncTask(SpotDao spotDao){
            this.spotDao = spotDao;
        }


        @Override
        protected Void doInBackground(Spot... spots) {

            spotDao.update(spots[0]);
            return null;
        }
    }


    private static class DeleteSpotAsyncTask extends AsyncTask<Spot, Void, Void>{
        private SpotDao spotDao;

        private DeleteSpotAsyncTask(SpotDao spotDao){
            this.spotDao = spotDao;
        }


        @Override
        protected Void doInBackground(Spot... spots) {

            spotDao.delete(spots[0]);
            return null;
        }
    }

}

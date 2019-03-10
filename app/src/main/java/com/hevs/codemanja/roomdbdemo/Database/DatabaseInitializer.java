package com.hevs.codemanja.roomdbdemo.Database;

import android.os.AsyncTask;
import android.util.Log;

import com.hevs.codemanja.roomdbdemo.entity.Shelf;





public class DatabaseInitializer {

    public static final String TAG = "DatabaseInitializer";

    public static void populateDatabase(final LibraryDB db) {
        Log.i(TAG, "Inserting demo data.");
        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }

    private static void addShelf(final LibraryDB db, final String spotId, final String desc
    ) {
        Shelf shelf = new Shelf(spotId, desc);
        db.shelfDao().insert(shelf);
    }

    private static void populateWithTestData(LibraryDB db) {
        db.shelfDao().deleteAll();

        addShelf(db, "IT-A-1", "Computer Section shelf A @ spot number 1");
        addShelf(db, "IT-A-2", "Computer Section shelf A @ spot number 2");
        addShelf(db, "BU-B-1", "Business Section shelf B @ spot number 1");
        addShelf(db, "BU-B-2", "Business Section shelf B @ spot number 2");
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final LibraryDB database;

        PopulateDbAsync(LibraryDB db) {
            database = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateWithTestData(database);
            return null;
        }

    }
}

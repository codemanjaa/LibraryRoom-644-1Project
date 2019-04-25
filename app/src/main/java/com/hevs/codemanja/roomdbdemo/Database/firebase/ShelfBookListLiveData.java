package com.hevs.codemanja.roomdbdemo.Database.firebase;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.hevs.codemanja.roomdbdemo.Database.entity.BookEntity;
import com.hevs.codemanja.roomdbdemo.Database.entity.ShelfEntity;
import com.hevs.codemanja.roomdbdemo.pojo.ShelfWithBook;

import java.util.ArrayList;
import java.util.List;

public class ShelfBookListLiveData extends LiveData<List<ShelfWithBook>> {

    private static final String TAG = "ShelfBookLiveData";

    private final DatabaseReference reference;
    private final String f_spotid;
    private final ShelfBookListLiveData.MyValueEventListener listener =
            new ShelfBookListLiveData.MyValueEventListener();

    public ShelfBookListLiveData(DatabaseReference ref, String f_spotid) {
        reference = ref;
        this.f_spotid = f_spotid;
    }

    @Override
    protected void onActive() {
        Log.d(TAG, "onActive");
        reference.addValueEventListener(listener);
    }

    @Override
    protected void onInactive() {
        Log.d(TAG, "onInactive");
    }

    private class MyValueEventListener implements ValueEventListener {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            setValue(toShelfWithBookList(dataSnapshot));
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.e(TAG, "Can't listen to query " + reference, databaseError.toException());
        }
    }

    private List<ShelfWithBook> toShelfWithBookList(DataSnapshot snapshot) {
        List<ShelfWithBook> shelfWithBookList = new ArrayList<>();
        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
            if (!childSnapshot.getKey().equals(f_spotid)) {
                ShelfWithBook shelfWithBook = new ShelfWithBook();
                shelfWithBook.spot = childSnapshot.getValue(ShelfEntity.class);
                shelfWithBook.spot.setSpotid(childSnapshot.getKey());
                shelfWithBook.books = toAccounts(childSnapshot.child("books"),
                        childSnapshot.getKey());
                shelfWithBookList.add(shelfWithBook);
            }
        }
        return shelfWithBookList;
    }

    private List<BookEntity> toAccounts(DataSnapshot snapshot, String F_spotId) {
        List<BookEntity> books = new ArrayList<>();
        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
            BookEntity entity = childSnapshot.getValue(BookEntity.class);
            //.setBid(childSnapshot.getKey());
            entity.setF_spotid(f_spotid);
            books.add(entity);
        }
        return books;
    }





}

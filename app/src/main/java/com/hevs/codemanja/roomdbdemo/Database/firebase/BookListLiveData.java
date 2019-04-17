package com.hevs.codemanja.roomdbdemo.Database.firebase;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.hevs.codemanja.roomdbdemo.Database.entity.BookEntity;

import java.util.ArrayList;
import java.util.List;

public class BookListLiveData extends LiveData<List<BookEntity>> {

    private static final String TAG = "BookListLiveData";

    private final DatabaseReference reference;
    private final String  f_spotid;
    private final MyValueEventListener listener = new MyValueEventListener();

    public BookListLiveData(DatabaseReference ref, String  f_spotid) {
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
            setValue(toBooks(dataSnapshot));
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.e(TAG, "Can't listen to query " + reference, databaseError.toException());
        }
    }

    private List<BookEntity> toBooks(DataSnapshot snapshot) {
        List<BookEntity> books = new ArrayList<>();
        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
            BookEntity entity = childSnapshot.getValue(BookEntity.class);
            entity.setBid(childSnapshot.getKey());
            entity.setF_spotid(f_spotid);
            books.add(entity);
        }
        return books;
    }






}

package com.hevs.codemanja.roomdbdemo.Database.firebase;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.hevs.codemanja.roomdbdemo.Database.entity.Book;

public class BookLiveData extends LiveData<Book> {

    private static final String TAG = "BookLiveData";

    private final DatabaseReference reference;
    private final String spot;
    private final BookLiveData.MyValueEventListener listener = new BookLiveData.MyValueEventListener();

    public BookLiveData(DatabaseReference ref) {
        reference = ref;
        spot = ref.getParent().getParent().getKey();
    }


    protected void onActive() {
        Log.d(TAG, "onActive");
        reference.addValueEventListener(listener);
    }


    protected void onInactive() {
        Log.d(TAG, "onInactive");
    }

    private class MyValueEventListener implements ValueEventListener {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            Book entity = dataSnapshot.getValue(Book.class);
            entity.setId(dataSnapshot.getKey());
            entity.setSpot(spot);
            setValue(entity);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.e(TAG, "Can't listen to query " + reference, databaseError.toException());
        }
    }
}

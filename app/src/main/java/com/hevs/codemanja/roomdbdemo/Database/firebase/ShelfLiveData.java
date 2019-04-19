package com.hevs.codemanja.roomdbdemo.Database.firebase;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.hevs.codemanja.roomdbdemo.Database.entity.ShelfEntity;

public class ShelfLiveData extends LiveData<ShelfEntity> {

    private static final String TAG = "ShelfLiveData";

    private final DatabaseReference reference;
    private final ShelfLiveData.MyValueEventListener listener = new ShelfLiveData.MyValueEventListener();

    public ShelfLiveData(DatabaseReference ref) {
        this.reference = ref;
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
            ShelfEntity entity = dataSnapshot.getValue(ShelfEntity.class);
            entity.setSpotid(dataSnapshot.getKey());
            setValue(entity);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.e(TAG, "Can't listen to query " + reference, databaseError.toException());
        }
    }














}

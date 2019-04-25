package com.hevs.codemanja.roomdbdemo.Database.repo;

import android.arch.lifecycle.LiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hevs.codemanja.roomdbdemo.Database.entity.ShelfEntity;
import com.hevs.codemanja.roomdbdemo.Database.firebase.FirebaseQueryLiveData;
import com.hevs.codemanja.roomdbdemo.Database.firebase.ShelfBookListLiveData;
import com.hevs.codemanja.roomdbdemo.Database.firebase.ShelfLiveData;
import com.hevs.codemanja.roomdbdemo.util.OnAsyncEventListener;

import java.util.List;


public class ShelfRepo {

    private LiveData<List<ShelfEntity>> allShelf;

    private static final DatabaseReference SPOT_REF = FirebaseDatabase.getInstance().getReference("spots");
    private final FirebaseQueryLiveData liveData = new FirebaseQueryLiveData(SPOT_REF);


    public LiveData<ShelfEntity> getSpot(final String spotId) {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("spots")
                .child(spotId);
        return new ShelfLiveData(reference);
    }

    /*
    public LiveData<List<ClientWithAccounts>> getOtherClientsWithAccounts(final String owner) {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("clients");
        return new ClientAccountsListLiveData(reference, owner);
    }
*/

    public void insert(final ShelfEntity shelf, final OnAsyncEventListener callback){

        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("spots");
        String id = reference.child("spots").push().getKey();

        FirebaseDatabase.getInstance()
                .getReference("spots")
                .child(id)
                .setValue(shelf, (databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        callback.onFailure(databaseError.toException());
                    } else {
                        callback.onSuccess();
                    }
                });

    }

    public void update(final ShelfEntity shelf, final OnAsyncEventListener callback){

        FirebaseDatabase.getInstance()
                .getReference("spots")
                .child(shelf.getSpotid())
                .updateChildren(shelf.toMap(), (databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        callback.onFailure(databaseError.toException());
                    } else {
                        callback.onSuccess();
                    }
                });

    }
    public void delete(final ShelfEntity shelf, final OnAsyncEventListener callback){


        FirebaseDatabase.getInstance()
                .getReference("spots")
                .child(shelf.getSpotid())
                .removeValue((databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        callback.onFailure(databaseError.toException());
                    } else {
                        callback.onSuccess();
                    }
                });



    }




    public ShelfLiveData getShelfSpots(){
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("spots");
        return new ShelfLiveData(reference);

    }

    public ShelfBookListLiveData getAllSpots(){

        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("spots");

    String spotId = reference.child("spots").getKey();
        return new ShelfBookListLiveData(reference, spotId);
    }



   // test
    public LiveData<List<ShelfEntity>> getCategorySpot(String category){

        return allShelf;
    }


    public LiveData<DataSnapshot> getDataSnapshotShelfLiveData(){
        return liveData;
    }



}

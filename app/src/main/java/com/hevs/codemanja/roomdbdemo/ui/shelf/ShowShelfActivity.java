package com.hevs.codemanja.roomdbdemo.ui.shelf;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.hevs.codemanja.roomdbdemo.Adapter.ShelfAdapter;
import com.hevs.codemanja.roomdbdemo.Database.entity.ShelfEntity;
import com.hevs.codemanja.roomdbdemo.R;
import com.hevs.codemanja.roomdbdemo.ui.Book.AddBookActivity;
import com.hevs.codemanja.roomdbdemo.util.OnAsyncEventListener;
import com.hevs.codemanja.roomdbdemo.viewmodel.ShelfViewModel;

import java.util.ArrayList;
import java.util.List;


public class ShowShelfActivity extends AppCompatActivity {
    public static final int ADD_NOTE_REQUEST = 1;
    public static final int EDIT_NOTE_REQUEST = 2;
    private ShelfViewModel shelfViewModel;
    //Button buttonEdit;
    Button buttonUpdate;
    Button buttonDelete;


    static List<ShelfEntity> shelfList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelf_show);


       // my_child_toolbar is defined in the layout file
        Toolbar toolbar =
                (Toolbar) findViewById(R.id.storage_toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

// Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

// Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        this.setTitle(R.string.shelfliste);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final ShelfAdapter adapter = new ShelfAdapter();
        recyclerView.setAdapter(adapter);



        shelfViewModel = ViewModelProviders.of(this).get(ShelfViewModel.class);

        LiveData<DataSnapshot> liveData = shelfViewModel.getDatasnapshotShelfLiveData();

        System.out.println("This is a live data "+liveData);


        liveData.observe(this, new Observer<DataSnapshot>() {
            @Override
            public void onChanged(@Nullable DataSnapshot dataSnapshot) {
                shelfList = new ArrayList<ShelfEntity>();
                if(dataSnapshot != null) {

                    System.out.println(" Yes........."+dataSnapshot);

                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                        ShelfEntity shelf = childSnapshot.getValue(ShelfEntity.class);
                        shelf.setSpotid(childSnapshot.getKey());
                        System.out.println(shelf.getSpotid());
                        System.out.println(shelf.getDesc());
                        System.out.println(shelf.getCategory());
                        shelfList.add(shelf);
                    }

                    adapter.submitList(shelfList);

                        System.out.println("Object Found");
                        String desc = dataSnapshot.child("desc").getValue(String.class);
                        String category = dataSnapshot.child("category").getValue(String.class);

                        System.out.println("Test begins...");
                        System.out.println(desc);

                }
            }
        });


       /* need to stay
        shelfViewModel.getAllSpots().observe(this, new Observer<List<ShelfEntity>>() {
            @Override
            public void onChanged(@Nullable List<ShelfEntity> bookEntities) {
                // update Recyclerview
               adapter.submitList(bookEntities);
            }
        });
*/
        // deleting an item
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                shelfViewModel.delete(adapter.getBook(viewHolder.getAdapterPosition()), new OnAsyncEventListener() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onFailure(Exception e) {

                    }
                });
                Toast.makeText(ShowShelfActivity.this,"Shelf deleted",Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        //edit shelf
        adapter.setOnItemClickListner(new ShelfAdapter.OnItemClickListner() {
            @Override
            public void onItemClick(ShelfEntity entity) {


                Intent intent = new Intent(ShowShelfActivity.this, AddShelfActivity.class);

                intent.putExtra(AddShelfActivity.EXTRA_SPOTID,entity.getSpotid());
                intent.putExtra(AddShelfActivity.EXTRA_DESC,entity.getDesc());
                intent.putExtra(AddShelfActivity.EXTRA_CATEGORY,entity.getCategory());
                startActivityForResult(intent, EDIT_NOTE_REQUEST);
            }
        });

        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                  //      .setAction("Action", null).show();

                // From the float button start the Addbook activity
               // startActivity(new Intent(ShowBookActivity.this, AddBookActivity.class));

                Intent intent = new Intent(ShowShelfActivity.this,AddShelfActivity.class);
                startActivityForResult(intent,ADD_NOTE_REQUEST);

            }
        });


        buttonUpdate = findViewById(R.id.buttonEdit);
        buttonDelete = findViewById(R.id.buttonDelete);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK){
            String desc = data.getStringExtra(AddShelfActivity.EXTRA_DESC);
            String category = data.getStringExtra(AddShelfActivity.EXTRA_CATEGORY);
            String spotId = data.getStringExtra(AddShelfActivity.EXTRA_SPOTID);

            ShelfEntity shelfEntity = new ShelfEntity();
            shelfEntity.setDesc(desc);
            shelfEntity.setCategory(category);
            shelfEntity.setSpotid(spotId);
            shelfViewModel = ViewModelProviders.of(this).get(ShelfViewModel.class);
            shelfViewModel.insert(shelfEntity, new OnAsyncEventListener() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onFailure(Exception e) {

                }
            });

            Toast.makeText(this,"Shelf Saved", Toast.LENGTH_SHORT);

        }else if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {
             int id = data.getIntExtra(AddBookActivity.EXTRA_ID,-1);

             if(id == -1){
                 Toast.makeText(this,"Book is not updated", Toast.LENGTH_SHORT);
                 return;
             }
            String desc = data.getStringExtra(AddShelfActivity.EXTRA_DESC);
            String category = data.getStringExtra(AddShelfActivity.EXTRA_CATEGORY);
            String spotId = data.getStringExtra(AddShelfActivity.EXTRA_SPOTID);

            System.out.println(""+spotId);


            ShelfEntity shelfEntity = new ShelfEntity(spotId, desc, category);

            shelfViewModel.update(shelfEntity, new OnAsyncEventListener() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onFailure(Exception e) {

                }
            });
        }else {
            Toast.makeText(this,"Book not Saved", Toast.LENGTH_SHORT);
        }
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data)  {
        super.onActivityReenter(resultCode, data);
    }
}

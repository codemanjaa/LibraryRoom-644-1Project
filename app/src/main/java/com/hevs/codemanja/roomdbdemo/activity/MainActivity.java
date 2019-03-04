package com.hevs.codemanja.roomdbdemo.activity;
import android.arch.persistence.room.Room;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hevs.codemanja.roomdbdemo.Database.LibraryDB;
import com.hevs.codemanja.roomdbdemo.R;
import com.hevs.codemanja.roomdbdemo.fragments.Welcome_Fragment;

public class MainActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;

    // Database operation reference

    public static LibraryDB libraryDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        libraryDB = Room.databaseBuilder(getApplicationContext(),LibraryDB.class, "librarydb")
                .allowMainThreadQueries()
                .build();


        if(findViewById(R.id.fragment_container) != null){
            if(savedInstanceState != null){
                return;
            }

            fragmentManager.beginTransaction().add(R.id.fragment_container, new Welcome_Fragment()).commit();
        }

    }
}

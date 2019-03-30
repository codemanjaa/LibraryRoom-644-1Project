package com.hevs.codemanja.roomdbdemo.ui.Transaction;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.hevs.codemanja.roomdbdemo.Database.LibraryDB;
import com.hevs.codemanja.roomdbdemo.Database.entity.BookEntity;
import com.hevs.codemanja.roomdbdemo.R;
import com.hevs.codemanja.roomdbdemo.fragments.About;
import com.hevs.codemanja.roomdbdemo.ui.Transaction.Welcome_Fragment;
import com.hevs.codemanja.roomdbdemo.ui.Book.AddBookActivity;
import com.hevs.codemanja.roomdbdemo.ui.Book.ShowBookActivity;
import com.hevs.codemanja.roomdbdemo.ui.shelf.AddShelfActivity;
import com.hevs.codemanja.roomdbdemo.ui.shelf.ShowShelfActivity;
import com.hevs.codemanja.roomdbdemo.viewmodel.BookViewModel;
import com.hevs.codemanja.roomdbdemo.viewmodel.ShelfViewModel;

import org.apache.log4j.chainsaw.Main;

import java.io.File;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, About.OnFragmentInteractionListener {

    private BookViewModel bookViewModel;
    private ShelfViewModel shelfViewModel;
    String currentLang;
    String currentLanguage;
    private DrawerLayout drawer;
    public static FragmentManager fragmentManager;

    // Database operation reference

    public static LibraryDB libraryDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentManager = getSupportFragmentManager();
       /* libraryDB = Room.databaseBuilder(getApplicationContext(),LibraryDB.class, "books")
                .allowMainThreadQueries()
                .build();*/



        if(findViewById(R.id.fragment_container) != null){
            if(savedInstanceState != null){
                return;
            }

            fragmentManager.beginTransaction().add(R.id.fragment_container, new Welcome_Fragment()).commit();
        }

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawermenu, menu);
        return true;
    }





    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Intent intent = new Intent();
        int id = item.getItemId();

        if (id == R.id.nav_main) {

            startActivity(intent.setClass(this, ShowBookActivity.class));

        } else if (id == R.id.nav_addBook) {

            startActivity(intent.setClass(this, AddBookActivity.class));

        } else if (id == R.id.nav_updateBook) {
            startActivity(intent.setClass(this, AddShelfActivity.class));

        } else if (id == R.id.nav_deleteBook) {

            startActivity(intent.setClass(this, ShowBookActivity.class));
        }
        else if (id == R.id.nav_ShelfManagment) {

            startActivity(intent.setClass(this, ShowShelfActivity.class));
        }
        else if (id == R.id.nav_about){
            this.setTitle(R.string.about_page);
            fragmentManager.beginTransaction().add(R.id.fragment_container, new About()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;



    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    public void setLocale(String localeName) {

        if (!localeName.equals(currentLanguage)) {
            Locale locale = new Locale(localeName);
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = locale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this, MainActivity.class);
            refresh.putExtra(currentLang, localeName);
            startActivity(refresh);
        } else {
            Toast.makeText(MainActivity.this, "Language already selected!", Toast.LENGTH_SHORT).show();
        }
    }
}

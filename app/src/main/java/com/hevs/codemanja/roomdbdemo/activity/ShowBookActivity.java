package com.hevs.codemanja.roomdbdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hevs.codemanja.roomdbdemo.Adapter.BookAdapter;
import com.hevs.codemanja.roomdbdemo.R;
import com.hevs.codemanja.roomdbdemo.entity.Book;

import java.util.ArrayList;
import java.util.List;

import static android.app.PendingIntent.getActivity;
import static com.hevs.codemanja.roomdbdemo.activity.MainActivity.libraryDB;

public class ShowBookActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    BookAdapter bookAdapter;
    Button buttonEdit;


    List<Book> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_show);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                  //      .setAction("Action", null).show();

                // From the float button start the Addbook activity
                startActivity(new Intent(ShowBookActivity.this, AddBookActivity.class));


            }
        });

        bookList = new ArrayList<>();

         bookList =  libraryDB.bookDao().getAllBooks();

         Log.d("TAG", bookList.toString());
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        for(int i=0; i<bookList.size(); i++){

            String img = bookList.get(i).getCategory().substring(0,1);

            switch (img){

                case "A":
                    bookList.get(i).setImage(R.drawable.ab);
                    break;

                case "B":
                    bookList.get(i).setImage(R.drawable.b);
                    break;

                case "K":
                    bookList.get(i).setImage(R.drawable.k);
                    break;

                    default:bookList.get(i).setImage(R.drawable.c);

            }


        }






//        bookList.add(
//                new Book(
//                        100,
//                        "Android Fundamentals",
//                        "Computer",
//                        R.drawable.c
//                       ));
//
//        bookList.add(
//                new Book(
//                        200,
//                        "Business Fundamentals",
//                        "Business",
//                        R.drawable.c
//                ));
//
//        bookList.add(
//                new Book(
//                        "300",
//                        "Cooking Fundamentals",
//                        "Health",
//                        R.drawable.c
//                ));
//        bookList.add(
//                new Book(
//                        400",
//                        "Business Fundamentals",
//                        "Business",
//                        R.drawable.c
//                ));
//
//        bookList.add(
//                new Book(
//                        "500",
//                        "Cooking Fundamentals",
//                        "Health",
//                        R.drawable.c
//                ));
//        bookList.add(
//                new Book(
//                        "600",
//                        "Business Fundamentals",
//                        "Business",
//                        R.drawable.c
//                ));
//
//        bookList.add(
//                new Book(
//                        "700",
//                        "Cooking Fundamentals",
//                        "Health",
//                        R.drawable.c
//                ));
//        bookList.add(
//                new Book(
//                        "800",
//                        "Cooking Fundamentals",
//                        "Health",
//                        R.drawable.c
//                ));
//        bookList.add(
//                new Book(
//                        "900",
//                        "Business Fundamentals",
//                        "Business",
//                        R.drawable.c
//                ));
//
//        bookList.add(
//                new Book(
//                        "1000",
//                        "Cooking Fundamentals",
//                        "Health",
//                        R.drawable.c
//                ));



        bookAdapter = new BookAdapter(this, bookList );
        recyclerView.setAdapter(bookAdapter);


        buttonEdit = findViewById(R.id.buttonEdit);



/*        buttonEdit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

              //startActivity(new Intent(ShowBookActivity.this, AddBookActivity.class));

            }
        });

*/



    }



}

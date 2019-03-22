package com.hevs.codemanja.roomdbdemo.activity;

import android.arch.lifecycle.ViewModel;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.hevs.codemanja.roomdbdemo.Database.LibraryDB;
import com.hevs.codemanja.roomdbdemo.R;
import com.hevs.codemanja.roomdbdemo.async.UpdateBook;
import com.hevs.codemanja.roomdbdemo.entity.Book;
import com.hevs.codemanja.roomdbdemo.util.OnAsyncEventListener;

public class UpdateBookActivity extends AppCompatActivity {

    private EditText editTextBid, editTextTitle, editTextSpotId;
    private Button buttonUpdate;
    private Spinner spinnerCategory, spinnerLocation;
    LibraryDB libraryDB;
    private Book book;
    private UpdateBook updateBook;
    ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_update);

        editTextBid = findViewById(R.id.editTextBid);
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextSpotId = findViewById(R.id.editTextSpotId);
        buttonUpdate = findViewById(R.id.buttonUpdateBook);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        spinnerLocation = findViewById(R.id.spinnerLocation);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.select_dialog_item);
        spinnerCategory.setAdapter(adapter);


        String spot[] = MainActivity.libraryDB.shelfDao().getAllSpots();
        if (spot.length > 0) {
            editTextSpotId.setText(spot[0]);

            ArrayAdapter<String> spotArray = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, spot);
            spotArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerLocation.setAdapter(spotArray);

            //spinnerLocation.setAdapter(adapter);


            libraryDB = Room.databaseBuilder(getApplicationContext(), LibraryDB.class, "books")
                    .allowMainThreadQueries().build();





            Intent intent = getIntent();
            book = intent.getParcelableExtra("book");

            int bookId = book.getBid();
            String title = book.getTitle();
            String category = book.getCategory();

/*
            String bookId = intent.getStringExtra("bookId");
            String title = intent.getStringExtra("title");
            String category = intent.getStringExtra("category");
*/


            int postion = intent.getIntExtra("position", 1);
            String spotId = intent.getStringExtra("spotId");



            editTextTitle.setText(title);
            editTextBid.setText(String.valueOf(bookId));
            editTextBid.setEnabled(false);
            spinnerCategory.setSelection(postion);


            if (spotId != null) {
                int spinnerPosition = spotArray.getPosition(spotId);
                spinnerLocation.setSelection(spinnerPosition);
            }

        }


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = editTextTitle.getText().toString();
                String category = spinnerCategory.getSelectedItem().toString();
                String spotId = editTextSpotId.getText().toString();



                Toast.makeText(UpdateBookActivity.this, "Book updated 0.1", Toast.LENGTH_SHORT).show();


                book.setTitle(title);
                book.setCategory(category);
                book.setF_spotid(spotId);




               libraryDB.bookDao().update(book);




                Toast.makeText(UpdateBookActivity.this, "Book updated.", Toast.LENGTH_SHORT).show();

               //


            }


        });



    }
}

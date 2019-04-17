package com.hevs.codemanja.roomdbdemo.ui.Book;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hevs.codemanja.roomdbdemo.Database.entity.BookEntity;
import com.hevs.codemanja.roomdbdemo.R;

import com.hevs.codemanja.roomdbdemo.ui.Transaction.MainActivity;

public class DeleteBookActivity extends AppCompatActivity {


    private EditText editTextBid, editTextTitle, editTextSpotId;
    private Button buttonDelete;

  //  LibraryDB libraryDB;
    private BookEntity book;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_delete);

        editTextBid = findViewById(R.id.editTextBid);
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextSpotId = findViewById(R.id.editTextSpotId);
        buttonDelete = findViewById(R.id.buttonDeleteteBook);


       // libraryDB = Room.databaseBuilder(getApplicationContext(), LibraryDB.class, "books")
         //       .allowMainThreadQueries().build();


        Intent intent = getIntent();
        book = intent.getParcelableExtra("book");

        //int bookId = book.getBid();
        String title = book.getTitle();
//        String category = book.getCategory();
        String spotId = book.getF_spotid();


        editTextTitle.setText(title);
       // editTextBid.setText(String.valueOf(bookId));
        editTextBid.setEnabled(false);


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = editTextTitle.getText().toString();


//               MainActivity.libraryDB.bookDao().delete(book);

                /*

                Toast toast = Toast.makeText(getApplicationContext(), title + "Book Removed from the Shelf spot "+ spotId, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                LinearLayout toastContentView = (LinearLayout) toast.getView();
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setImageResource(R.drawable.delete);
                toastContentView.addView(imageView, 0);
                toast.show();
                finish();

    */



                // Get the custom layout view.
                View toastView = getLayoutInflater().inflate(R.layout.activity_toast_book_delete_custom, null);

                // Initiate the Toast instance.
                Toast toast = new Toast(getApplicationContext());
                // Set custom view in toast.


                toast.setView(toastView);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();





            }


        });


    }


}

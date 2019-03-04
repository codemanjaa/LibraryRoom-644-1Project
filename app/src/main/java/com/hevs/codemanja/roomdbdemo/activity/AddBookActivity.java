package com.hevs.codemanja.roomdbdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.hevs.codemanja.roomdbdemo.R;
import com.hevs.codemanja.roomdbdemo.entity.Book;

import static android.app.PendingIntent.getActivity;

public class AddBookActivity extends AppCompatActivity {

    private EditText editTextBid, editTextTitle, editTextCategory;
    private Button buttonAdd;
    private Spinner spinnerCategory, spinnerLocation;


    public AddBookActivity(){

    }



    // Validation for the EditText

    public static boolean isEmpty(EditText editText) {

        String input = editText.getText().toString().trim();
        return input.length() == 0;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        editTextBid = findViewById(R.id.editTextBid);
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextCategory = findViewById(R.id.editTextCategory);
        buttonAdd = findViewById(R.id.buttonAddBook);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        spinnerLocation = findViewById(R.id.spinnerLocation);



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.select_dialog_item);
        spinnerCategory.setAdapter(adapter);

        spinnerLocation.setAdapter(adapter);


        buttonAdd.setEnabled(false);
        editTextBid.requestFocus();


        editTextBid.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(editTextBid.getText().toString().length() == 3){
                    buttonAdd.setEnabled(true);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(editTextBid.getText().toString().length() != 3){
                    buttonAdd.setEnabled(false);
                }


            }
        });


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bookId = editTextBid.getText().toString();
                String title = editTextTitle.getText().toString();
                String category = editTextCategory.getText().toString();


                Book book = new Book();
                book.setBid(bookId);
                book.setTitle(title);
                book.setCategory(category);

                MainActivity.libraryDB.bookDao().addBook(book);
              //  Toast.makeText(getActivity(), "Book added to the shelf", Toast.LENGTH_SHORT).show();

                editTextBid.setText("");
                editTextTitle.setText("");
                editTextCategory.setText("");

                editTextBid.requestFocus();
            }
        });












    }
}

package com.hevs.codemanja.roomdbdemo.fragments;


import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hevs.codemanja.roomdbdemo.Database.LibraryDB;
import com.hevs.codemanja.roomdbdemo.R;
import com.hevs.codemanja.roomdbdemo.activity.MainActivity;
import com.hevs.codemanja.roomdbdemo.entity.Book;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddBookFragment extends Fragment {

    // Init the components

    private EditText editTextBid, editTextTitle, editTextCategory;
    private Button buttonAdd;

    LibraryDB libraryDB;


    public AddBookFragment() {
        // Required empty public constructor
    }


    // Validation for the EditText

    public static boolean isEmpty(EditText editText) {

        String input = editText.getText().toString().trim();
        return input.length() == 0;

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_book_add, container, false);

        editTextBid = view.findViewById(R.id.editTextBid);
        editTextTitle = view.findViewById(R.id.editTextTitle);
        editTextCategory = view.findViewById(R.id.editTextCategory);
        buttonAdd = view.findViewById(R.id.buttonAddBook);
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
               Toast.makeText(getActivity(), "Book added to the shelf", Toast.LENGTH_SHORT).show();

               editTextBid.setText("");
               editTextTitle.setText("");
               editTextCategory.setText("");

               editTextBid.requestFocus();
           }
       });







        return view;
    }

}

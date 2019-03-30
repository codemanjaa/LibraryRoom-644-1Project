package com.hevs.codemanja.roomdbdemo.ui.Book;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.hevs.codemanja.roomdbdemo.Database.entity.BookEntity;
import com.hevs.codemanja.roomdbdemo.R;
import com.hevs.codemanja.roomdbdemo.ui.Transaction.MainActivity;

import static android.app.PendingIntent.getActivity;

public class AddBookActivity extends AppCompatActivity {

    public static final String EXTRA_ID =
            "com.hevs.codemanja.roomdbdemo.ui.Book.EXTRA_ID";

    public static final String EXTRA_TITLE =
            "com.hevs.codemanja.roomdbdemo.ui.Book.EXTRA_TITLE";

    public static final String EXTRA_CATEGORY =
            "com.hevs.codemanja.roomdbdemo.ui.Book.EXTRA_CATEGORY";

    public static final String EXTRA_SPOTID =
            "com.hevs.codemanja.roomdbdemo.ui.Book.EXTRA_SPOTID";


    private EditText editTextG, editTextTitle, editTextSpotId;
    private Button buttonAdd;
    private Spinner spinnerCategory, spinnerLocation;
    private static String category;



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
        setContentView(R.layout.activity_book_add);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextSpotId = findViewById(R.id.editTextSpotId);
        editTextG = findViewById(R.id.textViewG);
        spinnerCategory = findViewById(R.id.spinnerCategory);

        buttonAdd = findViewById(R.id.buttonAddBook);

        Intent intent = getIntent();
        // adding data from selected book
        if(intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Book");
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextG.setText(intent.getStringExtra(EXTRA_CATEGORY));
            editTextSpotId.setText(intent.getStringExtra(EXTRA_SPOTID));

        }else {
            setTitle("Add Book");
        }

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBook();

            }

        });



        editTextTitle = findViewById(R.id.editTextTitle);
        editTextSpotId = findViewById(R.id.editTextSpotId);
        buttonAdd = findViewById(R.id.buttonAddBook);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        spinnerLocation = findViewById(R.id.spinnerLocation);



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.select_dialog_item);
        spinnerCategory.setAdapter(adapter);


       // editTextSpotId.setVisibility(View.INVISIBLE);

        spinnerLocation.setAdapter(adapter);


        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = parent.getItemAtPosition(position).toString(); //this is your selected item

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });






/**

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                category = parent.getItemAtPosition(position).toString(); //this is your selected item




                String spot[] = MainActivity.libraryDB.shelfDao().getCategorySpot(category);
                if(spot.length >0) {
                    editTextSpotId.setText(spot[0]);

                    ArrayAdapter<String> spotArray= new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, spot);
                    spotArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerLocation.setAdapter(spotArray);
                    spinnerLocation.setEnabled(true);

                }

                else{

                    AlertDialog alertDialog = new AlertDialog.Builder(AddBookActivity.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Please reserve the Spot in the Shelf");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                        spinnerLocation.setEnabled(false);
                    alertDialog.show();



                }

            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        spinnerLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spotid = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });





        editTextTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // Check the book title length should be minumum 3
           //   if(editTextTitle.getText().toString().length() >1){
             //       buttonAdd.setEnabled(true);
               // }


            }

            @Override
            public void afterTextChanged(Editable s) {

                if(editTextBid.getText().toString().length() != 1){
                    buttonAdd.setEnabled(false);
                }


            }
        });*/

/*
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = editTextTitle.getText().toString();
                //String category = this.category;

                String spotId = editTextSpotId.getText().toString();

                if(title.equals("")){
                    AlertDialog alertDialog = new AlertDialog.Builder(AddBookActivity.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Please enter the Title");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                    editTextTitle.requestFocus();
                }


                else {

                    // Validation pass , book instance created
                    BookEntity book = new BookEntity();
                    //book.setBid(bookId);
                    book.setTitle(title);
                    book.setCategory(category);
                    book.setF_spotid(spotid);

                    // Call the static instance of the libraryDB to Add book
                    MainActivity.libraryDB.bookDao().insert(book);

                    // Display the confirmation to the user.

                    Toast toast = Toast.makeText(getApplicationContext(), title + " Book Added on the spot " + spotId, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    LinearLayout toastContentView = (LinearLayout) toast.getView();
                    ImageView imageView = new ImageView(getApplicationContext());
                    imageView.setImageResource(R.drawable.add);
                    toastContentView.addView(imageView, 0);
                    toast.show();


                    // Clear the elements

                    editTextTitle.setText("");
                    editTextSpotId.setText("");


                    // call the next activity and finish the current activity.

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();


                }

            }
        });*/



}

    private void saveBook() {

        String title = editTextTitle.getText().toString();
      //  categeory =   editTextG.getText().toString();
        String spotId = editTextSpotId.getText().toString();

      // if(title.trim().isEmpty()|| categeory.trim().isEmpty()){
       //     Toast.makeText(this,"please enter values",Toast.LENGTH_SHORT).show();
      //  return;
        //}

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE,title);
        data.putExtra(EXTRA_CATEGORY,category);
        data.putExtra(EXTRA_SPOTID, spotId);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if(id != -1){
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK,data);
        finish();

        //startActivity(new Intent(getApplicationContext(),ShowBookActivity.class));

    }

    private void DeleteBook() {

        String title = editTextTitle.getText().toString();
        String catageory =  editTextG.getText().toString();
        String spotId = editTextSpotId.getText().toString();

        if(title.trim().isEmpty()|| catageory.trim().isEmpty()){
            Toast.makeText(this,"please enter values",Toast.LENGTH_SHORT).show();
            return;
        }

        BookEntity bookEntity=new BookEntity(title, catageory, spotId);

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE,title);
        data.putExtra(EXTRA_CATEGORY,catageory);
        data.putExtra(EXTRA_SPOTID, spotId);

        setResult(RESULT_OK,data);
        finish();

        //startActivity(new Intent(getApplicationContext(),ShowBookActivity.class));

    }


}



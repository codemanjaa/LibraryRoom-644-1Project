package com.hevs.codemanja.roomdbdemo.ui.Book;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.hevs.codemanja.roomdbdemo.Database.entity.BookEntity;
import com.hevs.codemanja.roomdbdemo.R;



public class UpdateBookActivity extends AppCompatActivity {

    private EditText editTextBid, editTextTitle, editTextSpotId;
    private Button buttonUpdate;
    private Spinner spinnerCategory, spinnerLocation;
    private BookEntity book;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_update);

       /* editTextBid = findViewById(R.id.editTextBid);
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextSpotId = findViewById(R.id.editTextSpotId);
        buttonUpdate = findViewById(R.id.buttonUpdateBook);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        spinnerLocation = findViewById(R.id.spinnerLocation);



        spinnerLocation.setEnabled(false);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.select_dialog_item);
        spinnerCategory.setAdapter(adapter);


        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String category = parent.getItemAtPosition(position).toString(); //this is your selected item




                String spot[] = MainActivity.libraryDB.shelfDao().getCategorySpot(category);

                if(spot.length == 0){

                    AlertDialog alertDialog = new AlertDialog.Builder(UpdateBookActivity.this).create();
                    alertDialog.setTitle("Spot Alert");
                    alertDialog.setMessage("Reserve the spot for "+ category.toUpperCase() + " shelf");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    spinnerLocation.setEnabled(false);
                    alertDialog.show();

                }

                else if(spot.length >0) {
                    editTextSpotId.setText(spot[0]);

                    ArrayAdapter<String> spotArray = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, spot);
                    spotArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerLocation.setAdapter(spotArray);
                    spinnerLocation.setEnabled(true);






                    Intent intent = getIntent();
                    book = intent.getParcelableExtra("book");

                 //  int bookId = book.getBid();
                   String title = book.getTitle();
                   category = book.getCategory();


                   int postion = intent.getIntExtra("position", 1);
                   String spotId = intent.getStringExtra("spotId");



                   editTextTitle.setText(title);

                   editTextBid.setEnabled(false);
                   spinnerCategory.setSelection(postion);


                   if (spotId != null) {
                       int spinnerPosition = spotArray.getPosition(spotId);
                       spinnerLocation.setSelection(spinnerPosition);
                   }

                   if(category != null){
                       int spinPos = adapter.getPosition(category);
                       spinnerCategory.setSelection(spinPos);

                   }

               }

           }



            public void onNothingSelected(AdapterView<?> parent)
            {

            }
       });






        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = editTextTitle.getText().toString();
                String category = spinnerCategory.getSelectedItem().toString();
                String spotId =  spinnerLocation.getSelectedItem().toString();


                book.setTitle(title);
                book.setCategory(category);
                book.setF_spotid(spotId);

                String s = category.substring(0,1).toLowerCase();


              MainActivity.libraryDB.bookDao().update(book);

               /*
                Toast toast = Toast.makeText(getApplicationContext(), "Book Updated", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                LinearLayout toastContentView = (LinearLayout) toast.getView();
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setImageResource(R.drawable.edit);
                toastContentView.addView(imageView, 0);
                toast.show();
*/

            /*    // Get the custom layout view.
                View toastView = getLayoutInflater().inflate(R.layout.activity_toast_book_update_custom, null);

                // Initiate the Toast instance.
                Toast toast = new Toast(getApplicationContext());
                // Set custom view in toast.


                toast.setView(toastView);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0,0);
                toast.show();


                //Toast.makeText(UpdateBookActivity.this, "Book updated.", Toast.LENGTH_SHORT).show();
                finish();
               //


            }


        });

*/
    }
}

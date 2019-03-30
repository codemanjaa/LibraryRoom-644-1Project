package com.hevs.codemanja.roomdbdemo.ui.shelf;

import android.app.AlertDialog;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.hevs.codemanja.roomdbdemo.Database.LibraryDB;
import com.hevs.codemanja.roomdbdemo.R;
import com.hevs.codemanja.roomdbdemo.viewmodel.ShelfViewModel;

import java.util.ArrayList;
import java.util.List;

public class AddShelfActivity extends AppCompatActivity {

    public static final String EXTRA_DESC =
            "com.hevs.codemanja.roomdbdemo.ui.shelf.EXTRA_DESC";

    public static final String EXTRA_CATEGORY =
            "com.hevs.codemanja.roomdbdemo.ui.shelf.EXTRA_CATEGORY";

    public static final String EXTRA_SPOTID =
            "com.hevs.codemanja.roomdbdemo.ui.shelf.EXTRA_SPOTID";


    // Init the components

    private EditText editTextSpotId, editTextDesc, editTextCategory;
    private Button buttonAddSpot;
    private Spinner spinnerCategory;
    private String category;
    private ShelfViewModel shelfViewModel;


    public AddShelfActivity() {
        // Required empty public constructor
    }


    // Validation for the EditText

    public static boolean isEmpty(EditText editText) {

        String input = editText.getText().toString().trim();
        return input.length() == 0;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shelf);
        editTextSpotId = findViewById(R.id.editTextSpotId);
        editTextDesc = findViewById(R.id.editTextDesc);
        editTextCategory = findViewById(R.id.editTextCategory);
        buttonAddSpot = findViewById(R.id.buttonAddSpot);
        spinnerCategory = findViewById(R.id.spinnerCategory);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.category_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.select_dialog_item);
        spinnerCategory.setAdapter(adapter);


        // buttonAddSpot.setEnabled(false);
        editTextSpotId.setEnabled(true);
        editTextCategory.setVisibility(View.INVISIBLE);


        //Generate the Spot ID

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





/*
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = parent.getItemAtPosition(position).toString().toUpperCase();
                String spot = category.substring(0, 1); //this is your selected item
                String query = spot + "%";



                // String spots[] = ;

                ArrayList<String> spots = new ArrayList<>();

                for (int i = 0; i < spots.size(); i++) {
                    spots.add(shelfViewModel.getAllSpots().getValue().toString())  ;
                }




                if (spots.size() > 0) {

                    String temp = spots.get(spots.size() - 1);

                    String arr[] = temp.split("-");
                    String c = arr[0];
                    String r = arr[1];
                    String co = arr[2];

                    if (Integer.parseInt(arr[2]) > 14) {
                        Integer.parseInt(arr[1] + 1);

                    } else {

                        int col = (Integer.parseInt(arr[2]) + 1);
                        temp = c + "-" + r + "-" + String.valueOf(col);
                    }
                    editTextCategory.setText( temp);
                    editTextCategory.setVisibility(View.INVISIBLE);
                    editTextSpotId.setText(temp);

                } else {

                    String temp = spot + "-1-1";
                    /*
                    AlertDialog alertDialog = new AlertDialog.Builder(getApplicationContext()).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("This is your new shelf " + temp);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();


                    editTextSpotId.setText(temp);


                }


            }

            public void onNothingSelected(AdapterView<?> parent)
            {
/*
            }
        });
        */


        editTextDesc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (editTextDesc.getText().toString().length() > 1) {
                    buttonAddSpot.setEnabled(true);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        //


        editTextSpotId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (editTextSpotId.getText().toString().length() >= 1) {
                    buttonAddSpot.setEnabled(true);
                }


            }

            @Override
            public void afterTextChanged(Editable s) {


                if (editTextSpotId.getText().toString().length() != 1) {
                    buttonAddSpot.setEnabled(false);
                }


            }
        });


        buttonAddSpot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveShelf();


            }
        });

    }


        private void saveShelf() {

            String desc = editTextDesc.getText().toString();
            String spotId = editTextSpotId.getText().toString();


           /*
            if(title.trim().isEmpty()|| catageory.trim().isEmpty()){
                Toast.makeText(this,"please enter values",Toast.LENGTH_SHORT).show();
                return;
            }
            */

            Intent data = new Intent();
            data.putExtra(EXTRA_DESC,desc);
            data.putExtra(EXTRA_CATEGORY,category);
            data.putExtra(EXTRA_SPOTID, spotId);
/*
            int id = getIntent().getIntExtra(EXTRA_ID, -1);
            if(id != -1){
                data.putExtra(EXTRA_ID, id);
            }
*/
            setResult(RESULT_OK,data);
            finish();
            editTextSpotId.setText("");
            editTextDesc.setText("");
            editTextCategory.setText("");


            Toast.makeText(getApplicationContext(), "Spot reserved on the shelf", Toast.LENGTH_SHORT).show();

        }


    }


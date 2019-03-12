package com.hevs.codemanja.roomdbdemo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import com.hevs.codemanja.roomdbdemo.R;
import com.hevs.codemanja.roomdbdemo.activity.MainActivity;
import com.hevs.codemanja.roomdbdemo.entity.Shelf;

import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Add_Shelf_Fragment extends Fragment {

    // Init the components

    private EditText editTextSpotId, editTextDesc, editTextCategory;
    private Button buttonAddSpot;
    Spinner spinnerCategory;






    public Add_Shelf_Fragment() {
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
        View view =  inflater.inflate(R.layout.fragment_shelf_add, container, false);

        editTextSpotId = view.findViewById(R.id.editTextSpotId);
        editTextDesc = view.findViewById(R.id.editTextDesc);
        editTextCategory = view.findViewById(R.id.editTextCategory);
        buttonAddSpot = view.findViewById(R.id.buttonAddSpot);

        spinnerCategory = view.findViewById(R.id.spinnerCategory);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.category_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.select_dialog_item);
        spinnerCategory.setAdapter(adapter);


        buttonAddSpot.setEnabled(true);
        editTextSpotId.requestFocus();



        //Generate the Spot ID


        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String spot = parent.getItemAtPosition(position).toString().toUpperCase().substring(0,1); //this is your selected item
                String query = spot+"%";
                String spots[] = MainActivity.libraryDB.shelfDao().getAllSpotsList(query);

                if(spots.length > 0){

                    String temp = spots[spots.length - 1];

                    String arr[] = temp.split("-");
                    String c = arr[0];
                    String r= arr[1];
                    String co= arr[2];

                    if( Integer.parseInt(arr[2]) > 14){
                        Integer.parseInt(arr[1] + 1 );

                    }
                    else {

                       int col =  Integer.parseInt(arr[2] + 1);

                    }
                    editTextCategory.setText( c+"-"+r+"-"+co.toString());
                }



            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });







            //




        editTextSpotId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                /*
                if(editTextSpotId.getText().toString().length() == 1){
                    buttonAddSpot.setEnabled(true);
                }
                */

            }

            @Override
            public void afterTextChanged(Editable s) {

                /*
                if(editTextSpotId.getText().toString().length() != 1){
                    buttonAddSpot.setEnabled(false);
                }
                */


            }
        });


       buttonAddSpot.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String spotid = editTextSpotId.getText().toString();
               String desc = editTextDesc.getText().toString();
               String category = editTextCategory.getText().toString();



              Shelf shelf = new Shelf();
              shelf.setSpotid(spotid);
              shelf.setDesc(desc);


              System.out.println(shelf.getSpotid() + " " + shelf.getDesc());




              MainActivity.libraryDB.shelfDao().insert(shelf);
               Toast.makeText(getActivity(), "Spot reserved ot the shelf", Toast.LENGTH_SHORT).show();

               editTextSpotId.setText("");
               editTextDesc.setText("");
               editTextCategory.setText("");

               editTextSpotId.requestFocus();
           }
       });







        return view;
    }

}



package com.hevs.codemanja.roomdbdemo.ui.shelf;


import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.hevs.codemanja.roomdbdemo.Database.entity.ShelfEntity;
import com.hevs.codemanja.roomdbdemo.R;
import com.hevs.codemanja.roomdbdemo.viewmodel.ShelfViewModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Add_Shelf_Fragment extends Fragment {

    public static final String EXTRA_DESC =
            "com.hevs.codemanja.roomdbdemo.ui.Book.EXTRA_DESC";

    public static final String EXTRA_CATEGORY =
            "com.hevs.codemanja.roomdbdemo.ui.Book.EXTRA_CATEGORY";

    public static final String EXTRA_SPOTID =
            "com.hevs.codemanja.roomdbdemo.ui.Book.EXTRA_SPOTID";



    // Init the components

    private EditText editTextSpotId, editTextDesc, editTextCategory;
    private Button buttonAddSpot;
    private Spinner spinnerCategory;
    private String category;
    private ShelfViewModel shelfViewModel;




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


        ArrayAdapter adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.category_array, R.layout.spinner_item);
      // adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);

        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
          //      R.array.category_array, R.layout.spinner_item);

        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinnerCategory.setAdapter(adapter);


       // buttonAddSpot.setEnabled(false);
        editTextSpotId.setEnabled(false);



        //Generate the Spot ID



        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                category = parent.getItemAtPosition(position).toString().toUpperCase();
                String spot = category.substring(0, 1); //this is your selected item
                String query = spot + "%";



                   // String spots[] = ;

                ArrayList<String> spots = new ArrayList<>();

                for (int i = 0; i < spots.size(); i++) {
                    //spots.add(shelfViewModel.getAllSpots().getValue().toString())  ;
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
                        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
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

            }
        });





        editTextDesc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(editTextDesc.getText().toString().length() > 1){
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


                if(editTextSpotId.getText().toString().length() == 1){
                    buttonAddSpot.setEnabled(true);
                }


         }

            @Override
            public void afterTextChanged(Editable s) {


                if(editTextSpotId.getText().toString().length() != 1){
                    buttonAddSpot.setEnabled(false);
                }



            }
        });


       buttonAddSpot.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               String spotid = editTextSpotId.getText().toString();
               String desc = editTextDesc.getText().toString();
               String category = editTextCategory.getText().toString();
               //System.out.println(spotid+ " "+desc+" "+category);


               ShelfEntity shelf = new ShelfEntity();
               shelf.setSpotid(spotid);
               shelf.setDesc(desc);
               shelf.setCategory(category);


               System.out.println(shelf.getSpotid() + " Testing   " + shelf.getDesc());
              // shelfViewModel.insert(shelf);

               System.out.println("After insertion "+shelf.getSpotid() + " " + shelf.getDesc());



               editTextSpotId.setText("");
               editTextDesc.setText("");
               editTextCategory.setText("");







               Toast.makeText(getActivity(), "Spot reserved ot the shelf", Toast.LENGTH_SHORT).show();




           }
       });







        return view;
    }

}

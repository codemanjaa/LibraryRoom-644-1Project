package com.hevs.codemanja.roomdbdemo.ui.Transaction;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hevs.codemanja.roomdbdemo.R;
import com.hevs.codemanja.roomdbdemo.ui.Book.ShowBookActivity;
import com.hevs.codemanja.roomdbdemo.ui.shelf.ShowShelfActivity;

import static android.support.constraint.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class Welcome_Fragment extends Fragment implements View.OnClickListener {

    private Button buttonAdd;
    private Button buttonSearch;


    public Welcome_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_welcome_, container, false);

        buttonAdd = view.findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(this);

        buttonSearch = view.findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){

            case R.id.buttonAdd:

                Intent shefIntent = new Intent(getActivity(), ShowShelfActivity.class);
                getActivity().startActivity(shefIntent);
                Toast.makeText(getActivity(), "Shelf List", Toast.LENGTH_SHORT).show();

               // MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,
                 //       new Add_Shelf_Fragment()).addToBackStack(null).commit();


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("shelf");

                myRef.setValue("A-1-1");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String value = dataSnapshot.getValue(String.class);
                        Log.d(TAG, "Value is: " + value);
                    }



                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });

                break;


            case R.id.buttonSearch:

                Intent myIntent = new Intent(getActivity(), ShowBookActivity.class);
                getActivity().startActivity(myIntent);
                Toast.makeText(getActivity(), "Book List", Toast.LENGTH_SHORT).show();


        }


    }
}

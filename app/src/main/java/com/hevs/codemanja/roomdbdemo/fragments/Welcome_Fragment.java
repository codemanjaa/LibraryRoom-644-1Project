package com.hevs.codemanja.roomdbdemo.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.hevs.codemanja.roomdbdemo.R;
import com.hevs.codemanja.roomdbdemo.activity.MainActivity;
import com.hevs.codemanja.roomdbdemo.activity.ShowBookActivity;

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

                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,
                        new Add_Shelf_Fragment()).addToBackStack(null).commit();

                break;


            case R.id.buttonSearch:

                Intent myIntent = new Intent(getActivity(), ShowBookActivity.class);
                getActivity().startActivity(myIntent);
                Toast.makeText(getActivity(), "Book List", Toast.LENGTH_SHORT).show();


        }


    }
}

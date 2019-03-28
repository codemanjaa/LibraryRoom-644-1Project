package com.hevs.codemanja.roomdbdemo.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.hevs.codemanja.roomdbdemo.R;
import com.hevs.codemanja.roomdbdemo.entity.Shelf;


import java.util.List;

public class ShelfRecycleAdapter extends RecyclerView.Adapter<ShelfRecycleAdapter.ShelfViewHolder> {

    private Context mContext;
    private List<Shelf> shelfList;


    //

    public ShelfRecycleAdapter(Context mContext, List<Shelf> shelfList) {
        this.mContext = mContext;
        this.shelfList = shelfList;


    }

    @NonNull
    @Override
    public ShelfViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.listview_shelf, null);
        ShelfViewHolder holder = new ShelfViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull ShelfRecycleAdapter.ShelfViewHolder shelfViewHolder, int i) {


        Shelf shelf = shelfList.get(i);
        shelfViewHolder.textViewTitle.setText((shelf.getDesc()));


    }

    @Override
    public int getItemCount() {
        return shelfList.size();
    }

    class ShelfViewHolder extends RecyclerView.ViewHolder {


        TextView textViewTitle, textViewCategory, textViewSpotId;
        Button buttonEdit, buttonDelete;


        public ShelfViewHolder(@NonNull View itemView) {
            super(itemView);


            textViewTitle = itemView.findViewById(R.id.textViewTitle);


            textViewSpotId = itemView.findViewById(R.id.editTextSpotId);

            buttonEdit = itemView.findViewById(R.id.buttonEdit);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);


            buttonEdit.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    int position = getAdapterPosition();

                    Snackbar.make(view, "Click detected on item " + position,
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    // To modify the book
                    // Intent intent = new Intent(view.getContext(),UpdateBookActivity.class);
                    //Book book = bookList.get(position);

                    //intent.putExtra("book",book);


                    //Toast.makeText(view.getContext(), "Book update.", Toast.LENGTH_SHORT).show();
                    //view.getContext().startActivity(intent);


                }
            });


            buttonDelete.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    Snackbar.make(view, "Click detected on item " + position,
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    // To Remove the book


                 /*   Intent intent = new Intent(view.getContext(),DeleteBookActivity.class);
                    Book book = bookList.get(position);

                    intent.putExtra("book",book);


                    Toast.makeText(view.getContext(), "Book Will be Removed from the shelf.", Toast.LENGTH_SHORT).show();
                    view.getContext().startActivity(intent);
                    */
                }
            });


            // imageView = itemView.findViewById(R.id.imageView);
        }


    }
}


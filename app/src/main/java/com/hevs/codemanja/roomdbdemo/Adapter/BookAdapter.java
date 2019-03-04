package com.hevs.codemanja.roomdbdemo.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hevs.codemanja.roomdbdemo.R;
import com.hevs.codemanja.roomdbdemo.activity.AddBookActivity;
import com.hevs.codemanja.roomdbdemo.activity.MainActivity;
import com.hevs.codemanja.roomdbdemo.entity.Book;

import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

// RecycleView
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private Context mContext;
    private List<Book> bookList;


    // Testing the book edit button

    private Button buttonEdit;




    //

    public BookAdapter(Context mContext, List<Book> bookList) {
        this.mContext = mContext;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.listview_book, null);
        BookViewHolder holder = new BookViewHolder(view);
        return holder;
    }



    @Override
    public void onBindViewHolder(@NonNull BookViewHolder bookViewHolder, int i) {


        Book book = bookList.get(i);
        bookViewHolder.textViewTitle.setText((book.getTitle()));
        bookViewHolder.textViewCategory.setText((book.getCategory()));
        bookViewHolder.textViewId.setText(("ID:"+book.getBid()));
        bookViewHolder.imageView.setImageDrawable(mContext.getResources().getDrawable(book.getImage(), null));


    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textViewTitle, textViewCategory, textViewId;
        Button buttonEdit, buttonDelete;



        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textViewCategory = itemView.findViewById(R.id.textViewCategory);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewId = itemView.findViewById(R.id.textViewId);
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
                    Intent intent = new Intent(view.getContext(),AddBookActivity.class);
                    view.getContext().startActivity(intent);

                  //  showAddBookActivity();

                  // Intent intent = new Intent(mContext, TestActivity.class);

                    //startActivity(new Intent(AddBookActivity.class, mContext));

                }
            });


            buttonDelete.setOnClickListener(new View.OnClickListener(){


                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    Snackbar.make(view, "Click detected on item " + position,
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    // To Remove the book
                    Intent intent = new Intent(view.getContext(),MainActivity.class);
                    view.getContext().startActivity(intent);
                }
            });



           // imageView = itemView.findViewById(R.id.imageView);
        }




    }

    private void showAddBookActivity() {



    }

}

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

import com.hevs.codemanja.roomdbdemo.Database.entity.BookEntity;
import com.hevs.codemanja.roomdbdemo.R;
import com.hevs.codemanja.roomdbdemo.ui.Book.DeleteBookActivity;
import com.hevs.codemanja.roomdbdemo.ui.Book.UpdateBookActivity;


import java.util.ArrayList;
import java.util.List;

// RecycleView
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.bookHolder> {
private  List<BookEntity> books = new ArrayList<>();
private OnItemClickListner listner;

    @NonNull
    @Override
    public bookHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.listview_book,viewGroup,false);
        return new bookHolder(itemView) ;
    }

    @Override
    public void onBindViewHolder(@NonNull bookHolder bookHolder, int i) {

        BookEntity currenbooks = books.get(i);
        bookHolder.textView.setText(currenbooks.getTitle());
        bookHolder.textViewCategory.setText(currenbooks.getCategory());
        bookHolder.textViewSpot.setText(currenbooks.getF_spotid());
        bookHolder.imageView.setImageResource(currenbooks.getImage());

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(List<BookEntity> books){
        this.books = books;
        notifyDataSetChanged();
    }

    public BookEntity getBook(int pos){
        return books.get(pos);
    }

    class bookHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        private TextView textViewCategory;
        private TextView textViewSpot;
        private Button edit;
        private Button delete;
        private ImageView imageView;

        public bookHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewTitle);
            textViewCategory = itemView.findViewById(R.id.textViewCategory);
            textViewSpot = itemView.findViewById(R.id.textViewSpot);
            edit = itemView.findViewById(R.id.buttonEdit);
            delete = itemView.findViewById(R.id.buttonDelete);
            imageView = itemView.findViewById(R.id.imageView);

            // selecting a book for edit
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (listner != null && pos != RecyclerView.NO_POSITION)
                    listner.onItemClick(books.get(pos));
                }
            });
        }
    }

    public interface OnItemClickListner {
        void onItemClick(BookEntity entity);
    }

    public void setOnItemClickListner(OnItemClickListner listner){
        this.listner = listner;
    }

}

package com.hevs.codemanja.roomdbdemo.Adapter;


import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.hevs.codemanja.roomdbdemo.Database.entity.BookEntity;
import com.hevs.codemanja.roomdbdemo.R;


// RecycleView


public class BookAdapter extends ListAdapter<BookEntity,BookAdapter.bookHolder> {
private OnItemClickListner listner;

    public BookAdapter() {
        super(DIFF_CALLBACK);
    }

    private  static final DiffUtil.ItemCallback<BookEntity> DIFF_CALLBACK = new DiffUtil.ItemCallback<BookEntity>() {
        @Override
        public boolean areItemsTheSame(@NonNull BookEntity entity, @NonNull BookEntity t1) {
            return entity.getTitle() == t1.getTitle();
        }

        @Override
        public boolean areContentsTheSame(@NonNull BookEntity entity, @NonNull BookEntity t1) {
            return entity.getTitle().equals(t1.getTitle()) &&
                    entity.getCategory().equals(t1.getCategory()) &&
                    entity.getF_spotid().equals(t1.getF_spotid());
        }
    };
    @NonNull
    @Override
    public bookHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.listview_book,viewGroup,false);
        return new bookHolder(itemView) ;
    }

    @Override
    public void onBindViewHolder(@NonNull bookHolder bookHolder, int i) {

        BookEntity currenbooks = getItem(i);
        bookHolder.textView.setText(currenbooks.getTitle());
        bookHolder.textViewCategory.setText(currenbooks.getCategory());
        bookHolder.textViewSpot.setText(currenbooks.getF_spotid());
        bookHolder.imageView.setImageResource(currenbooks.getImage());

    }




    public BookEntity getBook(int pos){
        return getItem(pos);
    }

    class bookHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        private Spinner spinnerCategory;
        private TextView textViewCategory;
        private TextView textViewSpot;
        private Button edit;
        private Button delete;
        private ImageView imageView;

        public bookHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewTitle);
            textViewCategory = itemView.findViewById(R.id.textViewCategory);
            spinnerCategory = itemView.findViewById(R.id.spinnerCategory);
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
                    listner.onItemClick(getItem(pos));
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

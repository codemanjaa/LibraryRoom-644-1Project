package com.hevs.codemanja.roomdbdemo.Adapter;


import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;


import com.hevs.codemanja.roomdbdemo.Database.entity.BookEntity;
import com.hevs.codemanja.roomdbdemo.Database.entity.ShelfEntity;
import com.hevs.codemanja.roomdbdemo.R;
import com.hevs.codemanja.roomdbdemo.util.RecyclerViewItemClickListener;

import java.util.List;
import java.util.Objects;

public class RecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<T> mData;
    private RecyclerViewItemClickListener mListner;


    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;

        ViewHolder(TextView textView) {
            super(textView);
            mTextView = textView;
        }
    }

    public RecyclerAdapter(RecyclerViewItemClickListener Listner) {
        mListner = Listner;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parant, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parant.getContext())
                .inflate(R.layout.activity_book_show, parant, false);
        final ViewHolder viewHolder = new ViewHolder(v);
        v.setOnClickListener(view -> mListner.onItemClick(view, viewHolder.getAdapterPosition()));
        v.setOnLongClickListener(view -> {
            mListner.onItemLongClick(view, viewHolder.getAdapterPosition());
            return true;
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position){
        T item = mData.get(position);
        if (item.getClass().equals(BookEntity.class))
            holder.mTextView.setText(((BookEntity) item).getTitle());
        if(item.getClass().equals(ShelfEntity.class))
            holder.mTextView.setText(((ShelfEntity) item).getSpotid()+" "+((ShelfEntity) item).getCategory());
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        } else {
            return 0;
        }
    }

    public void setData(final List<T> data) {
        if (mData == null) {
            mData = data;
            notifyItemRangeInserted(0, data.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mData.size();
                }

                @Override
                public int getNewListSize() {
                    return data.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    if (mData instanceof BookEntity) {
                        return ((BookEntity) mData.get(oldItemPosition)).getBid().equals(((BookEntity) data.get(newItemPosition)).getBid());
                    }
                    if (mData instanceof ShelfEntity) {
                        return ((ShelfEntity) mData.get(oldItemPosition)).getSpotid().equals(
                                ((ShelfEntity) data.get(newItemPosition)).getSpotid());
                    }
                    return false;
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    if (mData instanceof BookEntity) {
                        BookEntity newBook = (BookEntity) data.get(newItemPosition);
                        BookEntity oldBook = (BookEntity) mData.get(newItemPosition);
                        return newBook.getBid().equals(oldBook.getBid())
                                && Objects.equals(newBook.getBid(), oldBook.getBid())
                                //&& Objects.equals(newBook.getBalance(), oldAccount.getBalance())
                                && newBook.getF_spotid().equals(oldBook.getF_spotid());
                    }
                    if (mData instanceof ShelfEntity) {
                        ShelfEntity newShelf = (ShelfEntity) data.get(newItemPosition);
                        ShelfEntity oldShelf = (ShelfEntity) mData.get(newItemPosition);
                        return Objects.equals(newShelf.getSpotid(), oldShelf.getSpotid())
                                && Objects.equals(newShelf.getCategory(), oldShelf.getCategory())
                                && Objects.equals(newShelf.getDesc(), oldShelf.getDesc())
                                && newShelf.getDesc().equals(oldShelf.getDesc());
                    }
                    return false;
                }
            });
            mData = data;
            result.dispatchUpdatesTo(this);
        }
    }

}

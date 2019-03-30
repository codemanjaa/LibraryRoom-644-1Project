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
import android.widget.TextView;
import com.hevs.codemanja.roomdbdemo.Database.entity.ShelfEntity;
import com.hevs.codemanja.roomdbdemo.R;

// RecycleView
public class ShelfAdapter extends ListAdapter<ShelfEntity, ShelfAdapter.shelfHolder> {
    private OnItemClickListner listner;

    public ShelfAdapter() {
        super(DIFF_CALLBACK);
    }

    private  static final DiffUtil.ItemCallback<ShelfEntity> DIFF_CALLBACK = new DiffUtil.ItemCallback<ShelfEntity>() {
        @Override
        public boolean areItemsTheSame(@NonNull ShelfEntity entity, @NonNull ShelfEntity t1) {
            return entity.getSpotid() == t1.getSpotid();
        }

        @Override
        public boolean areContentsTheSame(@NonNull ShelfEntity entity, @NonNull ShelfEntity t1) {
            return entity.getSpotid().equals(t1.getSpotid());
        }
    };
    @NonNull
    @Override
    public shelfHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.listview_shelf,viewGroup,false);
        return new shelfHolder(itemView) ;
    }

    @Override
    public void onBindViewHolder(@NonNull shelfHolder bookHolder, int i) {

        ShelfEntity currenbooks = getItem(i);
        bookHolder.textView.setText(currenbooks.getSpotid());
        bookHolder.textViewCategory.setText(currenbooks.getDesc());
        bookHolder.textViewSpot.setText(currenbooks.getCategory());

    }




    public ShelfEntity getBook(int pos){
        return getItem(pos);
    }

    class shelfHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        private TextView textViewCategory;
        private TextView textViewSpot;
        private Button edit;
        private Button delete;


        public shelfHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewTitle);
            textViewCategory = itemView.findViewById(R.id.textViewCategory);
            textViewSpot = itemView.findViewById(R.id.textViewSpot);
            edit = itemView.findViewById(R.id.buttonEdit);
            delete = itemView.findViewById(R.id.buttonDelete);

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
        void onItemClick(ShelfEntity entity);
    }

    public void setOnItemClickListner(OnItemClickListner listner){
        this.listner = listner;
    }

}




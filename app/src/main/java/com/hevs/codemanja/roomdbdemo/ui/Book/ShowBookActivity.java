package com.hevs.codemanja.roomdbdemo.ui.Book;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.hevs.codemanja.roomdbdemo.Adapter.BookAdapter;
import com.hevs.codemanja.roomdbdemo.Database.entity.BookEntity;
import com.hevs.codemanja.roomdbdemo.R;
import com.hevs.codemanja.roomdbdemo.ui.Transaction.MainActivity;
import com.hevs.codemanja.roomdbdemo.viewmodel.BookViewModel;

import org.apache.log4j.chainsaw.Main;

import java.util.ArrayList;
import java.util.List;
//import static com.hevs.codemanja.roomdbdemo.ui.Transaction.MainActivity.libraryDB;



public class ShowBookActivity extends AppCompatActivity {
    public static final int ADD_NOTE_REQUEST = 1;
    public static final int EDIT_NOTE_REQUEST = 2;
    private BookViewModel bookViewModel;
    //Button buttonEdit;
    Button buttonUpdate;
    Button buttonDelete;


    List<BookEntity> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_show);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        Toolbar toolbar =
                (Toolbar) findViewById(R.id.storage_toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        this.setTitle(R.string.bookliste);
// Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();


// Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final BookAdapter adapter = new BookAdapter();
        recyclerView.setAdapter(adapter);



        bookViewModel = ViewModelProviders.of(this).get(BookViewModel.class);
        bookViewModel.getAllBooks().observe(this, new Observer<List<BookEntity>>() {
            @Override
            public void onChanged(@Nullable List<BookEntity> bookEntities) {
                // update Recyclerview
               adapter.submitList(bookEntities);
            }
        });

        // deleting an item
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

               // bookViewModel.delete(adapter.getBook(viewHolder.getAdapterPosition()));
                Toast.makeText(ShowBookActivity.this,"Book deleted",Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        //edit book
        adapter.setOnItemClickListner(new BookAdapter.OnItemClickListner() {
            @Override
            public void onItemClick(BookEntity entity) {
                Intent intent = new Intent(ShowBookActivity.this, AddBookActivity.class);
                intent.putExtra(AddBookActivity.EXTRA_ID,entity.getBid());
                intent.putExtra(AddBookActivity.EXTRA_TITLE,entity.getTitle());
              //  intent.putExtra(AddBookActivity.EXTRA_CATEGORY,entity.getCategory());
                intent.putExtra(AddBookActivity.EXTRA_SPOTID,entity.getF_spotid());
                startActivityForResult(intent, EDIT_NOTE_REQUEST);
            }
        });

        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                  //      .setAction("Action", null).show();

                // From the float button start the Addbook activity
               // startActivity(new Intent(ShowBookActivity.this, AddBookActivity.class));

                Intent intent = new Intent(ShowBookActivity.this,AddBookActivity.class);


                startActivityForResult(intent,ADD_NOTE_REQUEST);

            }
        });


        buttonUpdate = findViewById(R.id.buttonEdit);
        buttonDelete = findViewById(R.id.buttonDelete);

    /*  buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





            }
        });

       /** bookList = new ArrayList<>();

         bookList = (List<BookEntity>) libraryDB.bookDao().getAllBooks();

         Log.d("TAG", bookList.toString());
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));**/

/**
       for(int i=0; i<bookList.size(); i++){

            String img = bookList.get(i).getCategory().substring(0,1);

            switch (img){

                case "A":
                    bookList.get(i).setImage(R.drawable.ab);
                    break;

                case "B":
                    bookList.get(i).setImage(R.drawable.b);
                    break;

                case "K":
                    bookList.get(i).setImage(R.drawable.k);
                    break;

                    default:bookList.get(i).setImage(R.drawable.c);

            }


        }

**/


/**
        bookAdapter = new BookAdapter(this, bookList );
        recyclerView.setAdapter(bookAdapter);
**/

      //  buttonEdit = findViewById(R.id.buttonEdit);



/*        buttonEdit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

              //startActivity(new Intent(ShowBookActivity.this, AddBookActivity.class));

            }
        });

*/



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK){
            String title = data.getStringExtra(AddBookActivity.EXTRA_TITLE);
            String category = data.getStringExtra(AddBookActivity.EXTRA_CATEGORY);
            String spotId = data.getStringExtra(AddBookActivity.EXTRA_SPOTID);

            //BookEntity bookEntity = new BookEntity(title,category,spotId);
            //bookViewModel.insert(bookEntity);

            Toast.makeText(this,"Book Saved", Toast.LENGTH_SHORT);

        }else if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {
             int id = data.getIntExtra(AddBookActivity.EXTRA_ID,-1);

             if(id == -1){
                 Toast.makeText(this,"Book is not updated", Toast.LENGTH_SHORT);
                 return;
             }
            String title = data.getStringExtra(AddBookActivity.EXTRA_TITLE);
            String category = data.getStringExtra(AddBookActivity.EXTRA_CATEGORY);
            String spotId = data.getStringExtra(AddBookActivity.EXTRA_SPOTID);

           // BookEntity bookEntity = new BookEntity(title, category, spotId);
        //    bookEntity.setBid(id);
          //  bookViewModel.update(bookEntity);
        }else {
            Toast.makeText(this,"Book not Saved", Toast.LENGTH_SHORT);
        }
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data)  {
        super.onActivityReenter(resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.drawermenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delBooks:
                bookViewModel.deleteAllBooks();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }
}

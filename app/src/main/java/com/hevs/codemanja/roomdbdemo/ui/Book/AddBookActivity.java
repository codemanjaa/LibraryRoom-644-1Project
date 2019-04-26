package com.hevs.codemanja.roomdbdemo.ui.Book;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.storage.StorageManager;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.hevs.codemanja.roomdbdemo.Database.entity.BookEntity;
import com.hevs.codemanja.roomdbdemo.Database.entity.ShelfEntity;
import com.hevs.codemanja.roomdbdemo.R;
import com.hevs.codemanja.roomdbdemo.viewmodel.ShelfViewModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class AddBookActivity extends AppCompatActivity {

    public static final String EXTRA_ID =
            "com.hevs.codemanja.roomdbdemo.ui.Book.EXTRA_ID";

    public static final String EXTRA_TITLE =
            "com.hevs.codemanja.roomdbdemo.ui.Book.EXTRA_TITLE";

    public static final String EXTRA_CATEGORY =
            "com.hevs.codemanja.roomdbdemo.ui.Book.EXTRA_CATEGORY";

    public static final String EXTRA_SPOTID =
            "com.hevs.codemanja.roomdbdemo.ui.Book.EXTRA_SPOTID";
    private static final int GET_FROM_GALLERY = 4;


    private EditText editTextG, editTextTitle, editTextSpotId;
    private Button buttonAdd;
    private ImageButton imageButton;
    private TextView textViewG;
    private Spinner spinnerCategory, spinnerLocation;
    private static String category;
    int requestCode;
    int resultCode;
    Intent imageReturnedIntent;
    private  static final int PICK_IMAGE_REQUEST = 1;
    private Button mButtonChooseImage;
    private ImageView mImageView;

    private Uri mImageUri;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;



    public AddBookActivity(){

    }



    // Validation for the EditText

    public static boolean isEmpty(EditText editText) {

        String input = editText.getText().toString().trim();
        return input.length() == 0;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_add);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextSpotId = findViewById(R.id.editTextSpotId);
        textViewG = findViewById(R.id.textViewG);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        mImageView = findViewById(R.id.imageView4);
        mButtonChooseImage = findViewById(R.id.addimage);
        buttonAdd = findViewById(R.id.buttonAddBook);

        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");


        // my_child_toolbar is defined in the layout file
        Toolbar myChildToolbar =
                (Toolbar) findViewById(R.id.storage_toolbar);
        setSupportActionBar(myChildToolbar);

// Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        myChildToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

// Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        // adding data from selected book
        if(intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Book");
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            //editTextG.setText(intent.getStringExtra(EXTRA_CATEGORY));
            editTextSpotId.setText(intent.getStringExtra(EXTRA_SPOTID));
            buttonAdd.setText("Update Book");
            editTextSpotId.setEnabled(false);
            textViewG.setTextColor(Color.RED);
            textViewG.setText("Spot Modification Not Allowed");


        }else {
            setTitle("Add Book");
        }

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveBook();

            }

        });





        editTextTitle = findViewById(R.id.editTextTitle);
        editTextSpotId = findViewById(R.id.editTextSpotId);
        buttonAdd = findViewById(R.id.buttonAddBook);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        spinnerLocation = findViewById(R.id.spinnerLocation);

        spinnerLocation.setVisibility(View.INVISIBLE);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.select_dialog_item);
        spinnerCategory.setAdapter(adapter);


        buttonAdd.setEnabled(false);

        spinnerLocation.setAdapter(adapter);
        spinnerLocation.setVisibility(View.INVISIBLE);

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = parent.getItemAtPosition(position).toString(); //this is your selected item




            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        editTextSpotId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(editTextSpotId.getText().toString().length() >=3){
                    buttonAdd.setEnabled(true);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {


                if(editTextTitle.getText().equals("")){
                    AlertDialog alertDialog = new AlertDialog.Builder(AddBookActivity.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Please enter the Title");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                    editTextTitle.requestFocus();
                }


            }
        });


        mButtonChooseImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openFileChooser();;
            }
        });



    }
    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == 1) {
            final Bundle extras = data.getExtras();
            if (extras != null) {
                //Get image
                Bitmap bitmap = extras.getParcelable("data");
                imageButton.setImageBitmap(null);
                imageButton.setImageBitmap(bitmap);

            }
        }
    }*/

    private String getFileExtension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void saveBook() {

        String title = editTextTitle.getText().toString();
        String spotId = editTextSpotId.getText().toString();


        if(mImageUri != null){
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()+"."+getFileExtension(mImageUri));

            fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            String url = taskSnapshot.getStorage().getDownloadUrl().toString();
                            Intent data = new Intent();
                            data.putExtra(EXTRA_TITLE, title);
                            data.putExtra(EXTRA_CATEGORY, url);
                            data.putExtra(EXTRA_SPOTID, spotId);

                            int id = getIntent().getIntExtra(EXTRA_ID, -1);
                            if (id != -1) {
                                data.putExtra(EXTRA_ID, id);
                            }

                            setResult(RESULT_OK, data);
                            finish();

                        }
                    });
        }





    }

    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null){
            mImageUri = data.getData();

            //Picasso.with(this).load(mImageUri).into(mImageView);
            mImageView.setImageURI(mImageUri);

        }
    }



    private void DeleteBook() {

        String title = editTextTitle.getText().toString();
        String catageory =  editTextG.getText().toString();
        String spotId = editTextSpotId.getText().toString();

        if(title.trim().isEmpty()|| catageory.trim().isEmpty()){
            Toast.makeText(this,"please enter values",Toast.LENGTH_SHORT).show();
            return;
        }

//        BookEntity bookEntity = new BookEntity(title, catageory, spotId);

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE,title);
        data.putExtra(EXTRA_CATEGORY,catageory);
        data.putExtra(EXTRA_SPOTID, spotId);
        setResult(RESULT_OK,data);
        finish();

    }




}
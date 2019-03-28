package com.hevs.codemanja.roomdbdemo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.support.annotation.NonNull;

import com.hevs.codemanja.roomdbdemo.Database.entity.BookEntity;
import com.hevs.codemanja.roomdbdemo.Database.repo.BookRepo;


import java.util.List;

public class BookViewModel extends AndroidViewModel {

   private BookRepo repository;
   private LiveData<List<BookEntity>> allBooks;


    public BookViewModel(@NonNull Application application) {
        super(application);
        repository = new BookRepo(application);
        allBooks = repository.getAllBooks();
    }

    public void insert(BookEntity book) {
        repository.insert(book);
    }

    public void update(BookEntity book){
        repository.update(book);
    }

    public void delete(BookEntity book){
        repository.delete(book);
    }

    public LiveData<List<BookEntity>> getAllBooks(){
        return allBooks;
    }


/**
    private Context applicationContext;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<Book> observableClient;

    public BookViewModel(@NonNull Application application,
                         final int bookId, BookRepo bookRepo) {
        super(application);

        repository = bookRepo;

        applicationContext = getApplication().getApplicationContext();

        observableClient = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        observableClient.setValue(null);

        //  LiveData<Book> client = repository.getBook(title, applicationContext);

        // observe the changes of the client entity from the database and forward them

        //observableClient.addSource(client, observableClient::setValue);
    }

    /**
     * A creator is used to inject the account id into the ViewModel
     */
   /** public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;**/

        //  private final String title;

        //   private final BookRepo repository;


       // public Factory(@NonNull Application application, String title) {
       //     this.application = application;
            //  this.title = clientEmail;
            //   repository = ClientRepository.getInstance();
     //   }
/*
        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new BookViewModel(application, email, repository);
        }
    }

    /**
     * Expose the LiveData ClientEntity query so the UI can observe it.
     /*
    public LiveData<ClientEntity> getClient() {
        return observableClient;
    }

    public void createClient(ClientEntity client, OnAsyncEventListener callback) {
        repository.insert(client, callback, applicationContext);
    }

    public void updateClient(ClientEntity client, OnAsyncEventListener callback) {
        repository.update(client, callback, applicationContext);
    }

    public void deleteClient(ClientEntity client, OnAsyncEventListener callback) {
        repository.delete(client, callback, applicationContext);
    }



    */
    }




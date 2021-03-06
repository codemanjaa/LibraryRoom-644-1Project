package com.hevs.codemanja.roomdbdemo.viewmodel;

import android.app.Application;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.support.annotation.NonNull;


import com.hevs.codemanja.roomdbdemo.Database.repo.BookRepo;
import com.hevs.codemanja.roomdbdemo.entity.Book;
import com.hevs.codemanja.roomdbdemo.util.OnAsyncEventListener;


public class BookViewModel extends AndroidViewModel {

    private BookRepo repository;

    private Context applicationContext;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<Book> observableClient;

    public BookViewModel(@NonNull Application application,
                           final String title, BookRepo bookRepo) {
        super(application);

        repository = bookRepo;

        applicationContext = getApplication().getApplicationContext();

        observableClient = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        observableClient.setValue(null);

        LiveData<Book> book = repository.getBook(title, applicationContext);

        // observe the changes of the client entity from the database and forward them
        observableClient.addSource(book, observableClient::setValue);
    }

    /**
     * A creator is used to inject the account id into the ViewModel
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;

        private final String title;

        private final BookRepo repository;

        public Factory(@NonNull Application application, String bookTitle) {
            this.application = application;
            this.title = bookTitle;
            repository = BookRepo.getInstance();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new BookViewModel(application, title, repository);
        }
    }

    /**
     * Expose the LiveData BookEntity query so the UI can observe it.
     */
    public LiveData<Book> getCBook() {
        return observableClient;
    }

    public void createBooke(Book book, OnAsyncEventListener callback) {
        repository.insert(book, callback, applicationContext);
    }

    public void updateBook(Book book, OnAsyncEventListener callback) {
        repository.update(book, callback, applicationContext);
    }

    public void deleteBook(Book client, OnAsyncEventListener callback) {
        repository.delete(client, callback, applicationContext);
    }

}


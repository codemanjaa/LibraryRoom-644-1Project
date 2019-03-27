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

import java.util.List;




public class BookListViewModel extends AndroidViewModel {

    private BookRepo repository;

    private Context applicationContext;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<List<Book>> observableClients;

    public BookListViewModel(@NonNull Application application, BookRepo clientRepository) {
        super(application);

        repository = clientRepository;

        applicationContext = getApplication().getApplicationContext();

        observableClients = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        observableClients.setValue(null);

        LiveData<List<Book>> clients = repository.getAllBooksLive(applicationContext);

        // observe the changes of the entities from the database and forward them
        observableClients.addSource(clients, observableClients::setValue);
    }

    /**
     * A creator is used to inject the account id into the ViewModel
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;

        private final BookRepo clientRepository;

        public Factory(@NonNull Application application) {
            this.application = application;
            clientRepository = BookRepo.getInstance();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new BookListViewModel(application, clientRepository);
        }
    }

    /**
     * Expose the LiveData ClientEntities query so the UI can observe it.
     */
    public LiveData<List<Book>> getAllbooks() {
        return observableClients;
    }
}

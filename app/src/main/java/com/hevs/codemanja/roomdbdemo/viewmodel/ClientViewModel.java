package com.hevs.codemanja.roomdbdemo.viewmodel;

import android.app.Application;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.support.annotation.NonNull;


import com.hevs.codemanja.roomdbdemo.Database.repo.ClientRepository;
import com.hevs.codemanja.roomdbdemo.entity.ClientEntity;
import com.hevs.codemanja.roomdbdemo.util.OnAsyncEventListener;




public class ClientViewModel extends AndroidViewModel {

    private ClientRepository repository;

    private Context applicationContext;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<ClientEntity> observableClient;

    public ClientViewModel(@NonNull Application application,
                           final String email, ClientRepository clientRepository) {
        super(application);

        repository = clientRepository;

        applicationContext = getApplication().getApplicationContext();

        observableClient = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        observableClient.setValue(null);

        LiveData<ClientEntity> client = repository.getClient(email, applicationContext);

        // observe the changes of the client entity from the database and forward them
        observableClient.addSource(client, observableClient::setValue);
    }

    /**
     * A creator is used to inject the account id into the ViewModel
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;

        private final String email;

        private final ClientRepository repository;

        public Factory(@NonNull Application application, String clientEmail) {
            this.application = application;
            this.email = clientEmail;
            repository = ClientRepository.getInstance();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new ClientViewModel(application, email, repository);
        }
    }

    /**
     * Expose the LiveData ClientEntity query so the UI can observe it.
     */
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
}

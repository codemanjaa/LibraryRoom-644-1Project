package com.hevs.codemanja.roomdbdemo.Database.repo;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.hevs.codemanja.roomdbdemo.Database.LibraryDB;
import com.hevs.codemanja.roomdbdemo.Database.dao.BookDao;
import com.hevs.codemanja.roomdbdemo.Database.entity.BookEntity;

import java.util.List;

public class BookRepo {

    private BookDao bookDao;
    private LiveData<List<BookEntity>> allBooks;


    public BookRepo(Application application){
        LibraryDB database = LibraryDB.getInstance(application);
        bookDao = database.bookDao();
        allBooks = bookDao.getAllBooks();
    }

    public void insert(BookEntity book){
        new InsertBookAsyncTask(bookDao).execute(book);
    }

    public void update(BookEntity book){
        new UpdateBookAsyncTask(bookDao).execute(book);

    }
    public void delete(BookEntity book){
        new DeleteBookAsyncTask(bookDao).execute(book);
    }

    public LiveData<List<BookEntity>> getAllBooks(){
         return allBooks;
    }


   public void deleteAllBooks(){
        new DeleteAllBooksAsyncTask(bookDao).execute();
   }


    private static class InsertBookAsyncTask extends AsyncTask<BookEntity,Void,Void>{
        private BookDao bookDao;

        private InsertBookAsyncTask(BookDao bookDao){
            this.bookDao = bookDao;
        }
        @Override
        protected Void doInBackground(BookEntity... bookEntities) {
            bookDao.insert(bookEntities[0]);
            return null;
        }
    }

    private static class UpdateBookAsyncTask extends AsyncTask<BookEntity,Void,Void>{
        private BookDao bookDao;

        private UpdateBookAsyncTask(BookDao bookDao){
            this.bookDao = bookDao;
        }
        @Override
        protected Void doInBackground(BookEntity... bookEntities) {
            bookDao.update(bookEntities[0]);
            return null;
        }
    }

    private static class DeleteAllBooksAsyncTask extends AsyncTask<Void,Void,Void>{
        private BookDao bookDao;

        private DeleteAllBooksAsyncTask(BookDao bookDao){
            this.bookDao = bookDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            bookDao.deleteAll();
            return null;
        }
    }


    private static class DeleteBookAsyncTask extends AsyncTask<BookEntity,Void,Void>{
        private BookDao bookDao;

        private DeleteBookAsyncTask(BookDao bookDao){
            this.bookDao = bookDao;
        }
        @Override
        protected Void doInBackground(BookEntity... bookEntities) {
            bookDao.delete(bookEntities[0]);
            return null;
        }
    }


 /*
    private static BookRepo instance;

    private BookRepo() {}

    public static BookRepo getInstance() {
        if (instance == null) {
            synchronized (BookRepo.class) {
                if (instance == null) {
                    instance = new BookRepo();
                }
            }
        }
        return instance;
    }*/

    /*
    public LiveData<Book> getClient(final String email, Context context) {
        return LibraryDB.getInstance(context).bookDao().getByTitle(title);
    }
    */

/**
    public LiveData<List<Book>> getAllBooks(Context context) {
        return (LiveData<List<Book>>) LibraryDB.getInstance(context).bookDao().getAllBooks();
    }**/
/*
    public void insert(final Book book, OnAsyncEventListener callback, Context context) {
        new CreateBook(context, callback).execute(book);
    }
*/
 /*   public void update(final Book book, OnAsyncEventListener callback, Context context) {
        new UpdateBook(context, callback).execute(book);
    }*/
/*
    public void delete(final Book book, OnAsyncEventListener callback, Context context) {
        new DeleteBook(context, callback).execute(book);
    }
*/
}

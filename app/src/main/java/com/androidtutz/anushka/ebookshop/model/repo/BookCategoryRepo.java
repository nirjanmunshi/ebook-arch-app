package com.androidtutz.anushka.ebookshop.model.repo;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.androidtutz.anushka.ebookshop.model.Book;
import com.androidtutz.anushka.ebookshop.model.Category;
import com.androidtutz.anushka.ebookshop.model.dao.BookDAO;
import com.androidtutz.anushka.ebookshop.model.dao.CategoryDAO;
import com.androidtutz.anushka.ebookshop.model.database.BookDatabase;

import java.util.List;

public class BookCategoryRepo {
    private CategoryDAO categoryDAO;
    private BookDAO bookDAO;
    private LiveData<List<Category>> categoryList;
    private LiveData<List<Book>> bookList;

    public BookCategoryRepo(Application application) {
        BookDatabase bookDatabase = BookDatabase.getInstance(application);
        categoryDAO = bookDatabase.categoryDao();
        bookDAO = bookDatabase.bookDao();
    }

    public LiveData<List<Category>> getCategoryList() {
        return categoryDAO.getAllCategories();
    }

    public LiveData<List<Book>> getBookList() {
        return bookDAO.getAllBooks();
    }

    public LiveData<List<Book>> getBookListByCategory(int categoryId) {
        return bookDAO.getBookById(categoryId);
    }

    public void insertCategory(Category category) {
        new InsertCategoryAsyncTask(categoryDAO).execute(category);
    }

    public void updateCategory(Category category) {
        new UpdateCategoryAsyncTask(categoryDAO).execute(category);
    }

    public void deleteCategory(Category category) {
        new DeleteCategoryAsyncTask(categoryDAO).execute(category);
    }

    public void insertBook(Book book) {
        new InsertBookAsyncTask(bookDAO).execute(book);
    }

    public void updateBook(Book book) {
        new UpdateBookAsyncTask(bookDAO).execute(book);
    }

    public void deleteBook(Book book) {
        new DeleteBookAsyncTask(bookDAO).execute(book);
    }

    private static class InsertCategoryAsyncTask extends AsyncTask<Category, Void, Void> {

        private CategoryDAO categoryDAO;

        InsertCategoryAsyncTask(CategoryDAO categoryDAO) {
            this.categoryDAO = categoryDAO;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDAO.insert(categories[0]);
            return null;
        }
    }

    private static class UpdateCategoryAsyncTask extends AsyncTask<Category, Void, Void> {

        private CategoryDAO categoryDAO;

        UpdateCategoryAsyncTask(CategoryDAO categoryDAO) {
            this.categoryDAO = categoryDAO;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDAO.update(categories[0]);
            return null;
        }
    }

    private static class DeleteCategoryAsyncTask extends AsyncTask<Category, Void, Void> {

        private CategoryDAO categoryDAO;

        DeleteCategoryAsyncTask(CategoryDAO categoryDAO) {
            this.categoryDAO = categoryDAO;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDAO.delete(categories[0]);
            return null;
        }
    }

    private static class InsertBookAsyncTask extends AsyncTask<Book, Void, Void> {

        private BookDAO bookDAO;

        InsertBookAsyncTask(BookDAO bookDAO) {
            this.bookDAO = bookDAO;
        }

        @Override
        protected Void doInBackground(Book... books) {
            bookDAO.insert(books[0]);
            return null;
        }
    }

    private static class UpdateBookAsyncTask extends AsyncTask<Book, Void, Void> {

        private BookDAO bookDAO;

        UpdateBookAsyncTask(BookDAO bookDAO) {
            this.bookDAO = bookDAO;
        }

        @Override
        protected Void doInBackground(Book... books) {
            bookDAO.update(books[0]);
            return null;
        }
    }

    private static class DeleteBookAsyncTask extends AsyncTask<Book, Void, Void> {

        private BookDAO bookDAO;

        DeleteBookAsyncTask(BookDAO bookDAO) {
            this.bookDAO = bookDAO;
        }

        @Override
        protected Void doInBackground(Book... books) {
            bookDAO.delete(books[0]);
            return null;
        }
    }


}

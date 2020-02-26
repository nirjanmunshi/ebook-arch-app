package com.androidtutz.anushka.ebookshop.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.androidtutz.anushka.ebookshop.model.Book;
import com.androidtutz.anushka.ebookshop.model.Category;
import com.androidtutz.anushka.ebookshop.model.repo.BookCategoryRepo;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private BookCategoryRepo bookCategoryRepo;
    private LiveData<List<Category>> categoryList;
    private LiveData<List<Book>> bookList;
    private LiveData<List<Book>> bookListByCategory;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        bookCategoryRepo = new BookCategoryRepo(application);
    }

    public LiveData<List<Category>> getCategoryList() {
        categoryList = bookCategoryRepo.getCategoryList();
        return categoryList;
    }

    public LiveData<List<Book>> getBookList() {
        bookList = bookCategoryRepo.getBookList();
        return bookList;
    }

    public LiveData<List<Book>> getBookListByCategory(int categoryId) {
        bookListByCategory = bookCategoryRepo.getBookListByCategory(categoryId);
        return bookListByCategory;
    }

    public void addNewBook(Book book) {
        bookCategoryRepo.insertBook(book);
    }

    public void updateBook(Book book) {
        bookCategoryRepo.updateBook(book);
    }

    public void deleteBook(Book book) {
        bookCategoryRepo.deleteBook(book);
    }

    public void addNewCategory(Category category) {
        bookCategoryRepo.insertCategory(category);
    }

    public void updateCategory(Category category) {
        bookCategoryRepo.updateCategory(category);
    }

    public void deleteCategory(Category category) {
        bookCategoryRepo.deleteCategory(category);
    }







}

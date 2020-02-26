package com.androidtutz.anushka.ebookshop.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.androidtutz.anushka.ebookshop.model.Book;

import java.util.List;

@Dao
public interface BookDAO {
    @Insert
    void insert(Book book);

    @Update
    void update(Book book);

    @Delete
    void delete(Book book);

    @Query("select * from tbl_book")
    LiveData<List<Book>> getAllBooks();

    @Query("select * from tbl_book where category_id = :categoryId")
    LiveData<List<Book>> getBookById(int categoryId);
}

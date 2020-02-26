package com.androidtutz.anushka.ebookshop.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.androidtutz.anushka.ebookshop.model.Category;

import java.util.List;

@Dao
public interface CategoryDAO {
    @Insert
    void insert(Category category);

    @Update
    void update(Category category);

    @Delete
    void delete(Category category);

    @Query("select * from tbl_category")
    LiveData<List<Category>> getAllCategories();
}

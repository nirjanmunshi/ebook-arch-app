package com.androidtutz.anushka.ebookshop;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidtutz.anushka.ebookshop.adapter.BookAdapter;
import com.androidtutz.anushka.ebookshop.databinding.ActivityMainBinding;
import com.androidtutz.anushka.ebookshop.model.Book;
import com.androidtutz.anushka.ebookshop.model.Category;
import com.androidtutz.anushka.ebookshop.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;
    private MainActivityClickHandler clickHandler;
    private Category spinnerSelectedCategory;
    private List<Category> categoryList;
    private List<Book> bookList;
    private RecyclerView bookRecyclerView;
    private BookAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View v = activityMainBinding.getRoot();
        setContentView(v);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // attach click handler for main activity
        clickHandler = new MainActivityClickHandler();
        activityMainBinding.setMainActivityClickHandlers(clickHandler);

        // initialize view model
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
//        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        mainActivityViewModel.getCategoryList().observe(this, categories -> {
            categoryList = categories;
            for (Category category : categories) {
                Log.d(TAG, "observe: " + category.getCategoryName());
            }
            ArrayAdapter<Category> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categoryList);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
            activityMainBinding.setSpinnerAdapter(arrayAdapter);
        });

    }

    private void loadRecyclerView() {
        bookRecyclerView = activityMainBinding.secondaryLayout.rvBooks;
        bookRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookRecyclerView.setHasFixedSize(true);

        adapter = new BookAdapter();
        bookRecyclerView.setAdapter(adapter);
        adapter.setBookList(bookList);


    }

    private void loadBookArrayList(int categoryId) {
        mainActivityViewModel.getBookListByCategory(categoryId).observe(this, books -> {
            bookList = books;
            loadRecyclerView();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class MainActivityClickHandler {

        public void onFabClicked(View v) {
            Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
        }

        public void onSpinnerItemSelected(AdapterView<?> parent,View view, int position, long id){
            spinnerSelectedCategory = (Category) parent.getItemAtPosition(position);
            loadBookArrayList(spinnerSelectedCategory.getId());
//            Toast.makeText(parent.getContext(), spinnerSelectedCategory.getCategoryName(), Toast.LENGTH_SHORT).show();
        }



    }
}

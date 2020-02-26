package com.androidtutz.anushka.ebookshop.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidtutz.anushka.ebookshop.databinding.RowBookBinding;
import com.androidtutz.anushka.ebookshop.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {


    private OnRecyclerViewItemClickListener listener;
    private List<Book> bookList = new ArrayList<>();



    public void setListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowBookBinding bookBinding = RowBookBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new BookViewHolder(bookBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.bookBinding.setBook(book);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
        notifyDataSetChanged();
    }

    class BookViewHolder extends RecyclerView.ViewHolder{
        private RowBookBinding bookBinding;
        public BookViewHolder(@NonNull RowBookBinding rowBookBinding) {
            super(rowBookBinding.getRoot());
            bookBinding = rowBookBinding;
            bookBinding.getRoot().setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION)
                    listener.onItemClick(bookList.get(position));
            });
        }
    }

    public interface OnRecyclerViewItemClickListener{
        void onItemClick(Book book);
    }
}

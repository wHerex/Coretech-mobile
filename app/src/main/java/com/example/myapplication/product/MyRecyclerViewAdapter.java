package com.example.myapplication.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coretech_mobile.R;
import com.example.myapplication.model.Product;

import java.util.Arrays;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>{

    Product[] products;
    Context context;

    public MyRecyclerViewAdapter(Context context, List<Product> products){
        this.context = context;
        this.products = products.toArray(new Product[0]);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.MyViewHolder holder, int position) {
        Long id = products[position].getId();
        String name = products[position].getName();
        holder.textView.setText(id + ". " + name);
    }

    @Override
    public int getItemCount() {
        return products.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }

}
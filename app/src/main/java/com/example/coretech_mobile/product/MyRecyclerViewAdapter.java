package com.example.coretech_mobile.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coretech_mobile.R;
import com.example.coretech_mobile.model.Product;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>{

    Product[] products;
    Context context;
    OnProductListener onProductListener;
    Button deleteButton;

    public MyRecyclerViewAdapter(Context context, List<Product> products, OnProductListener onProductListener){
        this.context = context;
        this.products = products.toArray(new Product[0]);
        this.onProductListener = onProductListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row_layout, parent, false);
        return new MyViewHolder(view, onProductListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.MyViewHolder holder, int position) {
        Long id = products[position].getId();
        String name = products[position].getName();
        holder.product = products[position];
        holder.textView.setText(id + ". " + name);
    }

    @Override
    public int getItemCount() {
        return products.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        Product product;
        OnProductListener onProductListener;
        TextView textView;

        public MyViewHolder(@NonNull View itemView, OnProductListener onProductListener){
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
            this.onProductListener = onProductListener;
            deleteButton = itemView.findViewById(R.id.delete_button);
            deleteButton.setOnClickListener(c ->{
                onProductListener.onDeleteButtonClick(product.getId());
            });
        }


        @Override
        public void onClick(View v) {
        onProductListener.onProductClick(product);
        }
    }

    public interface OnProductListener {
        void onProductClick(Product product);
        void onDeleteButtonClick(Long id);
    }
}
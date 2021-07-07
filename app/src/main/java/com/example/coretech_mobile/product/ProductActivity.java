package com.example.coretech_mobile.product;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coretech_mobile.R;
import com.example.coretech_mobile.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductActivity extends AppCompatActivity implements MyRecyclerViewAdapter.OnProductListener, NewProductDialog.NewProductDialogListener, EditProductDialog.UpdateProductDialogListener {

    RecyclerView recyclerView;
    TextView textView;
    Product product;
    MyRecyclerViewAdapter adapter;
    MyRecyclerViewAdapter.OnProductListener onProductListener = this;
    Button addProductButton;
    ProductApiCall productApiCall;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);
        productApiCall = getProductApiCall();
        recyclerView = findViewById(R.id.recyclerView);
        addProductButton = findViewById(R.id.add_product_button);
        addProductButton.setOnClickListener(v -> {
            NewProductDialog productDialog = new NewProductDialog();
            productDialog.show(getSupportFragmentManager(), "ProductDialog");
        });
        getProducts();
    }

    @Override
    public void onProductClick(Product product) {
        EditProductDialog productDialog = new EditProductDialog();
        Bundle bundle = new Bundle();
        bundle.putLong("id", product.getId());
        bundle.putString("name", product.getName());
        bundle.putString("description", product.getDescription());
        bundle.putLong("price", product.getPrice());
        bundle.putLong("quantity", product.getQuantity());
        productDialog.setArguments(bundle);
        productDialog.show(getSupportFragmentManager(), "EditProductDialog");
    }

    @Override
    public void onDeleteButtonClick(Long id) {
        deleteProductRequest(id);
    }

    @Override
    public void saveProduct(Product product) {
        saveProductRequest(product);
    }

    @Override
    public void updateProduct(Product product) {
        updateProductRequest(product);
    }

    private void updateProductRequest(Product product) {
        productApiCall.updateProduct(product, product.getId()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                getProducts();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    private ProductApiCall getProductApiCall() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://coretech-app.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ProductApiCall.class);
    }

    private void saveProductRequest(Product product) {
        productApiCall.saveProduct(product).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                getProducts();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }

    private void getProducts() {
        Call<List<Product>> getProductCall = productApiCall.getProduct();
        getProductCallEnqueue(getProductCall);
    }

    private void getProductCallEnqueue(Call<List<Product>> getProductCall) {
        getProductCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(getApplicationContext(), response.body(), onProductListener);
                recyclerView.setAdapter(myRecyclerViewAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                textView.append("Request failed getProduct " + t);
            }
        });
    }

    private void deleteProductRequest(Long id) {
        productApiCall.deleteProduct(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                getProducts();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

}

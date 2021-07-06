package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.coretech_mobile.R;
import com.example.myapplication.model.Product;
import com.example.myapplication.product.ProductApiCall;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Product product;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textview);

        ProductApiCall productApiCall = getProductApiCall();

        //product = new Product(1250);

        getProduct(productApiCall);

        //saveProduct(productApiCall, product);

    }



    private ProductApiCall getProductApiCall() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://coretech-app.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ProductApiCall.class);
    }

    private void saveProduct(ProductApiCall productApiCall, Product product) {
        productApiCall.saveProduct(product).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                textView.setText(response.toString());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                //textView.setText("Request failed save Product" + t);

            }
        });
    }

    private void getProduct(ProductApiCall productApiCall) {
        Call<List<Product>> getProductCall = productApiCall.getProduct();
        getProductCallEnqueue(getProductCall);
    }

    private void getProductCallEnqueue(Call<List<Product>> getProductCall) {
        getProductCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.code() != 200){
                    textView.setText("Check the connection");
                    return;
                }

                String json = "";

                for(Product p : response.body()){
                    json += "ID= " + p.getId() +
                            "  price= " + p.getPrice() + "\n";
                }
                textView.append(json);

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                textView.append("Request failed getProduct " + t);
            }
        });
    }
}
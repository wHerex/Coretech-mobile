package com.example.myapplication.product;

import com.example.myapplication.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ProductApiCall {

    @GET("products")
    Call<List<Product>> getProduct();

    @POST("products")
    Call<Void> saveProduct(@Body Product product);

}

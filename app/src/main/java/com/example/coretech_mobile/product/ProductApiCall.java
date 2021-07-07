package com.example.coretech_mobile.product;

import com.example.coretech_mobile.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductApiCall {

    @GET("products")
    Call<List<Product>> getProduct();

    @POST("products")
    Call<Void> saveProduct(@Body Product product);

    @PUT("products/{id}")
    Call<Void> updateProduct(@Body Product product, @Path("id") Long id);

    @DELETE("products/{id}")
    Call<Void> deleteProduct(@Path("id") Long id);

}

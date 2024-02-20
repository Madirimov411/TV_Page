package com.uzb_khiva.storeapp.data.remote

import com.uzb_khiva.storeapp.model.Product
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("products")
    suspend fun getProducts(): Call<List<Product>>

}
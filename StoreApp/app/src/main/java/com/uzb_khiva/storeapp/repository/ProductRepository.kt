package com.uzb_khiva.storeapp.repository

import com.uzb_khiva.storeapp.data.remote.ApiService

class ProductRepository(private val apiService: ApiService) {

    suspend fun getAllProducts() = apiService.getProducts()

}
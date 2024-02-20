package com.uzb_khiva.noteapp.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val BASE_URL = "https://sheetdb.io/api/v1/1l7myrpzmymjh/"

    private val retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()


    val apiService: ApiService =
        retrofit.create(ApiService::class.java)


}
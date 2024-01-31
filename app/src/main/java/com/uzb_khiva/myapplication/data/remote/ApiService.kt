package com.uzb_khiva.myapplication.data.remote

import com.uzb_khiva.myapplication.model.Movie
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/{id}")
    fun getMovieById(
        @Path("id") id: Int,
        @Query("api_key") api_key: String = "99b4808386d0dc2136f0e6efe977a911",
    ): Call<Movie>

}
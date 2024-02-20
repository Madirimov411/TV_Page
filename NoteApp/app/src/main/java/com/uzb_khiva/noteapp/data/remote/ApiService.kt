package com.uzb_khiva.noteapp.data.remote

import com.uzb_khiva.noteapp.model.Note
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET(" ")
    suspend fun getAllNote(): Response<ArrayList<Note>>

    @GET("search?id={id}")
    suspend fun getNoteById(
        @Path("id") id: String
    ): Response<Note>

    @POST(" ")
    suspend fun addNote(
        @Body note: Note
    ): Response<Note>

    @PATCH("id/{id}")
    suspend fun updateNote(
        @Path("id") id: String,
        @Body note: Note
    ): Response<Note>

    @DELETE("id/{id}")
    suspend fun deleteNote(
        @Path("id") id: String
    ): Response<Note>

}
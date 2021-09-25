package com.example.notebookapp.network

import com.example.notebookapp.model.ResponseDelete
import com.example.notebookapp.model.GET.ResponseGET
import com.example.notebookapp.model.ResponseInsert
import com.example.notebookapp.model.ResponseUpdate
import retrofit2.Call
import retrofit2.http.*

interface ApiEndpoint {
    @GET("notebooks")
    fun getNotes(): Call<ResponseGET>

    @FormUrlEncoded
    @POST("notebooks")
    fun insertNotes(
        @Field("title") title: String,
        @Field("body") body: String
    ): Call<ResponseInsert>

    @FormUrlEncoded
    @POST("notebooks/{id}")
    fun updateNotes(
        @Path("id") id: String,
        @Field("title") title: String,
        @Field("body") body: String,
        @Query("_method") _method: String
    ): Call<ResponseUpdate>

    @DELETE("notebooks/{id}")
    fun deleteNotes(
        @Path("id") id: String
    ): Call<ResponseDelete>
}
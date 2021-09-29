package com.example.notebookapp.network

import com.example.notebookapp.model.ResponseDelete
import com.example.notebookapp.model.GET.ResponseGET
import com.example.notebookapp.model.ResponseCreateUsers
import com.example.notebookapp.model.ResponseInsert
import com.example.notebookapp.model.ResponseUpdate
import okhttp3.MultipartBody
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

    @Multipart
    @POST("users")
    fun createUsers(
        @Query("name") name: String,
        @Query("username") username: String,
        @Query("email") email: String,
        @Query("password") password: String,
        @Query("gender") gender: String,
        @Query("address") address: String,
        @Part image: MultipartBody.Part
    ): Call<ResponseCreateUsers>
}
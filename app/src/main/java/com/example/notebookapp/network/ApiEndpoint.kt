package com.example.notebookapp.network

import com.example.notebookapp.model.notes.ResponseDelete
import com.example.notebookapp.model.notes.GET.ResponseGET
import com.example.notebookapp.model.notes.ResponseInsert
import com.example.notebookapp.model.notes.ResponseUpdate
import com.example.notebookapp.model.users.GET.ResponseGetUsers
import com.example.notebookapp.model.users.ResponseCreateUsers
import com.example.notebookapp.model.users.ResponseDeleteUsers
import com.example.notebookapp.model.users.ResponseUpdateUsers
import com.example.notebookapp.model.users.login.ResponseLogin
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiEndpoint {

    //EndPoint Notebooks
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

    // EndPOint Users
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

    @GET("users")
    fun getUsers(): Call<ResponseGetUsers>

    @Multipart
    @POST("users")
    fun UpdateUsers(
        @Query("name") name: String,
        @Query("username") username: String,
        @Query("email") email: String,
        @Query("password") password: String,
        @Query("gender") gender: String,
        @Query("address") address: String,
        @Part image: MultipartBody.Part,
        @Query("_method") _method: String
    ): Call<ResponseUpdateUsers>

    @DELETE("users/{id}")
    fun deleteUsers(
        @Path("id") id: String
    ): Call<ResponseDeleteUsers>

    @FormUrlEncoded
    @POST("auth/login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ) :Call<ResponseLogin>
}
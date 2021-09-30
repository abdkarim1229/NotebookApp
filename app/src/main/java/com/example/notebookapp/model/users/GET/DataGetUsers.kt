package com.example.notebookapp.model.users.GET

import com.google.gson.annotations.SerializedName

data class DataGetUsers(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("username") val username :String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("address") val address: String,
    @SerializedName("image") val image: String
)
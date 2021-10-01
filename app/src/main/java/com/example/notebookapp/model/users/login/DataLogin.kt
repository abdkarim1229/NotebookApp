package com.example.notebookapp.model.users.login

import com.google.gson.annotations.SerializedName

data class DataLogin(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("username") val username: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("address") val address: String
)
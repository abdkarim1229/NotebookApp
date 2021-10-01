package com.example.notebookapp.model.users.login

import com.google.gson.annotations.SerializedName

data class ResponseLogin(
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("users") val users: DataLogin
)
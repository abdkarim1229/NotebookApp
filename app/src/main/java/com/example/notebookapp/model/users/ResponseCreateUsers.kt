package com.example.notebookapp.model.users

import com.google.gson.annotations.SerializedName

data class ResponseCreateUsers(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String
)
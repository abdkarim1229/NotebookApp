package com.example.notebookapp.model

import com.google.gson.annotations.SerializedName

data class ResponseUpdate(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String
)
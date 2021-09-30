package com.example.notebookapp.model.notes

import com.google.gson.annotations.SerializedName

data class ResponseInsert(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String
)
package com.example.notebookapp.model.GET

import com.google.gson.annotations.SerializedName

data class DataGET(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String,
    @SerializedName("updated_at") val updated_at: String
)
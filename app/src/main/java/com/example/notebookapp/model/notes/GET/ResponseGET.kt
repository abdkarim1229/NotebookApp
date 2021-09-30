package com.example.notebookapp.model.notes.GET

import com.google.gson.annotations.SerializedName

data class ResponseGET(
    @SerializedName("data") val dataGet: List<DataGET>
)
package com.example.notebookapp.model.GET

import com.example.notebookapp.model.GET.DataGET
import com.google.gson.annotations.SerializedName

data class ResponseGET(
    @SerializedName("data") val dataGet: List<DataGET>
)
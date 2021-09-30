package com.example.notebookapp.model.users.GET

import com.google.gson.annotations.SerializedName

class ResponseGetUsers(
    @SerializedName("data") val dataGetUsers: List<DataGetUsers>
)
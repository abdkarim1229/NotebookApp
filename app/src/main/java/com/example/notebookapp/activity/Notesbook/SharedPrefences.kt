package com.example.notebookapp.activity.Notesbook

import android.content.Context
import android.content.SharedPreferences
import hu.autsoft.krate.Krate
import hu.autsoft.krate.stringPref

class SharedPrefences(context: Context) : Krate {

    private val USERNAME: String = "username"
    private val PASSWORD: String = "password"
    private val NAME: String = "name"

    override val sharedPreferences: SharedPreferences = context.applicationContext.getSharedPreferences(
        "users", Context.MODE_PRIVATE
    )

    var username by stringPref(USERNAME, "")
    var password by stringPref(PASSWORD, "")
    var name by stringPref(NAME, "")

    fun logout() {
        sharedPreferences.edit().clear().apply()
    }
}
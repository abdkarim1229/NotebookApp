package com.example.notebookapp.activity.Notesbook.Users

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.HorizontalScrollView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notebookapp.R
import com.example.notebookapp.activity.Notesbook.Notes.NotesAdapter
import com.example.notebookapp.activity.Notesbook.SharedPrefences
import com.example.notebookapp.activity.Notesbook.insert.InsertActivity
import com.example.notebookapp.model.notes.GET.DataGET
import com.example.notebookapp.model.notes.GET.ResponseGET
import com.example.notebookapp.model.users.GET.DataGetUsers
import com.example.notebookapp.model.users.GET.ResponseGetUsers
import com.example.notebookapp.network.ApiService
import kotlinx.android.synthetic.main.activity_users.*
import kotlinx.android.synthetic.main.item_rv.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPrefences
    val adapternotes = NotesAdapter(arrayListOf())
    val adapter = UsersAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        supportActionBar?.hide()
        dologin(sharedPreferences)

        float_add_notes.setOnClickListener {
            startActivity(Intent(this, InsertActivity::class.java))
        }
        ln_add_users.setOnClickListener {
            startActivity(Intent(this, CreateUsersActivity::class.java))
        }
        ln_logout.setOnClickListener {
            doLogout(sharedPreferences)
        }



        rv_users.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_users.adapter = adapter
        getDataUsers()

        rv_notes.layoutManager = LinearLayoutManager(this)
        rv_notes.adapter = adapternotes
        getDataNotes()
    }

    private fun doLogout(sharedPreferences: SharedPrefences) {
        sharedPreferences.logout()
        Toast.makeText(this, "Berhasil Logout", Toast.LENGTH_SHORT).show()
        onResultLogout()
    }

    private fun onResultLogout() {
        finish()
    }

    private fun dologin(sharedPreferences: SharedPrefences) {
        onResultLogin(sharedPreferences)

    }

    private fun onResultLogin(sharedPreferences: SharedPrefences) {
        name_users.text = sharedPreferences.name
    }

    private fun getDataNotes() {
        ApiService.endpoint.getNotes().enqueue(object : Callback<ResponseGET> {
            override fun onResponse(call: Call<ResponseGET>, response: Response<ResponseGET>) {
                if (response.isSuccessful) {
                    val notes: ResponseGET? = response.body()
                    resultNotes(notes!!)
                } else {
                    Toast.makeText(this@UsersActivity, "Gagal", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseGET>, t: Throwable) {
                Toast.makeText(this@UsersActivity, "Gagal Memuat ${t}", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun resultNotes(data: ResponseGET) {
        val result: List<DataGET> = data.dataGet
        adapternotes.setData(result)
    }

    private fun getDataUsers() {
        ApiService.endpoint.getUsers().enqueue(object : Callback<ResponseGetUsers> {
            override fun onResponse(
                call: Call<ResponseGetUsers>,
                response: Response<ResponseGetUsers>
            ) {
                if (response.isSuccessful) {
                    val users: ResponseGetUsers? = response.body()
                    resultUsers(users!!)
                }
                Toast.makeText(this@UsersActivity, "Gagal", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<ResponseGetUsers>, t: Throwable) {
                Toast.makeText(this@UsersActivity, "${t}", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun resultUsers(data: ResponseGetUsers) {
        val result: List<DataGetUsers> = data.dataGetUsers
        adapter.setData(result)
    }


}
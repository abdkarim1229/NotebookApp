package com.example.notebookapp.activity.Notesbook.Users

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notebookapp.R
import com.example.notebookapp.model.users.GET.DataGetUsers
import com.example.notebookapp.model.users.GET.ResponseGetUsers
import com.example.notebookapp.network.ApiService
import kotlinx.android.synthetic.main.activity_users.*
import kotlinx.android.synthetic.main.item_rv.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersActivity : AppCompatActivity() {
    val adapter = UsersAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        rv_users.layoutManager = LinearLayoutManager(this)
        rv_users.adapter = adapter
        getDataUsers()
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
        val result : List<DataGetUsers> = data.dataGetUsers
        adapter.setData(result)

    }
}
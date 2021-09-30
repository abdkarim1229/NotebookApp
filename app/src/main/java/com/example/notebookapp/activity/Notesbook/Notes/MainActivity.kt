package com.example.notebookapp.activity.Notesbook.Notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notebookapp.R
import com.example.notebookapp.activity.Notesbook.Users.CreateUsersActivity
import com.example.notebookapp.activity.Notesbook.insert.InsertActivity
import com.example.notebookapp.model.GET.DataGET
import com.example.notebookapp.model.GET.ResponseGET
import com.example.notebookapp.network.ApiService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_notes.*
import kotlinx.android.synthetic.main.item_rv.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val adapterNotes = NotesAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        float_add.setOnClickListener {
            startActivity(Intent(this, InsertActivity::class.java))
        }
        add_user.setOnClickListener {
            startActivity(Intent(this, CreateUsersActivity::class.java))
        }


        rv_notes.layoutManager = LinearLayoutManager(this)
        rv_notes.adapter = adapterNotes
        getDataNotes()
    }

    private fun getDataNotes() {
        ApiService.endpoint.getNotes().enqueue(object : Callback<ResponseGET> {
            override fun onResponse(call: Call<ResponseGET>, response: Response<ResponseGET>) {
                if (response.isSuccessful) {
                    val notes: ResponseGET? = response.body()
                    resultNotes(notes!!)
                } else {
                    Toast.makeText(this@MainActivity, "Gagal", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseGET>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Gagal Memuat ${t}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun resultNotes(data: ResponseGET) {
        val result: List<DataGET> = data.dataGet
        adapterNotes.setData(result)
    }
}
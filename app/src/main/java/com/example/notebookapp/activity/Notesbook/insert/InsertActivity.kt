package com.example.notebookapp.activity.Notesbook.insert

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notebookapp.R
import com.example.notebookapp.model.notes.ResponseInsert
import com.example.notebookapp.network.ApiService
import kotlinx.android.synthetic.main.activity_insert.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InsertActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)
        fab_insert.setOnClickListener {
            ApiService.endpoint.insertNotes(
                insert_title.text.toString(),
                insert_body.text.toString()
            ).enqueue(object : Callback<ResponseInsert> {
                override fun onResponse(
                    call: Call<ResponseInsert>,
                    response: Response<ResponseInsert>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@InsertActivity, "Succes", Toast.LENGTH_SHORT).show()

                    } else {
                        Toast.makeText(this@InsertActivity, "Failed", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseInsert>, t: Throwable) {
                    Toast.makeText(this@InsertActivity, "Failed ${it}", Toast.LENGTH_SHORT).show()
                }
            })
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}

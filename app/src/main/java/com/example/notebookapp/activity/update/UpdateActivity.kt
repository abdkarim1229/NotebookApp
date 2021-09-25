package com.example.notebookapp.activity.update

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notebookapp.R
import com.example.notebookapp.activity.Notes.MainActivity
import com.example.notebookapp.model.ResponseUpdate
import com.example.notebookapp.network.ApiService
import kotlinx.android.synthetic.main.activity_insert.*
import kotlinx.android.synthetic.main.activity_update.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val intent = intent
        val title = intent.getStringExtra("title")
        val body = intent.getStringExtra("body")
        val id = intent.getStringExtra("id")

        update_id.setText(id)
        update_title.setText(title)
        update_body.setText(body)

        fab_update.setOnClickListener {
            ApiService.endpoint.updateNotes(
                update_id.text.toString(),
                update_title.text.toString(),
                update_body.text.toString(),
                "PUT"
            ).enqueue(object : Callback<ResponseUpdate> {
                override fun onResponse(
                    call: Call<ResponseUpdate>,
                    response: Response<ResponseUpdate>
                ) {
                    if (response.isSuccessful){
                        Toast.makeText(this@UpdateActivity, "Berhasil di Update", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseUpdate>, t: Throwable) {
                    Toast.makeText(this@UpdateActivity, "Failed ${it}", Toast.LENGTH_SHORT).show()
                }
            })
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
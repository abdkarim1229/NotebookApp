package com.example.notebookapp.activity.Notesbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notebookapp.R
import com.example.notebookapp.activity.Notesbook.Users.UsersActivity
import com.example.notebookapp.model.users.login.DataLogin
import com.example.notebookapp.model.users.login.ResponseLogin
import com.example.notebookapp.network.ApiService
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var sharedPrefences: SharedPrefences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener {
            ApiService.endpoint.login(
                login_username.text.toString(),
                login_password.text.toString()
            ).enqueue(object : Callback<ResponseLogin> {
                override fun onResponse(
                    call: Call<ResponseLogin>,
                    response: Response<ResponseLogin>
                ) {
                    if (response.isSuccessful) {
                        val responseLogin : ResponseLogin? = response.body()

                        if (responseLogin!!.status){
                            onResult(responseLogin)
                        }
                        Toast.makeText(this@LoginActivity, "Login Berhasil", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(this@LoginActivity, "Login Gagal", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "${t}", Toast.LENGTH_SHORT).show()

                }
            })
        }
        startActivity(Intent(this, UsersActivity::class.java))
    }

    private fun onResult(responseLogin: ResponseLogin) {
        sharedPrefences(sharedPrefences, responseLogin.users)
        finish()

    }

    fun sharedPrefences(sharedPrefences: SharedPrefences, dataLogin: DataLogin) {
        sharedPrefences.name = dataLogin.name
        sharedPrefences.username = dataLogin.username
    }
}
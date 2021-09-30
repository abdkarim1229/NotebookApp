package com.example.notebookapp.activity.Notesbook.Users

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notebookapp.R
import kotlinx.android.synthetic.main.activity_update.*
import kotlinx.android.synthetic.main.activity_update_users.*

class UpdateUsersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_users)
        val intent = intent
        val id = intent.getStringExtra("id")
        val name = intent.getStringExtra("name")
        val username = intent.getStringExtra("username")
        val email = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")
        val gender = intent.getStringExtra("gender")
        val address = intent.getStringExtra("address")
        val image = intent.getStringExtra("image")

        update_users_id.setText(id)
        update_users_name.setText(name)
        update_users_username.setText(username)
        update_users_email.setText(email)
        update_users_password.setText(password)
        update_users_female.setText(gender)
        update_users_alamat.setText(address)
//        update_users_image.setImageURI(image)

    }
}
package com.example.notebookapp.activity.Notesbook.Users

import android.content.Intent
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import com.example.notebookapp.R
import com.example.notebookapp.model.users.ResponseUpdateUsers
import com.example.notebookapp.network.ApiService
import kotlinx.android.synthetic.main.activity_create_users.*
import kotlinx.android.synthetic.main.activity_update.*
import kotlinx.android.synthetic.main.activity_update_users.*
import kotlinx.android.synthetic.main.activity_update_users.view.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class UpdateUsersActivity : AppCompatActivity() {

    private var image: String? = ""
    val REQUEST_GALLERY: Int = 9544

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

        update_users_id.setText(id)
        update_users_name.setText(name)
        update_users_username.setText(username)
        update_users_email.setText(email)
        update_users_password.setText(password)
        update_users_female.setText(gender)
        update_users_alamat.setText(address)

        update_users_image.setOnClickListener {
            var intent = Intent()
            intent.type = "image/*"
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent, "open gallery"), REQUEST_GALLERY)
        }
        btn_update_users.setOnClickListener {
            val file = File(image)
            val req = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file)
            val part = MultipartBody.Part.createFormData("image", file.name, req)
            ApiService.endpoint.UpdateUsers(
                create_name.text.toString(),
                create_username.text.toString(),
                create_email.text.toString(),
                create_password.text.toString(),
                create_male.text.toString(),
                create_alamat.text.toString(),
                part,
            "PUT"
            ).enqueue(object: Callback<ResponseUpdateUsers>{
                override fun onResponse(
                    call: Call<ResponseUpdateUsers>,
                    response: Response<ResponseUpdateUsers>
                ) {
                }

                override fun onFailure(call: Call<ResponseUpdateUsers>, t: Throwable) {
                }
            })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_GALLERY) {
                val dataimage: Uri? = data?.data
                val imageprojection = arrayOf(MediaStore.Images.Media.DATA)
                val cursor: Cursor? =
                    contentResolver.query(dataimage!!, imageprojection, null, null, null)
                if (cursor != null) {
                    cursor.moveToFirst()
                    val indexImage = cursor.getColumnIndex(imageprojection[0])
                    image = cursor.getString(indexImage)
                    if (image != null) {
                        val imageFile: File = File(image)
                        create_image.setImageBitmap(BitmapFactory.decodeFile(imageFile.absolutePath))
                    }
                }
            }
        }
    }
}
package com.example.notebookapp.activity.Notesbook

import android.app.Activity
import android.content.ContentUris
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.notebookapp.R
import com.example.notebookapp.model.ResponseCreateUsers
import com.example.notebookapp.network.ApiService

import kotlinx.android.synthetic.main.activity_create_users.*
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.jar.Manifest


class CreateUsersActivity : AppCompatActivity() {
    private val pathFoto: String? = ""

    companion object {
        const val PICK_IMAGE_REQUEST_CODE = 1000
        const val READ_EXTERNAL_STORAGE_REQUEST_CODE = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_users)
        create_image.setOnClickListener {
            pickImage()
        }

        btn_create_users.setOnClickListener {
            if ("" == pathFoto) {
                Toast.makeText(this, "Masukkan Foto dengan Benar", Toast.LENGTH_SHORT).show()
            } else {
                val file = File(pathFoto)
                val requestFile = RequestBody.create(
                    "multipart/form-data".toMediaTypeOrNull(),
                    file
                )
                val part = MultipartBody.Part.createFormData("image", file.toString(), requestFile)
                ApiService.endpoint.createUsers(
                    create_name.text.toString(),
                    create_username.text.toString(),
                    create_email.text.toString(),
                    create_password.text.toString(),
                    create_male.text.toString(),
                    create_alamat.text.toString(),
                    part
                ).enqueue(object : Callback<ResponseCreateUsers>{
                    override fun onResponse(
                        call: Call<ResponseCreateUsers>,
                        response: Response<ResponseCreateUsers>
                    ) {
                        if (response.isSuccessful){
                        }

                    }

                    override fun onFailure(call: Call<ResponseCreateUsers>, t: Throwable) {

                    }

                })
            }
        }
    }

    private fun pickImage() {
        if (ActivityCompat.checkSelfPermission(
                this, android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val intent = Intent(
                Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI
            )
            intent.type = "image/*"
            intent.putExtra("crop", "true")
            intent.putExtra("scale", "true")
            intent.putExtra("aspectX", "16")
            intent.putExtra("aspectY", "9")
            startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE)
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                READ_EXTERNAL_STORAGE_REQUEST_CODE
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST_CODE) {
            if (resultCode != Activity.RESULT_OK) {
                return
            }
            val uri = data?.data
            if (uri != null) {
                val imageFile = uriToImageFile(uri)
                val bitmapImage = BitmapFactory.decodeFile(imageFile.toString())
                create_image.setImageBitmap(bitmapImage)
            }
            if (uri != null) {
                val imageBitmap = uriToBitmap(uri)
                create_image.setImageBitmap(imageBitmap)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            READ_EXTERNAL_STORAGE_REQUEST_CODE -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImage()
                }
            }
        }
    }

    private fun uriToImageFile(uri: Uri): File? {
        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(uri, filePathColumn, null, null, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                val filePath = cursor.getString(columnIndex)
                var urlImage = filePath
                cursor.close()
                return File(filePath)
            }
            cursor.close()
        }
        return null
    }

    private fun uriToBitmap(uri: Uri): Bitmap {
        return MediaStore.Images.Media.getBitmap(this.contentResolver, uri)

    }


}
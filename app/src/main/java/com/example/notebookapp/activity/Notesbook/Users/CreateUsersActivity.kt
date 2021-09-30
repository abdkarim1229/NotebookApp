package com.example.notebookapp.activity.Notesbook.Users

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.example.notebookapp.R
import com.example.notebookapp.network.ApiService

import kotlinx.android.synthetic.main.activity_create_users.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import android.database.Cursor
import com.example.notebookapp.model.users.ResponseCreateUsers


class CreateUsersActivity : AppCompatActivity() {
    var pathFoto: String? = ""
    var urlImage: String? = ""
    private var image: String? = ""
    val REQUEST_GALLERY: Int = 9544

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_users)
        create_image.setOnClickListener {
            var intent = Intent()
            intent.type = "image/*"
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent, "open gallery"), REQUEST_GALLERY)
        }
        btn_create_users.setOnClickListener {
            val file = File(image)
            val req = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file)
            val part = MultipartBody.Part.createFormData("image", file.name, req)
            ApiService.endpoint.createUsers(
                create_name.text.toString(),
                create_username.text.toString(),
                create_email.text.toString(),
                create_password.text.toString(),
                create_male.text.toString(),
                create_alamat.text.toString(),
                part
            ).enqueue(object : Callback<ResponseCreateUsers> {
                override fun onResponse(
                    call: Call<ResponseCreateUsers>,
                    response: Response<ResponseCreateUsers>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@CreateUsersActivity, "Berhasil", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                override fun onFailure(call: Call<ResponseCreateUsers>, t: Throwable) {
                    Toast.makeText(this@CreateUsersActivity, "${t}", Toast.LENGTH_SHORT)
                        .show()
                }

            })
//            if ("" === pathFoto) {
//                Toast.makeText(this, "Foto belum di pilih", Toast.LENGTH_SHORT).show()
//            } else {
//                val file = File(pathFoto)
////                urlImage = file.name
//                val requestFile = RequestBody.create(
//                    "multipart/form-data".toMediaTypeOrNull(),
//                    file
//                )
//                val part =
//                    MultipartBody.Part.createFormData("file", file.toString(), requestFile)
//                ApiService.endpoint.createUsers(
//                    create_name.text.toString(),
//                    create_username.text.toString(),
//                    create_email.text.toString(),
//                    create_password.text.toString(),
//                    create_male.text.toString(),
//                    create_alamat.text.toString(),
//                    part
//                ).enqueue(object : Callback<ResponseCreateUsers> {
//                    override fun onResponse(
//                        call: Call<ResponseCreateUsers>,
//                        response: Response<ResponseCreateUsers>
//                    ) {
//                        if (response.isSuccessful) {
//                            Toast.makeText(
//                                this@CreateUsersActivity,
//                                response.message(),
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        }
//                    }
//
//                    override fun onFailure(call: Call<ResponseCreateUsers>, t: Throwable) {
//                        Log.d("Log", "${t}")
//                        Toast.makeText(this@CreateUsersActivity, "${t}", Toast.LENGTH_SHORT)
//                            .show()
//                    }
//
//                })
//            }
//        }
        }
//    private fun pickImage() {
//        if (ActivityCompat.checkSelfPermission(
//                this, android.Manifest.permission.READ_EXTERNAL_STORAGE
//            ) == PackageManager.PERMISSION_GRANTED
//        ) {
//            val intent = Intent(
//                Intent.ACTION_PICK,
//                MediaStore.Images.Media.INTERNAL_CONTENT_URI
//            )
//            intent.type = "image/*"
//            intent.putExtra("crop", "true")
//            intent.putExtra("scale", "true")
//            intent.putExtra("aspectX", "16")
//            intent.putExtra("aspectY", "9")
//            startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE)
//        } else {
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
//                READ_EXTERNAL_STORAGE_REQUEST_CODE
//            )
//        }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == PICK_IMAGE_REQUEST_CODE) {
//            if (resultCode != Activity.RESULT_OK) {
//                return
//            }
//            val uri = data?.data
//            pathFoto = uri?.path
//
//            if (uri != null) {
//                val imageFile = uriToImageFile(uri)
//                val bitmapImage = BitmapFactory.decodeFile(imageFile.toString())
//                create_image.setImageBitmap(bitmapImage)
//            }
//            if (uri != null) {
//                val imageBitmap = uriToBitmap(uri)
//                create_image.setImageBitmap(imageBitmap)
//            }
//        }
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        when (requestCode) {
//            READ_EXTERNAL_STORAGE_REQUEST_CODE -> {
//                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    pickImage()
//                }
//            }
//        }
//    }
//
//    private fun uriToImageFile(uri: Uri): File? {
//        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
//        val cursor = contentResolver.query(uri, filePathColumn, null, null, null)
//        if (cursor != null) {
//            if (cursor.moveToFirst()) {
//                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
//                val filePath = cursor.getString(columnIndex)
//                pathFoto = filePath
//                cursor.close()
//                return File(filePath)
//            }
//            cursor.close()
//        }
//        return null
//    }
//
//    private fun uriToBitmap(uri: Uri): Bitmap {
//        return MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
//
//    }
//
//    companion object {
//        const val PICK_IMAGE_REQUEST_CODE = 1000
//        const val READ_EXTERNAL_STORAGE_REQUEST_CODE = 1001
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



package com.example.notebookapp.activity.Notesbook.Users

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.notebookapp.R
import com.example.notebookapp.activity.Notesbook.Notes.NotesAdapter
import com.example.notebookapp.model.users.GET.DataGetUsers
import com.example.notebookapp.model.users.ResponseDeleteUsers
import com.example.notebookapp.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersAdapter(private var listUser: ArrayList<DataGetUsers>) :
    RecyclerView.Adapter<UsersAdapter.MyVieHolder>() {
    class MyVieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.users_name)
        val email: TextView = itemView.findViewById(R.id.users_email)
        val gender: TextView = itemView.findViewById(R.id.users_gender)
        val image: ImageView = itemView.findViewById(R.id.users_image)
        val imageDelete: ImageView = itemView.findViewById(R.id.image_delete_users)
        val click: RelativeLayout = itemView.findViewById(R.id.click_users)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_users, parent, false)
        return MyVieHolder(view)
    }

    override fun onBindViewHolder(holder: MyVieHolder, position: Int) {
        val data = listUser[position]
        holder.email.text = data.email
        holder.gender.text = data.gender
        holder.name.text = data.name
        Glide.with(holder.itemView).load(data.image).apply(RequestOptions().override(320, 320))
            .into(holder.image)
        holder.click.setOnClickListener {
            val intent = Intent(holder.name.context, UpdateUsersActivity::class.java)
            intent.putExtra("id", listUser[position].id)
            intent.putExtra("name", listUser[position].name)
            intent.putExtra("username", listUser[position].username)
            intent.putExtra("email", listUser[position].email)
            intent.putExtra("password", listUser[position].password)
            intent.putExtra("gender", listUser[position].gender)
            intent.putExtra("address", listUser[position].address)
            intent.putExtra("image", listUser[position].image)
        }
        holder.imageDelete.setOnClickListener {
            ApiService.endpoint.deleteUsers(data.id)
                .enqueue(object : Callback<ResponseDeleteUsers> {
                    override fun onResponse(
                        call: Call<ResponseDeleteUsers>,
                        response: Response<ResponseDeleteUsers>
                    ) {
                        if (response.isSuccessful) {
                        }
                    }

                    override fun onFailure(call: Call<ResponseDeleteUsers>, t: Throwable) {
                    }
                })
        }


    }

    override fun getItemCount() = listUser.size

    fun setData(data: List<DataGetUsers>) {
        listUser.clear()
        listUser.addAll(data)
        notifyDataSetChanged()
    }
}


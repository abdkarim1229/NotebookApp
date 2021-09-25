package com.example.notebookapp.activity.Notes

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notebookapp.R
import com.example.notebookapp.activity.update.UpdateActivity
import com.example.notebookapp.model.GET.DataGET
import com.example.notebookapp.model.ResponseDelete
import com.example.notebookapp.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotesAdapter(private val listNote: ArrayList<DataGET>) :
    RecyclerView.Adapter<NotesAdapter.myViewHolder>() {

    class myViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val title: TextView = itemview.findViewById(R.id.notes_title)
        val body: TextView = itemview.findViewById(R.id.notes_Body)
        val update_at: TextView = itemview.findViewById(R.id.notes_updated_at)
        val notes: RelativeLayout = itemview.findViewById(R.id.notes)
        val id: TextView = itemview.findViewById(R.id.notes_id)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_notes, parent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val data = listNote[position]
        holder.title.text = data.title
        holder.body.text = data.body
        holder.update_at.text = data.updated_at
        holder.notes.setOnClickListener {
            val options = arrayOf<CharSequence>(
                "Update", "Delete"
            )
            val builder: AlertDialog.Builder = AlertDialog.Builder(holder.notes.context)
            builder.setTitle("Choose the Actons")
            builder.setCancelable(true)
            builder.setItems(options, DialogInterface.OnClickListener { dialog, position ->
                if (position == 0) {
                    val intent = Intent(holder.notes.context, UpdateActivity::class.java)
                    intent.putExtra("id", listNote[position].id)
                    intent.putExtra("title", listNote[position].title)
                    intent.putExtra("body", listNote[position].body)
                    holder.notes.context.startActivity(intent)
                }
                if (position == 1) {
                    ApiService.endpoint.deleteNotes(data.id.toString())
                        .enqueue(object : Callback<ResponseDelete> {
                            override fun onResponse(
                                call: Call<ResponseDelete>,
                                response: Response<ResponseDelete>
                            ) {

                            }

                            override fun onFailure(call: Call<ResponseDelete>, t: Throwable) {

                            }

                        })
                }
            })
            builder.show()
        }
    }

    override fun getItemCount() = listNote.size

    fun setData(data: List<DataGET>) {
        listNote.clear()
        listNote.addAll(data)
        notifyDataSetChanged()
    }
}


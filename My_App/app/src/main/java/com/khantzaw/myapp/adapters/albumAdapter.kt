package com.khantzaw.myapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.khantzaw.myapp.AlbumDetail
import com.khantzaw.myapp.R
import com.khantzaw.myapp.model.Album
import com.squareup.picasso.Picasso

class albumAdapter(val context: Context, val albums: List<Album>): RecyclerView.Adapter<albumAdapter.ViewHolder>() {
    companion object{
        val AlbumKey = "ALBUM_KEY"
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.album_row, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = albums.size

    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
        val album = albums[p1]
        Picasso.get().load(album.thumbnailUrl).into(holder.itemView.findViewById<ImageView>(R.id.naruto))
        holder.itemView.findViewById<TextView>(R.id.tvTitle).text = album.title.substring(1,10)
        val ary: List<String> = album.url.split("://")
        val second: List<String> = ary[1].split(".")
        val third: List<String> = second[1].split(" ")
        holder.itemView.findViewById<TextView>(R.id.txtOne).text = third[0]
        holder.itemView.findViewById<Button>(R.id.button).setOnClickListener{
            Toast.makeText(context, album.url,Toast.LENGTH_LONG).show()
            val intent = Intent(context, AlbumDetail::class.java)
            intent.putExtra(AlbumKey,album)
            context.startActivity(intent)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}


package com.khantzaw.myapp

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.khantzaw.myapp.adapters.albumAdapter
import com.khantzaw.myapp.model.Album
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.net.URL

@Suppress("DEPRECATION")
class albumList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_list)

        supportActionBar?.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        doAsync {
            val jsons = URL("https://jsonplaceholder.typicode.com/photos").readText()

            uiThread {
                val albums = Gson().fromJson(jsons, Array<Album>::class.java).toList()
                toast("There are ${albums.size}")

                var albumRecycler = findViewById(R.id.albumRecycler) as RecyclerView
                val layoutManager = GridLayoutManager(this@albumList,2)
                albumRecycler.layoutManager = layoutManager
                val adapter = albumAdapter(this@albumList,albums)
                albumRecycler.adapter = adapter
            }
        }
    }
}
package com.khantzaw.myapp

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.khantzaw.myapp.adapters.albumAdapter
import com.khantzaw.myapp.model.Album
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_album_detail.*

@Suppress("DEPRECATION")
class AlbumDetail : AppCompatActivity() {
    lateinit var mAdSecondView : AdView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_detail)
        supportActionBar?.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        MobileAds.initialize(this, getString(R.string.admob_app_id))

        mAdSecondView = findViewById(R.id.adSecondView)
        val adRequest = AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build()
        mAdSecondView.loadAd(adRequest)

        val album = intent.getParcelableExtra<Album>(albumAdapter.AlbumKey)

        if (album != null) {
            titleFinal.text         = album.title
            ALBUM_ID.text           = album.albumID.toString()
            ID.text                 = album.id.toString()
            THUMBNAIL_URL_ID.text   = album.thumbnailUrl
            Picasso.get().load(album.url).into(imageFinal)
        }
    }
}
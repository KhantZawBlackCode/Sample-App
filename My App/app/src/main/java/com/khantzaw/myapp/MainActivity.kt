@file:Suppress("DEPRECATION")

package com.khantzaw.myapp

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import de.hdodenhof.circleimageview.CircleImageView
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), Animation.AnimationListener {
    lateinit var mAdView : AdView
    lateinit var bounceAmin : Animation
    lateinit var zoomAmin : Animation
    lateinit var flipAmin : Animation

    override fun onCreate(savedInstanceState: Bundle?) {

            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            supportActionBar?.hide()

            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
            MobileAds.initialize(this, getString(R.string.admob_app_id))

            mAdView = findViewById(R.id.adView)
            val adRequest = AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build()
            mAdView.loadAd(adRequest)

                bounceAmin = AnimationUtils.loadAnimation(this,R.anim.bounce)
                zoomAmin = AnimationUtils.loadAnimation(this,R.anim.zoom)
                flipAmin = AnimationUtils.loadAnimation(this,R.anim.flip)

                val appLogo = findViewById(R.id.appLogo) as CircleImageView
                appLogo.animation = bounceAmin

            bounceAmin.setAnimationListener(this)
            zoomAmin.setAnimationListener(this)
    }

    override fun onAnimationStart(animation: Animation?) {

    }

    override fun onAnimationEnd(animation: Animation?) {
        val dream = findViewById(R.id.dream) as TextView
        val click = findViewById(R.id.click) as Button
        val progressBar = findViewById(R.id.progressBar) as ProgressBar
        if (animation == bounceAmin){
            if (checkConn()){
                dream.visibility = View.VISIBLE
                dream.animation = zoomAmin
            }else{
                alert("Your device need internet Connection","No Internet Connection") {
                    yesButton { toast("OK, Try again!")
                    noButton { toast("You can't use the Application") }
                    }
                }.show()
                progressBar.visibility = View.VISIBLE
            }

        }else if (animation == zoomAmin){
            click.visibility = View.VISIBLE
            click.animation = flipAmin

            click.setOnClickListener{
                val intent = Intent(this@MainActivity, albumList::class.java)
                startActivity(intent)
            }
        }

    }

    override fun onAnimationRepeat(animation: Animation?) {

    }

    fun checkConn(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork : NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }
}
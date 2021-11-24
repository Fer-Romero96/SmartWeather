package com.example.smartweather.view

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.airbnb.lottie.LottieDrawable
import com.example.smartweather.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Data Binding
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (isOnline()){
            animation()
        } else {
            Toast.makeText(applicationContext, "Sin Conexión", Toast.LENGTH_LONG).show()
        }

        // Delay the Main Activity
        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 5000)

    }

    // Function for Lottie Animation
    private fun animation(){
        binding.animationView.setAnimationFromUrl("https://assets3.lottiefiles.com/private_files/lf30_jmgekfqg.json")
        binding.animationView.repeatCount = LottieDrawable.INFINITE
        binding.animationView.playAnimation()
    }

    // Function to verify internet connection
    fun isOnline(): Boolean {
        val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
        return networkInfo?.isConnected == true
    }

}
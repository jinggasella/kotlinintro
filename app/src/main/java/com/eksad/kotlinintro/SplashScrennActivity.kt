package com.eksad.kotlinintro

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import com.eksad.kotlinintro.utilities.Constanta
import com.eksad.kotlinintro.utilities.SessionManager
import kotlinx.android.synthetic.main.activity_splash_screnn.*
import java.util.*

class SplashScrennActivity : AppCompatActivity() {
//    //cara 1 : lateinit(ikuti style java)
//    private lateinit var logoSplash1 : ImageView
//
//    //cara 2: lazy init (ikuti style java)
//    private val teksVersi1 : TextView by lazy {
//        findViewById(R.id.teksVersi) as TextView
//    }

    val context : Context = this //kalo sudah pasti static dan ga berubah langsung pake val

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //code untuk fullscrenn
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar!!.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(R.layout.activity_splash_screnn)
//cara 1
//        this.logoSplash1 = logoSplash
//_________________________________________________________________________________
//         langsung cara kotlin

//        .text untuk getter setter. ganti isi teks nya
        teksVersi.text = "Version 2.0"

        // buat timer delay sebelum pindah ke MainActivity
        generateDelay()

    }

    fun generateDelay() {
        var timerTask = object : TimerTask()  {   // obejct turunan dari Timer TAsk
            override fun run() {
                if(SessionManager().cekLogin(context)){
                    //true = sudah login
                    pindahKeMainActivity()
                }else{
                    //false = belom login
                    pindahKeLoginActiviyt()
                }

            }
        }

        var timer : Timer = Timer()
        timer.schedule(timerTask, Constanta.SPLASH_DELAY)
    }

    private fun pindahKeMainActivity() {
        //pindah ke activity main setelah bdelay
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)

        finish()
    }

    private fun pindahKeLoginActiviyt() {
        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)

        finish()
    }
}

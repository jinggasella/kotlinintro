package com.eksad.kotlinintro

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eksad.kotlinintro.utilities.Constanta
import com.eksad.kotlinintro.utilities.EsafirmActivity
import com.eksad.kotlinintro.utilities.SessionManager
import kotlinx.android.synthetic.main.activity_main.*

import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;

class MainActivity : AppCompatActivity(  ) {
    val context: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCenter.start(
            application, "cfbca860-ca46-4594-af40-3da17ed2c645",
            Analytics::class.java, Crashes::class.java
        )

        tangkapIntentExtra()
        tampilkanUsernameDariSessionManager()

        buttonLogout.setOnClickListener {
            //lakukan fungsi logout
            //step 1: ganti flag login menjadi false
            SessionManager().setLogIn(context, false)

            //step 2: buka screen login
            val intent: Intent = Intent(context, LoginActivity::class.java)

            //step 3 : tuutp screen main\
            finish()
        }

        menu1.setOnClickListener {
            val intent: Intent = Intent(context, CameraActivity::class.java)
            startActivity(intent)
        }

        menu3.setOnClickListener {
            val intent: Intent = Intent(context, EsafirmActivity::class.java)
            startActivity(intent)
        }

        menu2.setOnClickListener {
            val intent: Intent = Intent(context, VolleyActivity::class.java)
            startActivity(intent)
        }
        menu4.setOnClickListener {
            val intent: Intent = Intent(context, DaftarMahasiswaActivity::class.java)
            startActivity(intent)
        }

    }

    fun tampilkanUsernameDariSessionManager() {
        var valueUserName = SessionManager().getUserName(context)
        var valuePassword = SessionManager().getPassword(context)

        username.text = " "+valueUserName+ " "+valuePassword
    }

    fun tangkapIntentExtra() {
        var bundle: Bundle? = intent.extras

        //jika bundle != null, baru melaksanakan yg di dalam let
        //safe call ?.let()
        bundle?.let {
            //tidak null
            var valueUserName = bundle!!.getString(Constanta.KEY_EXTRA_USERNAME) // !! menjamin agar tidak null
            var valuePassword = bundle!!.getString(Constanta.KEY_EXTRA_PASSWORD)

            username.text = "" + valueUserName + " " +valuePassword //jadi setelah welcome,... nah .. itu tulisannya diganti sm ini

        }
    }
}

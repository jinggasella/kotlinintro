package com.eksad.kotlinintro

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.eksad.kotlinintro.utilities.Constanta
import com.eksad.kotlinintro.utilities.SessionManager
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    val context : Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttonLogin.setOnClickListener {
            validasiInput()

        }
    }

    fun  validasiInput() {
        var userName = input_username.text.toString().trim() // untuk menghilangkan spasi = trim()
        var userPassword = input_password.text.toString().trim()

        if(userName.length == 0) {
            Toast.makeText(context, "Username masih kosong!", Toast.LENGTH_SHORT).show()
        }else if(userPassword.length == 0) {
            Toast.makeText(context, "Password masih kosong!", Toast.LENGTH_SHORT).show()
        }
        else {
            //sudah diisi semua
            //step 1: simpan data login
            SessionManager().simpanDataLogin(context, userName, userPassword)

            //step 2: pindah ke eactivity main
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(Constanta.KEY_EXTRA_USERNAME, userName)
            intent.putExtra(Constanta.KEY_EXTRA_PASSWORD, userPassword)
            startActivity(intent)

            finish()
        }
    }
}

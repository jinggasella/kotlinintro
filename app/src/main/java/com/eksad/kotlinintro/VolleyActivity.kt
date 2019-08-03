package com.eksad.kotlinintro

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.eksad.kotlinintro.adapters.CustomListUserAdapter
import com.eksad.kotlinintro.models.ModelUser
import com.eksad.kotlinintro.utilities.loadingAnimationText
import kotlinx.android.synthetic.main.activity_volley.*

class VolleyActivity : AppCompatActivity() {
    val context: Context= this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volley)

        buttonGetList.setOnClickListener {
            getListUserFromAPI()
        }
    }

    private fun getListUserFromAPI() {
        var loading = loadingAnimationText("Tunggu sebentar....")
        loading.dismiss()
        val urlAPI = "https://reqres.in/api/users?page=1"

        val requestAPI = JsonObjectRequest(
                Request.Method.GET,
                urlAPI,
                null,
                Response.Listener {
                    response ->
                    //ini request sukses
                    //parsing
                    loading.dismiss()

                    val jsonArray = response.getJSONArray("data")
                    val sizeArray = jsonArray.length()

                    if (sizeArray>0) {
                        val modelUser = ArrayList<ModelUser>()

                        //for n, indeks mulai dari 0 samapai sizeArray
                        for (n in 0 until sizeArray) {
                            val dataObject = jsonArray.getJSONObject(n)

                            val model = ModelUser()
                            model.id = dataObject.getInt("id")
                            model.email = dataObject.getString("email")
                            model.first_name = dataObject.getString("first_name")
                            model.last_name = dataObject.getString("last_name")
                            model.avatar = dataObject.getString("avatar")

                            modelUser.add(model)
                        }
                        // isi model ke adapter list
                        isiListAdapter(modelUser)
                    }else{
                        //kosong
                        Toast.makeText(context, "Empty list user", Toast.LENGTH_SHORT).show()
                    }
                },
                Response.ErrorListener {
                    error ->

                    loading.dismiss()
                    Toast.makeText(context, "Error Get List User!" +error.message,
                        Toast.LENGTH_SHORT).show()

                    println("Error Get List User!" +error.message)
                }
        )
        Volley.newRequestQueue(context).add(requestAPI)
    }

    private fun isiListAdapter(modelUser: ArrayList<ModelUser>) {
        val adapterListUser = CustomListUserAdapter(context, modelUser)

        listUser.adapter = adapterListUser

    }
}

package com.eksad.kotlinintro

import android.content.Context
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eksad.kotlinintro.adapters.ListMahasiswaAdapter
import com.eksad.kotlinintro.utilities.DatabaseMahasiswaHelper
import kotlinx.android.synthetic.main.activity_daftar_mahasiswa.*

class DaftarMahasiswaActivity : AppCompatActivity() {
    val context: Context = this

    var databaseHelper: DatabaseMahasiswaHelper? = null
    var cursor: Cursor? = null

    var namaMahasiswa: MutableList<String> = ArrayList()
    var idMahasiswa: MutableList<Int> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_mahasiswa)

        buttonInput.setOnClickListener {
            val intent = Intent(context, InputDataMahasiswaActivity::class.java)
            startActivity(intent)
        }

        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        listMahasiswa!!.layoutManager = layoutManager

        readDataMahasiswa()
    }

    private fun readDataMahasiswa() {
        databaseHelper = DatabaseMahasiswaHelper(context)

        val db =databaseHelper!!.readableDatabase

        val queryRead = "SELECT * FROM biodata"
        cursor = db.rawQuery(queryRead, null)
        //pake count jd sama kyk .length/.size
        if (cursor!!.count == 0 ) {
            Toast.makeText(context, "Data masih kosong!!!!", Toast.LENGTH_SHORT).show()
        }
        else{
            for (c in 0 until cursor!!.count) {
                cursor!!.moveToPosition(c)

                var nama = cursor!!.getString(2)
                var id = cursor!!.getInt(0)

                namaMahasiswa.add(nama)
                idMahasiswa.add(id)
            }
            tampilkanDataMahasiswa()

        }
    }

    private fun tampilkanDataMahasiswa() {
        val adapterMahasiswa = ListMahasiswaAdapter(context, namaMahasiswa, idMahasiswa)
        listMahasiswa!!.adapter = adapterMahasiswa

        adapterMahasiswa!!.notifyDataSetChanged()

    }

    override fun onResume() {
        super.onResume()

        refreshList()
    }

    private fun refreshList() {
        namaMahasiswa = ArrayList()
        idMahasiswa = ArrayList()

        readDataMahasiswa()
    }
}

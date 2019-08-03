package com.eksad.kotlinintro

import android.content.Context
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.eksad.kotlinintro.utilities.Constanta
import com.eksad.kotlinintro.utilities.DatabaseMahasiswaHelper
import kotlinx.android.synthetic.main.activity_detail_mahasiswa.*


class DetailMahasiswaActivity : AppCompatActivity() {
    val context: Context = this

    var databaseHelper: DatabaseMahasiswaHelper? = null
    var cursor: Cursor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_mahasiswa)

        //tangkap get extra
        var bundle: Bundle? = intent.extras
        bundle?.let {
            var id: Int = bundle!!.getInt(Constanta.KEY_ID_ROW)

            readDataMahasiswa(id)
        }
    }

    private fun readDataMahasiswa(id: Int?) {

        databaseHelper = DatabaseMahasiswaHelper(context)
        val db = databaseHelper!!.readableDatabase

        var queryRead = "SELECT * FROM biodata WHERE id="+id
        db.rawQuery(queryRead, null)
        cursor = db.rawQuery(queryRead, null)

        if (cursor!!.count == 1) {
//            cursor!!.moveToPosition(0)
            cursor!!.moveToFirst()

            namaMahasiswa!!.text = cursor!!.getString(2)
            nimMahasiswa!!.text = cursor!!.getString(1)
            tglLahirMahasiswa!!.text = cursor!!.getString(4)
            genderMahasiswa!!.text = cursor!!.getString(3)
            jurusanMahasiswa!!.text = cursor!!.getString(6)

            val image_path = cursor!!.getString(7)
            Glide.with(context).load(image_path).into(fotoMahasiswa)
        }
        else{
            Toast.makeText(context, " Data tidak ditemuka !!", Toast.LENGTH_SHORT).show()
        }
    }
}

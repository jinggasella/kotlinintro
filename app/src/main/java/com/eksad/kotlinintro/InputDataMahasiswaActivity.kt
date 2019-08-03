package com.eksad.kotlinintro

import android.app.DatePickerDialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.bumptech.glide.Glide
import com.eksad.kotlinintro.utilities.Constanta
import com.eksad.kotlinintro.utilities.DatabaseMahasiswaHelper
import com.esafirm.imagepicker.features.ImagePicker
import kotlinx.android.synthetic.main.activity_input_data_mahasiswa.*
import java.text.SimpleDateFormat
import java.util.*

class InputDataMahasiswaActivity : AppCompatActivity() {
    val context : Context = this

    var databaseHelper: DatabaseMahasiswaHelper? = null

    var IMAGE_PATH = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_data_mahasiswa)

        isiSpinnerJurusan()
        setTanggalLahirPicker()

        fotoDiri.setOnClickListener {
            ambilFoto()
        }

        buttonSimpan.setOnClickListener {
            validasiInput()
        }
    }

    private fun ambilFoto() {
        ImagePicker.create(this@InputDataMahasiswaActivity)
            .single()
            .limit(1)
            .folderMode(true)
            .start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            val foto = ImagePicker.getFirstImageOrNull(data)
            foto?.let {
                val options =  BitmapFactory.Options()
                val bitmap = BitmapFactory.decodeFile(foto!!.path, options)

                Glide.with(context).load(bitmap).into(fotoDiri)

                //simpah path lokasi foto
                IMAGE_PATH = foto!!.path
            }
        }
    }
    private fun setTanggalLahirPicker() {
        tanggalLahir!!.isFocusable = false
        tanggalLahir!!.isClickable = true

        val today = Calendar.getInstance()
        val yearNow = today.get(Calendar.YEAR)
        val monthNow = today.get(Calendar.MONTH)
        val dayNow = today.get(Calendar.DATE)

        tanggalLahir.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                context,
                R.style.CustomDatePicker,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    val selected = Calendar.getInstance()
                    selected.set(year, month, dayOfMonth)

                    //konversi ke string
                    val formateDate = SimpleDateFormat("dd/MM/yyyy")
                    val tanggal = formateDate.format(selected.time)

                    tanggalLahir!!.setText(tanggal)

                },
                yearNow,
                monthNow,
                dayNow
            )
            datePickerDialog.show()
        }
    }

    private fun validasiInput() {
        if (NIM.text.toString().trim().length == 0) {
            Toast.makeText(context, "NIM masih kosong !!", Toast.LENGTH_SHORT).show()
        }
        else if (namaLengkap.text.toString().trim().length == 0){
            Toast.makeText(context, "Nama masih kosong !!", Toast.LENGTH_SHORT).show()
        }
        else if (tanggalLahir.text.toString().trim().length == 0){
            Toast.makeText(context, "Tanggal Lahir masih kosong !!", Toast.LENGTH_SHORT).show()
        }
        else if (alamat.text.toString().trim().length == 0){
            Toast.makeText(context, "Alamat masih kosong !!", Toast.LENGTH_SHORT).show()
        }
        else if (gender.text.toString().trim().length == 0){
            Toast.makeText(context, "Jenis Kelamin masih kosong !!", Toast.LENGTH_SHORT).show()
        }
        else if(IMAGE_PATH.length == 0) {
            Toast.makeText(context, "Anda belum memilih Foto !!", Toast.LENGTH_SHORT).show()
        }
        else {
            //VALIDASI SUKSES
            // SIMPAN KE DATABASE
            insertToDataBase()
        }
    }

    private fun insertToDataBase() {
        val nim = NIM!!.text.toString().trim()
        val namalengkap = namaLengkap.text.toString().trim()
        val tanggallahir = tanggalLahir.text.toString().trim()
        val gendervalue = gender.text.toString().trim()
        val alamatvalue = alamat.text.toString().trim()
        val jurusan = Constanta.ARRAY_JURUSAN[inputJurusan.selectedItemPosition] // array[]

        //insert db cara 2. menggunakan bantuan dari android
        val contentValue = ContentValues()
        contentValue.put("nim", nim)
        contentValue.put("nama_lengkap", namalengkap)
        contentValue.put("gender", gendervalue)
        contentValue.put("tanggal_lahir", tanggallahir)
        contentValue.put("alamat", alamatvalue)
        contentValue.put("jurusan", jurusan)
        contentValue.put("path_foto", IMAGE_PATH)
        //jadi sebenarnya juga akan diconvert ke query di dalam android nya, tapi ini menghindari typo dan kesalahan lainnya

        //INSERT INTO DB
        databaseHelper = DatabaseMahasiswaHelper(context)
        val db = databaseHelper!!.writableDatabase

        db.insert("biodata", null, contentValue)

        Toast.makeText(context, "Input data berhasil", Toast.LENGTH_SHORT).show()
        finish()

    }

    private fun isiSpinnerJurusan() {
        var adapterSpinner = ArrayAdapter<String>(
            context, android.R.layout.simple_spinner_item, Constanta.ARRAY_JURUSAN
        )
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        inputJurusan.adapter = adapterSpinner
    }
}

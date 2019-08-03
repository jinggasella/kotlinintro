package com.eksad.kotlinintro.utilities

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseMahasiswaHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null,
                                                             DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) { // jadi database tabel cuma dibikin sekali
        //create table biodata dan isi
        val query = "CREATE TABLE 'biodata' (" +
                " 'id' INTEGER PRIMARY KEY AUTOINCREMENT,"+
                " 'nim' INTEGER,"+
                " 'nama_lengkap' TEXT,"+
                " 'gender' TEXT,"+
                " 'tanggal_lahir' TEXT,"+
                " 'alamat' TEXT,"+
                " 'jurusan' TEXT,"+
                " 'path_foto' TEXT"+
                ");"

        db!!.execSQL(query)

        val queryInsert = "INSERT INTO 'biodata' " +
                "('nim', 'nama_lengkap', 'gender', 'tanggal_lahir', 'alamat', 'jurusan', 'path_foto')" +
                " VALUES (" +
                "123456789, " +
                "'Peter Parker', " +
                "'L', " +
                "'01/01/1975', " +
                "'Toronto', " +
                "'Teknik Mesin', " +
                "'-'" +
                ");"

        db!!.execSQL(queryInsert)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        //belum ada aksi
    }

    companion object{
        internal val DATABASE_NAME = "database_mahasiswa.db"
        internal val DATABASE_VERSION = 1 //increment jd harus dimulai dari yg paling rendah
    }
}
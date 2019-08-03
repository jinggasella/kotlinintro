package com.eksad.kotlinintro.viewholder

import android.graphics.Color
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eksad.kotlinintro.R
import kotlinx.android.synthetic.main.activity_daftar_mahasiswa.view.*

class ViewHolderListMahasiswa (itemView: View) : RecyclerView.ViewHolder(itemView) {
    var listMahasiswa: LinearLayout
    var namaMahasiswa: TextView

    init {
        listMahasiswa = itemView.findViewById(R.id.listItemMahasiswa) as LinearLayout
        namaMahasiswa = itemView.findViewById(R.id.namaItemMahasiswa) as TextView
    }

    fun setDataMahasiswa(nama: String?, index: Int) {
        namaMahasiswa.text = nama

        if (index % 2 == 0) {
            //genap
            listMahasiswa.setBackgroundColor(Color.GRAY)
        }
        else{
            //ganjil
            listMahasiswa.setBackgroundColor(Color.MAGENTA)
        }
    }
}
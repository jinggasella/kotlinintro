package com.eksad.kotlinintro.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eksad.kotlinintro.DetailMahasiswaActivity
import com.eksad.kotlinintro.R
import com.eksad.kotlinintro.utilities.Constanta
import com.eksad.kotlinintro.viewholder.ViewHolderListMahasiswa

class ListMahasiswaAdapter (val context: Context,
                            val namaMahasiswa: List<String>,
                            val idMahasiswa: List<Int>) : RecyclerView.Adapter<ViewHolderListMahasiswa>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderListMahasiswa {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_list_mahasiswa,
            parent, false)

        return ViewHolderListMahasiswa(view)
    }

    override fun getItemCount(): Int {
        return namaMahasiswa?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolderListMahasiswa, position: Int) {
        println("masuk onBindViewHolder >>>>"+position)
        val nama = namaMahasiswa!![position]
        holder.setDataMahasiswa(nama, position)

        holder.listMahasiswa.setOnClickListener {
            val intent= Intent(context, DetailMahasiswaActivity::class.java)
            intent.putExtra(Constanta.KEY_ID_ROW, idMahasiswa[position])
            context.startActivity(intent)
        }

}
}
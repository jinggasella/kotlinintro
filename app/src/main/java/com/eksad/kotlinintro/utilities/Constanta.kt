package com.eksad.kotlinintro.utilities

object Constanta {
    const val SPLASH_DELAY: Long = 3000 // sebagai contanta, jd bisa dipake di class lain
    const val KEY_EXTRA_USERNAME: String = "USERNAME"
    const val KEY_EXTRA_PASSWORD: String = "PASSWORD"
    const val KEY_ID_ROW: String = "ROW_ID"

    val ARRAY_JURUSAN = arrayOf(
        "- Pilih Jurusan-", // yang tampil ke andorid indeks ke 0. jadi validasi nya, kalo user belom memilih apapun, dia tetep di indeks ke 0
        "Informatika",
        "Sistem Komputer",
        "Teknik Elektro",
        "Teknik Mesin",
        "Teknik Sipil",
        "Akuntansi",
        "Hukum",
        "Psikologi"
    )

}
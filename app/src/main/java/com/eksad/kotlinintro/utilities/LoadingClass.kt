package com.eksad.kotlinintro.utilities

import android.app.ProgressDialog
import android.content.Context
import com.eksad.kotlinintro.R

fun Context.loadingAnimationText(teks: String): ProgressDialog {
        val loading = ProgressDialog(this, R.style.CustomLoadingStyle)
        loading.setProgressStyle(android.R.style.Widget_ProgressBar_Small)
        loading.setCancelable(false)
        loading.setCanceledOnTouchOutside(false)
        loading.setMessage(teks)

        return loading
    }

fun Context.loadingAnimation(): ProgressDialog {
    val loading = ProgressDialog(this, R.style.CustomLoadingStyle)
    loading.setProgressStyle(android.R.style.Widget_ProgressBar_Small)
    loading.setCancelable(false)
    loading.setCanceledOnTouchOutside(false)

    return loading
}
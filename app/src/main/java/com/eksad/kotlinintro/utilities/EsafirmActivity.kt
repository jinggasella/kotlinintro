package com.eksad.kotlinintro.utilities

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.eksad.kotlinintro.R
import com.esafirm.imagepicker.features.ImagePicker
import com.esafirm.imagepicker.model.Image
import kotlinx.android.synthetic.main.activity_esafirm.*

class EsafirmActivity : AppCompatActivity() {
    val context: Context = this

    val REQUEST_CODE_CAMERA = 2
    val REQUEST_CODE_GALERY = 22

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_esafirm)

        button1.setOnClickListener {
            cameraByEsaFirm()
        }

        button2.setOnClickListener {
            galeryByEsaFirm()
        }
    }

    private fun galeryByEsaFirm() {
        ImagePicker.create(this@EsafirmActivity)
            .multi()
            .limit(3)
            .folderMode(true)
            .start(REQUEST_CODE_GALERY)

    }

    private fun cameraByEsaFirm() {
        // cara panggil kamera dari library esafirm
        ImagePicker.cameraOnly().start(this@EsafirmActivity, REQUEST_CODE_CAMERA)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_CAMERA) {
            var image = ImagePicker.getFirstImageOrNull(data)
            image?.let {
                var options = BitmapFactory.Options()
                var bitmap = BitmapFactory.decodeFile(image.path,options)

                Glide.with(context).load(bitmap).into(tampilImage)
            }
        }
        else if (requestCode == REQUEST_CODE_GALERY) {
            var images: List<Image> = ImagePicker.getImages(data)
            for (image in images){
                var options = BitmapFactory.Options()
                var bitmap = BitmapFactory.decodeFile(image.path,options)

//                 Glide.with(context).load(bitmap).into(tampilImage)

                var imageView = ImageView(context)
                Glide.with(context)
                    .load(bitmap)
                    .apply(RequestOptions()
                        .override(700, 700))
                    .into(imageView)

                sliderImage.addView(imageView)
            }
        }
    }
}

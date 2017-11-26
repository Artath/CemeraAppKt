package com.example.cemeraappkt

import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_photo.*

class PhotoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)
        imageView.setImageBitmap(intent.extras.getParcelable<Bitmap>("photo"))
        txtView.text =  intent.extras.getString("name")
    }
}

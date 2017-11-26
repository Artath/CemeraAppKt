package com.example.cemeraappkt

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val CAMERA_REQUEST_CODE = 1
    lateinit var nameTxt: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        photoBtnView.setOnClickListener(View.OnClickListener {
            nameTxt = nameEditTxtView.text.toString()
            if(nameTxt.isNotEmpty()){
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                if(cameraIntent.resolveActivity(packageManager) != null){
                    startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
                }
             }else{
                Toast.makeText(applicationContext, R.string.type_name_toast, Toast.LENGTH_SHORT).show()
            }
        })
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            CAMERA_REQUEST_CODE -> {
                 if(resultCode == Activity.RESULT_OK && data != null){
                     val showPhotoIntent = Intent(applicationContext, PhotoActivity::class.java)
                     val bundle = Bundle()
                     bundle.putString("name", nameTxt)
                     bundle.putParcelable("photo", data.extras.get("data") as Bitmap)
                     showPhotoIntent.putExtras(bundle)
                     startActivity(showPhotoIntent)
                 }
            }
            else -> {
                Toast.makeText(applicationContext, R.string.unrecognized_toast, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

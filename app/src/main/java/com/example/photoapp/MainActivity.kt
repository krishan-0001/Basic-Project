package com.example.photoapp

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val Camerareqcode = 1
    private val Galleryreqcode = 2
    private lateinit var ImgView: ImageView
    private lateinit var buttonCamera: Button
    private lateinit var buttonGallery: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ImgView = findViewById(R.id.imgCamera)
        buttonCamera = findViewById(R.id.btnCamera)
        buttonGallery = findViewById(R.id.btnGallery)

        buttonCamera.setOnClickListener {
            val iCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(iCamera, Camerareqcode)
        }
        buttonGallery.setOnClickListener {
            val iGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(iGallery, Galleryreqcode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
                if(requestCode==Camerareqcode) {
                    val imageBitmap = data?.extras?.get("data") as Bitmap
                    ImgView.setImageBitmap(imageBitmap)
                }
                else if(requestCode==Galleryreqcode){
                    val selectedImage = data?.data
                    val imageBitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, selectedImage)
                    ImgView.setImageBitmap(imageBitmap)
                }
        }
    }
}

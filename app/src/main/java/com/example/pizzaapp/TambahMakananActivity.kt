package com.example.pizzaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class TambahMakananActivity : AppCompatActivity() {

    val filePath = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_makanan)

        //hide title bar
        getSupportActionBar()?.hide()

        val txtId:EditText = findViewById(R.id.editTextIdMakanan)
        val txtNama:EditText = findViewById(R.id.editTextNmMakanan)
        val txtHarga:EditText = findViewById(R.id.editTextHrgMakanan)
        val imgFoto:ImageView = findViewById(R.id.imageMakanan)
        val btnSave:Button = findViewById(R.id.buttonSaveMenu)
        val btnImage:FloatingActionButton = findViewById(R.id.floatingActionButton)

        btnImage.setOnClickListener {  }


    }
}
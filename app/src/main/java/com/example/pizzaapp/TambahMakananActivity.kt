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
        /**
         * Upload images and text to the server
         */
        private fun upload(imageToUpload: File, s: Star) {
            val api = client!!.create(RestApi::class.java)
            /*
            `Call<T>` is an interface that represents an invocation of a Retrofit method that sends
            a request to a webserver and returns a response.
            */
            val uploadCall = api.upload(
                rb("UPLOAD"), rb(s.name), rb(s.description),
                rb(s.type), rb(s.galaxy), rb(s.dod), getMultipartBody(imageToUpload)
            )
            b!!.pb.visibility = View.VISIBLE
            show(this, "Uploading...")
            /*
            `enqueue()` is an abstract method that will asynchronously send the request and notify callback of
            its response or if an error occurred
            talking to the server, creating the request, or processing the response.
            `Callback` is an interface that Communicates responses from a server or offline requests.
            One and only one method will be invoked in response to a given request.
            */
            uploadCall!!.enqueue(object : Callback<ResponseModel?> {
                override fun onResponse(call: Call<ResponseModel?>, response: Response<ResponseModel?>) {
                    b!!.pb.visibility = View.GONE
                    if (!validateResponse(a, response)
                    ) return
                    //By this time we have already validated our response body so ignore nullpointer warning
                    val rc = response.body()!!.code
                    if (rc == "1") {
                        CACHE_IS_DIRTY = true
                        STARS_CACHE.clear()
                        show("CONGRATS: n 1. Data Inserted Successfully. n 2. ResponseCode: $rc")
                        clearEditTexts(
                            b!!.nameTxt,
                            b!!.descriptionTxt,
                            b!!.galaxyTxt,
                            b!!.typeTxt,
                            b!!.dodTxt
                        )
                    } else if (rc.equals("0", ignoreCase = true)) {
                        CACHE_IS_DIRTY = true
                        STARS_CACHE.clear()
                        showInfoDialog(
                            a,
                            "TEXT SAVED SUCCESSFULLY",
                            getString(R.string.response_code_0)
                        )
                    } else if (rc.equals("2", ignoreCase = true)) {
                        showInfoDialog(a, "UNSUCCESSFUL", getString(R.string.response_code_2))
                    } else {
                        showInfoDialog(
                            a, "UNKNOWN ERROR", "We can't identify the error you encountered." +
                                    "Please re-examine your code. "
                        )
                    }
                }

                override fun onFailure(call: Call<ResponseModel?>, t: Throwable) {
                    b!!.pb.visibility = View.GONE
                    showInfoDialog(
                        a, "FAILURE",
                        "FAILURE THROWN DURING INSERT." +
                                " ERROR Message: " + t.message
                    )
                }
            })
        }

    }
}
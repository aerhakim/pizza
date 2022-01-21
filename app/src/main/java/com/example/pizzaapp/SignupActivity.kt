package com.example.pizzaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.pizzaapp.client.RetrofitClient
import com.example.pizzaapp.response.pengguna.PenggunaResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        //hide title bar
        getSupportActionBar()?.hide()
        val textEmail: EditText = findViewById(R.id.editTextEmail)
        val textNama: EditText = findViewById(R.id.editTextPersonName)
        val textPassword: EditText = findViewById(R.id.editTextPassword)
        val btnSave: Button = findViewById(R.id.buttonSaveAccount)
        val btnLogin:TextView = findViewById(R.id.login)
        btnLogin.setOnClickListener {
            val intentLogin = Intent(this, LoginActivity::class.java)
            startActivity(intentLogin)
        }
        btnSave.setOnClickListener {
            RetrofitClient.instance.postPengguna(
                textEmail.text.toString(),
                textNama.text.toString(),
                "Kasir",
                textPassword.text.toString()
            ).enqueue(object : Callback<PenggunaResponse> {
                override fun onResponse(
                    call: Call<PenggunaResponse>,
                    response: Response<PenggunaResponse>
                ) {
                    val respon = response.body()
                    Toast.makeText(this@SignupActivity, "Daftar sukses Silahkan Login", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@SignupActivity,LoginActivity::class.java)
                    startActivity(intent)
                }

                override fun onFailure(call: Call<PenggunaResponse>, t: Throwable) {
                    Toast.makeText(this@SignupActivity, "Daftar sukses Silahkan Login", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@SignupActivity,LoginActivity::class.java)
                    startActivity(intent)
                }
            })
        }
    }
}
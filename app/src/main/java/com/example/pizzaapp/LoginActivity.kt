package com.example.pizzaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.pizzaapp.client.RetrofitClient
import com.example.pizzaapp.response.login.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //hide title bar
        getSupportActionBar()?.hide()

        //instance text
        val txtUsername:EditText = findViewById(R.id.editTextEmail)
        val txtPassword:EditText = findViewById(R.id.editTextPassword)
        //instance button login
        val btnLogin:Button = findViewById(R.id.buttonLogin)

        //event button login
        btnLogin.setOnClickListener {
            val email = txtUsername.text.toString().trim()
            val password = txtPassword.text.toString().trim()

            //cek username/password not empty
            if(email.isEmpty()){
                txtUsername.error = "Email required"
                txtUsername.requestFocus()
                return@setOnClickListener
            }

            if(password.isEmpty()){
                txtPassword.error = "Password required"
                txtPassword.requestFocus()
                return@setOnClickListener
            }

            RetrofitClient.instance.postLogin(
                email,password
            ).enqueue(object : Callback<LoginResponse>{
                override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                ) {
                    val user = response.body()
                    Log.e("token", user!!.token)
                    Toast.makeText(this@LoginActivity, "login sukses sukses ", Toast.LENGTH_SHORT).show()
                    FragmentProfile.email = user.data.username
                    FragmentProfile.nama = user.data.nama
                    FragmentProfile.level = user.data.level
                    FragmentProfile.password = user.data.password
                    val intentLogin = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intentLogin)
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                }
            })

        }
    }
}
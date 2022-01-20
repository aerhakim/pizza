package com.example.pizzaapp.client

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    const val BASE_URL = "http://192.168.74.120:8080/rest_api/index.php/"
    const val IMAGES_URL = "http://192.168.74.120:8080/rest_api/foto/"

    val instance:Api by lazy {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofit.create(Api::class.java)
    }
}


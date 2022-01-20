package com.example.pizzaapp.response.pengguna

data class PenggunaResponse(
    val `data`: List<Data>,
    val message: String,
    val success: Boolean
)
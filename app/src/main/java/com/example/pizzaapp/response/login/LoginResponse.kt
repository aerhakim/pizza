package com.example.pizzaapp.response.login

data class LoginResponse(
    val `data`: Data,
    val message: String,
    val success: Boolean,
    val token: String
)
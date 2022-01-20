package com.example.pizzaapp.client

import com.example.pizzaapp.response.login.LoginResponse
import com.example.pizzaapp.response.menu.MenuResponse
import com.example.pizzaapp.response.pengguna.PenggunaResponse
import com.example.pizzaapp.response.transaksi.TransaksiResponse
import retrofit2.Call
import retrofit2.http.*

interface Api {
    //show (get) menu
    @GET("makanan")
    fun getMenu():Call<ArrayList<MenuResponse>>

    @FormUrlEncoded
    @POST("login")
    fun postLogin(
            @Field("username") username:String,
            @Field("password") password:String
    ): Call<LoginResponse>

    @FormUrlEncoded
    @PUT("pengguna")
    fun putPengguna(
        @Field("username") username: String,
        @Field("nama") nama:String,
        @Field("level") level:String,
        @Field("password") password: String
    ):Call<PenggunaResponse>

    @FormUrlEncoded
    @POST("transaksi")
    fun postTransaksi(
        @Field("tanggal") tanggal:String,
        @Field("username") username:String
    ): Call<TransaksiResponse>

    @FormUrlEncoded
    @POST("detail_transaksi")
    fun postDetailTransaksi(
        @Field("no_transaksi") no_transaksi:Int,
        @Field("harga") harga:Int,
        @Field("jumlah") jumlah:Int,
        @Field("id_makanan") id_makanan:String
    ): Call<TransaksiResponse>
}


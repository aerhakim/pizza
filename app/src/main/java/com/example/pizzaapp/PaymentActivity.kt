package com.example.pizzaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.pizzaapp.client.RetrofitClient
import com.example.pizzaapp.response.detailtransaksi.DetailTrxResponse
import com.example.pizzaapp.response.menu.MenuResponse
import com.example.pizzaapp.response.transaksi.TransaksiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class PaymentActivity : AppCompatActivity() {

    companion object{
        var no_transaksi = 0;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        getSupportActionBar()?.hide()

        val txtTotal:TextView = findViewById(R.id.textTotalBayar)
        val txtCash:TextView = findViewById(R.id.editTextCash)
        val txtChange:TextView = findViewById(R.id.textChange)
        val btnFinish: Button = findViewById(R.id.btnFinish)

        txtTotal.text = (TransaksiAdapter.harga + (TransaksiAdapter.harga * 0.10)).toString()
        txtChange.text = "0"

        btnFinish.setOnClickListener{
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val tanggal = sdf.format(Date())
            val username = FragmentProfile.email
            RetrofitClient.instance.postTransaksi(tanggal, username).enqueue(object :
                Callback <TransaksiResponse> {
                override fun onResponse(
                    call: Call<TransaksiResponse>,
                    response: Response<TransaksiResponse>
                ) {
                    val user = response.body()
                    if (user != null){
                        no_transaksi = user.data.last_id
                    }
                    Toast.makeText(applicationContext,"Transaksi Dengan ID "+ no_transaksi, Toast.LENGTH_SHORT).show()

                    var i = 0
                    while (i<TransaksiAdapter.listId.count()){
                        RetrofitClient.instance.postDetailTransaksi(no_transaksi,TransaksiAdapter.listId[i], TransaksiAdapter.listHarga[i], 1).enqueue(
                            object :Callback<DetailTrxResponse>{
                                override fun onResponse(
                                    call: Call<DetailTrxResponse>,
                                    response: Response<DetailTrxResponse>
                                ) {
                                    Toast.makeText(applicationContext,"Telah Berhasil", Toast.LENGTH_SHORT).show()
                                }

                                override fun onFailure(
                                    call: Call<DetailTrxResponse>,
                                    t: Throwable
                                ) {
                                    Toast.makeText(applicationContext,t.message, Toast.LENGTH_SHORT).show()
                                }

                            }
                        )
                        i+=1
                    }
                    val intent = Intent(this@PaymentActivity,MainActivity::class.java)
                    startActivity(intent)
                }

                override fun onFailure(call: Call<TransaksiResponse>, t: Throwable) {
                    Toast.makeText(applicationContext,t.message, Toast.LENGTH_SHORT).show()
                }



            })
        }
    }
}


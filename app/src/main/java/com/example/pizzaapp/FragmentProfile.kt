package com.example.pizzaapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.pizzaapp.client.RetrofitClient
import com.example.pizzaapp.response.login.LoginResponse
import com.example.pizzaapp.response.pengguna.PenggunaResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentProfile.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentProfile : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textEmail:EditText = view.findViewById(R.id.editTextEmail)
        val textNama:EditText = view.findViewById(R.id.editTextPersonName)
        val textLevel:EditText = view.findViewById(R.id.editTextLevel)
        val textPassword:EditText = view.findViewById(R.id.editTextPassword)
        val btnSave:Button = view.findViewById(R.id.buttonSaveAccount)

        textEmail.setText(email)
        textNama.setText(nama)
        textLevel.setText(level)
        textPassword.setText(password)

        btnSave.setOnClickListener {
            RetrofitClient.instance.putPengguna(
                textEmail.text.toString(),
                textNama.text.toString(),
                textLevel.text.toString(),
                textPassword.text.toString()
            ).enqueue(object : Callback<PenggunaResponse>{
                override fun onResponse(
                    call: Call<PenggunaResponse>,
                    response: Response<PenggunaResponse>
                ) {
                    val respon = response.body()
                    Toast.makeText(activity, "update sukses sukses ", Toast.LENGTH_SHORT).show()
                    email = textEmail.text.toString()
                    nama = textNama.text.toString()
                    level = textLevel.text.toString()
                    password = textPassword.text.toString()
                }

                override fun onFailure(call: Call<PenggunaResponse>, t: Throwable) {
                    Toast.makeText(activity, t.message, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    companion object {

        var email: String = ""
        var nama: String = ""
        var level: String = ""
        var password: String = ""


        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentProfile().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
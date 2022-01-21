package com.example.pizzaapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FragmentTransaction : Fragment() {

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
        return inflater.inflate(R.layout.fragment_transaction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val rvTrx: RecyclerView = view.findViewById(R.id.recyclerView)
        rvTrx.apply {
            rvTrx.layoutManager = LinearLayoutManager(activity)
            var adapter = TransaksiAdapter()
            rvTrx.adapter = adapter
        }
        val txtOrder: TextView = view.findViewById(R.id.textTotalOrder)
        val txtTax: TextView = view.findViewById(R.id.textTax)
        val txtTotal: TextView = view.findViewById(R.id.textTotalPrice)

        txtOrder.text =  TransaksiAdapter.harga.toString()
        txtTax.text =  (TransaksiAdapter.harga * 0.1).toString()
        txtTotal.text =  (TransaksiAdapter.harga+(TransaksiAdapter.harga * 0.1)).toString()

        val btnPay: Button = view.findViewById(R.id.btnPayNow)
        btnPay.setOnClickListener {
            activity?.let {
                val intent = Intent(it, PaymentActivity::class.java)
                it.startActivity(intent)
            }
        }
    }


    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentTransaction().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
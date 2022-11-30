package com.ademozalp.criptokotlin.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ademozalp.criptokotlin.R
import com.ademozalp.criptokotlin.adapter.CryptoAdapter.CryptoHolder
import com.ademozalp.criptokotlin.databinding.RecyclerrowBinding
import com.ademozalp.criptokotlin.model.CoinModel
import android.view.View
import android.widget.Toast
import kotlin.random.Random

class CryptoAdapter(val cryptoList : ArrayList<CoinModel>,val context : Context) : RecyclerView.Adapter<CryptoHolder>() {

    private val colors : Array<String> = arrayOf("#ff81ae", "#1adf75","#f4f867","#a962f8", "#d921cf", "#faaac4","#faaac4", "#ae9999")

    class CryptoHolder(val binding: RecyclerrowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHolder {
        val binding = RecyclerrowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CryptoHolder(binding)
    }

    override fun onBindViewHolder(holder: CryptoHolder, position: Int) {
        holder.binding.currency.text =  cryptoList.get(position).currency
        holder.binding.price.text = cryptoList.get(position).price

        var random  = Random.nextInt(0,8)
        holder.itemView.setBackgroundColor(Color.parseColor(colors[random]))

        holder.itemView.setOnClickListener{
            Toast.makeText(context, "${cryptoList.get(position).currency} : ${cryptoList.get(position).price}", Toast.LENGTH_LONG ).show()
        }
    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }

}
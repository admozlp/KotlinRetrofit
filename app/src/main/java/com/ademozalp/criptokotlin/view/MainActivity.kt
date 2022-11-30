package com.ademozalp.criptokotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.ademozalp.criptokotlin.R
import com.ademozalp.criptokotlin.adapter.CryptoAdapter
import com.ademozalp.criptokotlin.databinding.ActivityMainBinding
import com.ademozalp.criptokotlin.model.CoinModel
import com.ademozalp.criptokotlin.service.CryptoApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    val BASE_URL = "https://raw.githubusercontent.com/"
    private lateinit var binding: ActivityMainBinding
    private lateinit var arrayList : ArrayList<CoinModel>
    private var compositeSubscription = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        arrayList = ArrayList()

        loadData()
    }

    private fun loadData(){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(CryptoApi::class.java)

        compositeSubscription.add(retrofit.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse)
        )
        /*
        val service = retrofit.create(CryptoApi::class.java)
        val call = service.getData()
        call.enqueue(object : Callback<List<CoinModel>>{
            override fun onResponse(call: Call<List<CoinModel>>, response: Response<List<CoinModel>>) {
                if(response.isSuccessful){
                    response.body()?.let {
                        arrayList = ArrayList(it)

                        binding.cryptoRv.layoutManager = LinearLayoutManager(this@MainActivity)
                        val adapter = CryptoAdapter(arrayList, this@MainActivity)
                        binding.cryptoRv.adapter = adapter
                    }
                }
            }
            override fun onFailure(call: Call<List<CoinModel>>, t: Throwable) {
                println(t.localizedMessage)
                Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_LONG).show()
            }
        })*/

    }

    fun handleResponse (cryptoList : List<CoinModel>) {
        arrayList = ArrayList(cryptoList)

        binding.cryptoRv.layoutManager = LinearLayoutManager(this@MainActivity)
        val adapter = CryptoAdapter(arrayList, this@MainActivity)
        binding.cryptoRv.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeSubscription.clear()
    }
}



package com.ademozalp.criptokotlin.service

import com.ademozalp.criptokotlin.model.CoinModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.Call
import java.util.*

interface CryptoApi {
    //https://raw.githubusercontent.com/
    //atilsamancioglu/K21-JSONDataSet/master/crypto.json
    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    fun getData() : Observable<List<CoinModel>>


    //fun getData() : Call<List<CoinModel>>


}
package com.ademozalp.criptokotlin.model

import com.google.gson.annotations.SerializedName

data class CoinModel (
    //@SerializedName("currency")
    val currency : String,

    //@SerializedName("price")
    val price : String
)
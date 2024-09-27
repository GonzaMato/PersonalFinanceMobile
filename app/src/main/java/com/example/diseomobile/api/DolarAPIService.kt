package com.example.diseomobile.api

import retrofit.Call
import retrofit.http.GET

data class DolarPrice(
    val moneda : String,
    val casa : String,
    val nombre : String,
    val compra : Double,
    val venta : Double,
    val fechaDeActualizacion : String
)

interface DolarAPIService {

    @GET("v1/dolares")
    fun getDolarPrice(): Call<List<DolarPrice>>

}
package com.example.androidmaster.seleccionarcliente

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/clientes")  // Esta ruta debe coincidir con tu backend en Go
    fun obtenerClientes(): Call<List<Cliente>>
}

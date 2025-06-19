package com.example.androidmaster.seleccionarproductos

import java.io.Serializable

data class Producto(
    val id: String,
    val nombre: String,
    var precio: Double,
    var cantidad: Int = 0
) : Serializable

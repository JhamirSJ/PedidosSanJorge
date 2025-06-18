package com.example.androidmaster.seleccionarproductos

data class Producto(
    val id: String,
    val nombre: String,
    val precio: Double,
    var cantidad: Int = 0
)
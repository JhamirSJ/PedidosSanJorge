package com.example.androidmaster.registrarpedidos

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidmaster.R
import java.util.Date
import java.util.Locale

class RegPedidosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reg_pedidos)

        // Obtener el TextView
        val tvFecha: TextView = findViewById(R.id.tvFechaActual)

        // Obtener la fecha actual
        val fechaActual = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())

        // Mostrarla en el TextView
        tvFecha.text = "Fecha: $fechaActual"
    }

}
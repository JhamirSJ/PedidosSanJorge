package com.example.androidmaster.registrarpedidos

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidmaster.R
import com.example.androidmaster.seleccionarcliente.SelecClienteActivity
import com.google.android.material.button.MaterialButton
import java.util.Date
import java.util.Locale

class RegPedidosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg_pedidos)

        val btnSelecCliente = findViewById<MaterialButton>(R.id.btnSelecCliente)
        btnSelecCliente.setOnClickListener { navigateToSelecCliente() }

        // Obtener el TextView
        val tvFecha: TextView = findViewById(R.id.tvFechaActual)

        // Obtener la fecha actual
        val fechaActual = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())

        // Mostrarla en el TextView
        tvFecha.text = "Fecha: $fechaActual"
    }

    val SELECCIONAR_CLIENTE = 1

    fun navigateToSelecCliente() {
        val intent = Intent(this, SelecClienteActivity::class.java)
        startActivityForResult(intent, SELECCIONAR_CLIENTE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SELECCIONAR_CLIENTE && resultCode == RESULT_OK && data != null) {
            val codigo = data.getStringExtra("codigo")
            val nombre = data.getStringExtra("nombre")

            val tvCodigo = findViewById<TextView>(R.id.tvCodigoSeleccionado)
            val tvNombre = findViewById<TextView>(R.id.tvNombreSeleccionado)
            val tvDireccion = findViewById<TextView>(R.id.tvDireccionSeleccionado)

            tvCodigo.text = codigo
            tvNombre.text = nombre
            tvDireccion.text = "Dirección de prueba" // o traerla desde el objeto si lo tienes
        }
    }
}
package com.example.androidmaster.registrarpedidos

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidmaster.R
import com.example.androidmaster.seleccionarcliente.SelecClienteActivity
import com.example.androidmaster.seleccionarproductos.SelecProductosActivity
import com.google.android.material.button.MaterialButton
import java.util.Date
import java.util.Locale

class RegPedidosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg_pedidos)

        val btnSelecCliente = findViewById<MaterialButton>(R.id.btnSelecCliente)
        val btnSelecProductos = findViewById<MaterialButton>(R.id.btnSelecProductos)

        btnSelecCliente.setOnClickListener { navigateToSelecCliente() }
        btnSelecProductos.setOnClickListener { navigateToSelecProductos() }

        //Fecha Actual
        val tvFecha: TextView = findViewById(R.id.tvFechaActual)
        val fechaActual = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        tvFecha.text = "Fecha: $fechaActual"
    }

    val SELECCIONAR_CLIENTE = 1

    fun navigateToSelecCliente() {
        val intent = Intent(this, SelecClienteActivity::class.java)
        startActivityForResult(intent, SELECCIONAR_CLIENTE)
    }

    fun navigateToSelecProductos() {
        val intent = Intent(this, SelecProductosActivity::class.java)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SELECCIONAR_CLIENTE && resultCode == RESULT_OK && data != null) {
            val codigo = data.getStringExtra("codigo")
            val nombre = data.getStringExtra("nombre")
            val direccion = data.getStringExtra("direccion")

            val tvCodigo = findViewById<TextView>(R.id.tvCodigoSeleccionado)
            val tvNombre = findViewById<TextView>(R.id.tvNombreSeleccionado)
            val tvDireccion = findViewById<TextView>(R.id.tvDireccionSeleccionado)

            tvCodigo.text = codigo
            tvNombre.text = nombre
            tvDireccion.text = direccion
        }
    }
}
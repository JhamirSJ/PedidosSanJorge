package com.example.androidmaster.registrarpedidos

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidmaster.R
import com.example.androidmaster.seleccionarcliente.SelecClienteActivity
import com.example.androidmaster.seleccionarproductos.Producto
import com.example.androidmaster.seleccionarproductos.SelecProductosActivity
import com.google.android.material.button.MaterialButton
import java.util.Date
import java.util.Locale

class RegPedidosActivity : AppCompatActivity() {

    private var productosSeleccionados: ArrayList<Producto> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg_pedidos)

        val btnSelecCliente = findViewById<MaterialButton>(R.id.btnSelecCliente)
        val btnSelecProductos = findViewById<MaterialButton>(R.id.btnSelecProductos)

        btnSelecCliente.setOnClickListener { navigateToSelecCliente() }
        btnSelecProductos.setOnClickListener { navigateToSelecProductos() }

        val tvFecha: TextView = findViewById(R.id.tvFechaActual)
        val fechaActual = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        tvFecha.text = "Fecha: $fechaActual"
    }

    val SELECCIONAR_CLIENTE = 1
    val SELECCIONAR_PRODUCTOS = 2

    fun navigateToSelecCliente() {
        val intent = Intent(this, SelecClienteActivity::class.java)
        startActivityForResult(intent, SELECCIONAR_CLIENTE)
    }

    fun navigateToSelecProductos() {
        val intent = Intent(this, SelecProductosActivity::class.java)
        intent.putExtra("productosSeleccionados", productosSeleccionados)
        startActivityForResult(intent, SELECCIONAR_PRODUCTOS)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SELECCIONAR_PRODUCTOS && resultCode == RESULT_OK) {
            val nuevosSeleccionados = data?.getSerializableExtra("productosSeleccionados") as? ArrayList<Producto>
            val total = data?.getDoubleExtra("total", 0.0) ?: 0.0

            if (nuevosSeleccionados != null) {
                productosSeleccionados = nuevosSeleccionados

                val tvTotal = findViewById<TextView>(R.id.tvTotal)
                tvTotal.text = "S/. %.2f".format(total)
            }
        }

        if (requestCode == SELECCIONAR_CLIENTE && resultCode == RESULT_OK) {
            val codigo = data?.getStringExtra("codigo")
            val nombre = data?.getStringExtra("nombre")
            val direccion = data?.getStringExtra("direccion")

            findViewById<TextView>(R.id.tvCodigoSeleccionado).text = codigo
            findViewById<TextView>(R.id.tvNombreSeleccionado).text = nombre
            findViewById<TextView>(R.id.tvDireccionSeleccionado).text = direccion
        }
    }
}
package com.example.androidmaster.seleccionarproductos

import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmaster.R
import com.example.androidmaster.data.SQLiteHelper

class SelecProductosActivity : AppCompatActivity() {

    private lateinit var rvProductos: RecyclerView
    private lateinit var etBuscar: EditText
    private lateinit var productoAdapter: ProductoAdapter

    private var productos: List<Producto> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selec_productos)

        initComponents()
        obtenerClientesDB()
        configurarBuscador()
    }

    private fun initComponents() {
        rvProductos = findViewById(R.id.rvProductos)
        etBuscar = findViewById(R.id.etBuscar)
        rvProductos.layoutManager = LinearLayoutManager(this)
    }

    private fun obtenerClientesDB() {
        productos = SQLiteHelper(this).obtenerProductos() // Usa la función centralizada

        productoAdapter = ProductoAdapter(productos)
        rvProductos.adapter = productoAdapter
    }

    private fun configurarBuscador() {
        etBuscar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val texto = s.toString().lowercase()
                val filtrados = productos.filter {
                    it.nombre.lowercase().contains(texto) || it.id.lowercase().contains(texto)
                }
                productoAdapter.actualizarLista(filtrados)
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    // Puedes agregar aquí un botón para confirmar el pedido.
    //private fun confirmarPedido() {
    //    val seleccionados = adapter.obtenerSeleccionados()
    //    if (seleccionados.isEmpty()) {
    //        Toast.makeText(this, "No has seleccionado productos", Toast.LENGTH_SHORT).show()
    //    } else {
    // Guardar en Room, pasar a siguiente pantalla, etc.
    //    }
}
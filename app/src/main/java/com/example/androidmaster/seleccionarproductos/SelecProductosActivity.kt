package com.example.androidmaster.seleccionarproductos

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.text.TextWatcher
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmaster.R
import com.example.androidmaster.data.SQLiteHelper
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SelecProductosActivity : AppCompatActivity() {

    private lateinit var rvProductos: RecyclerView
    private lateinit var etBuscar: EditText
    private lateinit var productoAdapter: ProductosAdapter

    private var productos: List<Producto> = emptyList()

    private var productosFiltrados: List<Producto> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selec_productos)

        val fabConfirmar = findViewById<FloatingActionButton>(R.id.fabConfirmar)
        fabConfirmar.setOnClickListener { confirmarSeleccionDeProductos() }

        initComponents()
        obtenerProductosDB()
        configurarBuscador()
    }

    private fun initComponents() {
        rvProductos = findViewById(R.id.rvProductos)
        etBuscar = findViewById(R.id.etBuscar)
        rvProductos.layoutManager = LinearLayoutManager(this)
    }

    private fun obtenerProductosDB() {
        productos = SQLiteHelper(this).obtenerProductos().toMutableList()

        val productosPrevios = intent.getSerializableExtra("productosSeleccionados") as? ArrayList<Producto>

        productosPrevios?.forEach { previo ->
            productos.find { it.id == previo.id }?.cantidad = previo.cantidad
        }

        productosFiltrados = productos
        productoAdapter = ProductosAdapter()
        productoAdapter.setListaOriginal(productos)
        rvProductos.adapter = productoAdapter
    }

    private fun configurarBuscador() {
        etBuscar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val texto = s.toString().lowercase()

                productosFiltrados = productos.filter {
                    it.nombre.lowercase().contains(texto) || it.id.lowercase().contains(texto)
                }

                productoAdapter.actualizarLista(productosFiltrados)
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun confirmarSeleccionDeProductos() {
        val seleccionados = productoAdapter.obtenerSeleccionados()

        if (seleccionados.isEmpty()) {
            Toast.makeText(this, "No has seleccionado ningún producto", Toast.LENGTH_SHORT).show()
            return
        }

        val total = seleccionados.sumOf { it.precio * it.cantidad }

        val intent = Intent()
        intent.putExtra("productosSeleccionados", ArrayList(seleccionados)) // Producto debe ser Serializable
        intent.putExtra("total", total)
        setResult(RESULT_OK, intent)
        finish()
    }
}

package com.example.androidmaster.seleccionarproductos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmaster.R

class ProductoAdapter : RecyclerView.Adapter<ProductoViewHolder>() {

    private var productosOriginales: MutableList<Producto> = mutableListOf()
    private var productosVisibles: MutableList<Producto> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)
        return ProductoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = productosVisibles[position]
        holder.bind(producto)
    }

    override fun getItemCount(): Int = productosVisibles.size

    fun actualizarLista(nuevaLista: List<Producto>) {
        productosVisibles = nuevaLista.toMutableList()
        notifyDataSetChanged()
    }

    fun setListaOriginal(lista: List<Producto>) {
        productosOriginales = lista.toMutableList()
        productosVisibles = lista.toMutableList()
        notifyDataSetChanged()
    }

    fun obtenerSeleccionados(): List<Producto> {
        return productosOriginales.filter { it.cantidad > 0 }
    }
}


package com.example.androidmaster.seleccionarproductos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmaster.R

class ProductoAdapter(
    private var productos: List<Producto>
) : RecyclerView.Adapter<ProductosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductosViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)
        return ProductosViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductosViewHolder, position: Int) {
        holder.bind(productos[position])
    }

    override fun getItemCount(): Int = productos.size

    fun actualizarLista(nuevaLista: List<Producto>) {
        productos = nuevaLista
        notifyDataSetChanged()
    }

    fun obtenerSeleccionados(): List<Producto> {
        return productos.filter { it.cantidad > 0 }
    }
}
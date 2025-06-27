package com.example.androidmaster.seleccionarcliente

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmaster.R

class ClienteViewHolder(
    view: View,
    private val onClick: (Cliente) -> Unit
) : RecyclerView.ViewHolder(view) {
    val tvCodigo: TextView = view.findViewById(R.id.tvCodigoCliente)
    val tvNombre: TextView = view.findViewById(R.id.tvNombreCliente)

    fun bind(cliente: Cliente) {
        tvCodigo.text = cliente.codigo
        tvNombre.text = cliente.nombre
        itemView.setOnClickListener {
            onClick(cliente)
        }
    }
}
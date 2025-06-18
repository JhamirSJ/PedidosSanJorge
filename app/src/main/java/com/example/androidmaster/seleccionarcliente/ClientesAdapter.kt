package com.example.androidmaster.seleccionarcliente

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmaster.R

class ClientesAdapter(
    private var clientes: List<Cliente>,
    private val onClick: (Cliente) -> Unit
) : RecyclerView.Adapter<ClientesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cliente, parent, false)
        return ClientesViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ClientesViewHolder, position: Int) {
        holder.bind(clientes[position])
    }

    override fun getItemCount() = clientes.size

    fun actualizarLista(nuevaLista: List<Cliente>) {
        val diffCallback = ClienteDiffCallback(clientes, nuevaLista)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        clientes = nuevaLista
        diffResult.dispatchUpdatesTo(this)
    }
}

private class ClienteDiffCallback(
    private val oldList: List<Cliente>,
    private val newList: List<Cliente>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        // Compara por ID único
        return oldList[oldItemPosition].codigo == newList[newItemPosition].codigo
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        // Compara todos los contenidos
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}

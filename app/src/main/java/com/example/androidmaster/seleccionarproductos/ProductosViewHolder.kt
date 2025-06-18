    package com.example.androidmaster.seleccionarproductos

    import android.text.Editable
    import android.text.TextWatcher
    import android.view.View
    import android.widget.Button
    import android.widget.EditText
    import android.widget.TextView
    import androidx.recyclerview.widget.RecyclerView
    import com.example.androidmaster.R

    class ProductosViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val id: TextView = view.findViewById(R.id.tvIdProducto)
        val nombre: TextView = view.findViewById(R.id.tvNombreProducto)
        val cantidad: EditText = view.findViewById(R.id.etCantidad)
        val btnMenos: Button = view.findViewById(R.id.btnMenos)
        val btnMas: Button = view.findViewById(R.id.btnMas)

        fun bind(producto: Producto) {
            id.text = producto.id
            nombre.text = producto.nombre
            cantidad.setText(producto.cantidad.toString())

            // Para evitar múltiples listeners
            cantidad.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    val cantidadNueva = s.toString().toIntOrNull() ?: 0
                    producto.cantidad = cantidadNueva
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })

            btnMas.setOnClickListener {
                producto.cantidad++
                cantidad.setText(producto.cantidad.toString())
            }

            btnMenos.setOnClickListener {
                if (producto.cantidad > 0) producto.cantidad--
                cantidad.setText(producto.cantidad.toString())
            }
        }
    }
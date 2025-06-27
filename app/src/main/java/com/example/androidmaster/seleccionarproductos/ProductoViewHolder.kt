    package com.example.androidmaster.seleccionarproductos

    import android.text.Editable
    import android.text.TextWatcher
    import android.view.View
    import android.widget.Button
    import android.widget.EditText
    import android.widget.TextView
    import androidx.recyclerview.widget.RecyclerView
    import com.example.androidmaster.R

    class ProductoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val id: TextView = view.findViewById(R.id.tvIdProducto)
        private val nombre: TextView = view.findViewById(R.id.tvNombreProducto)
        private val cantidadEditText: EditText = view.findViewById(R.id.etCantidad)
        private val btnMenos: Button = view.findViewById(R.id.btnMenos)
        private val btnMas: Button = view.findViewById(R.id.btnMas)

        private var watcher: TextWatcher? = null

        fun bind(producto: Producto) {
            id.text = producto.id
            nombre.text = producto.nombre

            watcher?.let { cantidadEditText.removeTextChangedListener(it) }
            cantidadEditText.setText(producto.cantidad.toString())

            watcher = object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    val cantidadNueva = s?.toString()?.toIntOrNull() ?: 0
                    producto.cantidad = cantidadNueva
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            }

            cantidadEditText.addTextChangedListener(watcher)

            btnMas.setOnClickListener {
                producto.cantidad++
                cantidadEditText.setText(producto.cantidad.toString())
            }

            btnMenos.setOnClickListener {
                if (producto.cantidad > 0) {
                    producto.cantidad--
                    cantidadEditText.setText(producto.cantidad.toString())
                }
            }
        }
    }

    package com.example.androidmaster.seleccionarcliente

    import android.content.Context
    import android.content.Intent
    import android.os.Bundle
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.ArrayAdapter
    import android.widget.EditText
    import android.widget.ListView
    import android.widget.TextView
    import androidx.appcompat.app.AppCompatActivity
    import com.example.androidmaster.R

    class SelecClienteActivity : AppCompatActivity() {

        private lateinit var listView: ListView
        private lateinit var searchBox: EditText
        private lateinit var adapter: ClienteAdapter

        private val clientes: List<Cliente> = listOf(
            Cliente("C001", "Juan Pérez"),
            Cliente("C002", "Ana López"),
            Cliente("C003", "Carlos Gómez")
        )

        private val clientesFiltrados: MutableList<Cliente> = clientes.toMutableList()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_selec_cliente)

            listView = findViewById(R.id.lvClientes)
            searchBox = findViewById(R.id.etBuscar)

            adapter = ClienteAdapter(this, clientes)
            listView.adapter = adapter

            searchBox.setOnEditorActionListener { v, actionId, event ->
                val texto = searchBox.text.toString().lowercase()
                val filtrados = clientes.filter {
                    it.nombre.lowercase().contains(texto) || it.codigo.lowercase().contains(texto)
                }
                adapter.clear()
                adapter.addAll(filtrados)
                adapter.notifyDataSetChanged()
                true // indica que manejamos la acción del teclado
            }

            listView.setOnItemClickListener { _, _, position, _ ->
                val cliente = adapter.getItem(position)
                val resultIntent = Intent().apply {
                    putExtra("codigo", cliente?.codigo)
                    putExtra("nombre", cliente?.nombre)
                }
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }
    }

    class ClienteAdapter(context: Context, clientes: List<Cliente>) :
        ArrayAdapter<Cliente>(context, 0, clientes) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val cliente = getItem(position)
            val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.cliente_item, parent, false)

            val tvCodigo = view.findViewById<TextView>(R.id.tvCodigoCliente)
            val tvNombre = view.findViewById<TextView>(R.id.tvNombreCliente)

            tvCodigo.text = "Código: ${cliente?.codigo}"
            tvNombre.text = cliente?.nombre

            return view
        }
    }

    data class Cliente(val codigo: String, val nombre: String)
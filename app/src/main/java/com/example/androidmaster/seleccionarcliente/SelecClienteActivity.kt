    package com.example.androidmaster.seleccionarcliente

    import android.content.Intent
    import android.os.Bundle
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.EditText
    import android.widget.TextView
    import androidx.appcompat.app.AppCompatActivity
    import androidx.recyclerview.widget.LinearLayoutManager
    import androidx.recyclerview.widget.RecyclerView
    import com.example.androidmaster.R
    import android.util.Log


    class SelecClienteActivity : AppCompatActivity() {

        private lateinit var recyclerView: RecyclerView
        private lateinit var searchBox: EditText
        private lateinit var adapter: ClienteAdapter

        private var clientes: List<Cliente> = emptyList()

        private val clientesFiltrados: MutableList<Cliente> = clientes.toMutableList()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_selec_cliente)

            obtenerClientesDesdeApi()

            recyclerView = findViewById(R.id.rvClientes)
            searchBox = findViewById(R.id.etBuscar)

            adapter = ClienteAdapter(clientes) { cliente ->
                val resultIntent = Intent().apply {
                    putExtra("codigo", cliente.documento)
                    putExtra("nombre", cliente.nombre)
                }
                setResult(RESULT_OK, resultIntent)
                finish()
            }

            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = adapter

            searchBox.setOnEditorActionListener { _, _, _ ->
                val texto = searchBox.text.toString().lowercase()
                val filtrados = clientes.filter {
                    it.nombre.lowercase().contains(texto) || it.documento.lowercase().contains(texto)
                }
                adapter.actualizarLista(filtrados)
                true
            }
        }
        private fun obtenerClientesDesdeApi() {
            RetrofitClient.instancia.obtenerClientes().enqueue(object : retrofit2.Callback<List<Cliente>> {
                override fun onResponse(call: retrofit2.Call<List<Cliente>>, response: retrofit2.Response<List<Cliente>>) {
                    if (response.isSuccessful) {
                        val respuesta = response.body() ?: emptyList()
                        Log.d("API_RESPONSE", "Clientes recibidos: $respuesta")

                        clientes = respuesta
                        adapter.actualizarLista(clientes)
                    } else {
                        Log.e("API_RESPONSE", "Error: código ${response.code()}")
                    }
                }

                override fun onFailure(call: retrofit2.Call<List<Cliente>>, t: Throwable) {
                    Log.e("API_ERROR", "Fallo en la llamada: ${t.message}", t)
                }
            })
        }

    }

    class ClienteAdapter(
        private var clientes: List<Cliente>,
        private val onClick: (Cliente) -> Unit
    ) : RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder>() {

        inner class ClienteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tvCodigo: TextView = view.findViewById(R.id.tvCodigoCliente)
            val tvNombre: TextView = view.findViewById(R.id.tvNombreCliente)

            fun bind(cliente: Cliente) {
                tvCodigo.text = "Documento: ${cliente.documento}"
                tvNombre.text = cliente.nombre
                itemView.setOnClickListener {
                    onClick(cliente)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClienteViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.cliente_item, parent, false)
            return ClienteViewHolder(view)
        }

        override fun onBindViewHolder(holder: ClienteViewHolder, position: Int) {
            holder.bind(clientes[position])
        }

        override fun getItemCount(): Int = clientes.size

        fun actualizarLista(nuevaLista: List<Cliente>) {
            clientes = nuevaLista
            Log.d("ADAPTER", "Lista actualizada con ${clientes.size} clientes")
            notifyDataSetChanged()
        }
    }

    data class Cliente(
        val nombre: String,
        val documento: String
    )

    package com.example.androidmaster.seleccionarcliente

    import android.content.Intent
    import android.os.Bundle
    import android.text.Editable
    import android.text.TextWatcher
    import android.widget.EditText
    import androidx.appcompat.app.AppCompatActivity
    import androidx.recyclerview.widget.LinearLayoutManager
    import androidx.recyclerview.widget.RecyclerView
    import com.example.androidmaster.R
    import com.example.androidmaster.data.SQLiteHelper

    class SelecClienteActivity : AppCompatActivity() {

        private lateinit var rvCliente: RecyclerView
        private lateinit var sbCliente: EditText
        private lateinit var clientesAdapter: ClientesAdapter

        private var clientes: List<Cliente> = emptyList()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_selec_cliente)

            initComponent()
            initInterface()
            obtenerClientesDB()
            initSearchBox()

        }

        private fun initComponent(){
            rvCliente = findViewById(R.id.rvClientes)
            sbCliente = findViewById(R.id.etBuscar)

            rvCliente.layoutManager = LinearLayoutManager(this)
        }

        private fun initInterface(){
            clientesAdapter = ClientesAdapter(clientes) { cliente ->
                val resultIntent = Intent().apply {
                    putExtra("codigo", cliente.codigo)
                    putExtra("nombre", cliente.nombre)
                }
                setResult(RESULT_OK, resultIntent)
                finish()
            }
            rvCliente.adapter = clientesAdapter
        }

        private fun obtenerClientesDB() {
            val helper = SQLiteHelper(this)
            clientes = helper.obtenerClientes()
            clientesAdapter.actualizarLista(clientes)
        }


        private fun initSearchBox() {
            sbCliente.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    val texto = s.toString().lowercase()
                    val filtrados = clientes.filter {
                        it.nombre.lowercase().contains(texto) || it.codigo.lowercase().contains(texto)
                    }
                    clientesAdapter.actualizarLista(filtrados)
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })
        }
    }

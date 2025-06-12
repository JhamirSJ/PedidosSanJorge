package com.example.androidmaster

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.androidmaster.registrarpedidos.RegPedidosActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val btn_RgPedidos = findViewById<AppCompatButton>(R.id.btn_RgPedidos)
        btn_RgPedidos.setOnClickListener { navigateToRgPedidos() }
    }
    private fun navigateToRgPedidos(){
        val intent = Intent(this, RegPedidosActivity::class.java)
        startActivity(intent)
    }
}
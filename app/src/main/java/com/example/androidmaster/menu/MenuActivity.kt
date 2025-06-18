package com.example.androidmaster.menu

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.androidmaster.R
import com.example.androidmaster.registrarpedidos.RegPedidosActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val btnRgPedidos = findViewById<AppCompatButton>(R.id.btnRgPedidos)
        btnRgPedidos.setOnClickListener { navigateToRgPedidos() }
    }
    private fun navigateToRgPedidos(){
        val intent = Intent(this, RegPedidosActivity::class.java)
        startActivity(intent)
    }
}
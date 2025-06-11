package com.example.androidmaster.firstapp

import android.content.Intent
import android.os.Bundle
import android.provider.Telephony.Mms.Intents
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidmaster.R

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val btn_RgPedidos = findViewById<AppCompatButton>(R.id.btn_RgPedidos)
        btn_RgPedidos.setOnClickListener {}
    }

    fun navigateToRgPedidos(){
        val intent = Intent(this, FirstAppActivity::class.java)
        startActivity(intent)
    }
}
package com.example.androidmaster.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.androidmaster.seleccionarcliente.Cliente
import com.example.androidmaster.seleccionarproductos.Producto
import java.io.File
import java.io.FileOutputStream

class SQLiteHelper(private val context: Context) {

    private val dbName = "sanjorgev2.db"
    private val dbPath: String
        get() = context.getDatabasePath(dbName).path

    init {
        copyDatabaseIfNeeded()
    }

    private fun copyDatabaseIfNeeded() {
        val dbFile = File(dbPath)
        if (!dbFile.exists()) {
            context.assets.open(dbName).use { input ->
                File(dbFile.parent ?: "").mkdirs()
                FileOutputStream(dbFile).use { output ->
                    input.copyTo(output)
                }
            }
        }
    }

    private fun getReadableDatabase(): SQLiteDatabase {
        return SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY)
    }

    fun obtenerClientes(): List<Cliente> {
        val lista = mutableListOf<Cliente>()
        val db = getReadableDatabase()
        val cursor = db.rawQuery(
            "SELECT customers.altname, customers.custentity_pe_document_number, customeraddressbooks.label FROM customers JOIN customeraddressbooks ON customers.codid = customeraddressbooks.entity;", null)

        while (cursor.moveToNext()) {
            val nombre = cursor.getString(0)
            val codigo = cursor.getString(1)
            val direccion = cursor.getString(2)
            lista.add(Cliente(nombre = nombre, codigo = codigo, direccion = direccion))
        }

        cursor.close()
        db.close()
        return lista
    }

    fun obtenerProductos(): List<Producto> {
        val productos = mutableListOf<Producto>()
        val db = getReadableDatabase()
        val cursor = db.rawQuery("SELECT itemid, displayname FROM item", null)

        while (cursor.moveToNext()) {
            val id = cursor.getString(0)
            val nombre = cursor.getString(1)
            productos.add(Producto(id = id, nombre = nombre, precio = 0.0)) // Asigna precio si lo tuvieras
        }

        cursor.close()
        db.close()

        return productos
    }

}
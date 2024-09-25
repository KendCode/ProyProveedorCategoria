package com.example.primeropasoskotlin


import android.content.ContentValues
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import com.example.primeropasoskotlin.db.AdminSQLiteOpenHelper
import com.example.primeropasoskotlin.models.Productos

class Productos : AppCompatActivity() {
    lateinit var btnAgre:Button
    lateinit var txtNom: EditText
    lateinit var txtPre: EditText
    lateinit var txtCod: EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_productos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        cargarR()
        estadoButon()
    }
    fun cargarR() {
        btnAgre = findViewById(R.id.btnRegistrarProducto)
        txtNom = findViewById(R.id.txtNomPro)
        txtCod = findViewById(R.id.txtCodPro)
        txtPre = findViewById(R.id.txtPrePro)
    }
    fun estadoButon() {
        btnAgre.setOnClickListener{
            val obj = Productos(txtNom.getText().toString(),txtPre.getText().toString().toDouble())
            val admin = AdminSQLiteOpenHelper(this,"administracion", null, 1)
            val bd = admin.writableDatabase
            val registro = ContentValues()
            registro.put("id_producto", txtCod.getText().toString())
            registro.put("nombre", obj.getNombre())
            registro.put("precio", obj.getPrecio())
            bd.insert("producto", null, registro)
            bd.close()
            txtCod.setText("")
            txtNom.setText("")
            txtPre.setText("")
            Toast.makeText(this, "Se cargaron los datos del producto", Toast.LENGTH_SHORT).show()
        }

    }
}
package com.example.primeropasoskotlin

import Proveedor
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


class Proveedores : AppCompatActivity() {

    lateinit var btnAgregarProv: Button
    lateinit var txtNomProv: EditText
    lateinit var txtCodProvedor: EditText
    lateinit var txtNITProv: EditText
    lateinit var txtDireccionProv: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_proveedores)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        cargarR()
        estadoButon()
    }

    fun cargarR() {
        btnAgregarProv = findViewById(R.id.btnRegisProveedor)
        txtCodProvedor = findViewById(R.id.txtCodProv)
        txtNomProv = findViewById(R.id.txtNomProveedor)
        txtNITProv = findViewById(R.id.txtNitProv)
        txtDireccionProv = findViewById(R.id.txtDireccionProv)
    }

    fun estadoButon() {
        btnAgregarProv.setOnClickListener {
            val obj = Proveedor(txtNomProv.getText().toString(),txtCodProvedor.getText().toString().toInt())
            val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
            val bd = admin.writableDatabase
            val registro = ContentValues()
            registro.put("id_proveedor", txtCodProvedor.getText().toString())
            registro.put("nombreprov", obj.getNombreProv())
            registro.put("nit", txtNITProv.getText().toString())
            registro.put("direccion", txtDireccionProv.getText().toString())
            bd.insert("producto", null, registro)
            bd.close()
            txtCodProvedor.setText("")
            txtNomProv.setText("")
            txtNITProv.setText("")
            txtDireccionProv.setText("")
            Toast.makeText(this, "Se cargaron los datos del producto", Toast.LENGTH_SHORT).show()
        }
    }
}
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
import com.example.primeropasoskotlin.models.Categoria

class Kategoria : AppCompatActivity() {
    lateinit var btnAgreCate: Button
    lateinit var txtNomCate: EditText
    lateinit var txtCodCate: EditText
    lateinit var txtDescripCate: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_categoria)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        cargarR()
        estadoButon()
    }
        fun cargarR() {
            btnAgreCate = findViewById(R.id.btnRegistrarCategoria)
            txtCodCate = findViewById(R.id.txtCodCate)
            txtNomCate = findViewById(R.id.txtNomCate)
            txtDescripCate = findViewById(R.id.txtDescripCate)
        }
        fun estadoButon() {
            btnAgreCate.setOnClickListener {
                val obj = Categoria(
                    txtNomCate.getText().toString(),
                    txtCodCate.getText().toString().toInt()
                )
                val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
                val bd = admin.writableDatabase
                val registro = ContentValues()
                registro.put("id_categoria", txtCodCate.getText().toString())
                registro.put("nombrecat", obj.getNombreCategoria())
                registro.put("descripcion", txtDescripCate.getText().toString())
                bd.insert("producto", null, registro)
                bd.close()
                txtCodCate.setText("")
                txtNomCate.setText("")
                txtDescripCate.setText("")
                Toast.makeText(this, "Se cargaron los datos", Toast.LENGTH_SHORT).show()
            }
        }
}

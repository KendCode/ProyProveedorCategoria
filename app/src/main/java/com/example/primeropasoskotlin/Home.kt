package com.example.primeropasoskotlin

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Home : AppCompatActivity(){
    lateinit var cardPro:CardView
    lateinit var carIva:CardView
    lateinit var carProveedor:CardView
    lateinit var carCategoria:CardView
    lateinit var carCliente:CardView

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        cargarR()
        estadoBoton()
    }

    fun cargarR() {
        cardPro = findViewById(R.id.cardPro)
        carIva = findViewById(R.id.carIva)
        carProveedor = findViewById(R.id.carProveedor)
        carCategoria = findViewById(R.id.cardCategoria)
        carCliente = findViewById(R.id.cardCliente)
    }

    fun estadoBoton(){
        carIva.setOnClickListener{
            val i = Intent(this,MainActivity::class.java)
            startActivity(i)
        }
        cardPro.setOnClickListener{
            val i = Intent(this,Productos::class.java)
            startActivity(i)
        }
        carProveedor.setOnClickListener{
            val i = Intent(this,Proveedores::class.java)
            startActivity(i)
        }
        carCategoria.setOnClickListener{
            val i = Intent(this,Kategoria::class.java)
            startActivity(i)
        }


    }
}
package com.example.appsenasoft2020

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class RegisterForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_form)
        var botonaccion= findViewById<Button>(R.id.actionregister)
        var codigoEmpresa = findViewById<EditText>(R.id.codigoEmpresa)
        var nombrepersona= findViewById<EditText>(R.id.nombrep)
        var apellidopersona = findViewById<EditText>(R.id.apellidop)
        var clavePersona = findViewById<EditText>(R.id.clave)
        var documentoPersona = findViewById<EditText>(R.id.documento)

    }
}
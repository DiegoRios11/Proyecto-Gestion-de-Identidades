package com.diegorios.vistatest.ui.login

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.diegorios.vistatest.R

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_forgot_password)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val etEmail = findViewById<AppCompatEditText>(R.id.etRecoverEmail)
        val btnRecoverPassword = findViewById<AppCompatButton>(R.id.btnRecoverPassword)
        btnRecoverPassword.setOnClickListener {
            val Email = etEmail.text.toString()

            if (Email.isNotEmpty()) {
                try {
                    Toast.makeText(this, "Correo de Recuperación Enviado ", Toast.LENGTH_SHORT)
                        .show()
                    finish()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {
                etEmail.error = "Ingrese Correo de Recuperación"
            }

        }

        val btnRegresar= findViewById<AppCompatButton>(R.id.btnRegresar)

        btnRegresar.setOnClickListener{
            try {
                finish()
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


}
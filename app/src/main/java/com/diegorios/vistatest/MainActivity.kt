package com.diegorios.vistatest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

public class MainLogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnLogin = findViewById<AppCompatButton>(R.id.btnLogin)
        val etUser = findViewById<AppCompatEditText>(R.id.etUser)
        val etPassword = findViewById<AppCompatEditText>(R.id.etPassword)
        val btnRegister = findViewById<AppCompatButton>(R.id.btnRegister)
        val btnForgotPassword = findViewById<AppCompatButton>(R.id.btnForgotPassword)

        btnLogin.setOnClickListener {
            val user = etUser.text.toString()
            val password = etPassword.text.toString()

            if (user.isEmpty() && password.isEmpty()) {
                etUser.error = "El usuario es obligatorio"
                etPassword.error = "La contraseña es obligatoria"
                return@setOnClickListener
            } else if (user.isEmpty()) {
                etUser.error = "El usuario es obligatorio"
                return@setOnClickListener
            } else if (password.isEmpty()) {
                etPassword.error = "La contraseña es obligatoria"
                return@setOnClickListener
            }

            if (user.isNotEmpty() && password.isNotEmpty()) {
                try {
                    val intent = Intent(this, NavDrawerActivity::class.java)
                    startActivity(intent)
                    clearInputs()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else if (user.isEmpty() || password.isEmpty()) {
                try {
                    Toast.makeText(this, "Usuario y/o Contraseña inválidos", Toast.LENGTH_SHORT)
                        .show()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        btnRegister.setOnClickListener {
            try {
                val intent1 = Intent(this, SolicitateUser::class.java)
                startActivity(intent1)
                clearInputs()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        btnForgotPassword.setOnClickListener{
            try {
                val intent2 = Intent(this, ForgotPasswordActivity::class.java)
                startActivity(intent2)
                clearInputs()
            }catch (e: Exception) {
                e.printStackTrace()
            }

        }

    }

    fun clearInputs() {
        val etUser = findViewById<AppCompatEditText>(R.id.etUser)
        val etPassword = findViewById<AppCompatEditText>(R.id.etPassword)
        etPassword.text?.clear()
        etUser.text?.clear()
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            currentFocus?.clearFocus()  // Remueve el foco del EditText
        }
        return super.dispatchTouchEvent(ev)
    }


}
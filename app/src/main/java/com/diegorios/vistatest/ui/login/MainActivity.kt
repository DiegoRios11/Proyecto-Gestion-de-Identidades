package com.diegorios.vistatest.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.diegorios.vistatest.R
import com.diegorios.vistatest.ui.login.register.SolicitateUser

class MainLogActivity : AppCompatActivity() {

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

            if (errorMessage(user, password, etUser, etPassword)) {
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
                    error_login()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        btnRegister.setOnClickListener {
            try {
                val intentSolicitateUser = Intent(this, SolicitateUser::class.java)
                startActivity(intentSolicitateUser)
                clearInputs()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        btnForgotPassword.setOnClickListener {
            try {
                val intentForgotPassword = Intent(this, ForgotPasswordActivity::class.java)
                startActivity(intentForgotPassword)
                clearInputs()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

    }
    @VisibleForTesting
    internal fun errorMessage(
        user: String,
        password: String,
        etUser: AppCompatEditText,
        etPassword: AppCompatEditText
    ): Boolean {
        return when {
            user.isEmpty() && password.isEmpty() -> {
                etUser.error = "El usuario es obligatorio"
                etPassword.error = "La contraseña es obligatoria"
                error_login()
                true
            }

            user.isEmpty() -> {
                etUser.error = "El usuario es obligatorio"
                error_login()
                true
            }

            password.isEmpty() -> {
                etPassword.error = "La contraseña es obligatoria"
                error_login()
                true
            }

            else -> false
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

    fun error_login(){
        Toast.makeText(this, "Usuario y/o Contraseña inválidos", Toast.LENGTH_SHORT)
            .show()
    }
}
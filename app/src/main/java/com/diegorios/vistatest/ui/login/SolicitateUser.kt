package com.diegorios.vistatest.ui.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.diegorios.vistatest.R

class SolicitateUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_solicitate_user)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val supervisor: Spinner = findViewById(R.id.spSupervisor)
        val documento: Spinner = findViewById(R.id.spTypeDoc)
        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            this,
            R.array.typeOfDocuments,
            R.layout.spinner_item
        )
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        val adaptersp: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            this,
            R.array.Supervisor,
            R.layout.spinner_item
        )
        adaptersp.setDropDownViewResource(R.layout.spinner_dropdown_item)
        supervisor.adapter = adaptersp
        documento.adapter = adapter

        try {
            val btnLogin = findViewById<AppCompatButton>(R.id.btnLogin)
            btnLogin.setOnClickListener {
                try {
                    finish()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } catch (e: Exception) {
            Log.e("ERROR", "Error setting OnClickListener", e)
        }

        try {
            val btnSolicitate = findViewById<AppCompatButton>(R.id.btnSolicitate)
            btnSolicitate.setOnClickListener {
                try {
                    finish()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } catch (e: Exception) {
            Log.e("ERROR", "Error setting OnClickListener", e)
        }

    }

//Método para ocultar el teclado cuando se toca fuera del EditText
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            currentFocus?.clearFocus()  // Remueve el foco del EditText
        }
        return super.dispatchTouchEvent(ev)
    }
}

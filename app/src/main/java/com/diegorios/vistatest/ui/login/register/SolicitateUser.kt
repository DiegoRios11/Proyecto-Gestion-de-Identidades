package com.diegorios.vistatest.ui.login.register

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.diegorios.vistatest.R

class SolicitateUser : AppCompatActivity() {
    private lateinit var etFirstName: EditText
    private lateinit var etFirstSurname: EditText
    private lateinit var etSecondName: EditText // Opcionales
    private lateinit var etSecondSurname: EditText // Opcionales
    private lateinit var etNumDoc: EditText
    private lateinit var etEmail: EditText
    private lateinit var etCargo: EditText
    private lateinit var spTypeDoc: Spinner
    private lateinit var spSupervisor: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_solicitate_user)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Referencias a los elementos de la interfaz
        etFirstName = findViewById(R.id.etFirstName)
        etFirstSurname = findViewById(R.id.etFirstSurname)
        etSecondName = findViewById(R.id.etSecondName) // Opcional
        etSecondSurname = findViewById(R.id.etSecondSurname) // Opcional
        etNumDoc = findViewById(R.id.etNumDoc)
        etEmail = findViewById(R.id.etEmail)
        etCargo = findViewById(R.id.etCargo)

        spSupervisor = findViewById(R.id.spSupervisor)
        spTypeDoc = findViewById(R.id.spTypeDoc)
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
        spSupervisor.adapter = adaptersp
        spTypeDoc.adapter = adapter

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
                    registerClick()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } catch (e: Exception) {
            Log.e("ERROR", "Error setting OnClickListener", e)
        }

    }

    private fun registerClick() {
        val fieldsToValidate = listOf(
            etFirstName to "El nombre no puede estar vacío",
            etFirstSurname to "El primer apellido no puede estar vacío",
            spTypeDoc to "Debe seleccionar un tipo de documento",
            etNumDoc to "El número de documento no puede estar vacío",
            etEmail to "El correo electrónico no puede estar vacío",
            etCargo to "El cargo no puede estar vacío",
            spSupervisor to "Debe seleccionar un supervisor"
        )

        if (validateRegistrationFields(fieldsToValidate)) {
            // Recolecta los valores de los campos obligatorios
            val firstName = etFirstName.text.toString()
            val firstSurname = etFirstSurname.text.toString()
            val documentType = spTypeDoc.selectedItem.toString()
            val documentNumber = etNumDoc.text.toString()
            val email = etEmail.text.toString()
            val position = etCargo.text.toString()
            val supervisor = spSupervisor.selectedItem.toString()

            // Recolecta los valores opcionales si están presentes
            val secondName = etSecondName.text.toString().takeIf { it.isNotEmpty() }
            val secondSurname =
                etSecondSurname.text.toString().takeIf { it.isNotEmpty() }

            // Crea el objeto de registro
            val registrationData = RegistrationRequest(
                firstName = firstName,
                firstSurname = firstSurname,
                secondName = secondName,
                secondSurname = secondSurname,
                documentType = documentType,
                documentNumber = documentNumber,
                email = email,
                position = position,
                supervisor = supervisor
            )

            // Llamar a la API o guarda los datos en la base de datos
            submitRegistration(registrationData)
            finish()
        }
    }

    private fun validateRegistrationFields(fields: List<Pair<View, String>>): Boolean {
        var allValid = true
        for ((field, errorMessage) in fields) {
            when (field) {
                is EditText -> {
                    if (field.text.isNullOrEmpty()) {
                        field.error = errorMessage
                        allValid = false
                    }
                }

                is Spinner -> {
                    if (field.selectedItemPosition == 0) {
                        // Muestra un mensaje (puedes usar un Toast o cualquier indicador visual)
                        Toast.makeText(field.context, errorMessage, Toast.LENGTH_SHORT).show()
                        allValid = false
                    }
                }
            }
        }
        return allValid
    }

    private fun submitRegistration(data: RegistrationRequest) {
        // Aquí se puede enviar los datos a través de una API o almacenarlos en Room
        Toast.makeText(this, "Registro enviado con éxito", Toast.LENGTH_SHORT).show()

    }

    //Función para ocultar el teclado cuando se toca fuera del EditText
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            currentFocus?.clearFocus()  // Remueve el foco del EditText
        }
        return super.dispatchTouchEvent(ev)
    }
}

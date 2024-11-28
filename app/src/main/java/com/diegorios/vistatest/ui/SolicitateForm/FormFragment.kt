package com.diegorios.vistatest.ui.SolicitateForm

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.diegorios.vistatest.R
import com.diegorios.vistatest.ui.database.entities.SolicitudEntity
import com.diegorios.vistatest.ui.database.entities.Status

class FormFragment : Fragment() {
    private lateinit var etFirstName: EditText
    private lateinit var etFirstSurname: EditText
    private lateinit var spTypeDoc: Spinner
    private lateinit var etNumDoc: EditText
    private lateinit var etLugarExpedicion: EditText
    private lateinit var etCargo: EditText
    private lateinit var etEmail: EditText
    private lateinit var etTelFijo: EditText
    private lateinit var etDateContrato: EditText
    private lateinit var etSolType: EditText
    private lateinit var etObsrvUser: EditText
    private lateinit var etSecondName: EditText
    private lateinit var etSecondSurname: EditText
    private lateinit var spSexo: Spinner
    private lateinit var spSede: Spinner
    private lateinit var spAplicativo: Spinner
    private lateinit var spModulo: Spinner
    private lateinit var checkbox: CheckBox
    private lateinit var checkbox2: CheckBox
    private lateinit var btnEnviarSoli: AppCompatButton

    @SuppressLint("CutPasteId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //val viewModel = ViewModelProvider(this).get(SolicitudFragmentViewModel::class.java)

        val view = inflater.inflate(R.layout.fragment_form, container, false)

        // Ajuste de sistema de barras
        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Referencias a los elementos obligatorios
        etFirstName = view.findViewById(R.id.etFirstName)
        etFirstSurname = view.findViewById(R.id.etFirstSurname)
        spTypeDoc = view.findViewById(R.id.spTypeDoc)
        etNumDoc = view.findViewById(R.id.etNumDoc)
        etLugarExpedicion = view.findViewById(R.id.etLugardeExpedicion)
        etCargo = view.findViewById(R.id.etCargo)
        etEmail = view.findViewById(R.id.etEmail)
        etDateContrato = view.findViewById(R.id.etDateContrato)
        etSolType = view.findViewById(R.id.etSolType)
        etObsrvUser = view.findViewById(R.id.etObsrvUser)
        etDateContrato.setOnClickListener { showDatePickerDialog() }

        // Referencias a los elementos opcionales
        etTelFijo = view.findViewById(R.id.etTelFijo)
        etSecondName = view.findViewById(R.id.etSecondName)
        etSecondSurname = view.findViewById(R.id.etSecondSurname)
        spSexo = view.findViewById(R.id.spSexo)
        spSede = view.findViewById(R.id.spSede)
        spAplicativo = view.findViewById(R.id.spAplicativo)
        spModulo = view.findViewById(R.id.spModulo)

        context?.let {
            spSexo.adapter = ArrayAdapter.createFromResource(
                it, R.array.Genero, R.layout.spinner_item
            )
            spTypeDoc.adapter = ArrayAdapter.createFromResource(
                it, R.array.typeOfDocuments, R.layout.spinner_item
            )
            spSede.adapter = ArrayAdapter.createFromResource(
                it, R.array.Sede, R.layout.spinner_item
            )
            spAplicativo.adapter = ArrayAdapter.createFromResource(
                it, R.array.Aplicativo, R.layout.spinner_item
            )
            spModulo.adapter = ArrayAdapter.createFromResource(
                it, R.array.Modulo, R.layout.spinner_item
            )
        }

        // Configuración de CheckBoxes y botón de enviar
        btnEnviarSoli = view.findViewById(R.id.btnEnviarSolicitud)
        checkbox = view.findViewById(R.id.checkTermsConf)
        checkbox2 = view.findViewById(R.id.checkDataTreatment)



        btnEnviarSoli.setOnClickListener {

            if (checkbox.isChecked && checkbox2.isChecked) {
                submitClick(view)
                //handleValidSubmission(view)
            } else {
                handleInvalidSubmission()
            }
        }

        checkbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                showTermsDialog(btnEnviarSoli)
            } else {
                btnEnviarSoli.isEnabled = false
            }
        }

        checkbox2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                showTermsDialog2(btnEnviarSoli)
            } else {
                btnEnviarSoli.isEnabled = false
            }
        }

        return view

    }

    private fun showTermsDialog(btnProceed: Button) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Términos y Condiciones")
        builder.setMessage(
            "Por favor, lee detenidamente estos términos y condiciones antes de utilizar nuestra aplicación. Al acceder o usar la aplicación, aceptas estar sujeto a estos términos y condiciones. Si no estás de acuerdo con cualquiera de los términos, no deberías utilizar nuestra aplicación.\n" +
                    "\n" +
                    "Aceptación de los Términos\n" +
                    "Al utilizar esta aplicación, confirmas que has leído y aceptas cumplir con todos los términos y condiciones aquí descritos. Nos reservamos el derecho de modificar estos términos en cualquier momento sin previo aviso, por lo que se recomienda revisarlos periódicamente.\n" +
                    "\n" +
                    "Licencia de Uso\n" +
                    "Se otorga una licencia limitada, no exclusiva, intransferible y revocable para acceder y utilizar la aplicación conforme a los términos descritos. La licencia se otorga para uso personal y no comercial. No se permite la reventa, redistribución, o cualquier uso comercial no autorizado de la aplicación.\n" +
                    "\n" +
                    "Propiedad Intelectual\n" +
                    "Todo el contenido, diseño gráfico, código, imágenes, texto, logotipos y otros elementos disponibles en la aplicación son propiedad de [Nombre de la Empresa] o de sus licenciantes. No se permite la copia, modificación, distribución o uso del contenido sin autorización expresa por escrito de los propietarios.\n" +
                    "\n" +
                    "Uso Permitido y Restricciones\n" +
                    "Está prohibido:\n" +
                    "\n" +
                    "Utilizar la aplicación para cualquier propósito ilegal.\n" +
                    "Intentar acceder sin autorización a nuestros sistemas o interferir con el funcionamiento de la aplicación.\n" +
                    "Recolectar información personal de otros usuarios sin su consentimiento.\n" +
                    "Privacidad y Protección de Datos\n" +
                    "Tomamos muy en serio la privacidad de nuestros usuarios. Los datos recopilados se utilizan de acuerdo con nuestra Política de Privacidad, la cual puedes consultar en la sección correspondiente de la aplicación. Al utilizar la aplicación, aceptas nuestra recopilación y uso de datos personales según lo descrito en dicha política.\n" +
                    "\n" +
                    "Limitación de Responsabilidad\n" +
                    "[Nombre de la Empresa] no será responsable de ningún daño indirecto, incidental, especial o consecuente que surja del uso o la incapacidad de utilizar la aplicación, incluso si hemos sido advertidos de la posibilidad de tales daños. El uso de la aplicación es bajo tu propio riesgo.\n" +
                    "\n" +
                    "Modificaciones y Actualizaciones\n" +
                    "Nos reservamos el derecho de modificar o discontinuar, temporal o permanentemente, cualquier función o característica de la aplicación en cualquier momento sin previo aviso. No seremos responsables ante ti o terceros por cualquier modificación, suspensión o interrupción del servicio.\n" +
                    "\n" +
                    "Cancelación de la Cuenta\n" +
                    "Nos reservamos el derecho de cancelar o suspender tu cuenta en cualquier momento si consideramos que has violado estos términos o has realizado una actividad ilegal dentro de la aplicación. No tendrás derecho a ningún tipo de compensación por la cancelación de tu cuenta.\n" +
                    "\n" +
                    "Enlaces a Terceros\n" +
                    "La aplicación puede contener enlaces a sitios web de terceros. Estos sitios no están bajo nuestro control, y no somos responsables del contenido ni de la política de privacidad de estos sitios. Acceder a sitios de terceros es bajo tu propio riesgo.\n" +
                    "\n" +
                    "Indemnización\n" +
                    "Aceptas indemnizar y mantener indemne a [Nombre de la Empresa] frente a cualquier reclamo, daño, pérdida o gasto derivado de tu uso de la aplicación o de la violación de estos términos y condiciones.\n" +
                    "\n" +
                    "Jurisdicción y Ley Aplicable\n" +
                    "Estos términos y condiciones se regirán por las leyes de [País o Estado]. Cualquier disputa que surja en relación con estos términos deberá resolverse en los tribunales de [Lugar].\n" +
                    "\n" +
                    "Contacto\n" +
                    "Si tienes alguna pregunta o inquietud sobre estos términos y condiciones, por favor contacta con nosotros a través de [correo electrónico o número de contacto]."
        )

        builder.setPositiveButton("Aceptar") { dialog, _ ->
            btnProceed.isEnabled = true
            Toast.makeText(
                context,
                "Has aceptado el Acuerdo de Confidencialidad.",
                Toast.LENGTH_SHORT
            ).show()
            dialog.dismiss()
        }

        builder.setNegativeButton("Rechazar") { dialog, _ ->
            btnProceed.isEnabled = false
            checkbox.isChecked = false
            Toast.makeText(context, "Debes aceptar el Acuerdo para continuar.", Toast.LENGTH_SHORT)
                .show()
            dialog.dismiss()
        }

        builder.create().show()
    }

    private fun showTermsDialog2(btnProceed: Button) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Términos y Condiciones")
        builder.setMessage(
            "Por favor, lee detenidamente estos términos y condiciones antes de utilizar nuestra aplicación. Al acceder o usar la aplicación, aceptas estar sujeto a estos términos y condiciones. Si no estás de acuerdo con cualquiera de los términos, no deberías utilizar nuestra aplicación.\n" +
                    "\n" +
                    "Aceptación de los Términos\n" +
                    "Al utilizar esta aplicación, confirmas que has leído y aceptas cumplir con todos los términos y condiciones aquí descritos. Nos reservamos el derecho de modificar estos términos en cualquier momento sin previo aviso, por lo que se recomienda revisarlos periódicamente.\n" +
                    "\n" +
                    "Licencia de Uso\n" +
                    "Se otorga una licencia limitada, no exclusiva, intransferible y revocable para acceder y utilizar la aplicación conforme a los términos descritos. La licencia se otorga para uso personal y no comercial. No se permite la reventa, redistribución, o cualquier uso comercial no autorizado de la aplicación.\n" +
                    "\n" +
                    "Propiedad Intelectual\n" +
                    "Todo el contenido, diseño gráfico, código, imágenes, texto, logotipos y otros elementos disponibles en la aplicación son propiedad de [Nombre de la Empresa] o de sus licenciantes. No se permite la copia, modificación, distribución o uso del contenido sin autorización expresa por escrito de los propietarios.\n" +
                    "\n" +
                    "Uso Permitido y Restricciones\n" +
                    "Está prohibido:\n" +
                    "\n" +
                    "Utilizar la aplicación para cualquier propósito ilegal.\n" +
                    "Intentar acceder sin autorización a nuestros sistemas o interferir con el funcionamiento de la aplicación.\n" +
                    "Recolectar información personal de otros usuarios sin su consentimiento.\n" +
                    "Privacidad y Protección de Datos\n" +
                    "Tomamos muy en serio la privacidad de nuestros usuarios. Los datos recopilados se utilizan de acuerdo con nuestra Política de Privacidad, la cual puedes consultar en la sección correspondiente de la aplicación. Al utilizar la aplicación, aceptas nuestra recopilación y uso de datos personales según lo descrito en dicha política.\n" +
                    "\n" +
                    "Limitación de Responsabilidad\n" +
                    "[Nombre de la Empresa] no será responsable de ningún daño indirecto, incidental, especial o consecuente que surja del uso o la incapacidad de utilizar la aplicación, incluso si hemos sido advertidos de la posibilidad de tales daños. El uso de la aplicación es bajo tu propio riesgo.\n" +
                    "\n" +
                    "Modificaciones y Actualizaciones\n" +
                    "Nos reservamos el derecho de modificar o discontinuar, temporal o permanentemente, cualquier función o característica de la aplicación en cualquier momento sin previo aviso. No seremos responsables ante ti o terceros por cualquier modificación, suspensión o interrupción del servicio.\n" +
                    "\n" +
                    "Cancelación de la Cuenta\n" +
                    "Nos reservamos el derecho de cancelar o suspender tu cuenta en cualquier momento si consideramos que has violado estos términos o has realizado una actividad ilegal dentro de la aplicación. No tendrás derecho a ningún tipo de compensación por la cancelación de tu cuenta.\n" +
                    "\n" +
                    "Enlaces a Terceros\n" +
                    "La aplicación puede contener enlaces a sitios web de terceros. Estos sitios no están bajo nuestro control, y no somos responsables del contenido ni de la política de privacidad de estos sitios. Acceder a sitios de terceros es bajo tu propio riesgo.\n" +
                    "\n" +
                    "Indemnización\n" +
                    "Aceptas indemnizar y mantener indemne a [Nombre de la Empresa] frente a cualquier reclamo, daño, pérdida o gasto derivado de tu uso de la aplicación o de la violación de estos términos y condiciones.\n" +
                    "\n" +
                    "Jurisdicción y Ley Aplicable\n" +
                    "Estos términos y condiciones se regirán por las leyes de [País o Estado]. Cualquier disputa que surja en relación con estos términos deberá resolverse en los tribunales de [Lugar].\n" +
                    "\n" +
                    "Contacto\n" +
                    "Si tienes alguna pregunta o inquietud sobre estos términos y condiciones, por favor contacta con nosotros a través de [correo electrónico o número de contacto]."
        )

        builder.setPositiveButton("Aceptar") { dialog, _ ->
            btnProceed.isEnabled = true
            Toast.makeText(
                context,
                "Has aceptado el Acuerdo de Tratamiento de Datos Personales.",
                Toast.LENGTH_SHORT
            ).show()
            dialog.dismiss()
        }

        builder.setNegativeButton("Rechazar") { dialog, _ ->
            btnProceed.isEnabled = false
            checkbox2.isChecked = false
            Toast.makeText(context, "Debes aceptar el Acuerdo para continuar.", Toast.LENGTH_SHORT)
                .show()
            dialog.dismiss()
        }

        builder.create().show()
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(parentFragmentManager, "datePicker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        view?.findViewById<EditText>(R.id.etDateContrato)?.setText("$day / $month / $year")
    }
    private fun submitClick(view: View) {
        val fieldsToValidate = listOf(
            etFirstName to "El nombre no puede estar vacío",
            etFirstSurname to "El primer apellido no puede estar vacío",
            spTypeDoc to "Debe seleccionar un tipo de documento",
            etNumDoc to "El número de documento no puede estar vacío",
            etLugarExpedicion to "Debe proporcionar el lugar de expedición",
            etCargo to "El cargo no puede estar vacío",
            etEmail to "El correo electrónico no puede estar vacío",
            etDateContrato to "Debe especificar la fecha del contrato",
            etSolType to "Debe especificar el tipo de solicitud",
            etObsrvUser to "Debe proporcionar observaciones del usuario"
        )

        if (validateRegistrationFields(fieldsToValidate)) {
            // Si pasa la validación, continúa con la lógica para enviar la solicitud
            sendRequest()
            resetCheckboxes()
            clearInputs(view)
        }
    }
    private fun clearInputs(view: View) {
        val fields = listOf(
            view.findViewById<EditText>(R.id.etFirstName),
            view.findViewById<EditText>(R.id.etSecondName),
            view.findViewById<EditText>(R.id.etFirstSurname),
            view.findViewById<EditText>(R.id.etSecondSurname),
            view.findViewById<EditText>(R.id.etNumDoc),
            view.findViewById<EditText>(R.id.etLugardeExpedicion),
            view.findViewById<EditText>(R.id.etTelFijo),
            view.findViewById<EditText>(R.id.etCelular),
            view.findViewById<EditText>(R.id.etAddress),
            view.findViewById<EditText>(R.id.etEmail),
            view.findViewById<EditText>(R.id.etCargo),
            view.findViewById<EditText>(R.id.etSupervisorId),
            view.findViewById<EditText>(R.id.etDependencia),
            view.findViewById<EditText>(R.id.etUbiLaboral),
            view.findViewById<EditText>(R.id.etProCard),
            view.findViewById<EditText>(R.id.etDateContrato),
            view.findViewById<EditText>(R.id.etSolType),
            view.findViewById<EditText>(R.id.etObsrvUser),
            view.findViewById<Spinner>(R.id.spSexo),
            view.findViewById<Spinner>(R.id.spTypeDoc),
            view.findViewById<Spinner>(R.id.spSede),
            view.findViewById<Spinner>(R.id.spAplicativo),
            view.findViewById<Spinner>(R.id.spModulo)
        )

        fields.forEach { field ->
            when (field) {
                is EditText -> field.text.clear()
                is Spinner -> field.setSelection(0) // Restablece el Spinner a su primer elemento
            }
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
    //    private fun handleValidSubmission(view: View) {
//        val solicitud = createSolicitudEntity(view)
//
//        // Insertar los datos usando el ViewModel (descomentar cuando lo uses)
//        // viewModel.insertSolicitud(solicitud)
//
//        context?.let { context ->
//            resetCheckboxes()
//            clearInputs(view)
//        }
//    }
    private fun handleInvalidSubmission() {
        val message = when {
            checkbox.isChecked && !checkbox2.isChecked ->
                "Solicitud Rechazada, Acepte Tratamiento de Datos Personales"
            !checkbox.isChecked && checkbox2.isChecked ->
                "Solicitud Rechazada, Acepte Acuerdo de Confidencialidad"
            else ->
                "Solicitud Rechazada, Acepte Acuerdos"
        }

        context?.let { context ->
            showToast(context, message)
        }
    }
    private fun resetCheckboxes() {
        checkbox.isChecked = false
        checkbox2.isChecked = false
    }
    private fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
    private fun createSolicitudEntity(view: View): SolicitudEntity {
        return SolicitudEntity(
            firstName = etFirstName.text.toString(),
            secondName = etSecondName.text.toString(),
            firstSurname = etFirstSurname.text.toString(),
            secondSurname = etSecondSurname.text.toString(),
            typeDoc = spTypeDoc.selectedItem.toString(),
            numDoc = etNumDoc.text.toString(),
            lugarExpedicion = etLugarExpedicion.text.toString(),
            sexo = spSexo.selectedItem.toString(),
            telFijo = etTelFijo.text.toString(),
            celular = view.findViewById<EditText>(R.id.etCelular).text.toString(),
            address = view.findViewById<EditText>(R.id.etAddress).text.toString(),
            email = view.findViewById<EditText>(R.id.etEmail).text.toString(),
            cargo = view.findViewById<EditText>(R.id.etCargo).text.toString(),
            supervisorId = view.findViewById<EditText>(R.id.etSupervisorId).text.toString(),
            dependencia = view.findViewById<EditText>(R.id.etDependencia).text.toString(),
            sede = spSede.selectedItem.toString(),
            ubiLaboral = view.findViewById<EditText>(R.id.etUbiLaboral).text.toString(),
            proCard = view.findViewById<EditText>(R.id.etProCard).text.toString(),
            dateContrato = etDateContrato.text.toString(),
            solType = view.findViewById<EditText>(R.id.etSolType).text.toString(),
            aplicativo = spAplicativo.selectedItem.toString(),
            modulo = spModulo.selectedItem.toString(),
            obsrvUser = view.findViewById<EditText>(R.id.etObsrvUser).text.toString(),
            assignUser = view.findViewById<EditText>(R.id.etAssignUser).text.toString(),
            assignPassword = view.findViewById<EditText>(R.id.etAssignPassword).text.toString(),
            obsrvAdmin = view.findViewById<EditText>(R.id.etObsrvAdmin).text.toString(),
            statusFragmentA = Status.PENDIENTE.toString(),
            statusFragmentB = Status.PENDIENTE.toString()
        )
    }
    private fun sendRequest() {
        // Aquí se implementa la lógica para enviar la solicitud a la base de datos o API
        Toast.makeText(requireContext(), "Solicitud enviada con éxito", Toast.LENGTH_SHORT).show()
    }

}
//val layout1 = view.findViewById<ConstraintLayout>(R.id.lytSystemRequired)
//etDate.setOnClickListener { showDatePickerDialog() }
//
//val btnRemoveLyt = view.findViewById<AppCompatButton>(R.id.btnDeny)
//btnRemoveLyt.setOnClickListener {
//    layout1.visibility = View.GONE
//}
//
//val btnShowLyt = view.findViewById<AppCompatButton>(R.id.btnApprove)
//btnShowLyt.setOnClickListener {
//    layout1.visibility = View.VISIBLE
//}
//
//val btnClose = view.findViewById<AppCompatButton>(R.id.btnClose)
//btnClose.setOnClickListener {
//    activity?.onBackPressed()
//}

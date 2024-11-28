package com.diegorios.vistatest.ui.login.register

data class RegistrationRequest(
    val firstName: String,
    val firstSurname: String,
    val secondName: String?, // Opcional
    val secondSurname: String?, // Opcional
    val documentType: String,
    val documentNumber: String,
    val email: String,
    val position: String,
    val supervisor: String
)


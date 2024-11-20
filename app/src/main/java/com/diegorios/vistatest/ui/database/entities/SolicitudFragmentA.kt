package com.diegorios.vistatest.ui.database.entities

data class SolicitudFragmentA(
    val firstName: String,
    val secondName: String?,
    val firstSurname: String,
    val secondSurname: String?,
    val aplicativo: String,
    val modulo: String,
    val solType: String,
    val obsrvUser: String?,
    val statusFragmentA: String
)

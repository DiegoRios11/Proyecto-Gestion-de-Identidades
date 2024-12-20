package com.diegorios.vistatest.ui.Solicitudes

data class Solicitud(
    val id: Int,
    val fullName: String,
    val aplicativo: String,
    val modulo: String,
    val tipoDeSolicitud: String,
    val observacion: String,
    var status: Status = Status.PENDIENTE
)

enum class Status {
    PENDIENTE,
    ACEPTADO,
    RECHAZADO
}


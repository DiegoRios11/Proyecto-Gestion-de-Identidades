package com.diegorios.vistatest.ui.PazySalvo

data class PazySalvo(
    val id: Int,
    val fullName: String,
    val cedula: String,
    val cargoContrato: String,
    val fechaSolicitud: String,
    var status: Status = Status.PENDIENTE
)

enum class Status {
    PENDIENTE,
    ACEPTADO,
    RECHAZADO
}

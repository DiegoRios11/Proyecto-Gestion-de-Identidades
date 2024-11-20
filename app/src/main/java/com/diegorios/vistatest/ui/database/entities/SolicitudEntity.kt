package com.diegorios.vistatest.ui.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "solicitudes")
data class SolicitudEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val firstName: String,
    val secondName: String?,
    val firstSurname: String,
    val secondSurname: String?,
    val typeDoc: String,
    val numDoc: String,
    val lugarExpedicion: String,
    val sexo: String,
    val telFijo: String?,
    val celular: String,
    val address: String,
    val email: String,
    val cargo: String,
    val supervisorId: String,
    val dependencia: String,
    val sede: String,
    val ubiLaboral: String,
    val proCard: String?,
    val dateContrato: String,
    val solType: String,
    val aplicativo: String,
    val modulo: String,
    val obsrvUser: String?,
    val assignUser: String?,
    val assignPassword: String?,
    val obsrvAdmin: String?,
    val statusFragmentA: String = Status.PENDIENTE.name, // Status para el Fragment Solicitud
    val statusFragmentB: String = Status.PENDIENTE.name  // Status para el Fragment PazySalvo
)

enum class Status {
    PENDIENTE,
    ACEPTADO,
    RECHAZADO
}


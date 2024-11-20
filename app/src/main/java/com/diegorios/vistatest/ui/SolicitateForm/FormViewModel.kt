package com.diegorios.vistatest.ui.SolicitateForm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.diegorios.vistatest.ui.database.bd.SolicitudDatabaseInstance
import com.diegorios.vistatest.ui.database.entities.SolicitudEntity

import kotlinx.coroutines.launch

class SolicitudFragmentViewModel(application: Application) : AndroidViewModel(application) {

    // Inicializamos el DAO de la base de datos
    private val solicitudDao = SolicitudDatabaseInstance.getDatabase(application).solicitudDao()

    // Función para insertar una nueva solicitud
    fun insertSolicitud(solicitud: SolicitudEntity) {
        viewModelScope.launch {
            // Llamamos al DAO para insertar la solicitud en la base de datos
            solicitudDao.insertSolicitud(solicitud)
        }
    }
}
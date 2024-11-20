package com.diegorios.vistatest.ui.database.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.diegorios.vistatest.ui.database.entities.SolicitudEntity
import com.diegorios.vistatest.ui.database.entities.SolicitudFragmentA

@Dao
interface SolicitudDao {
    // Insertar una nueva solicitud
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSolicitud(solicitud: SolicitudEntity)

    // Actualizar una solicitud existente
    @Update
    suspend fun updateSolicitud(solicitud: SolicitudEntity)

    // Eliminar una solicitud
    @Delete
    suspend fun deleteSolicitud(solicitud: SolicitudEntity)

    // Obtener todas las solicitudes
    @Query("SELECT * FROM solicitudes")
    suspend fun getAllSolicitudes(): List<SolicitudEntity>

    // Obtener solicitudes con un status específico para Fragment A
    @Query("SELECT * FROM solicitudes WHERE statusFragmentA = :status")
    suspend fun getSolicitudesByStatusFragmentA(status: String): List<SolicitudEntity>

    // Obtener solicitudes con un status específico para Fragment B
    @Query("SELECT * FROM solicitudes WHERE statusFragmentB = :status")
    suspend fun getSolicitudesByStatusFragmentB(status: String): List<SolicitudEntity>

    // Obtener una solicitud específica por ID
    @Query("SELECT * FROM solicitudes WHERE id = :id")
    suspend fun getSolicitudById(id: Int): LiveData<SolicitudEntity>

    // Consulta específica para el Fragment A
    @Query(
        """
        SELECT firstName, secondName, firstSurname, secondSurname, 
               aplicativo, modulo, solType, obsrvUser, statusFragmentA 
        FROM solicitudes
        WHERE statusFragmentA = :status
    """
    )
    suspend fun getSolicitudesForFragmentA(status: String): List<SolicitudFragmentA>
}
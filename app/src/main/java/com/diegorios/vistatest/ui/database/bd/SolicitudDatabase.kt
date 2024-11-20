package com.diegorios.vistatest.ui.database.bd

import androidx.room.Database
import androidx.room.RoomDatabase
import com.diegorios.vistatest.ui.database.entities.SolicitudEntity
import com.diegorios.vistatest.ui.database.DAO.SolicitudDao

@Database(entities = [SolicitudEntity::class], version = 1, exportSchema = false)
abstract class SolicitudDatabase : RoomDatabase() {

    // Declaración del DAO
    abstract fun solicitudDao(): SolicitudDao

    companion object {
        const val DATABASE_NAME = "solicitudes_db"
    }
}

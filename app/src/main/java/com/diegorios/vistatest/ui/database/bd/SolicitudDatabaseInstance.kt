package com.diegorios.vistatest.ui.database.bd

import android.content.Context
import androidx.room.Room

object SolicitudDatabaseInstance {
    @Volatile
    private var INSTANCE: SolicitudDatabase? = null

    fun getDatabase(context: Context): SolicitudDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                SolicitudDatabase::class.java,
                SolicitudDatabase.DATABASE_NAME
            ).build()
            INSTANCE = instance
            instance
        }
    }
}
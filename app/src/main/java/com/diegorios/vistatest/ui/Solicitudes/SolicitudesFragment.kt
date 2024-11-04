package com.diegorios.vistatest.ui.Solicitudes

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diegorios.vistatest.R

class SolicitudesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_view_solicitudes, container, false)

        // Datos de ejemplo
        val solicitudesList = listOf(
            Solicitud(1, "John Doe", "App1", "ModuleA", "Type1", "Observación 1"),
            Solicitud(2, "Jane Smith", "App2", "ModuleB", "Type2", "Observación 2"),
            Solicitud(3, "Carlos Ruiz", "App3", "ModuleC", "Type3", "Observación 3")
            // Agrega más solicitudes si es necesario
        )

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvSolicitud)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = SolicitudAdapter(solicitudesList)

        return view
    }
}

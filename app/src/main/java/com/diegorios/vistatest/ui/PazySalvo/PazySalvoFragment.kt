package com.diegorios.vistatest.ui.PazySalvo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diegorios.vistatest.R

class PazySalvoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_paz_y_salvo, container, false)

        // Datos de ejemplo
        val pazysalvoList = listOf(
            PazySalvo(1, "John Doe", "12345678", "Analista", "2023-01-01"),
            PazySalvo(2, "Jane Smith", "87654321", "Desarrollador", "2023-02-15"),
            PazySalvo(3, "Carlos Ruiz", "11223344", "Consultor", "2023-03-05")
            // Agrega más elementos según sea necesario
        )

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvPazySalvo)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = PazySalvoAdapter(pazysalvoList)

        return view
    }
}

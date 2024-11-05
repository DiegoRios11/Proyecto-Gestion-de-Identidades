package com.diegorios.vistatest.ui.Solicitudes

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SolicitudAdapter(private val solicitudesList: List<Solicitud>) :
    RecyclerView.Adapter<SolicitudViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SolicitudViewHolder {
        return SolicitudViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: SolicitudViewHolder, position: Int) {
        holder.bind(solicitudesList[position])
    }

    override fun getItemCount(): Int = solicitudesList.size
}
package com.diegorios.vistatest.ui.Solicitudes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.diegorios.vistatest.R

class SolicitudViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvId: TextView = view.findViewById(R.id.tvId)
    val tvFullName: TextView = view.findViewById(R.id.tvFullName)
    val tvAplicativo: TextView = view.findViewById(R.id.tvAplicativo)
    val tvModulo: TextView = view.findViewById(R.id.tvModulo)
    val tvTipoDeSolicitud: TextView = view.findViewById(R.id.tvTipoDeSolicitud)
    val tvObservacion: TextView = view.findViewById(R.id.tvObservacion)

    fun bind(solicitud: Solicitud) {
        tvId.text = solicitud.id.toString()
        tvFullName.text = solicitud.fullName
        tvAplicativo.text = solicitud.aplicativo
        tvModulo.text = solicitud.modulo
        tvTipoDeSolicitud.text = solicitud.tipoDeSolicitud
        tvObservacion.text = solicitud.observacion
    }

    companion object {
        fun create(parent: ViewGroup): SolicitudViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_solicitud, parent, false)
            return SolicitudViewHolder(view)
        }
    }
}
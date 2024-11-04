package com.diegorios.vistatest.ui.Solicitudes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.diegorios.vistatest.R
import org.w3c.dom.Text

class SolicitudAdapter(private val solicitudesList: List<Solicitud>) :
    RecyclerView.Adapter<SolicitudAdapter.SolicitudViewHolder>() {

    inner class SolicitudViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvId: TextView = view.findViewById(R.id.tvId)
        val tvFullName: TextView = view.findViewById(R.id.tvFullName)
        val tvAplicativo: TextView = view.findViewById(R.id.tvAplicativo)
        val tvModulo: TextView = view.findViewById(R.id.tvModulo)
        val tvTipoDeSolicitud: TextView = view.findViewById(R.id.tvTipoDeSolicitud)
        val tvObservacion: TextView = view.findViewById(R.id.tvObservacion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SolicitudViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_solicitud, parent, false)
        return SolicitudViewHolder(view)
    }

    override fun onBindViewHolder(holder: SolicitudViewHolder, position: Int) {
        val solicitud = solicitudesList[position]
        holder.tvId.text = solicitud.id.toString()
        holder.tvFullName.text = solicitud.fullName
        holder.tvAplicativo.text = solicitud.aplicativo
        holder.tvModulo.text = solicitud.modulo
        holder.tvTipoDeSolicitud.text = solicitud.tipoDeSolicitud
        holder.tvObservacion.text = solicitud.observacion
    }

    override fun getItemCount(): Int = solicitudesList.size
}

package com.diegorios.vistatest.ui.PazySalvo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.diegorios.vistatest.R

class PazySalvoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvId: TextView = view.findViewById(R.id.tvId)
    val tvFullName: TextView = view.findViewById(R.id.tvFullName)
    val tvCedula: TextView = view.findViewById(R.id.tvCedula)
    val tvCargoContrato: TextView = view.findViewById(R.id.tvCargoContrato)
    val tvFechaSolicitud: TextView = view.findViewById(R.id.tvFechaSolicitud)

    fun bind(pazysalvo: PazySalvo) {
        tvId.text = pazysalvo.id.toString()
        tvFullName.text = pazysalvo.fullName
        tvCedula.text = pazysalvo.cedula
        tvCargoContrato.text = pazysalvo.cargoContrato
        tvFechaSolicitud.text = pazysalvo.fechaSolicitud
    }

    companion object {
        fun create(parent: ViewGroup): PazySalvoViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_pazysalvo, parent, false)
            return PazySalvoViewHolder(view)
        }
    }
}
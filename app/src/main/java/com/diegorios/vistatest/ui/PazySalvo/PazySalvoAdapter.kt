package com.diegorios.vistatest.ui.PazySalvo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.diegorios.vistatest.R

class PazySalvoAdapter(private val pazysalvoList: List<PazySalvo>) :
    RecyclerView.Adapter<PazySalvoAdapter.PazysalvoViewHolder>() {

    inner class PazysalvoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvId: TextView = view.findViewById(R.id.tvId)
        val tvFullName: TextView = view.findViewById(R.id.tvFullName)
        val tvCedula: TextView = view.findViewById(R.id.tvCedula)
        val tvCargoContrato: TextView = view.findViewById(R.id.tvCargoContrato)
        val tvFechaSolicitud: TextView = view.findViewById(R.id.tvFechaSolicitud)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PazysalvoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pazysalvo, parent, false)
        return PazysalvoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PazysalvoViewHolder, position: Int) {
        val pazysalvo = pazysalvoList[position]
        holder.tvId.text = pazysalvo.id.toString()
        holder.tvFullName.text = pazysalvo.fullName
        holder.tvCedula.text = pazysalvo.cedula
        holder.tvCargoContrato.text = pazysalvo.cargoContrato
        holder.tvFechaSolicitud.text = pazysalvo.fechaSolicitud
    }

    override fun getItemCount(): Int = pazysalvoList.size
}

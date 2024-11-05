package com.diegorios.vistatest.ui.PazySalvo

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PazySalvoAdapter(private val pazysalvoList: List<PazySalvo>) :
    RecyclerView.Adapter<PazySalvoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PazySalvoViewHolder {
        return PazySalvoViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PazySalvoViewHolder, position: Int) {
        holder.bind(pazysalvoList[position])
    }

    override fun getItemCount(): Int = pazysalvoList.size
}
package com.eamcompany.lembretes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eamcompany.lembretes.R
import com.eamcompany.lembretes.adapter.TarefaAdapter.MyViewHolder
import com.eamcompany.lembretes.model.Tarefa

class TarefaAdapter(private val listaTarefas: List<Tarefa>) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemLista = LayoutInflater.from(parent.context)
                .inflate(R.layout.lista_tarefa_adapter, parent, false)
        return MyViewHolder(itemLista)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val tarefa = listaTarefas[position]
        holder.tarefa.text = tarefa.nomeTarefa
    }

    override fun getItemCount(): Int {
        return listaTarefas.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tarefa: TextView

        init {
            tarefa = itemView.findViewById(R.id.textTarefa)
        }
    }
}
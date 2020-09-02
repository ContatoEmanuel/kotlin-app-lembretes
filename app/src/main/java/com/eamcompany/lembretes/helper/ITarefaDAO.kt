package com.eamcompany.lembretes.helper

import com.eamcompany.lembretes.model.Tarefa

interface ITarefaDAO {
    fun salvar(tarefa: Tarefa?): Boolean
    fun atualizar(tarefa: Tarefa?): Boolean
    fun deletar(tarefa: Tarefa?): Boolean
    fun listar(): List<Tarefa?>?
}
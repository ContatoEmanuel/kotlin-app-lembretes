package com.eamcompany.lembretes.helper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.eamcompany.lembretes.model.Tarefa
import java.util.*

class TarefaDAO(context: Context?) : ITarefaDAO {
    private val escreve: SQLiteDatabase
    private val le: SQLiteDatabase
    override fun salvar(tarefa: Tarefa?): Boolean {
        val cv = ContentValues()
        cv.put("nome", tarefa!!.nomeTarefa)
        try {
            escreve.insert(DbHelper.TABELA_TAREFAS, null, cv)
        } catch (e: Exception) {
            Log.e("INFO", "Erro ao salvar tarefa" + e.message)
            return false
        }
        return true
    }

    override fun atualizar(tarefa: Tarefa?): Boolean {
        val cv = ContentValues()
        cv.put("nome", tarefa!!.nomeTarefa)
        try {
            val args = arrayOf(
                    tarefa.id.toString()
            )
            escreve.update(DbHelper.TABELA_TAREFAS, cv, "id=?", args)
        } catch (e: Exception) {
            Log.e("INFO", "Erro ao atualizar tarefa" + e.message)
            return false
        }
        return true
    }

    override fun deletar(tarefa: Tarefa?): Boolean {
        try {
            val args = arrayOf(
                    tarefa!!.id.toString()
            )
            escreve.delete(DbHelper.TABELA_TAREFAS, "id=?", args)
        } catch (e: Exception) {
            Log.e("INFO", "Erro ao excluir tarefa" + e.message)
            return false
        }
        return true
    }

    override fun listar(): List<Tarefa?>? {
        val tarefas: MutableList<Tarefa?> = ArrayList()
        val sql = "SELECT * FROM " + DbHelper.TABELA_TAREFAS + ";"
        val c = le.rawQuery(sql, null)
        while (c.moveToNext()) {
            val tarefa = Tarefa()
            val id = c.getLong(c.getColumnIndex("id"))
            val nomeTarefa = c.getString(c.getColumnIndex("nome"))
            tarefa.id = id
            tarefa.nomeTarefa = nomeTarefa
            tarefas.add(tarefa)
        }
        return tarefas
    }

    init {
        val db = DbHelper(context)
        escreve = db.writableDatabase
        le = db.readableDatabase
    }
}
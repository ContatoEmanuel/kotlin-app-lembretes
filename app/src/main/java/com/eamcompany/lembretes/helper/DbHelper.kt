package com.eamcompany.lembretes.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DbHelper(context: Context?) : SQLiteOpenHelper(context, NOME_DB, null, VERSION) {
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        val sql = "CREATE TABLE IF NOT EXISTS " + TABELA_TAREFAS +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL);"
        try {
            sqLiteDatabase.execSQL(sql)
        } catch (e: Exception) {
            Log.e("INFO DB", "Erro ao criar a tabela" + e.message)
        }
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {}

    companion object {
        var VERSION = 1
        var NOME_DB = "DB_TAREFAS"
        @JvmField
        var TABELA_TAREFAS = "tarefas"
    }
}
package com.eamcompany.lembretes.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.eamcompany.lembretes.R
import com.eamcompany.lembretes.helper.TarefaDAO
import com.eamcompany.lembretes.model.Tarefa
import com.google.android.material.textfield.TextInputEditText

class AdicionarTarefaActivity : AppCompatActivity() {
    private var editTarefa: TextInputEditText? = null
    private var tarefaAtual: Tarefa? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_tarefa)
        editTarefa = findViewById(R.id.textTarefa)
        tarefaAtual = intent.getSerializableExtra("tarefaSelecionada") as Tarefa?
        if (tarefaAtual != null) {
            editTarefa?.run { setText(tarefaAtual!!.nomeTarefa) }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_adicionar_tarefa, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemSalvar -> {
                val tarefaDAO = TarefaDAO(applicationContext)
                val nomeTarefa = editTarefa!!.text.toString()
                if (tarefaAtual != null) {
                    if (!nomeTarefa.isEmpty()) {
                        val tarefa = Tarefa()
                        tarefa.nomeTarefa = nomeTarefa
                        tarefa.id = tarefaAtual!!.id
                        if (tarefaDAO.atualizar(tarefa)) {
                            finish()
                            Toast.makeText(applicationContext,
                                    "Sucesso ao Atualizar Tarefa!",
                                    Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(applicationContext,
                                    "Erro ao Atualizar Tarefa!",
                                    Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    if (!nomeTarefa.isEmpty()) {
                        val tarefa = Tarefa()
                        tarefa.nomeTarefa = nomeTarefa
                        if (tarefaDAO.salvar(tarefa)) {
                            finish()
                            Toast.makeText(applicationContext,
                                    "Sucesso ao Salvar Tarefa!",
                                    Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(applicationContext,
                                    "Erro ao Salvar Tarefa!",
                                    Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
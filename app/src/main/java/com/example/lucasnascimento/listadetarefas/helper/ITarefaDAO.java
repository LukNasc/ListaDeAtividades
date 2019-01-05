package com.example.lucasnascimento.listadetarefas.helper;

import com.example.lucasnascimento.listadetarefas.Model.TarefasModel;

import java.util.List;

public interface ITarefaDAO {
    public boolean salvar(TarefasModel tarefa);
    public boolean atualizar(TarefasModel tarefa);
    public boolean deletar(TarefasModel tarefa);
    public List<TarefasModel> listar();
}

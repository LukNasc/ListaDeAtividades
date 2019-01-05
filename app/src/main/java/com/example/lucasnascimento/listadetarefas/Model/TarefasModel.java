package com.example.lucasnascimento.listadetarefas.Model;

import java.io.Serializable;

public class TarefasModel implements Serializable {
    private Long id;
    private String NomeTarefa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeTarefa() {
        return NomeTarefa;
    }

    public void setNomeTarefa(String nomeTarefa) {
        NomeTarefa = nomeTarefa;
    }
}

package com.example.lucasnascimento.listadetarefas.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lucasnascimento.listadetarefas.Model.TarefasModel;
import com.example.lucasnascimento.listadetarefas.R;

import java.util.List;

public class AdapterListaTarefas extends RecyclerView.Adapter<AdapterListaTarefas.MyViewHolder> {
    private List<TarefasModel> listaTarefas;


    public AdapterListaTarefas(List<TarefasModel> lista) {
            this.listaTarefas = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemLista = LayoutInflater.from(viewGroup.getContext())
                                        .inflate(R.layout.layout_recycler, viewGroup, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        TarefasModel tarefa = listaTarefas.get(i);
        myViewHolder.tarefa.setText(tarefa.getNomeTarefa());

    }

    @Override
    public int getItemCount() {
        return this.listaTarefas.size();
    }


    //Inner Class
    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tarefa;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tarefa = itemView.findViewById(R.id.textTarefa);
        }
    }
}

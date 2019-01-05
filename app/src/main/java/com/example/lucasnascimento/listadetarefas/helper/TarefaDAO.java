package com.example.lucasnascimento.listadetarefas.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.lucasnascimento.listadetarefas.Model.TarefasModel;

import java.util.ArrayList;
import java.util.List;

public class TarefaDAO implements ITarefaDAO{
    private SQLiteDatabase escreve;
    private SQLiteDatabase ler;

    public TarefaDAO(Context context) {
        BDHelper bd = new BDHelper(context);
        escreve = bd.getWritableDatabase();
        ler = bd.getReadableDatabase();
    }

    @Override
    public boolean salvar(TarefasModel tarefa) {
        //Fazendo os valores para guardar no banco de dados
        ContentValues cv =  new ContentValues();
        cv.put("nome", tarefa.getNomeTarefa());
        try{
            //INSERT()
            //1->nome da tabela
            //2->caso o usuário não dogote nada
            //3->valor
            escreve.insert(BDHelper.TABELA_TAREFAS, null, cv);

        }catch(Exception e){
            Log.e("INFO ","ERRO AO SALVAR TAREFA "+e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean atualizar(TarefasModel tarefa) {
        ContentValues cv = new ContentValues();
        cv.put("nome", tarefa.getNomeTarefa());
        try{
            //1->nome tabela
            //2->valor
            //3->clausula where
            //4-:parametros
            String[] args = {tarefa.getId().toString()};
            escreve.update(BDHelper.TABELA_TAREFAS,cv,"id=?", args);
        }catch(Exception e){
            Log.e("INFO ","ERRO AO ATUALIZAR TAREFA "+e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean deletar(TarefasModel tarefa) {
        try{
          String[] args = {tarefa.getId().toString()};
          escreve.delete(BDHelper.TABELA_TAREFAS,"id=?",args);
        }catch(Exception e){
            Log.e("INFO ","ERRO AO REMOVER TAREFA "+e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<TarefasModel> listar() {
        List<TarefasModel> listaTarefas = new ArrayList<>();
        String sql = "SELECT * FROM "+BDHelper.TABELA_TAREFAS+";";
        Cursor c = ler.rawQuery(sql, null);

        while (c.moveToNext()){

            TarefasModel tarefa = new TarefasModel();

            Long id = c.getLong(c.getColumnIndex("id"));
            String nomeTarefa = c.getString(c.getColumnIndex("nome"));

            tarefa.setId(id);
            tarefa.setNomeTarefa(nomeTarefa);

            listaTarefas.add(tarefa);

        }

        return listaTarefas;
    }
}

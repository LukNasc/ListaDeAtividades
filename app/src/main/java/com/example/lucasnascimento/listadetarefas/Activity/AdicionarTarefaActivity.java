package com.example.lucasnascimento.listadetarefas.Activity;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.lucasnascimento.listadetarefas.Model.TarefasModel;
import com.example.lucasnascimento.listadetarefas.R;
import com.example.lucasnascimento.listadetarefas.helper.TarefaDAO;

import java.io.Serializable;

public class AdicionarTarefaActivity extends AppCompatActivity {
    private TextInputEditText editTarefa;
    private TarefasModel tarefaAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);

        editTarefa = findViewById(R.id.tvTarefa);

        tarefaAtual = (TarefasModel)  getIntent().getSerializableExtra("tarefaSelecionada");
        if(tarefaAtual != null){
            editTarefa.setText(tarefaAtual.getNomeTarefa());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adicionar_tarefa, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        TarefaDAO tarefaDao = new TarefaDAO(getApplicationContext());
        switch (item.getItemId()){
            case R.id.salvarTarefas:
              if (tarefaAtual != null){
                  //Códigos para atualizar tarefa
                  if(!editTarefa.getText().toString().isEmpty()){
                      TarefasModel tarefa = new TarefasModel();
                      tarefa.setNomeTarefa(editTarefa.getText().toString());
                      tarefa.setId(tarefaAtual.getId());

                      if (tarefaDao.atualizar(tarefa)){
                          finish();
                          Toast.makeText(getApplicationContext(),"Sucesso ao atualizar tarefa",
                                  Toast.LENGTH_SHORT).show();
                      }else{
                          finish();
                          Toast.makeText(getApplicationContext(),"Erro ao atualizar tarefa",
                                  Toast.LENGTH_SHORT).show();
                      }
                  }
                  //tarefaDao.atualizar();
              }else{
                  //Execução do Código de salvar itens
                  if(!editTarefa.getText().toString().isEmpty()){
                      TarefasModel tarefa = new TarefasModel();
                      tarefa.setNomeTarefa(editTarefa.getText().toString());
                        if(tarefaDao.salvar(tarefa)){
                            finish();
                            Toast.makeText(getApplicationContext(),"Sucesso ao salvar tarefa",
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(),"Erro ao salvar tarefa",
                                    Toast.LENGTH_SHORT).show();
                      }

                  }
              }
             break;
        }

        return super.onOptionsItemSelected(item);
    }
}

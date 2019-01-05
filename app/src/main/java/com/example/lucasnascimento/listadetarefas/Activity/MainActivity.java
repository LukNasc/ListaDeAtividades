package com.example.lucasnascimento.listadetarefas.Activity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lucasnascimento.listadetarefas.Adapter.AdapterListaTarefas;
import com.example.lucasnascimento.listadetarefas.Model.TarefasModel;
import com.example.lucasnascimento.listadetarefas.R;
import com.example.lucasnascimento.listadetarefas.helper.BDHelper;
import com.example.lucasnascimento.listadetarefas.helper.RecyclerItemClickListener;
import com.example.lucasnascimento.listadetarefas.helper.TarefaDAO;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerListaTarefas;
    private AdapterListaTarefas adapterTarefas;
    private List<TarefasModel> listTarefas = new ArrayList<>();
    private TarefasModel tarefaSelecionada;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Configurações do ToolBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Configurações da RecyclerView
        recyclerListaTarefas = findViewById(R.id.recyclerListaDeTarefas);


        recyclerListaTarefas.addOnItemTouchListener(new RecyclerItemClickListener(
                getApplicationContext(),
                recyclerListaTarefas,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                       //Recuperando a tarefa para edição
                        TarefasModel tarefaSelecionada = listTarefas.get(position);

                        //Enviando dados
                        Intent intent = new Intent(MainActivity.this, AdicionarTarefaActivity.class);
                        intent.putExtra("tarefaSelecionada", tarefaSelecionada);

                        startActivity(intent);

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        tarefaSelecionada = listTarefas.get(position);
                        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                        dialog.setTitle("Confirmar Exclusão");
                        dialog.setMessage("Deseja realmente apagar a tarefa " +tarefaSelecionada.getNomeTarefa()+"?" );
                        dialog.setPositiveButton("sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                TarefaDAO tarefaDao = new TarefaDAO(getApplicationContext());
                                if(tarefaDao.deletar(tarefaSelecionada)){
                                    CarregarListaDeTarefas();
                                    Toast.makeText(getApplicationContext(),"Sucesso ao excluir tarefa",
                                            Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(getApplicationContext(),"Erro ao excluir tarefa",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        dialog.setNegativeButton("não", null);
                        dialog.create();
                        dialog.show();
                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }
        ));


        //Configurações do FAB
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(getApplicationContext(), AdicionarTarefaActivity.class);
               startActivity(intent);
            }
        });
    }
    public void CarregarListaDeTarefas(){
        //Listar Tarefas
           TarefaDAO tarefaDao = new TarefaDAO(getApplicationContext());
           listTarefas = tarefaDao.listar();

        //Exibir Lista


        //Configurar Adapter
        adapterTarefas = new AdapterListaTarefas(listTarefas);

        //Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerListaTarefas.setLayoutManager(layoutManager);
        recyclerListaTarefas.setHasFixedSize(true);
        recyclerListaTarefas.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerListaTarefas.setAdapter(adapterTarefas);


    }

    @Override
    protected void onStart() {
        this.CarregarListaDeTarefas();
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

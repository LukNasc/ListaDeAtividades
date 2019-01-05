package com.example.lucasnascimento.listadetarefas.helper;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BDHelper extends SQLiteOpenHelper {
    public static int VERSION = 1;
    public static String NOME_DB = "db_tarefas";
    public static String TABELA_TAREFAS = "tarefas";


    public BDHelper(Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS "+TABELA_TAREFAS+
                " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " nome TEXT NOT NULL" +
                  ");";
        try{

            db.execSQL(sql);
            Log.i("Informacao", "Sucesso ao criar tabela");

        }catch(Exception e){

            Log.e("INFO_DB", e.getMessage());

        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

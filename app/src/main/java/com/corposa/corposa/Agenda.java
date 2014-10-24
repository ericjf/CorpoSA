package com.corposa.corposa;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class Agenda extends ListActivity {

    static final ArrayList<PositionId> positionId = new ArrayList<PositionId>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(Agenda.this, Compromissos.class);
        startActivity(intent);
        finish();

    }



    public void startAgendaAdicionar(View view) {

        Intent intent = new Intent(this, AgendaAdicionar.class);
        startActivity(intent);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
       // super.onListItemClick(l,v,position, id);
        //SingleList singlelist = (SingleList) this.getListAdapter().getItem(position);

        // Intent intent = new Intent (Tratamentos.this, Tratamento.class);
        // intent.putExtra("posicao", position);
        //startActivity(intent);
    }
}

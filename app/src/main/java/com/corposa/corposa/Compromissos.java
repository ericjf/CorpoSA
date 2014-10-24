package com.corposa.corposa;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Compromissos extends ListActivity{

    static final ArrayList<PositionId> positionId = new ArrayList<PositionId>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<AgendaSingleList> list = new ArrayList<AgendaSingleList>();

        DBAgenda databaseHelper = new DBAgenda(this);


        List<Compromisso> agendaObjetos = databaseHelper.getAllContacts();

        int flag = agendaObjetos.size();
        int i = 0;

        while(i < flag){

            list.add(new AgendaSingleList(agendaObjetos.get(i).desc, agendaObjetos.get(i).getID(), agendaObjetos.get(i).getPhoneNumber()));
            i++;
        }

        //setContentView(R.layout.activity_agenda);
        setListAdapter(new AgendaSingleImageListAdapter(this, list));
        //Button button = new Button(this);
        //button.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        //LinearLayout linearLayout = (LinearLayout) findViewById(R.id.x1);
        //linearLayout.addView(button);
    }



    public void startAgendaAdicionar(View view) {

        Intent intent = new Intent(this, AgendaAdicionar.class);
        startActivity(intent);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.compromissos, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Intent intent = new Intent(this, AgendaAdicionar.class);
                startActivity(intent);
                return true;
            case R.id.voltar:
                Intent intent2 = new Intent(this, Home.class);
                startActivity(intent2);
                return true;
            default:
                return true;
        }
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
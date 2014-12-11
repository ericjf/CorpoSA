package com.corposa.corposa;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
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

    static final ArrayList<PositionIntId> positionIntIds = new ArrayList<PositionIntId>();
    DBAgenda databaseHelper = new DBAgenda(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ArrayList<AgendaSingleList> list = new ArrayList<AgendaSingleList>();

        getActionBar().setDisplayHomeAsUpEnabled(true);




        List<Compromisso> agendaObjetos = databaseHelper.getAllContacts();

        int flag = agendaObjetos.size();
        int i = 0;

        while(i < flag){

            list.add(new AgendaSingleList(agendaObjetos.get(i).desc, agendaObjetos.get(i).getID(), agendaObjetos.get(i).getPhoneNumber()));
            PositionIntId posicaoIntId = new PositionIntId();
            posicaoIntId.id = agendaObjetos.get(i).getID();
            posicaoIntId.position = i;
            positionIntIds.add(posicaoIntId);
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
                this.finish();
                return true;
            default:
                intent = new Intent(this, Home.class);
                startActivity(intent);
                this.finish();
                return true;
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, final int position, long id){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setTitle("Apagar")
                .setMessage("Você tem certeza que deseja Apagar esse Compromisso?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        databaseHelper.deleteContact(positionIntIds.get(position).id);

                        Intent thisIntent = getIntent();
                        startActivity(thisIntent);
                        finish();

                    }
                })
                .setNegativeButton("Não", null)						//Do nothing on no
                .show();




        // super.onListItemClick(l,v,position, id);
        //SingleList singlelist = (SingleList) this.getListAdapter().getItem(position);

        // Intent intent = new Intent (Tratamentos.this, Tratamento.class);
        // intent.putExtra("posicao", position);
        //startActivity(intent);
    }
}
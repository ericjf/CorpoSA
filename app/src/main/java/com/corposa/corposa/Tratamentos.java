package com.corposa.corposa;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ListView;

import java.util.ArrayList;

public class Tratamentos extends ListActivity {


    @Override
    public void onCreate(Bundle icicle){
        super.onCreate(icicle);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ArrayList<SingleList> list = new ArrayList<SingleList>();


        list.add(new SingleList("Medida Certa S/A", SingleList.MEDIDACERTA));
        list.add(new SingleList("Modelagem S/A", SingleList.MODELAGEM));
        list.add(new SingleList("Carbox S/A", SingleList.CARBOX));
        list.add(new SingleList("Pós Parto S/A", SingleList.POSPARTO));
        list.add(new SingleList("Noivas S/A", SingleList.NOIVAS));
        list.add(new SingleList("Facial S/A", SingleList.FACIAL5));
        list.add(new SingleList("Turbinada S/A", SingleList.TURBINADA));
        list.add(new SingleList("Mais S/A", SingleList.MAISSA));

        setListAdapter(new SingleImageListAdapter(this, list));

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){

        super.onListItemClick(l,v,position, id);

        SingleList singlelist = (SingleList) this.getListAdapter().getItem(position);

        //Intent intent = new Intent(this, Tratamento.class);
        // final Bundle params = new Bundle();
        // params.putInt("id_tratamento", position);
        //intent.putExtras(params);
        //startActivity(intent);
        String noiva = "NoivaSA";

        //Intent intent = new Intent(this, Tratamento.class);
        //intent.putExtra("posicao", noiva);
        //startActivity(intent);


        Intent intent = new Intent (Tratamentos.this, Tratamento.class);
        intent.putExtra("posicao", position);
        startActivity(intent);



    }

}

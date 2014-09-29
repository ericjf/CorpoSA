package com.corposa.corposa;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Noticias extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Noticia noticia = new Noticia();
        noticia.setDescription("namala");
        noticia.setTitle("NamaLa");
        noticia.setUrl("urldanoticia.com.br");

        DatabaseHelper databaseHelper = new DatabaseHelper(this, null, null, 1);
        databaseHelper.addProduct(noticia);

        Noticia noticiateste = databaseHelper.findNoticiaByTitle("NamaLa");




        String[] itens = new String[] {noticiateste.getTitle(), "Notícia 2", "Notícia 3"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itens);
        setListAdapter(arrayAdapter);



    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);
        Object o = this.getListAdapter().getItem(position);
        String item = o.toString();
        Toast.makeText(this, "Voce selecionou: " + item, Toast.LENGTH_SHORT).show();
    }


}

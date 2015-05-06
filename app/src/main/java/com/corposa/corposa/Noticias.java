package com.corposa.corposa;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ListView;

import java.util.ArrayList;

public class Noticias extends ListActivity {




    ArrayList<PositionId> positionIds = new ArrayList<PositionId>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ArrayList<NoticiaSingleList> list = new ArrayList<NoticiaSingleList>();



        DatabaseHelper databaseHelper = new DatabaseHelper(this, null, null, 1);

        ArrayList<Noticia> noticiateste = databaseHelper.selectNoticiasForDisplay();


        int flag = noticiateste.size();
        int i = 0;
        if (flag != 0) {
            while (i < flag) {
                String string_id = Integer.toString(noticiateste.get(i).getId());
                String string_nome = noticiateste.get(i).getTitle();
                String string_trueid = Integer.toString(noticiateste.get(i).getTrueId());
                list.add(new NoticiaSingleList(string_nome, string_id, this, string_trueid));
                PositionId positionId = new PositionId();
                positionId.id = string_id;
                positionId.position = i;
                positionIds.add(positionId);
                i++;


            }
        }
//        String[] itens = new String[] {noticiateste.get(1).getTitle(), noticiateste.get(2).getTitle(), noticiateste.get(0).getTitle()};
        setListAdapter(new NoticiaSingleImageListAdapter(this, list));


    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);
        Object o = this.getListAdapter().getItem(position);
        Intent intent = new Intent(Noticias.this, NoticiaActivity.class);
        Bundle b = new Bundle();
        String string_id = positionIds.get(position).id;
        b.putString("key", string_id);
        intent.putExtras(b);
        startActivity(intent);
        finish();

    }

    public void voltar(View view){
        Intent intent = new Intent(Noticias.this, Home.class);
        startActivity(intent);
        finish();

    }


}
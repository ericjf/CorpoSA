package com.corposa.corposa;

import android.app.ActionBar;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Eric Teixeira on 20/10/2014.
 */
public class AgendaSingleImageListAdapter extends BaseAdapter {
    private Context context;

    private List<AgendaSingleList> lista;

    public AgendaSingleImageListAdapter(Context context, List<AgendaSingleList> lista){
        this.context = context;
        this.lista = lista;
    }

    public int getCount(){
        return lista.size();
    }
    public Object getItem(int position){
        return lista.get(position);
    }
    public long getItemId(int position){
        return position;
    }

    public View getView(int posicao, View convertView, ViewGroup parent){
        AgendaSingleList singleList = lista.get(posicao);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.singlelsit_detalhes_agenda, null);

        TextView textNome = (TextView) v.findViewById(R.id.nome);
        TextView textDate = (TextView) v.findViewById(R.id.data);
        textDate.setText(singleList.data);
        textNome.setText(singleList.nome);
        ImageView img = (ImageView) v.findViewById(R.id.img);
        img.setImageResource(singleList.getImagem());




        return v;
    }
}
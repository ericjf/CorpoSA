package com.corposa.corposa;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Eric Teixeira on 13/09/2014.
 */
public class SingleImageListAdapter extends BaseAdapter {
    private Context context;
    private List<SingleList> lista;
    public SingleImageListAdapter(Context context, List<SingleList> lista){
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
        SingleList singleList = lista.get(posicao);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.singlelist_detalhes, null);

        TextView textNome = (TextView) v.findViewById(R.id.nome);
        textNome.setText(singleList.nome);
        ImageView img = (ImageView) v.findViewById(R.id.img);
        img.setImageResource(singleList.getImagem());

        return v;
    }
}

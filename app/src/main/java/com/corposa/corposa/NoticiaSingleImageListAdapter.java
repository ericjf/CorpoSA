package com.corposa.corposa;

/**
 * Created by Eric Teixeira on 24/10/2014.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Eric Teixeira on 20/10/2014.
 */
public class NoticiaSingleImageListAdapter extends BaseAdapter {
    private Context context;

    private List<NoticiaSingleList> lista;

    public NoticiaSingleImageListAdapter(Context context, List<NoticiaSingleList> lista){
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

        NoticiaSingleList noticiaSingleList = lista.get(posicao);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.singlelist_detalhes, null);
        TextView textNome = (TextView) v.findViewById(R.id.nome);
        textNome.setText(noticiaSingleList.nome);
        Bitmap bitMapIMG = noticiaSingleList.getimage();
        ImageView img = (ImageView) v.findViewById(R.id.img);
        if (bitMapIMG == null){

            img.setImageResource(R.drawable.corposa);
        }
        if(bitMapIMG != null){

        img.setImageBitmap(bitMapIMG);
        }

        return v;
    }
}
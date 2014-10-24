package com.corposa.corposa;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Eric Teixeira on 24/10/2014.
 */
public class NoticiaSingleList {

    public String nome;
    private Context context;
    public String id;

    public NoticiaSingleList(String nome,String id, Context context){

        this.nome = nome;
        this.id = id;
        this.context=context;

    }

    public Bitmap getimage(){

        DBImageHelper dbImageHelper = new DBImageHelper(context);
        ImageHelper imageHelper  = dbImageHelper.getImage(id);



        byte[] bytes = imageHelper.getImageByteArray();


        if (bytes == null){
        //    ImageView imageView = null;
        //    imageView.setImageResource(R.drawable.corposa);
        //   Bitmap bmap = imageView.getDrawingCache();
            return null;
        }
        else{

            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            return  bitmap;
        }
    }
}

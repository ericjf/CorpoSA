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
    public String trueid;

    public NoticiaSingleList(String nome,String id, Context context, String trueid){

        this.nome = nome;
        this.id = id;
        this.context=context;
        this.trueid = trueid;
    }

    public Bitmap getimage(){

        DBImageHelper dbImageHelper = new DBImageHelper(context);
        ImageHelper imageHelper  = dbImageHelper.getImage(trueid);



        byte[] bytes = imageHelper.getImageByteArray();


        if (bytes == null){
            //    ImageView imageView = null;
            //    imageView.setImageResource(R.drawable.corposa);
            //   Bitmap bmap = imageView.getDrawingCache();
            return null;
        }
        else{

            BitmapFactory.Options options = new BitmapFactory.Options(); options.inSampleSize = 8;
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length,options);
              //      BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            return  bitmap;
        }
    }
}

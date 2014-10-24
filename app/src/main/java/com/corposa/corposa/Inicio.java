package com.corposa.corposa;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class Inicio extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

    //   DBImageHelper dbImageHelper = new DBImageHelper(this);
   //    ImageHelper imageHelper  = dbImageHelper.getImage("3");


       setContentView(R.layout.activity_inicio);
   //     byte[] bytes = imageHelper.getImageByteArray();
   //     Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

   //    ImageView imageView  = (ImageView)findViewById(R.id.imageView);
   //    imageView.setImageBitmap(bitmap);
    }
}

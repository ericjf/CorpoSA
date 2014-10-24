package com.corposa.corposa;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class NoticiaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        String id = b.getString("key");

        DatabaseHelper databaseHelper = new DatabaseHelper(this, null, null, 1);
        DBImageHelper dbImageHelper = new DBImageHelper(this);

        Noticia noticia = databaseHelper.findNoticiaById(id);
        setContentView(R.layout.activity_noticia);


        TextView textViewTitle = (TextView) findViewById(R.id.titlenot);
        TextView textViewDesc = (TextView) findViewById(R.id.descnot);
        String title = noticia.getTitle();
        String desc = noticia.getDescription();
        textViewTitle.setText(title);
        textViewDesc.setText(desc);

        ImageHelper imageHelper  = dbImageHelper.getImage(id);
        byte[] bytes = imageHelper.getImageByteArray();
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        ImageView imageView  = (ImageView)findViewById(R.id.imgnot);
        imageView.setImageBitmap(bitmap);
        //Toast.makeText(getBaseContext(), "Received!" + noticia.getTitle() , Toast.LENGTH_LONG).show();
 }




    public void voltar(View view){

        Intent intent = new Intent(NoticiaActivity.this, Home.class);
        startActivity(intent);
        finish();

    }



}

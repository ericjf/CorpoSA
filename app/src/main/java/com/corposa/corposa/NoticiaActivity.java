package com.corposa.corposa;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class NoticiaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        String id = b.getString("key");
        getActionBar().setDisplayHomeAsUpEnabled(true);

        DatabaseHelper databaseHelper = new DatabaseHelper(this, null, null, 1);
        DBImageHelper dbImageHelper = new DBImageHelper(this);

        Noticia noticia = databaseHelper.findNoticiaById(id);


        setContentView(R.layout.activity_noticia);


        TextView textViewTitle = (TextView) findViewById(R.id.titlenot);
        TextView textViewDesc = (TextView) findViewById(R.id.descnot);
        ImageView imageView = (ImageView) findViewById(R.id.imgnot);


        String title = noticia.getTitle();
        String desc = noticia.getDescription();
        textViewTitle.setText(title);
        textViewDesc.setText(desc);


        int trueid = noticia.getTrueId();
        String stringTrueId = Integer.toString(trueid);

        setTitle(title);

        ImageHelper imageHelper  = dbImageHelper.getImage(stringTrueId);


        byte[] bytes = imageHelper.getImageByteArray();


        if (bytes == null){
            //    ImageView imageView = null;
            //    imageView.setImageResource(R.drawable.corposa);
            //   Bitmap bmap = imageView.getDrawingCache();
            imageView.setImageResource(R.drawable.corposa);
        }
        else{
            BitmapFactory.Options options = new BitmapFactory.Options(); options.inSampleSize = 4;
            Bitmap bitmap = bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length,options);
            //      BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            } else {
                imageView.setImageResource(R.drawable.corposa);
            }

        }


        //Toast.makeText(getBaseContext(), "Received!" + noticia.getTitle() , Toast.LENGTH_LONG).show();
 }




    public void voltar(View view){

        Intent intent = new Intent(NoticiaActivity.this, Home.class);
        startActivity(intent);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.agenda_adicionar, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent(this, Home.class);
        startActivity(intent);


        return true;
    }



}

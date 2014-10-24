package com.corposa.corposa;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Home extends TabActivity implements TabHost.OnTabChangeListener {

    private static final String CATEGORIA = "livro";
    ImageView image;
    Button button;
    ProgressDialog mProgressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        TabHost tabHost = getTabHost();
        tabHost.setOnTabChangedListener(this);
        // call AsynTask to perform network operation on separate thread
        new HttpAsyncTask().execute("http://162.243.229.85/getnews.php");

        // Inicializando Tab Início
        TabHost.TabSpec tab_inicio = tabHost.newTabSpec("Início");
        tab_inicio.setIndicator("Início", getResources().getDrawable(android.R.drawable.ic_dialog_dialer));
        tab_inicio.setContent(new Intent(this, Inicio.class));
        tabHost.addTab(tab_inicio);

        //Inicializando Tab Notícias
        TabHost.TabSpec tab_noticias = tabHost.newTabSpec("Notícias");
        tab_noticias.setIndicator("Notícias", getResources().getDrawable(android.R.drawable.ic_menu_mapmode));
        tab_noticias.setContent(new Intent (this, Noticias.class));
        tabHost.addTab(tab_noticias);

        //Inicializando Tab Tratamentos
        TabHost.TabSpec tab_tratamentos = tabHost.newTabSpec("Tratamentos");
        tab_tratamentos.setIndicator("Tratamentos", getResources().getDrawable(android.R.drawable.ic_menu_mapmode));
        tab_tratamentos.setContent(new Intent (this, Tratamentos.class));
        tabHost.addTab(tab_tratamentos);

        TabHost.TabSpec tab_agenda = tabHost.newTabSpec("Agenda");
        tab_agenda.setIndicator("Agenda", getResources().getDrawable(android.R.drawable.ic_dialog_dialer));
        tab_agenda.setContent(new Intent(this, Agenda.class));
        tabHost.addTab(tab_agenda);

    }

    /**
     * @see android.widget.TabHost.TabContentFactory#createTabContent(String)
     */

   // public View createTabContent(String tabId){
   //     TextView tv = new TextView(this);
   //     tv.setText("Utilizando uma Factory bla bla bla" + tabId);
   //     return tv;
   // }

    public static String GET(String url){
        InputStream inputStream = null;
        String result = "";
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }
    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }
    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return GET(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {


           // Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
            try{

                //    DownloadImage down = new DownloadImage();
                 //   down.imageid = "3";
                 //   down.execute("http://bubbledev.com.br/images/imagem1.jpg");


                JSONArray jsonArray = new JSONArray(result);
                // looping through All jsonObject
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String id = jsonObject.getString("id");
                    String title = jsonObject.getString("title");
                    String description = jsonObject.getString("description");
                    String img_url = jsonObject.getString("img_url");
                    int idInt = Integer.parseInt(id);
                    Noticia noticia = new Noticia();
                    noticia.setDescription(description);
                    noticia.setTitle(title);
                    noticia.setUrl(img_url);
                    noticia.setId(idInt);
                    DatabaseHelper databaseHelper = new DatabaseHelper(getCurrentActivity(), null, null, 1);
                    DBImageHelper dbImageHelper = new DBImageHelper(getCurrentActivity());

                    if(!databaseHelper.VerificaNoticiabyId(idInt)) {



                    DownloadImage down = new DownloadImage();
                    down.imageid = id;
                    down.execute(img_url);
                    databaseHelper.addProduct(noticia);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    // DownloadImage AsyncTask
    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {

        String imageid;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(Home.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Downloading Image!");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... URL) {

            String imageURL = URL[0];

            Bitmap bitmap = null;
            try {
                // Download Image from URL
                InputStream input = new java.net.URL(imageURL).openStream();
                // Decode Bitmap
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }
        @Override
        protected void onPostExecute(Bitmap result) {
            // Set the bitmap into ImageView
            Bitmap bitmapteste = result;
            Drawable drawable = new BitmapDrawable(bitmapteste);
            DBImageHelper dbImageHelper = new DBImageHelper(getCurrentActivity());
            dbImageHelper.insetImage(drawable, imageid);
            mProgressDialog.dismiss();
        }


    }

    /**
     * @see android.widget.TabHost.OnTabChangeListener#onTabChanged(String)
     */

    public void onTabChanged(String tabId){
    //    Log.i(CATEGORIA, "Trocou" + tabId);
    }

}

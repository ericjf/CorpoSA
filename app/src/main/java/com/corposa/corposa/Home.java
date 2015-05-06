package com.corposa.corposa;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Home extends TabActivity implements TabHost.OnTabChangeListener {

    ImageView image;
    Button button;
    ProgressDialog mProgressDialog;
    GoogleCloudMessaging gcm;
    String SENDER_ID = "866649988233";
    String regid;
    String CHAVE_CACHE = "devicetoken";
    String CHAVE_CACHE_BANCO = "chavebanco";
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TabHost tabHost = getTabHost();
        tabHost.setOnTabChangedListener(this);
        if (isConnected()) {
            if (checkPlayServices()) {
                gcm = GoogleCloudMessaging.getInstance(this);
                //regid = getRegistrationId(context);

                //if (regid.isEmpty()) {
                registerInBackground();
                //}
            } else {
                Log.i("TAG1", "No valid Google Play Services APK found.");
            }
            new HttpAsyncTask().execute("http://162.243.229.85/getnews.php");
        }


        // Inicializando Tab Início
        TabHost.TabSpec tab_inicio = tabHost.newTabSpec("Início");
        tab_inicio.setIndicator("", getResources().getDrawable(R.drawable.homeroboto));

        tab_inicio.setContent(new Intent(this, Inicio.class));
        tabHost.addTab(tab_inicio);

        //Inicializando Tab Notícias
        TabHost.TabSpec tab_noticias = tabHost.newTabSpec("Notícias");
        tab_noticias.setIndicator("", getResources().getDrawable(R.drawable.noticias));
        tab_noticias.setContent(new Intent (this, Noticias.class));
        tabHost.addTab(tab_noticias);

        //Inicializando Tab Tratamentos
        TabHost.TabSpec tab_tratamentos = tabHost.newTabSpec("Tratamentos");
        tab_tratamentos.setIndicator("", getResources().getDrawable(R.drawable.tratamentosicon2));
        tab_tratamentos.setContent(new Intent(this, Tratamentos.class));
        tabHost.addTab(tab_tratamentos);

        TabHost.TabSpec tab_agenda = tabHost.newTabSpec("Agenda");
        tab_agenda.setIndicator("", getResources().getDrawable(R.drawable.agendaicon));
        tab_agenda.setContent(new Intent(this, Agenda.class));
        tabHost.addTab(tab_agenda);


        for(int i=0;i<tabHost.getTabWidget().getChildCount();i++)
        {
            if (i == 0) tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#F6E6E7"));

            else tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FFFFFF"));
        }

        //for(int i=0; i<tabHost.getTabWidget().getChildCount();i++)
        //{
        //    tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#8A4117"));
        //}
        //tabHost.getTabWidget().setCurrentTab(1);
        //tabHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#C35817"));

    }

     /**
     * @see android.widget.TabHost.TabContentFactory#createTabContent(String)
     */



    private boolean checkPlayServices() {
        try {
            int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());
            if (resultCode != ConnectionResult.SUCCESS) {
                if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                    GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                            PLAY_SERVICES_RESOLUTION_REQUEST).show();
                } else {
                    Log.i("TAG", "This device is not supported.");
                    finish();
                }
                return false;
            }
            return true;
        }catch(Exception e){
            Log.i("error",e.getMessage());
            return false;
        }
    }

    private void registerInBackground() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
                    }


                    regid = gcm.register(SENDER_ID);
                    msg = "Device registered, registration ID=" + regid;

                    Log.i("ID", regid);
                    FileOutputStream fos = openFileOutput(CHAVE_CACHE, Context.MODE_PRIVATE);
                    if (fos.getChannel() != null){
                        Log.i("ID", "Deu Certo");
                    }
                    else {
                        SendtoPHP(regid);
                    }


                    // You should send the registration ID to your server over HTTP, so it
                    // can use GCM/HTTP or CCS to send messages to your app.
                    //sendRegistrationIdToBackend();

                    // For this demo: we don't need to send it because the device will send
                    // upstream messages to a server that echo back the message using the
                    // 'from' address in the message.

                    // Persist the regID - no need to register again.
                    //storeRegistrationId(context, regid);
                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                    // If there is an error, don't just keep trying to register.
                    // Require the user to click a button again, or perform
                    // exponential back-off.
                }
                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {
                Log.i("ID", regid);
            }
        }.execute(null, null, null);
    }


    //enviando para o backend
    private void SendtoPHP(String reg) throws IOException {


        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://bubbledev.com.br/gcm/getdevice.php");

        try{
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("regid", "" + reg ));
            nameValuePairs.add(new BasicNameValuePair("teste", "teste" ));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);


            String devicetoken = regid.toString();


            FileOutputStream fos = openFileOutput(CHAVE_CACHE, Context.MODE_PRIVATE);
            fos.write(devicetoken.getBytes());
            fos.close();



        }
        catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }

    }



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

                    FileOutputStream fos2 = openFileOutput(CHAVE_CACHE_BANCO, Context.MODE_PRIVATE);
                    if (fos2.getChannel() != null){
                        Log.i("ID", "Deu Certo");
                    }
                    else {

                        Boolean Apaga = databaseHelper.deleteNoticias();
                        fos2 = openFileOutput(CHAVE_CACHE_BANCO, Context.MODE_PRIVATE);
                        String devicetoken2 = "feito";
                        fos2.write(devicetoken2.getBytes());
                        fos2.close();
                    }

                    if(!databaseHelper.VerificaNoticiabyId(idInt)) {


                        if(!databaseHelper.VerificaNoticiabyId(idInt)) {
                            if (noticia.getTitle() != null) {
                                DBImageHelper dbImageHelper = new DBImageHelper(getCurrentActivity());
                                DownloadImage down = new DownloadImage();
                                down.imageid = id;
                                down.execute(img_url);
                                if(!databaseHelper.VerificaNoticiabyId(idInt)) {
                                    databaseHelper.addProduct(noticia);
                                }
                            }
                        }
                    }


                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
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
            mProgressDialog.setTitle("Atualizando Dados");
            // Set progressdialog message
            mProgressDialog.setMessage("Carregando...");
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
            if (result != null) {
                Bitmap bitmapteste = result;
                Drawable drawable = new BitmapDrawable(bitmapteste);
                DBImageHelper dbImageHelper = new DBImageHelper(getCurrentActivity());
                dbImageHelper.insetImage(drawable, imageid);
                mProgressDialog.dismiss();
            }
            else{
                mProgressDialog.dismiss();
            }

        }


    }

    /**
     * @see android.widget.TabHost.OnTabChangeListener#onTabChanged(String)
     */

    //public void onTabChanged(String tabId){
    ////    Log.i(CATEGORIA, "Trocou" + tabId);
    // }
    @Override
    public void onTabChanged(String tabId) {
        // TODO Auto-generated method stub
        TabHost tabHost = getTabHost();
        for(int i=0;i<tabHost.getTabWidget().getChildCount();i++)
        {
            tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FFFFFF"));
        }

        tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#F6E6E7"));
    }





}

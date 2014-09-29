package com.corposa.corposa;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;


public class Home extends TabActivity implements TabHost.OnTabChangeListener {

    private static final String CATEGORIA = "livro";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TabHost tabHost = getTabHost();
        tabHost.setOnTabChangedListener(this);


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

    }

    /**
     * @see android.widget.TabHost.TabContentFactory#createTabContent(String)
     */

    public View createTabContent(String tabId){
        TextView tv = new TextView(this);
        tv.setText("Utilizando uma Factory bla bla bla" + tabId);
        return tv;
    }

    /**
     * @see android.widget.TabHost.OnTabChangeListener#onTabChanged(String)
     */

    public void onTabChanged(String tabId){
        Log.i(CATEGORIA, "Trocou" + tabId);
    }

}

package com.corposa.corposa;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;

public class Tratamento extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Intent intent = getIntent();
        //String posicao = intent.getStringExtra("posicao");

        Intent intent = getIntent();
        int posicao = intent.getIntExtra("posicao", 0);

        if (posicao == 0) {
            setContentView(R.layout.medidacerta);
        }
        if (posicao == 1){
            setContentView(R.layout.modelagem);
        }
        if (posicao == 2){
            setContentView(R.layout.carbox);
        }
        if (posicao == 3){
            setContentView(R.layout.posparto);
        }
        if ( posicao == 4){
            setContentView(R.layout.noiva);
        }

        if (posicao == 5){
            setContentView(R.layout.facial);
        }
        if (posicao == 6){
            setContentView(R.layout.turbinada);
        }
        if (posicao == 7){
            setContentView(R.layout.maissa);
        }

        //ArrayList<String> titulo = new ArrayList<String>();
        // ArrayList<String> desc = new ArrayList<String>();




        //titulo.add(0, "Medida Certa S/A");
        // titulo.add(1, "Modelagem S/A");
        //titulo.add(2, "Carbox S/A");
        // titulo.add(3, "Noivas S/A");
        //  titulo.add(4, "Facial  5 semanas");
        // titulo.add(5, "Turbinada S/A 5 semanas");
        // titulo.add(6, "Alimentos e suplementos S/A");


        //ArrayList<TextView> arrayDesc = new ArrayList<TextView>();




        //TextView textNome = (TextView) findViewById(R.id.tratamentotitle);
        //textNome.setText(titulo.get(posicao));


        //TextView textDesc = (TextView) findViewById(R.id.tratamentodesc);
        // textDesc.setText("namala");



    }

}
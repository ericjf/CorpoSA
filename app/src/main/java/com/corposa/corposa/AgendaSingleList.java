package com.corposa.corposa;

/**
 * Created by Eric Teixeira on 20/10/2014.
 */
public class AgendaSingleList {

    public String nome;
    public String data;
    public int id;

    public AgendaSingleList(String nome, int id, String data){

        this.nome = nome;
        this.id = id;
        this.data = data;
    }

    /** Retornaa Imagem do Smile.
     * As Imagems foram insediras no /res/drawable
     * @return
     */

    public int getImagem() {
        return R.drawable.abc_ic_clear;
    }

}
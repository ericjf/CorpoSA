package com.corposa.corposa;

/**
 * Created by Eric Teixeira on 16/09/2014.
 */
public class Noticia {

    private int id;
    private String title;
    private String description;
    private String url;


    public Noticia(){}
    public Noticia (int position, String title, String description, String url){
        super();
        this.title = title;
        this.description = description;
        this.url = url;

    }

    //getters e setters
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public  String getTitle(){
        return this.title;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return this.url;
    }



}

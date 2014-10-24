package com.corposa.corposa;

/**
 * Created by Eric Teixeira on 21/10/2014.
 */
public class Compromisso {

    //private variables
    int _id;
    String desc;
    String date;

    // Empty constructor
    public Compromisso(){

    }
    // constructor
    public Compromisso(int id, String name, String _phone_number){
        this._id = id;
        this.desc = name;
        this.date = _phone_number;
    }

    // constructor
    public Compromisso(String name, String _phone_number){
        this.desc = name;
        this.date = _phone_number;
    }
    // getting ID
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    // getting name
    public String getName(){
        return this.desc;
    }

    // setting name
    public void setName(String name){
        this.desc = name;
    }

    // getting phone number
    public String getPhoneNumber(){
        return this.date;
    }

    // setting phone number
    public void setPhoneNumber(String phone_number){
        this.date = phone_number;
    }
}
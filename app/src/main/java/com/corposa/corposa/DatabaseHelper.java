package com.corposa.corposa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Eric Teixeira on 23/09/2014.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "corposa.db";
    public static final String TABLE_NOTICIAS = "noticias";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_URL = "url";

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
                TABLE_NOTICIAS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_TITLE
                + " TEXT," + COLUMN_DESCRIPTION + " TEXT," + COLUMN_URL + " TEXT" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    public DatabaseHelper(Context context, String name, CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTICIAS);
        onCreate(db);
    }

    public void addProduct(Noticia noticia) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, noticia.getTitle());
        values.put(COLUMN_DESCRIPTION, noticia.getDescription());
        values.put(COLUMN_URL, noticia.getUrl());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_NOTICIAS, null, values);
        db.close();
    }

    public Noticia findNoticiaByTitle(String title) {
        String query = "Select * FROM " + TABLE_NOTICIAS+ " WHERE " + COLUMN_TITLE + " =  \"" + title + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Noticia noticia = new Noticia();

        if(!cursor.moveToFirst()){
            noticia = null;
        }

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            noticia.setId(Integer.parseInt(cursor.getString(0)));
            noticia.setTitle(cursor.getString(1));
            noticia.setDescription(cursor.getString(2));
            cursor.close();
        }

        db.close();
        return noticia;
    }

    public boolean deleteNoticiaByTitle(String title) {

        boolean result = false;

        String query = "Select * FROM " + TABLE_NOTICIAS + " WHERE " + COLUMN_TITLE + " =  \"" + title + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Noticia noticia = new Noticia();

        if (cursor.moveToFirst()) {
            noticia.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_NOTICIAS, COLUMN_ID + " = ?",
                    new String[] { String.valueOf(noticia.getId()) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
}
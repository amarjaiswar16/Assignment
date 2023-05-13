package com.amrdroid.assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class NotesDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "NotesDB";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_NOTES = "notes";
    private static final String NOTES_ID = "id";
    private static final String NOTES_TITLE = "title";
    private static final String NOTES_DATE = "date";
    private static final String NOTES_CONTENT = "content";

    public NotesDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table notes (id integer primary key autoincrement,title text,date text,content text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists " + TABLE_NOTES);
        onCreate(db);

    }



/*    public void addNotes(String title, String date, String content) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOTES_TITLE,title);
        values.put(NOTES_DATE,date);
        values.put(NOTES_CONTENT,content);


        db.insert(TABLE_NOTES,null,values);

    }*/

    public boolean insertData(String title,String date,String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOTES_TITLE,title);
        values.put(NOTES_DATE,date);
        values.put(NOTES_CONTENT,content);

        long result = db.insert(TABLE_NOTES,null,values);
        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<NotesModel> getNotesData() {
        ArrayList<NotesModel> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select id,title,date,content from " + TABLE_NOTES,null);
        if(cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                NotesModel model = new NotesModel();
                model.setId(cursor.getInt(0));
                model.setTitle(cursor.getString(1));
                model.setDate(cursor.getString(2));
                model.setContent(cursor.getString(3));

                list.add(model);
            }
        }

        return list;
    }

    public Cursor getNotesById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from notes where id ="+ id,null);
        if(cursor != null)
            cursor.moveToFirst();

        return cursor;
    }

    public int deleteOrder(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NOTES,"id="+id,null);

    }

    public boolean updateData(String title,String date,String content,int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOTES_TITLE,title);
        values.put(NOTES_DATE,date);
        values.put(NOTES_CONTENT,content);

        long result = db.update(TABLE_NOTES,values,"id="+id, null);
        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NOTES,null);
        return cursor;
    }
}

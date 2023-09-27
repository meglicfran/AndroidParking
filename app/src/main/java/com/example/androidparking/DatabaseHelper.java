package com.example.androidparking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "CarmenPal.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_EVENTS = "events";
    private static final String TABLE_UNAUTHORIZED = "unauthorized";
    private static final String COLUMN_ID ="id";
    private static final String COLUMN_JSON="json";
    private static final String COLUMN_SYNCED="synced";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_EVENTS +
                " ("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +COLUMN_JSON+" TEXT, "
                +COLUMN_SYNCED+" INTEGER);";
        db.execSQL(query);

        query = "CREATE TABLE " + TABLE_UNAUTHORIZED +
                " ("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +COLUMN_JSON+" TEXT);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_EVENTS);
        onCreate(db);
    }

    public boolean eventsInsertJSON(String json){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_JSON,json);
        cv.put(COLUMN_SYNCED,0); //0 = unsynced, 1 = syncing, 2 = synced;

        long insert = db.insert(TABLE_EVENTS, null, cv);
        if(insert==-1){
            return false;
        }else{
            return true;
        }
    }
    public boolean eventsDeleteRow(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("DELETE FROM " + TABLE_EVENTS + " WHERE " + COLUMN_ID + " = "+ id + ";", null);
        if(cursor.moveToFirst()){
            return true;
        }else{
            return false;
        }
    }
    public Cursor eventsGetUnsyncedRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM "+ TABLE_EVENTS + " WHERE "+COLUMN_SYNCED+" = 0;",null);
    }
    public boolean eventsChangeSyncSyncing(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("UPDATE " + TABLE_EVENTS +  " SET " + COLUMN_SYNCED + " = 1  WHERE " + COLUMN_ID + " = "+ id + ";", null);
        if(cursor.moveToFirst()){
            return true;
        }else{
            return false;
        }
    }
    public boolean eventsChangeSyncUnsynced(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("UPDATE " + TABLE_EVENTS +  " SET " + COLUMN_SYNCED + " = 0  WHERE " + COLUMN_ID + " = "+ id + ";", null);
        if(cursor.moveToFirst()){
            return true;
        }else{
            return false;
        }
    }

    public boolean unauthorizedInsert(String json){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_JSON,json);

        long insert = db.insert(TABLE_UNAUTHORIZED, null, cv);
        if(insert==-1){
            return false;
        }else{
            return true;
        }
    }
    public boolean unauthorizedDeleteRow(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("DELETE FROM " + TABLE_UNAUTHORIZED + " WHERE " + COLUMN_ID + " = "+ id + ";", null);
        if(cursor.moveToFirst()){
            return true;
        }else{
            return false;
        }
    }
    public Cursor unauthorizedGetRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM "+ TABLE_UNAUTHORIZED + " ;",null);
    }

}

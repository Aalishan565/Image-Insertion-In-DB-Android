package com.imageinsertionindb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * Created by aalishan on 9/3/16.
 */
public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, DBConstancts.DBNAME, null, DBConstancts.DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBConstancts.CREATE_TABLE_IMAGE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS ");
        onCreate(db);
    }

    public void addImageToDB(byte[] image) throws SQLiteException {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new  ContentValues();
        cv.put(DBConstancts.COLOUMN_IMAGE, image);
        database.insert(DBConstancts.TABLE_IMAGE, null, cv);
       database.close();
    }
    public Bitmap getImageFromDB(int i){
        // SQLiteDatabase db;
        SQLiteDatabase database = this.getWritableDatabase();
        //String qu = "select img  from table where feedid=" + i ;
        Cursor cur = database.rawQuery("SELECT image  from ImageTable",null);

        if (cur.moveToFirst()){
            byte[] imgByte = cur.getBlob(0);
            cur.close();
            return BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
        }
        if (cur != null && !cur.isClosed()) {
            cur.close();
        }

        return null ;
    }


}

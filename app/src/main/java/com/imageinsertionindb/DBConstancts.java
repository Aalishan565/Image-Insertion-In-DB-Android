package com.imageinsertionindb;

/**
 * Created by aalishan on 9/3/16.
 */
public class DBConstancts {
    public static final int DATABASE_VERSION = 1;
    public static final String DBNAME = "IMageInsertionInDB";
    //Table Names
    public static final String TABLE_IMAGE = "ImageTable";
    //Coloumn Names
    public static final String COLOUMN_IMAGE = "image";
    //Query to create database
    public static final String CREATE_TABLE_IMAGE = "CREATE TABLE " + TABLE_IMAGE
            + " ( " + COLOUMN_IMAGE + " BLOB"+")";


    // "CREATE TABLE " + TABLE_EVENT + "(" + NAME + " TEXT," + NOTE +" TEXT," + DATE + " TEXT," + EVENT + " TEXT" + ")";


}

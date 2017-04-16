//Abrahams, Thomas
//Kick it app.
//With help from many tutorials (Lynda, Stackoverflow), it was a fun experience. Android is alright.
package com.example.thomas.cskickitappv2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Thomas on 3/30/2017.
 */

public class DBAssistant extends SQLiteOpenHelper {

    public static final String DB_FILE_NAME = "planning.db";
    public static final int DB_VERSION = 1;                 //Each version must be integer.

    public DBAssistant(Context context) {
        super(context, DB_FILE_NAME,null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PlanTable.SQL_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //This is for update the database.
        //Need to have an importer.
        db.execSQL(PlanTable.SQL_DELETE);
        onCreate(db);

    }
}

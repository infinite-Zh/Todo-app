package com.infinite.todoapp.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 19082 on 2017/6/7.
 */

public class TaskDbHelper extends SQLiteOpenHelper {
    public static final int VERSION = 1;
    public static final String TASK_DB_NAME = "task.db";
    public static final String TEXT_TYPE = " TEXT";
    public static final String BOOLEAN_TYPE = " INTEGER";
    public static final String SEPEROR = ",";
    public static final String TABLE_NAME = "task";
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String COMPLETED = "completed";
    private static final String SQL = "create table " + TABLE_NAME + " (" +
            ID + TEXT_TYPE  + " primary key," +
            TITLE + TEXT_TYPE + SEPEROR +
            DESCRIPTION + TEXT_TYPE + SEPEROR +
            COMPLETED + BOOLEAN_TYPE +
            ")";

    public TaskDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

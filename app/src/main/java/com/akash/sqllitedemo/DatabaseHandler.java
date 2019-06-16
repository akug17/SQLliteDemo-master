package com.akash.sqllitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.akash.sqllitedemo.Model.Details;

public class DatabaseHandler extends SQLiteOpenHelper {



    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "detailsmanager";
    private static final String TABLE_DETAILS = "details";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ROLL_NO = "roll";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_DATE = "date";
    private static final String KEY_IMAGE = "image";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_DETAILS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_ROLL_NO + " TEXT," + KEY_GENDER + " TEXT," + KEY_DATE + " TEXT," + KEY_IMAGE + " BLOB NOT NULL" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    void add_details(Details details) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, details.getName());
        values.put(KEY_ROLL_NO, details.getRoll());
        values.put(KEY_GENDER, details.getGender());
        values.put(KEY_DATE,details.getDate());
        values.put(KEY_IMAGE,details.getImage());



        // Inserting Row
        db.insert(TABLE_DETAILS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    Details getDetails(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DETAILS, new String[] {
                        KEY_NAME, KEY_ROLL_NO,KEY_GENDER ,KEY_DATE,KEY_IMAGE}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Details details = new Details(cursor.getString(0), cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getBlob(4));

        return details;
    }


}

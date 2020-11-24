package com.mad2020reg.docchannel.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/****Question 03 (a) ****/

import androidx.annotation.Nullable;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "DocChannel.db";
    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String SQL_CREATE_ENTRIES_CHANNALING = "CREATE TABLE " + Channeling.ChannelingDetails.TABLE_NAME + " (" +
                Channeling.ChannelingDetails._ID + " INTEGER PRIMARY KEY," +
                Channeling.ChannelingDetails.COLUMN_NAME_PATIENT_ID + " TEXT," +
                Channeling.ChannelingDetails.COLUMN_NAME_DATE + " TEXT," +
                Channeling.ChannelingDetails.COLUMN_NAME_DOC_CAT + " TEXT," +
                Channeling.ChannelingDetails.COLUMN_NAME_DOCTOR + " TEXT," +
                Channeling.ChannelingDetails.COLUMN_NAME_TOTAL+ " TEXT)";

                /****Question 03 (b)- (i) ****/


        db.execSQL(SQL_CREATE_ENTRIES_CHANNALING);




        String SQL_CREATE_ENTRIES_LAB_TESTS = "CREATE TABLE " + LabTests.TestDetails.TABLE_NAME + " (" +
                LabTests.TestDetails._ID + " INTEGER PRIMARY KEY," +
                LabTests.TestDetails.COLUMN_NAME_PID + " TEXT," +
                LabTests.TestDetails.COLUMN_NAME_DATE + " TEXT," +
                LabTests.TestDetails.COLUMN_NAME_TEST + " TEXT," +
                LabTests.TestDetails.COLUMN_NAME_PRICE+ " TEXT)";
                /****Question 03 (b)- (ii) ****/

        db.execSQL(SQL_CREATE_ENTRIES_LAB_TESTS);




        String SQL_CREATE_ENTRIES_PATIENT = "CREATE TABLE " + Patient.PatientDetails.TABLE_NAME + " (" +
                Patient.PatientDetails._ID + " INTEGER PRIMARY KEY," +
                Patient.PatientDetails.COLUMN_NAME_ID + " TEXT," +
                Patient.PatientDetails.COLUMN_NAME_NAME + " TEXT," +
                Patient.PatientDetails.COLUMN_NAME_AGE + " TEXT," +
                Patient.PatientDetails.COLUMN_NAME_CONTACT+ " TEXT)";


                /****Question 03 (b)- (iii) ****/

        db.execSQL(SQL_CREATE_ENTRIES_PATIENT);
    }

  @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
      sqLiteDatabase.execSQL(SQL_DELETE_1);
      sqLiteDatabase.execSQL(SQL_DELETE_2);
      sqLiteDatabase.execSQL(SQL_DELETE_3);
      onCreate(sqLiteDatabase);
    }

    private static final String SQL_DELETE_1 =
            "DROP TABLE IF EXISTS " + Patient.PatientDetails.TABLE_NAME;

    private static final String SQL_DELETE_2 =
            "DROP TABLE IF EXISTS " +  LabTests.TestDetails.TABLE_NAME;

    private static final String SQL_DELETE_3 =
            "DROP TABLE IF EXISTS " + Channeling.ChannelingDetails.TABLE_NAME;



    //****Question 03 (c) ****//*
    public long addPatient(String id ,String name, String age,String contact){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

                // statements?

        values.put(Patient.PatientDetails.COLUMN_NAME_ID, id);
        values.put(Patient.PatientDetails.COLUMN_NAME_NAME, name);
        values.put(Patient.PatientDetails.COLUMN_NAME_AGE, age);
        values.put(Patient.PatientDetails.COLUMN_NAME_CONTACT, contact);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(Patient.PatientDetails.TABLE_NAME, null, values);
        return newRowId;

    }


    //****Question 03 (d) ****//*

    public long addChanneling(String date,String category, String doctor, String total){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Channeling.ChannelingDetails.COLUMN_NAME_DATE, date);
        values.put(Channeling.ChannelingDetails.COLUMN_NAME_DOC_CAT, category);
        values.put(Channeling.ChannelingDetails.COLUMN_NAME_DOCTOR, doctor);
        values.put(Channeling.ChannelingDetails.COLUMN_NAME_TOTAL, total);



        // statements?
        long newRowId = db.insert(Patient.PatientDetails.TABLE_NAME, null, values);
        return newRowId;

    }

    //****Question 03 (e) ****//*
      public long addTest(String date,String test, String price){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(LabTests.TestDetails.COLUMN_NAME_DATE, date);
        values.put(LabTests.TestDetails.COLUMN_NAME_TEST, test);
        values.put(LabTests.TestDetails.COLUMN_NAME_PRICE, price);


        // statements?
        long newRowId = db.insert(Patient.PatientDetails.TABLE_NAME, null, values);
        return newRowId;

    }

    public List getPatient(){

        //****Question 03 (f) ****//*
  SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                Patient.PatientDetails.COLUMN_NAME_NAME,
                Patient.PatientDetails.COLUMN_NAME_AGE,
                Patient.PatientDetails.COLUMN_NAME_CONTACT

        };


// How you want the results sorted in the resulting Cursor
        String sortOrder =
                Patient.PatientDetails.COLUMN_NAME_NAME + " DESC";

        Cursor cursor = db.query(
                Patient.PatientDetails.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        ArrayList itemIds = new ArrayList<>();
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndexOrThrow(Patient.PatientDetails.COLUMN_NAME_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(Patient.PatientDetails.COLUMN_NAME_NAME));
            String age = cursor.getString(cursor.getColumnIndexOrThrow(Patient.PatientDetails.COLUMN_NAME_AGE));
            String cont = cursor.getString(cursor.getColumnIndexOrThrow(Patient.PatientDetails.COLUMN_NAME_CONTACT));
            String dd =  id+ "\n"+name+"\n" +age +"\n"+cont;
            itemIds.add(dd);
        }
        cursor.close();
        return itemIds;
    }







    public List getChannelingInfo(){

        /****Question 03 (g) ****/


        //****Question 03 (f) ****//*
        SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                Channeling.ChannelingDetails.COLUMN_NAME_PATIENT_ID,
                Channeling.ChannelingDetails.COLUMN_NAME_DATE,
                Channeling.ChannelingDetails.COLUMN_NAME_DOC_CAT,
                Channeling.ChannelingDetails.COLUMN_NAME_DOCTOR,
                Channeling.ChannelingDetails.COLUMN_NAME_TOTAL


        };


// How you want the results sorted in the resulting Cursor
        String sortOrder =
                Channeling.ChannelingDetails.COLUMN_NAME_PATIENT_ID + " DESC";

        Cursor cursor = db.query(
                Channeling.ChannelingDetails.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        List itemIds = new ArrayList<>();
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndexOrThrow(Channeling.ChannelingDetails.COLUMN_NAME_PATIENT_ID));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(Channeling.ChannelingDetails.COLUMN_NAME_DATE));
            String category = cursor.getString(cursor.getColumnIndexOrThrow(Channeling.ChannelingDetails.COLUMN_NAME_DOC_CAT));
            String doctor = cursor.getString(cursor.getColumnIndexOrThrow(Channeling.ChannelingDetails.COLUMN_NAME_DOCTOR));
            String tot = cursor.getString(cursor.getColumnIndexOrThrow(Channeling.ChannelingDetails.COLUMN_NAME_TOTAL));

            String dd =  id+ "\n"+date+"\n" +category +"\n"+doctor+"\n"+tot;
            itemIds.add(dd);
        }
        cursor.close();
        return itemIds;

    }

    public List getTestInfo(){


        /****Question 03 (h) ****/



        //****Question 03 (f) ****//*
        SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                LabTests.TestDetails.COLUMN_NAME_PID,
                LabTests.TestDetails.COLUMN_NAME_DATE,
                LabTests.TestDetails.COLUMN_NAME_TEST,
                LabTests.TestDetails.COLUMN_NAME_PRICE
        };


// How you want the results sorted in the resulting Cursor
        String sortOrder =
                LabTests.TestDetails.COLUMN_NAME_PID + " DESC";

        Cursor cursor = db.query(
                LabTests.TestDetails.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        List itemIds = new ArrayList<>();
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndexOrThrow(LabTests.TestDetails.COLUMN_NAME_PID));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(LabTests.TestDetails.COLUMN_NAME_DATE));
            String test = cursor.getString(cursor.getColumnIndexOrThrow(LabTests.TestDetails.COLUMN_NAME_TEST));
            String  price = cursor.getString(cursor.getColumnIndexOrThrow(LabTests.TestDetails.COLUMN_NAME_PRICE));


            String dd =  id+ "\n"+date+"\n" +test +"\n"+price;
            itemIds.add(dd);
        }
        cursor.close();
        return itemIds;

    }
//////////////////////////////////////////////////////LOGIN PATIENT



    public Cursor loginPatient(String number) {
        SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                Patient.PatientDetails.COLUMN_NAME_NAME,
                Patient.PatientDetails.COLUMN_NAME_AGE,
                Patient.PatientDetails.COLUMN_NAME_CONTACT
        };

// Filter results WHERE "title" = 'My Title'
        String selection = Patient.PatientDetails.COLUMN_NAME_CONTACT + " = ?";
        String[] selectionArgs = {number};

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                Patient.PatientDetails.COLUMN_NAME_NAME + " DESC";

        Cursor cursor = db.query(
                Patient.PatientDetails.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        return cursor;
    }
}

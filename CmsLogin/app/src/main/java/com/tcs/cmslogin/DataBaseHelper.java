package com.tcs.cmslogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper
{
    public DataBaseHelper(Context context, String name,CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }
    // Called when no database exists in disk and the helper class needs
    // to create a new one.
    @Override
    public void onCreate(SQLiteDatabase _db)
    {
        try {
            _db.execSQL(LoginDataBaseAdapter.DATABASE_CREATE_A);
            _db.execSQL(LoginDataBaseAdapter.DATABASE_CREATE);
           _db.execSQL(LoginDataBaseAdapter.DATABASE_CREATE_E);
            _db.execSQL(LoginDataBaseAdapter.DATABASE_CREATE_COMPLAINT);
            _db.execSQL(LoginDataBaseAdapter.DATABASE_ASSIGN_COMPLAINT);
            //_db.execSQL("INSERT INTO A_LOGIN VALUES ('admin','admin')");

            ContentValues values = new ContentValues();
            values.put("USERNAME", "admin");
            values.put("PASSWORD", "admin");

            ContentValues eng1 = new ContentValues();
            eng1.put("EMPID", "CMS01");
            eng1.put("NAME", "Ramesh Khan");
            eng1.put("EMAILID", "ramesh@tcs.com");
            eng1.put("PASSWORD", "CMS01");
            eng1.put("DEPARTMENT", "Software");

            ContentValues eng2 = new ContentValues();
            eng2.put("EMPID", "CMS02");
            eng2.put("NAME", "Hina Sharma");
            eng2.put("EMAILID", "s_hina@tcs.com");
            eng2.put("PASSWORD", "CMS02");
            eng2.put("DEPARTMENT", "Software");

            ContentValues comp1 = new ContentValues();
            comp1.put("COMPLAINT_ID", "01");
            comp1.put("NAME", "Rahul");
            comp1.put("EMAILID", "r_ahul@gmail.com");
            comp1.put("COMPLAINT", "XYZ device is not working");
            comp1.put("CONTACT_NO", "9867896756");

// Inserting Row
            _db.insert("A_LOGIN", null, values);
            _db.insert("E_LOGIN", null, eng1);
            _db.insert("E_LOGIN", null, eng2);
            _db.insert("COMPLAINT_TABLE", null, comp1);
        }
        catch(SQLException e)
        {
             e.printStackTrace();
        }


    }
    // Called when there is a database version mismatch meaning that the version
    // of the database on disk needs to be upgraded to the current version.
    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion)
    {
        // Log the version upgrade.
        Log.w("TaskDBAdapter", "Upgrading from version " +_oldVersion + " to " +_newVersion + ", which will destroy all old data");

        // Upgrade the existing database to conform to the new version. Multiple
        // previous versions can be handled by comparing _oldVersion and _newVersion
        // values.
        // The simplest case is to drop the old table and create a new one.
        _db.execSQL("DROP TABLE IF EXISTS " + "LOGIN");
        _db.execSQL("DROP TABLE IF EXISTS " + "A_LOGIN");
        // Create a new one.
        onCreate(_db);
    }

}

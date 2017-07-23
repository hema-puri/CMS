package com.tcs.cmslogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class LoginDataBaseAdapter
{
    static final String DATABASE_NAME = "login.db";
    static final int DATABASE_VERSION = 12;
    public static final int NAME_COLUMN = 1;
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE = "create table "+"LOGIN"+
            "( " +"ID"+" integer primary key autoincrement,"+ "USERNAME  text,PASSWORD text); ";

    static final String DATABASE_CREATE_A = "create table "+"A_LOGIN"+
            "( " +"ID"+" integer primary key autoincrement,"+ "USERNAME  text,PASSWORD text) ";

    static final String DATABASE_CREATE_E = "create table "+"E_LOGIN"+
            "( " + "EMPID text primary key,NAME  text, EMAILID text,  PASSWORD text, DEPARTMENT text) ";

    static final String DATABASE_CREATE_COMPLAINT = "create table "+"COMPLAINT_TABLE"+
            "( " + "COMPLAINT_ID text primary key,NAME  text, EMAILID text,  COMPLAINT text, CONTACT_NO text) ";

    static final String DATABASE_ASSIGN_COMPLAINT = "create table "+"ASSIGN_COMPLAINT"+
            "( " +"COMPLAINT_ID"+" text primary key ,"+ "ENGINEER_ID  text) ";


    // Variable to hold the database instance
    public  SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private DataBaseHelper dbHelper;
    public  LoginDataBaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public  LoginDataBaseAdapter open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        db.close();
    }

    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    public void insertEntry(String userName,String password)
    {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("USERNAME", userName);
        newValues.put("PASSWORD",password);

        // Insert the row into your table
        db.insert("LOGIN", null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }

    public void insertEntryE(String empid,String name,String emailid,String pass,String dept)
    {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("NAME", name);
        newValues.put("EMAILID", emailid);
        newValues.put("EMPID", empid);
        newValues.put("PASSWORD",pass);
        newValues.put("DEPARTMENT", dept);

        // Insert the row into your table
        db.insert("E_LOGIN", null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }
    public int deleteEntry(String UserName)
    {
        //String id=String.valueOf(ID);
        String where="USERNAME=?";
        int numberOFEntriesDeleted= db.delete("LOGIN", where, new String[]{UserName}) ;
        // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }
    public String getSingleEntry(String userName)
    {
        Cursor cursor=db.query("LOGIN", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }


    /******** METHOD TO GET THE ID OF EMPLOYEE ***********/

    public ArrayList getEmpId()
    {
        db=dbHelper.getReadableDatabase();
        ArrayList<String> empid=new ArrayList<>();
        Cursor cursor;
        cursor = db.rawQuery("select EMPID,NAME,DEPARTMENT from E_LOGIN",null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return null;
        }
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            String id=cursor.getString(cursor.getColumnIndex("EMPID"));
             empid.add(id);
            cursor.moveToNext();
        }
        cursor.close();
        return empid;
    }

    /******** METHOD TO GET THE LIST OF EMPLOYEE ID ***********/

    public ArrayList getSelect_EmpId()
    {
        db=dbHelper.getReadableDatabase();
        ArrayList<String> empid=new ArrayList<>();
        empid.add("Select Employee ID");
        Cursor cursor;
        cursor = db.rawQuery("select EMPID from E_LOGIN",null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return null;
        }
        cursor.moveToFirst();
        for (int i = 1; i <= cursor.getCount(); i++) {
            String id=cursor.getString(cursor.getColumnIndex("EMPID"));
            empid.add(id);
            cursor.moveToNext();
        }
        cursor.close();
        return empid;
    }

    /******** METHOD TO GET THE ASSIGNED EMPLOYEE ID ***********/

    public ArrayList getAssigned_EmpId()
    {
        db=dbHelper.getReadableDatabase();
        ArrayList<String> empid=new ArrayList<>();
        Cursor cursor;
        cursor = db.rawQuery("select ENGINEER_ID from ASSIGN_COMPLAINT",null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return null;
        }
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            String id=cursor.getString(cursor.getColumnIndex("ENGINEER_ID"));
            empid.add(id);
            cursor.moveToNext();
        }
        cursor.close();
        return empid;
    }


    /******** METHOD TO GET THE NAME OF EMPLOYEE ***********/

    public ArrayList getEmpName()
{
    db=dbHelper.getReadableDatabase();
    ArrayList<String> emp_name=new ArrayList<>();
    Cursor cursor;
    cursor = db.rawQuery("select EMPID,NAME,DEPARTMENT from E_LOGIN",null);
    if(cursor.getCount()<1) // UserName Not Exist
    {
        cursor.close();
        return null;
    }
    cursor.moveToFirst();
    for (int i = 0; i < cursor.getCount(); i++) {
        String name=cursor.getString(cursor.getColumnIndex("NAME"));
        emp_name.add(name);
        cursor.moveToNext();
    }
    cursor.close();
    return emp_name;
}

    /******** METHOD TO GET THE DEPARTMENT OF EMPLOYEE ***********/

    public ArrayList<String> getEmpDept()
    {
        db=dbHelper.getReadableDatabase();
        ArrayList<String> emp_dept=new ArrayList<>();
        Cursor cursor;
        cursor = db.rawQuery("select DEPARTMENT from E_LOGIN",null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return null;
        }
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            String name=cursor.getString(cursor.getColumnIndex("DEPARTMENT"));
            emp_dept.add(name);
            cursor.moveToNext();
        }
        cursor.close();
        return emp_dept;
    }


    /******** METHOD TO GET THE COMPLAINT ID ***********/

public ArrayList<String> getCompId()
{
    db=dbHelper.getReadableDatabase();
    ArrayList<String> comp_id=new ArrayList<>();
    Cursor cursor;
    cursor = db.rawQuery("select COMPLAINT_ID from COMPLAINT_TABLE",null);
    if(cursor.getCount()<1) // UserName Not Exist
    {
        cursor.close();
        return null;
    }
    cursor.moveToFirst();
    for (int i = 0; i < cursor.getCount(); i++) {
        String name=cursor.getString(cursor.getColumnIndex("COMPLAINT_ID"));
        comp_id.add(name);
        cursor.moveToNext();
    }
    cursor.close();
    return comp_id;
}

    /******** METHOD TO GET THE COMPLAINT ID ***********/

    public ArrayList<String> getSelect_CompId()
    {
        db=dbHelper.getReadableDatabase();
        ArrayList<String> comp_id=new ArrayList<>();
        comp_id.add("Select Complaint ID");
        Cursor cursor;
        cursor = db.rawQuery("select COMPLAINT_ID from COMPLAINT_TABLE",null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return null;
        }
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            String name=cursor.getString(cursor.getColumnIndex("COMPLAINT_ID"));
            comp_id.add(name);
            cursor.moveToNext();
        }
        cursor.close();
        return comp_id;
    }

    /******** METHOD TO GET THE NAME OF COMPLAINEE ***********/

    public ArrayList getCompName()
    {
        db=dbHelper.getReadableDatabase();
        ArrayList<String> comp_name=new ArrayList<>();
        Cursor cursor;
        cursor = db.rawQuery("select NAME from COMPLAINT_TABLE",null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return null;
        }
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            String name=cursor.getString(cursor.getColumnIndex("NAME"));
            comp_name.add(name);
            cursor.moveToNext();
        }
        cursor.close();
        return comp_name;
    }

    /******** METHOD TO GET THE COMPLAINT ***********/

    public ArrayList getComplaint()
    {
        db=dbHelper.getReadableDatabase();
        ArrayList<String> complaint=new ArrayList<>();
        Cursor cursor;
        cursor = db.rawQuery("select COMPLAINT from COMPLAINT_TABLE",null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return null;
        }
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            String name=cursor.getString(cursor.getColumnIndex("COMPLAINT"));
            complaint.add(name);
            cursor.moveToNext();
        }
        cursor.close();
        return complaint;
    }

    /******** METHOD TO GET THE COMPLAINT ***********/

    public String getSelect_Complaint(String id)
    {
        db=dbHelper.getReadableDatabase();
        String complaint=new String();
        Cursor cursor;
        cursor=db.query("COMPLAINT_TABLE", null, " COMPLAINT_ID=?", new String[]{id}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return null;
        }
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            complaint=cursor.getString(cursor.getColumnIndex("COMPLAINT"));
            //complaint.add(name);
            cursor.moveToNext();
        }
        cursor.close();
        return complaint;
    }

    /******** METHOD TO ASSIGN THE COMPLAINT ***********/

    public void assignComplaint(String empid,String compid)
    {
        db=dbHelper.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put("COMPLAINT_ID",compid);
        values.put("ENGINEER_ID",empid);

        String where="COMPLAINT_ID = ?";
      //  db.update("ASSIGN_COMPLAINT",values, where, new String[]{compid});
        db.insert("ASSIGN_COMPLAINT", null, values);

    }

    /************** METHOD TO GET THE DETAILS OF ENGINEER FROM EMPLOYEE ID **********/
    public ArrayList getDetails(String id)
    {
        db=dbHelper.getReadableDatabase();
        ArrayList<String> emp_details=new ArrayList<>();
        Cursor cursor;
         cursor=db.query("E_LOGIN", null, " EMPID=?", new String[]{id}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return null;
        }
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            String name=cursor.getString(cursor.getColumnIndex("NAME"));
            emp_details.add(name);
            String dept=cursor.getString(cursor.getColumnIndex("DEPARTMENT"));
            emp_details.add(dept);
            String emailid=cursor.getString(cursor.getColumnIndex("EMAILID"));
            emp_details.add(emailid);
            cursor.moveToNext();
        }
        cursor.close();
        return emp_details;
    }


    public String getSingleEntryA(String userName)
    {
        Cursor cursor=db.query("A_LOGIN", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }

    public String getSingleEntryE(String userName)
    {
        Cursor cursor=db.query("E_LOGIN", null, " EMPID=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }


    public void  updateEntry(String userName,String password)
    {
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        updatedValues.put("USERNAME", userName);
        updatedValues.put("PASSWORD",password);

        String where="USERNAME = ?";
        db.update("LOGIN",updatedValues, where, new String[]{userName});
    }

    public void  updateEntryA(String password)
    {
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        //updatedValues.put("USERNAME", userName);
        updatedValues.put("PASSWORD",password);

        String where="USERNAME = ?";
        db.update("A_LOGIN",updatedValues, where, new String[]{"admin"});
    }

    public void  updateEntryE(String id,String name,String emailid,String department)
    {
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        updatedValues.put("NAME", name);
        updatedValues.put("EMAILID",emailid);
        updatedValues.put("DEPARTMENT",department);

        String where="EMPID = ?";
        db.update("E_LOGIN",updatedValues, where, new String[]{id});
    }

    public void updateEntryU(String name, String newpassword) {
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
//        updatedValues.put("USERNAME", userName);
        updatedValues.put("USERNAME", name);

        updatedValues.put("PASSWORD", newpassword);

        String where = "USERNAME = ?";
        db.update("LOGIN", updatedValues, where, new String[]{name});
    }

}
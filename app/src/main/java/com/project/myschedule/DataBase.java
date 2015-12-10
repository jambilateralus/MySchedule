package com.project.myschedule;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

/**
 * Created by sushil on 10/25/15.
 */
public class DataBase {
    //Private variables and constant variables for schedule table
    public static  final String KEY_ROWID = "schedule_id";
    public static  final String KEY_TITTLE = "schedule_title";
    public static  final String KEY_FROM = "from";
    public static  final String KEY_TILL = "till";

    //variables for database
    private static final String DATABASE_NAME ="MySchedule";
    private static final String DATABASE_TABLE ="schedule";
    private static final int DATABASE_VERSION =1;

   //instances of DbHelper class and variables
    private DbHelper ourHelper;
    private Context ourContext;
    private SQLiteDatabase ourDatabase;


//private class for sqlite opertion ---like a mediator
private static class DbHelper extends SQLiteOpenHelper{

    //DbHelper constructor for database setting
    public DbHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //method to create database for first time
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " +DATABASE_TABLE + " (" +
                               KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                               KEY_TITTLE +" TEXT NOT NULL, "+
                               KEY_FROM +" DATE NOT NULL, "+
                               KEY_TILL +" DATE NOT NULL);"
        );

    }

    //if already have DAtabase in system
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +DATABASE_TABLE);
        onCreate(sqLiteDatabase);

    }
} //end DbHelper

    //constructor
    public DataBase(Context c){
        ourContext =c;

    }

    //open conection for database
    public DataBase open(){
        ourHelper = new DbHelper(ourContext);
        ourDatabase =ourHelper.getWritableDatabase();
        return this;
    }

    //close conection
    public void close(){
        ourHelper.close();
    }

    //insert query
    public long addSchedule(String title, Date from, Date till){
        ContentValues cv = new ContentValues();
        cv.put(KEY_TITTLE,title);
        cv.put(KEY_FROM, String.valueOf(from));
        cv.put(KEY_TILL, String.valueOf(till));
        return ourDatabase.insert(DATABASE_TABLE,null,cv);

    }

    //view querry
    public String getSchedule(){
        String[] columns = new String[]{KEY_ROWID, KEY_TITTLE, KEY_FROM, KEY_TILL};
        Cursor c = ourDatabase.query(DATABASE_TABLE,columns,null,null,null,null,null);
        String result= "";

        int iRow =c.getColumnIndex(KEY_ROWID);
        int iTitle =c.getColumnIndex(KEY_TITTLE);
        int iFrom =c.getColumnIndex(KEY_FROM);
        int iTill =c.getColumnIndex(KEY_TILL);

        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            result = result + c.getString(iRow) +" "+ c.getString(iTitle)+" "+c.getString(iFrom)+" "+c.getString(iTill)+"\n";
        }


        return result;
    }

}//end DataBAse

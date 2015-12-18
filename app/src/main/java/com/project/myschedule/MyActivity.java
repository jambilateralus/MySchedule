package com.project.myschedule;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);


        ListView lv = (ListView) findViewById(R.id.listView);
        DataBase db = new DataBase(this);
        db.open();
        String[] scheduleTile = new String[db.getScheduleCount()];
        String[] scheduleTo = new String[db.getScheduleCount()];
        String[] scheduleFrom = new String[db.getScheduleCount()];


       //Load database contents to arrays
        for (int i = 0; i < db.getScheduleCount(); i++) {
            scheduleTile[i] = db.getScheduleTitle(i);
            scheduleTo[i] = db.getScheduleToDate(i);
            scheduleFrom[i] = db.getScheduleFromDate(i);
        }
        db.close();



        //set custom adapter to list view
        ScheduleAdapter adpt = new ScheduleAdapter(this,scheduleTile,scheduleTo,scheduleFrom);
        lv.setAdapter(adpt);




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent add = new Intent("com.project.myschedule.ADD");

        //creating intent for activity to be done
        Intent cadd = new Intent("android.intent.action.CalenderView");

        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_add) {

            startActivity(add);
        } else if (id == R.id.action_calender) {

            startActivity(cadd);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //finish();
    }
}


package com.project.myschedule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        TextView tv = (TextView)findViewById(R.id.tvScheduleView);
        DataBase view = new DataBase(this);
        view.open();
        String data = view.getSchedule();
        view.close();
        tv.setText(data);

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
        }else if(id==R.id.action_add){

            startActivity(add);
        }
        else if(id==R.id.action_calender){

            startActivity(cadd);
        }
        return super.onOptionsItemSelected(item);
    }
}

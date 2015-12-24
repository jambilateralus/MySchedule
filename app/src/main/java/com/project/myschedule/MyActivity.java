package com.project.myschedule;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;


public class MyActivity extends Activity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);





        //ListView lv = (ListView) findViewById(R.id.listView);
        DataBase db = new DataBase(this);
        db.open();
        final String[] scheduleTitle = new String[db.getScheduleCount()];
        String[] scheduleTo = new String[db.getScheduleCount()];
        String[] scheduleFrom = new String[db.getScheduleCount()];


        ArrayList<String> list = new ArrayList<String>();
        //Load database contents to arrays
        for (int i = 0; i < db.getScheduleCount(); i++) {
            scheduleTitle[i] = db.getScheduleTitle(i);
            scheduleTo[i] = db.getScheduleToDate(i);
            scheduleFrom[i] = db.getScheduleFromDate(i);
            list.add(db.getScheduleTitle(i));
        }
        db.close();


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapter(getDataSet());
        mRecyclerView.setAdapter(mAdapter);

        //set custom adapter to list view
        //ScheduleAdapter adpt = new ScheduleAdapter(this, scheduleTitle, scheduleTo, scheduleFrom);
        //lv.setAdapter(adpt);

        //lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          //  @Override
            //public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getBaseContext(), "You have selected item : " + scheduleTitle[position], Toast.LENGTH_SHORT).show();

              //  Intent taskList = new Intent(view.getContext(),TaskListActivity.class);

                //taskList.putExtra("hello",scheduleTitle[position]);

                //startActivity(taskList);

            //}
        //});


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
    protected void onResume() {
        super.onResume();
        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, " Clicked on Item " + position);
            }
        });
    }

    private ArrayList<DataObject> getDataSet() {
        ArrayList results = new ArrayList<DataObject>();
        for (int index = 0; index < 20; index++) {
            DataObject obj = new DataObject("Some Primary Text " + index,
                    "Secondary " + index);
            results.add(index, obj);
        }
        return results;
    }

}
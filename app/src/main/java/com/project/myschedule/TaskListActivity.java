package com.project.myschedule;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class TaskListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);


        /////
        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText(this.getIntent().getStringExtra("index"));

    }


    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}

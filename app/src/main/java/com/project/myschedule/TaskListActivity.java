package com.project.myschedule;

import android.os.Bundle;
import android.app.Activity;

public class TaskListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
    }


    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}

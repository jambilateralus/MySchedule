package com.project.myschedule;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;


public class Add extends Activity {

    static final int FROM_DATE_DIALOG_ID = 0;
    static final int TILL_DATE_DIALOG_ID = 1;

    //Variables to store user selected date.
    public int year,month,day;

    //Variables to store date when it appears first
    private int mYear, mMonth, mDay;

    //From and till buttons
    Button fButton;
    Button tButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //Assign current date to variables.
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        //Get references of buttons
        fButton= (Button) findViewById(R.id.from_button);
        tButton = (Button) findViewById(R.id.till_button);

        //Set label of buttons to current date
        fButton.setText(mYear+"/"+mMonth+"/"+mDay);
        tButton.setText(mYear+"/"+mMonth+"/"+mDay);

        //Set click listener on fButton
        fButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(FROM_DATE_DIALOG_ID);

            }
        });

        //Set click listener on tButton
        tButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(TILL_DATE_DIALOG_ID);

            }
        });
    }

    // Register  FromDatePickerDialog listener
    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                // the callback received when the user "sets" the Date in the DatePickerDialog
                public void onDateSet(DatePicker view, int yearSelected,
                                      int monthOfYear, int dayOfMonth) {
                    year = yearSelected;
                    month = monthOfYear;
                    day = dayOfMonth;
                    // Set the Selected Date in Select date Button
                    fButton.setText(day + "/" + month + "/" + year);
                }
            };

    // Register  TillDatePickerDialog listener
    private DatePickerDialog.OnDateSetListener nDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                // the callback received when the user "sets" the Date in the DatePickerDialog
                public void onDateSet(DatePicker view, int yearSelected,
                                      int monthOfYear, int dayOfMonth) {
                    year = yearSelected;
                    month = monthOfYear;
                    day = dayOfMonth;
                    // Set the Selected Date in Select date Button
                    tButton.setText(day + "/" + month + "/" + year);
                }
            };


    // Method automatically gets Called when you call showDialog()  method
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case FROM_DATE_DIALOG_ID:
                // create a new FromDatePickerDialog with values you want to show
                return new DatePickerDialog(this,
                        mDateSetListener,
                        mYear, mMonth, mDay);
            // create a new TillDatePickerDialog with values you want to show
            case  TILL_DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        nDateSetListener,
                        mYear, mMonth, mDay);
        }
        return null;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
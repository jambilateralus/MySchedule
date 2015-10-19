package com.project.myschedule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;


public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        //Wait for few seconds and open MyActivity
        Thread timer = new Thread(){

            public void run(){
                try {
                    sleep(2500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    Intent openStartingPoint = new Intent("com.project.myschedule.MYACTIVITY");
                    startActivity(openStartingPoint);
                }
            }
        };
        timer.start();

    }

    //Remove splash activity from memory
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}

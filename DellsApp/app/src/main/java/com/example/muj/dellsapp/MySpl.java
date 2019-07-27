package com.example.muj.dellsapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import static com.example.muj.dellsapp.R.layout.activity_my_spl;

/**
 * Created by MUJ on 8/21/2017.
 */

public class MySpl extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(activity_my_spl);


        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent i = new Intent(MySpl.this, MainActivity.class); // getActionBar().show();
                startActivity(i);
                finish();
            }
        },900);//950

    }
}
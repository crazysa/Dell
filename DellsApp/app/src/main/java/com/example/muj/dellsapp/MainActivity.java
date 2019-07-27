package com.example.muj.dellsapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final String MyPREFERENCES = "MyPrefs";                            // 1.186.164.190   10.169.0.206  192.168.43.202  0f318790.ngrok.io
    public static final String key = "MyKey",key2="h",key3="hi",key4="ho";
    public static String last="", keyofip = "192.168.43.202:7007";
    public static int point = 0, dia = 0;
    EditText eotp=null; WebView w;
    SharedPreferences sharedpreferences;
    public static DrawerLayout drawer1;
    AlertDialog alert1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedpreferences = this.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        drawer1 = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer1, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer1.setDrawerListener(toggle);
        toggle.syncState();
        drawer1.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        last = sharedpreferences.getString("key2", "");

        w=(WebView)findViewById(R.id.webp);
        w.setWebViewClient(new MyBrowser());

//jabri
        if (sharedpreferences.getBoolean("key", true) || last.length() <1) {

            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putBoolean("key", false);
            editor.commit();

            FragmentTransaction obj = getSupportFragmentManager().beginTransaction();
            obj.replace(R.id.container, new UserEntryOneTime());
            obj.commit();
        } else {
            FragmentTransaction obj2 = getSupportFragmentManager().beginTransaction();
            obj2.replace(R.id.container, new Frag0());
            obj2.commit();
        }
    }

    public void store() {
        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor = sharedpreferences.edit();
        editor.putString("key2", last);
        editor.commit();
    }



    public void onBackPressed() {

        if (point == 1) {

            point = 0;
            dia = 0;
            FragmentTransaction obj5 = getSupportFragmentManager().beginTransaction();
            obj5.replace(R.id.container, new Frag0());
            obj5.commit();
        } else {
            AlertDialog.Builder obj = new AlertDialog.Builder(this).setMessage("Are you sure you want to exit").setCancelable(true)
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            // TODO Auto-generated method stub
                            finish();

                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            // TODO Auto-generated method stub

                        }
                    });

            AlertDialog alert = obj.create();
            alert.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.share)

        {
            Intent i = new Intent(android.content.Intent.ACTION_SEND);
            i.setType("text/plain");
            String shareBody = "Download Dells App from google play:";
            i.putExtra(Intent.EXTRA_SUBJECT, "ALPHABETS");
            i.putExtra(Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(i, "Share with your friends..."));

        }
        return true;


    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.one) {

            /////////////////////
try{
            eotp=new EditText(this);}
   catch (Exception e){}

            AlertDialog.Builder obj = new AlertDialog.Builder(this).setTitle("System Database Name").setCancelable(true)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            // TODO Auto-generated method stub
                            w.loadUrl("http://" + keyofip  +  "/db_name.php?id=" +  eotp.getText().toString());

                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                                    // TODO Auto-generated method stub
                               // finish();
                        }
                    });

            AlertDialog alertx = obj.create();
            try{alertx.setView(eotp);} catch(Exception e){}
            alertx.show();

        }

        else if (id == R.id.two) {
            FragmentTransaction obj11 = getSupportFragmentManager().beginTransaction();
            obj11.replace(R.id.container, new Two());
            obj11.commit();
        }

        else if (id == R.id.three) {
            FragmentTransaction obj12 = getSupportFragmentManager().beginTransaction();
            obj12.replace(R.id.container, new Three());
            obj12.commit();
        }

         else if(id == R.id.four) {
            FragmentTransaction obj13 = getSupportFragmentManager().beginTransaction();
            obj13.replace(R.id.container, new Four());
            obj13.commit();
        }

        else if(id == R.id.five) {
            w.loadUrl("http://" + keyofip  +  "/graph_age.py");
        }


        else if(id == R.id.six) {
            w.loadUrl("http://" + keyofip  +  "/graph_service.py");
        }


        else if(id == R.id.seven) {
            w.loadUrl("http://" + keyofip  +  "/graph_gender.py");
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class MyBrowser extends WebViewClient
    {
        public boolean shouldOverrideUrlLoading(WebView view, String urL) {
            view.loadUrl(urL); return true;
        }
        public void onPageFinished(WebView view,String url){
            super.onPageFinished(view,url);
        }

        public void onPageStarted(WebView view, String urL, Bitmap favicon) {
            super.onPageStarted(view,urL,favicon);
        }

    }

}

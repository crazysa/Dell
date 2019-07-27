package com.example.muj.dellsapp;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserEntryOneTime extends Fragment implements View.OnClickListener {
    Button b;
    View view,viewed;
    EditText e1,e2; int ct=0;
    String name="", num = "";  AlertDialog alert1=null,alert2=null;
    LinearLayout.LayoutParams params1=null,params2=null;
    private static final int REQUEST_PHONE_SMS = 1,REQUEST_PHONE_CALL = 1;
    public static int APP_REQUEST_CODE = 99;
    Context mAppContext;
    ProgressDialog obj;
    WebView po;
    public UserEntryOneTime() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        view = inflater.inflate(R.layout.fragment_user_entry_one_time, container, false);
       // po=(WebView) view.findViewById(R.id.web34);
        b = (Button) view.findViewById(R.id.contin);
        e1 = (EditText) view.findViewById(R.id.et1);
        e2 = (EditText) view.findViewById(R.id.et2);


        b.setOnClickListener(this);
        return view;
    }



    public void onClick(View v) {
        name = e1.getText().toString().trim();
        num = e2.getText().toString().trim();
       // sh1.loadUrl(((MainActivity)getActivity()).keyofip  +  "/temp1.php?id1=" + name + "&id2=" + num);
        if (name.isEmpty() || num.isEmpty())
            Toast.makeText(getContext(), "Entries Missing", Toast.LENGTH_SHORT).show();

        else {
         //   po.loadUrl("http://" + (((MainActivity)getActivity()).keyofip ) +  "/temp1.php?id1=" + name + "&id2=" + num);
            obj = new ProgressDialog(getContext());
            obj.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            obj.setProgressStyle(Gravity.CENTER);
            obj.setCancelable(false);
            obj.show();
            put();
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    obj.hide();
                }
            }, 1000);

        }
    }



    //////////////////////

    private void put(){

        try{

            getJSON("http://" +  ((MainActivity)getActivity()).keyofip  +  "/user_auth.php?id1=" + name + "&id2=" + num);
        }
        catch(Exception e) {
        }

    }




    private void getJSON(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                try{
                    super.onPreExecute();}
                catch(Exception e){}
            }


            @Override
            protected void onPostExecute(String s) {
                try{
                    super.onPostExecute(s);}
                catch(Exception e){}
                //  Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        try{
            GetJSON getJSON = new GetJSON();
            getJSON.execute();}
        catch(Exception e){}
    }



    private void loadIntoListView(String json) throws JSONException {
        String[] heroes=new String[0];
        try{
            // if(json.length()!=0){
            JSONArray jsonArray = new JSONArray(json);
            heroes = new String[jsonArray.length()];//}

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                heroes[i] = obj.getString("id");
            }


            //Toast.makeText(getContext(), ret[0], Toast.LENGTH_SHORT).show();


        }
        catch(Exception e) {
        }        //    Toast.makeText(getContext(), heroes[0], Toast.LENGTH_SHORT).show();
        try{

            if(heroes[0]==""||heroes[0]==null||heroes.length==0||heroes==null)
            {
                Toast.makeText(getContext(),"User Authentication failed", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getContext(),"Successful Authentication", Toast.LENGTH_SHORT).show();
                ((MainActivity) getActivity()).last = heroes[0];
                ((MainActivity) getActivity()).store();}
            FragmentTransaction obj6 = getFragmentManager().beginTransaction();
            obj6.replace(R.id.container, new Frag0());
            obj6.commit();
        }
        catch(Exception e){ Toast.makeText(getContext(),"User Authentication Failed", Toast.LENGTH_SHORT).show();}
    }



    ///////////
}



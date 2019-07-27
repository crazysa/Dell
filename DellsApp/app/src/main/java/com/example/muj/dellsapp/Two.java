package com.example.muj.dellsapp;


import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.muj.dellsapp.R.id.s;


/**
 * A simple {@link Fragment} subclass.
 */
public class Two extends Fragment implements View.OnClickListener , AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    View view;WebView w2;  Button b1; TextView u00,u0,u1,u2,u3,u4,u5,u6,u7,u8,u9;
    TextView j1,j2,j3,j4,j5,j6,j7,j8,j9,j10,j11,j12,j13,j14,j15,j16,j17,j18;
    private Spinner spinner;
    String ent="";
    String ans1,ans2,ans3,ans4,ans5,ans6,ans7,ans8,ans9,ans10,ans11,ans12,ans13,ans14,ans15,ans16,ans17,ans18;

    private static final String[]paths = {"Alienware 15", "Alienware 17", "Dell 22 Monitor - P2219H", "Dell PowerEdge R440 Rack Server","Dell PowerEdge T30 Server"};
    ProgressDialog obj;
    public Two() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        (((MainActivity) getActivity()).point) = 1;
        view =inflater.inflate(R.layout.fragment_two, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        w2=(WebView) view.findViewById(R.id.showt);
        b1=(Button) view.findViewById(s);
        u00=(TextView) view.findViewById(R.id.g11);
        u0=(TextView) view.findViewById(R.id.g0);
        u1=(TextView) view.findViewById(R.id.g1);
        u2=(TextView) view.findViewById(R.id.g2);
        u3=(TextView) view.findViewById(R.id.g3);
        u4=(TextView) view.findViewById(R.id.g4);
        u5=(TextView) view.findViewById(R.id.g5);
        u6=(TextView) view.findViewById(R.id.g6);
        u7=(TextView) view.findViewById(R.id.g7);
        u8=(TextView) view.findViewById(R.id.g8);
        u9=(TextView) view.findViewById(R.id.g9);



        u00.setVisibility(View.INVISIBLE);
        u0.setVisibility(View.INVISIBLE);
        u1.setVisibility(View.INVISIBLE);
        u2.setVisibility(View.INVISIBLE);
        u3.setVisibility(View.INVISIBLE);
        u4.setVisibility(View.INVISIBLE);
        u5.setVisibility(View.INVISIBLE);
        u6.setVisibility(View.INVISIBLE);
        u7.setVisibility(View.INVISIBLE);
        u8.setVisibility(View.INVISIBLE);
        u9.setVisibility(View.INVISIBLE);

        spinner = (Spinner)view.findViewById(R.id.veh);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        b1.setOnClickListener(this);

        w2.setWebViewClient(new MyBrowser());

        return view;
    }

    public void onClick(View v) {
        //do what you want to do when button is clicked
        switch (v.getId()) {
            case s:

                if(ent.length()!=0)
                {
//                    try{
//                        getJSON("http://" + ((MainActivity)getActivity()).keyofip  +  "/prod_dem.py?pro=" + ent);}
//                    catch(Exception e) {}


                    obj = new ProgressDialog(getContext());
                    obj.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    obj.setProgressStyle(Gravity.CENTER);
                    obj.setCancelable(false);
                    obj.show();
                    new Handler().postDelayed(new Runnable() {
                        public void run() {

                            u00.setVisibility(View.VISIBLE);
                            u0.setVisibility(View.VISIBLE);
                            u1.setVisibility(View.VISIBLE);
                            u2.setVisibility(View.VISIBLE);
                            u3.setVisibility(View.VISIBLE);
                            u4.setVisibility(View.VISIBLE);
                            u5.setVisibility(View.VISIBLE);
                            u6.setVisibility(View.VISIBLE);
                            u7.setVisibility(View.VISIBLE);
                            u8.setVisibility(View.VISIBLE);
                            u9.setVisibility(View.VISIBLE);

w2.loadUrl("http://" + ((MainActivity)getActivity()).keyofip  +  "/prod_dem.py?pro=" + ent);

                            obj.hide();

                        }

                    }, 2500);
                    break;}
                else
                {
                    Toast.makeText(
                            getContext(),
                            "Select Product",
                            Toast.LENGTH_SHORT)
                            .show();
                }

        }
    }


    ///////////////////


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

        try{
            // if(json.length()!=0){
            Toast.makeText(getContext(),json, Toast.LENGTH_SHORT).show();
            JSONArray jsonArray = new JSONArray(json);
            Toast.makeText(getContext(),"kk", Toast.LENGTH_SHORT).show();
            JSONObject obj1 = jsonArray.getJSONObject(0);
            ans1 = obj1.getString("l1");

            Toast.makeText(getContext(),ans1, Toast.LENGTH_SHORT).show();
            JSONObject obj2 = jsonArray.getJSONObject(1);
            ans2 = obj2.getString("l2");

            JSONObject obj3 = jsonArray.getJSONObject(2);
            ans3 = obj3.getString("l3");

            JSONObject obj4 = jsonArray.getJSONObject(3);
            ans4 = obj4.getString("l4");

            JSONObject obj5 = jsonArray.getJSONObject(4);
            ans5 = obj5.getString("l5");

            JSONObject obj6 = jsonArray.getJSONObject(5);
            ans6 = obj6.getString("l6");

            JSONObject obj7 = jsonArray.getJSONObject(6);
            ans7 = obj7.getString("l7");

            JSONObject obj8 = jsonArray.getJSONObject(7);
            ans8 = obj8.getString("l8");

            JSONObject obj9 = jsonArray.getJSONObject(8);
            ans9 = obj9.getString("l9");

            JSONObject obj10 = jsonArray.getJSONObject(9);
            ans10 = obj10.getString("l10");

            JSONObject obj11 = jsonArray.getJSONObject(10);
            ans11 = obj11.getString("l11");

            JSONObject obj12 = jsonArray.getJSONObject(11);
            ans12 = obj12.getString("l12");

            JSONObject obj13 = jsonArray.getJSONObject(12);
            ans13 = obj13.getString("l13");

            JSONObject obj14 = jsonArray.getJSONObject(13);
            ans14 = obj14.getString("l14");

            JSONObject obj15 = jsonArray.getJSONObject(14);
            ans15 = obj15.getString("l15");

            JSONObject obj16 = jsonArray.getJSONObject(15);
            ans16 = obj16.getString("l16");

            JSONObject obj17 = jsonArray.getJSONObject(16);
            ans17 = obj17.getString("l17");

            JSONObject obj18 = jsonArray.getJSONObject(17);
            ans18 = obj18.getString("l18");

            //Toast.makeText(getContext(), heroes[0], Toast.LENGTH_SHORT).show();


        }
        catch(Exception e) {

        }        //    Toast.makeText(getContext(), heroes[0], Toast.LENGTH_SHORT).show();

    }



    ///////////////////




    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {}
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
        switch (pos) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                ent="Alienware 15";
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                ent="Alienware 17";
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                ent="Dell 22 Monitor - P2219H";
                break;
            case 3:
                // Whatever you want to happen when the thrid item gets selected
                ent="Dell PowerEdge R440 Rack Server";
                break;
            case 4:
                // Whatever you want to happen when the thrid item gets selected
                ent="Dell PowerEdge T30 Server";
                break;

        }
    }


}

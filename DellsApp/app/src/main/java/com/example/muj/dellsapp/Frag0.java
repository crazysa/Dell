package com.example.muj.dellsapp;


import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Frag0 extends Fragment {
    View view;
    Button b1, b2, b3;
    WebView w;
    String url = "";
    ImageView b0;
    TextView tx;
    ProgressDialog obj, obj2;
    AlertDialog.Builder objx;

    public Frag0() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        (((MainActivity) getActivity()).drawer1).setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        view = inflater.inflate(R.layout.fragment_frag0, container, false);

        tx = (TextView) view.findViewById(R.id.t);
        tx.setText(((MainActivity)getActivity()).last);
        return view;
    }

}
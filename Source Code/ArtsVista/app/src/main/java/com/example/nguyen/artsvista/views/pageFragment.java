package com.example.nguyen.artsvista.views;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguyen.artsvista.R;

/**
 * Created by rythxms on 19/10/15.
 */
public class pageFragment extends android.support.v4.app.Fragment implements View.OnClickListener{
    //THE FRAGMENTS FOR THE INTRO LAUNCH PAGES WHICH APPEAR ON THE FIRST LAUNCH
    private Bundle bundle;
    private TextView circle;
    private String data;
    private TextView textdata;
    private ImageView imagebutton;
    private ImageView background;
    private Typeface font;
    private CheckBox cb;
    private TextView tos;

    public pageFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_page_fragment, container, false);
        bundle = getArguments();
        //circle text for page indicators
        circle = (TextView)view.findViewById(R.id.circle_launch);
        textdata = (TextView)view.findViewById(R.id.launch_Text);
        //button on final page to go to the main app
        imagebutton = (ImageView)view.findViewById(R.id.imageButton);
        background =(ImageView)view.findViewById(R.id.imageView);
        font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/tg20b.otf");
        textdata.setTypeface(font);

        //textdata.setTextSize(10 * getResources().getDisplayMetrics().density);
        //circle.setTextSize(20 * getResources().getDisplayMetrics().density);

        cb = (CheckBox)view.findViewById(R.id.checkBox);
        tos = (TextView)view.findViewById(R.id.tosText);
        imagebutton.setOnClickListener(this);
        tos.setOnClickListener(this);
        check_page(bundle.getInt("count"), view);
        return view;
    }

    //cases to determine what to display based on what page you are on
    public void check_page(int n, View v){
        switch(n) {
            case 1:data = "<font color='#CACDCC'>&#8226;</font><font color='#FFFFFF'>&#8226;&#8226;&#8226;</font>";
                circle.setText(Html.fromHtml(data));
                textdata.setText("Discover cultural delights \naround the world");
               background.setImageResource(R.drawable.db);

                break;
            case 2:data = "<font color='#FFFFFF'>&#8226;</font><font color='#CACDCC'>&#8226;</font>"
                    + "<font color='#FFFFFF'>&#8226;&#8226;</font>";
                circle.setText(Html.fromHtml(data));
                textdata.setText("Meet amazing artists & \npresenters near you");
                background.setImageResource(R.drawable.v);
                break;
            case 3:data = "<font color='#FFFFFF'>&#8226;&#8226;</font><font color='#CACDCC'>&#8226;</font>"
                    + "<font color='#FFFFFF'>&#8226;</font>";
                circle.setText(Html.fromHtml(data));
                textdata.setText("Find the best events & \nperformances in the city");
                background.setImageResource(R.drawable.v2);
                break;
            case 4:data = "<font color='#FFFFFF'>&#8226;&#8226;&#8226;</font>"
                    + "<font color='#CACDCC'>&#8226;</font>";
                circle.setText(Html.fromHtml(data));
                textdata.setText("");
                textdata = (TextView)v.findViewById(R.id.last_launch_text);
                textdata.setText("Arts Vista curates over \nthousands of cultural delights \naround your city");
                textdata.setTypeface(font);

                cb.setVisibility(View.VISIBLE);
                tos.setVisibility(View.VISIBLE);
                cb.setTypeface(font);
                tos.setTypeface(font);
                imagebutton.setImageResource(R.drawable.startbutton3);
                double width = this.getResources().getDisplayMetrics().widthPixels/1.2  ;
                imagebutton.setMaxWidth((int)width);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) imagebutton.getLayoutParams();
                params.width = (int)width;
                params.height = (int)width / 4;
                imagebutton.setLayoutParams(params);
                //imagebutton.getLayoutParams().width = -1750 / imagebutton.getLayoutParams().width;
                //imagebutton.getLayoutParams().height = imagebutton.getLayoutParams().height * -100;
                background.setImageResource(R.drawable.image4);
                break;
            default:circle.setText("Blank");
                textdata.setText("Blank");
                break;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageButton:
                if(cb.isChecked()){
                    Intent intent = new Intent(getActivity(), discovery.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getActivity(), "You must agree to the terms of service to continue", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tosText:
                Intent intent = new Intent(getActivity(), tos.class);
                startActivity(intent);
                break;
        }
    }
}

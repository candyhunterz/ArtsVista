package com.example.nguyen.artsvista.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.nguyen.artsvista.R;


public class LaunchScreen extends AppCompatActivity {
    //THE FIRST ACTIVITY, WILL SKIP TO THE DISCOVERY PAGE IF USER HAS LAUNCHED THE APP BEFORE

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        if (!isFirstRun) {
            Intent intent = new Intent(this, discovery.class);
            startActivity(intent);
       }

        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).commit();
        setContentView(R.layout.activity_launch_screen);
        viewPager =(ViewPager)findViewById(R.id.pager);
        SwipeAdapter sa = new SwipeAdapter(getSupportFragmentManager());
        viewPager.setAdapter(sa);

    }
}

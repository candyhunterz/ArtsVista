package com.example.nguyen.artsvista.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguyen.artsvista.R;


public class discovery extends AppCompatActivity {
    //DISCOVERY PAGE THAT SHOWS THE LIST OF CITIES, THIS IS THE FIRST PAGE THE USER SEES AFTER THE INTRODUCTORY PAGES
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discovery);

        /*SearchView s = (SearchView) findViewById(R.id.persistantSearch);
        s.setIconifiedByDefault(false);
        s.bringToFront();
        s.setBackgroundColor(Color.WHITE);*/

        TextView textdata = (TextView)this.findViewById(R.id.txtVan);
        TextView textdata2 = (TextView)this.findViewById(R.id.txtVic);
        Typeface font = Typeface.createFromAsset(this.getAssets(), "fonts/tg20b.otf");
        textdata.setTypeface(font);
        textdata2.setTypeface(font);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_discovery, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void clickVancouver(View v){

        //Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, EventList.class);
        startActivity(i);
    }

    public void clickVictoria(View v){
        Toast.makeText(this, "Coming Soon!", Toast.LENGTH_SHORT).show();
    }


    private void doExit() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                discovery.this);

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                moveTaskToBack(true);
            }
        });

        alertDialog.setNegativeButton("No", null);
        alertDialog.setMessage("Do you want to exit the application?");
        alertDialog.setTitle("Warning");
        alertDialog.show();
    }

    /*@Override
    public void onBackPressed() {
        doExit();
    }*/
}

package com.example.nguyen.artsvista.views;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.nguyen.artsvista.R;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class eDetail extends AppCompatActivity {

    //THIS IS THE EVENT DETAILS PAGE, USE THIS INSTEAD OF THE EventDetail CLASS
    Typeface font, font2;
    TextView eventTitle, separator0, eventDate, eventLocation, eventAddress,
            separator, eventDescription, getdir, buyTicket;
    String id = "";
    String title = "";
    String video = "";
    String description = "";
    String vendor_name = "";
    String vendor_address = "";
    String start_date = "";
    Double latitude = 0.0;
    Double longitude = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edetail);
        WebView wb = (WebView)findViewById(R.id.webView);
        WebSettings webSettings = wb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        font  = Typeface.createFromAsset(this.getAssets(), "fonts/tg20b.otf");
        font2  = Typeface.createFromAsset(this.getAssets(), "fonts/tg18c.otf");

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        //int width = wb.getLayoutParams().width;
        //int height = wb.getLayoutParams().height;
        int width = this.getResources().getDisplayMetrics().widthPixels/3;
        int height = (size.y)/9;

        Intent intent = getIntent();
        if (intent != null) {
            Bundle b = getIntent().getExtras();
            id = intent.getStringExtra("ImageEvent_id");
            title = intent.getStringExtra("title");
            video = intent.getStringExtra("video");
            description = intent.getStringExtra("description");
            description = description.replaceAll("\\s+", " ");          //remove consecutive spaces in description
            vendor_name = intent.getStringExtra("vendor_name");
            vendor_address = intent.getStringExtra("vendor_address");
            start_date = intent.getStringExtra("start_date");
            latitude = intent.getDoubleExtra("latitude", 0.0);
            longitude = intent.getDoubleExtra("longitude", 0.0);

        }
        //test.setText(video);

        if(checkYoutube(video)){
            String pattern = "(?<=watch\\?v=|/videos/|youtu.be/|&v=|embed/)[^#&?]*";
            String video_id = "test";
            Pattern compiledPattern = Pattern.compile(pattern);
            Matcher matcher = compiledPattern.matcher(video);

            if(matcher.find()){
                video_id= matcher.group();
            }

            String youtube= "<html><head><style>* {margin:0;padding:0;}</style></\n" +
                    "head><body><iframe width=\""+ width +"\" height=\""+height+"\"" +
                    " src=\"https://www.youtube.com/embed/"+video_id+"\" frameborder=\"0\" allowfullscreen></iframe></body></html>";
            wb.loadData(youtube, "text/html", "utf-8");
        }
        else if(checkVimeo(video)){
            int number = 0;
            for (int i=0; i < video.length(); i++) {
                char c = video.charAt(i);
                if (c < '0' || c > '9')
                    continue;
                number = number * 10 + (c - '0');
            }
            String vimeo ="<html><head><style>* {margin:0;padding:0;}</style></\n" +
                    "head><body><iframe src=\"https://player.vimeo.com/video/"+number+"\" width=\""+width+"\" " +
                    "height=\""+height+"\" frameborder=\"0\" webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe> " +
                    "<p><a href=\"https://vimeo.com/"+number+"\"></a></p></body></html>";

            wb.loadData(vimeo, "text/html", "utf-8");
        }


        populate(title,
                "________",
                "Starts: " + start_date,
                "Location: " + vendor_name,
                vendor_address,
                "________",
                description );
    }

    public void populate(String eTitle, String eseparator0, String eDate, String eLocation, String eAddress,
                         String eseparator1 , String eDescription) {
        eventTitle = (TextView)findViewById(R.id.eventTitle);
        separator0 = (TextView)findViewById(R.id.separator0);
        eventDate = (TextView)findViewById(R.id.eventDate);
        eventLocation =  (TextView)findViewById(R.id.eventLocation);
        eventAddress = (TextView)findViewById(R.id.eventAddress);
        separator = (TextView)findViewById(R.id.separator);
        eventDescription =  (TextView)findViewById(R.id.eventDescription);
        buyTicket = (TextView)findViewById(R.id.buyTicket);

        buyTicket.setTypeface(font);
        eventTitle.setText(eTitle);
        eventTitle.setTypeface(font);
        separator0.setText(eseparator0);
        eventDate.setText(eDate);
        eventDate.setTypeface(font2);
        eventLocation.setText(eLocation);
        eventLocation.setTypeface(font2);
        eventAddress.setText(eAddress);
        eventAddress.setTypeface(font2);
       // eventPriceSymbol.setText(ePriceSymbol);
       // eventPriceSymbol.setTypeface(font2);
        separator.setText(eseparator1);
        eventDescription.setText(eDescription);
        eventDescription.setTypeface(font2);

        getdir = (TextView)findViewById(R.id.getDirections);
        getdir.setTypeface(font);
        //eventDescription.setMovementMethod(new ScrollingMovementMethod());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void getDirections(View v){
        //Intent i = new Intent(this,location.class);
        //startActivity(i);
    }

    public boolean checkYoutube(String link){
        Boolean found;
        found = link.contains("youtu");
        return found;
    }

    public boolean checkVimeo(String link){
        Boolean found;
        found = link.contains("vimeo");
        return found;
    }

    public void getMap(View v){
        Intent i = new Intent(this,location.class);
        Bundle b = new Bundle();
        i.putExtra("vendor_address", vendor_address);
        i.putExtra("vendor_name", vendor_name);
        i.putExtra("title", title);
        i.putExtra("latitude", latitude);
        i.putExtra("longitude", longitude);
        startActivity(i);
    }


    public void click(View view) {
        Intent intentRes = getIntent();
        String ticket= intentRes.getStringExtra("ticket");
        Intent intent = new Intent(this, BuyTicket.class);
        intent.putExtra("buy_ticket", ticket);
        startActivity(intent);
    }

}

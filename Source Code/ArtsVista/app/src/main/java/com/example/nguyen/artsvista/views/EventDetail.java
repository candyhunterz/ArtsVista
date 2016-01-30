package com.example.nguyen.artsvista.views;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.nguyen.artsvista.R;

public class EventDetail extends AppCompatActivity {

    //THIS CLASS IS NEVER USED, eDetail IS USED INSTEAD
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        Intent intent = getIntent();
        String id = "";
        String title = "";
        String video = "";

        if (intent != null) {
            id = intent.getStringExtra("ImageEvent_id");
            title = intent.getStringExtra("title");
            video = intent.getStringExtra("video");
        }
        TextView idText = (TextView) findViewById(R.id.id);
        TextView titleText = (TextView) findViewById(R.id.title);
        idText.setText(id);
        titleText.setText(title);

    }
    public void click(View view) {
        Intent intentRes = getIntent();
        String ticket= intentRes.getStringExtra("ticket");
        Intent intent = new Intent(this, BuyTicket.class);
        intent.putExtra("buy_ticket", ticket);
        startActivity(intent);
    }

}

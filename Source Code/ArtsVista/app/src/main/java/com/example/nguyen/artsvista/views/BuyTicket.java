package com.example.nguyen.artsvista.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.nguyen.artsvista.R;

public class BuyTicket extends AppCompatActivity {
//ACTIVITY THAT OPENS WHEN A USER PRESSES "BUY TICKET" ON THE EVENT DETAILS PAGE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_ticket);
        Intent intent = getIntent();
        String ticket = intent.getStringExtra("buy_ticket");

        Log.d("ticket", ticket);
        WebView webview = (WebView) findViewById(R.id.webView);
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);


        if (ticket != null && !ticket.isEmpty() && ticket.length() !=0 && !ticket.equals("null")) {
            Log.d("not null", "not null");
            webview.loadUrl(ticket);
        }
        else {
            Log.d("null", "null");
            webview.loadUrl("file:///android_asset/html/noTickets.html");
        }
    }

    public void back(View v){
        this.finish();
    }
}

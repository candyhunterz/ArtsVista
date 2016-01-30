package com.example.nguyen.artsvista.views;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.example.nguyen.artsvista.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class location extends AppCompatActivity {

    //THE MAP THAT OPENS ONCE A USER PRESSES GET DIRECTIONS FROM THE EVENT DETAILS PAGE
    private GoogleMap googleMap;
    LatLng eventloc;

    String title = "";
    String vendor_address ="";
    String vendor_name = "";
    Double latitude = 0.0;
    Double longitude = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_layout);
        SupportMapFragment mapFrag = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        Log.d("test 1", "test");
        Intent intent = getIntent();
        if (intent != null) {
            Log.d("test 2", "test");
            title = intent.getStringExtra("title");
            vendor_address = intent.getStringExtra("vendor_address");
            vendor_name = intent.getStringExtra("vendor_name");
            latitude = intent.getDoubleExtra("latitude", 0.0);
            longitude = intent.getDoubleExtra("longitude", 0.0);
            Log.d("address", vendor_address);
            Log.d("latitude", latitude.toString());
        }




        googleMap = mapFrag.getMap();
        eventloc = new LatLng(latitude, longitude);

        googleMap.addMarker(new MarkerOptions().position(eventloc).title(vendor_name + ", " + vendor_address));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eventloc, 15));
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


}

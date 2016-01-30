package com.example.nguyen.artsvista.views;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.nguyen.artsvista.R;
import com.example.nguyen.artsvista.models.EventModel;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EventList extends AppCompatActivity {
    //THIS IS THE MAIN EVENT LIST PAGE, WHICH APPEARS AFTER YOU SELECT A CITY

    private TextView jsontext;
    private ListView lvEvents;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        dialog = new ProgressDialog(this);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setMessage("Loading...");

        lvEvents = (ListView)findViewById(R.id.lvevents);
        new JSONTask().execute("http://192.168.0.104/ArtsVistaWS/get_all_events.php");
        //new JSONTask().execute("http://192.168.3.102:8080/artsvista/get_all_events.php");
        //new JSONTask().execute("http://10.0.2.2:8080/ArtsVistaWS/get_all_events.php");

        // Create default options which will be used for every
    //  displayImage(...) call if no options will be passed to this method
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
        .cacheInMemory(true)
                .cacheOnDisk(true)
        .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
        .defaultDisplayImageOptions(defaultOptions)
        .build();
        ImageLoader.getInstance().init(config); // Do it on Application start

    }

    public class JSONTask extends AsyncTask<String, String, List<EventModel>> {
        private List<EventModel> eventModelList;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected List<EventModel> doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);

                }
                String finalJson = buffer.toString();

                JSONObject parentObject = new JSONObject(finalJson);
                JSONArray parentArray = parentObject.getJSONArray("events");

                eventModelList = new ArrayList<>();

              //  StringBuffer finalBufferedData = new StringBuffer();

                for (int i=0; i<parentArray.length(); i++) {

                    JSONObject finalObject = parentArray.getJSONObject(i);
                    EventModel eventModel = new EventModel();
                    //eventModel.setId(finalObject.getString("id"));
                    eventModel.setImageEvent_ID(finalObject.getString("ImageEvent_ID"));
                    eventModel.setTitle(finalObject.getString("title"));
                    eventModel.setImageID(finalObject.getString("ImageID"));
                    eventModel.setImage(finalObject.getString("Image"));
                    eventModel.setTicket(finalObject.getString("ticket"));
                    eventModel.setVideo(finalObject.getString("video"));
                    eventModel.setDescription(finalObject.getString("description"));
                    eventModel.setVendor_Name(finalObject.getString("vendor_name"));
                    eventModel.setVendor_Address(finalObject.getString("vendor_address"));
                    eventModel.setLatitude(finalObject.getDouble("latitude"));
                    eventModel.setLongitude(finalObject.getDouble("longitude"));
                    eventModel.setStart_Date(finalObject.getString("start_date"));


                    eventModelList.add(eventModel);
                }


                return eventModelList;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if ((connection != null))
                    connection.disconnect();
                try {
                    if (reader != null)
                        reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;

        }


        @Override
        protected void onPostExecute(List<EventModel> s) {
            final EventAdapter adapter;

            super.onPostExecute(s);
            dialog.dismiss();
            adapter = new EventAdapter(getApplicationContext(), R.layout.row, s);

            lvEvents.setAdapter(adapter);

            lvEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //Intent intent = new Intent(view.getContext(), EventDetail.class);
                    Intent intent = new Intent(view.getContext(), eDetail.class);

                    EventModel event = (EventModel) adapter.getItem(position);
                    intent.putExtra("ImageEvent_id", event.getImageEvent_ID());
                    intent.putExtra("title", event.getTitle());
                    intent.putExtra("ticket", event.getTicket());
                    intent.putExtra("video", event.getVideo());
                    intent.putExtra("description", event.getDescription());
                    intent.putExtra("vendor_name", event.getVendor_Name());
                    intent.putExtra("vendor_address", event.getVendor_Address());
                    intent.putExtra("latitude", event.getLatitude());
                    intent.putExtra("longitude", event.getLongitude());
                    intent.putExtra("start_date", event.getStart_Date());
                    startActivity(intent);
                   // String event = adapter.getItem(position).getClass().


                    //eventDetail.putExtra("event", position);
                   // startActivityForResult(eventDetail, 0);
                }
            });




        }
    }

    public class EventAdapter extends ArrayAdapter {
        private List<EventModel> eventModelList;
        private int resource;
        private LayoutInflater inflater;
        private ViewHolder holder;


        public EventAdapter(Context context, int resource, List<EventModel> objects) {
            super(context, resource, objects);
            eventModelList = objects;
            this.resource = resource;
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Typeface font;
            font = Typeface.createFromAsset(EventList.this.getAssets(), "fonts/tg20b.otf");
          //  ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(resource, null);
                holder.ivEvent = (ImageView)convertView.findViewById(R.id.imageView);
                holder.title = (TextView)convertView.findViewById(R.id.textView);
                convertView.setTag(holder);
                holder.title.setTypeface(font);
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }


            final ProgressBar progressBar = (ProgressBar)convertView.findViewById(R.id.progressBar);

            ImageLoader.getInstance().displayImage(eventModelList.get(position).getImage(), holder.ivEvent, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {
                    progressBar.setVisibility(View.VISIBLE);
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    progressBar.setVisibility(View.GONE);

                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    progressBar.setVisibility(View.GONE);

                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {
                    progressBar.setVisibility(View.GONE);

                }
            }); // Default options will be used


            holder.title.setText(eventModelList.get(position).getTitle());

            return convertView;
        }


        class ViewHolder  {
            private ImageView ivEvent;
            private TextView title;

        }
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
        if (id == R.id.action_refresh) {
            new JSONTask().execute("http://192.168.135.1:8080/artsvista/get_all_events.php");
            //new JSONTask().execute("http://192.168.3.102:8080/artsvista/get_all_events.php");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

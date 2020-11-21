package com.example.weather;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.attribute.AclEntry;

public class MainActivity extends AppCompatActivity {

    TextView view_city;
    TextView view_temp;
    TextView view_desc;

    ImageView view_weather;
    EditText search;
    FloatingActionButton search_floating;
    private int value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view_city = (TextView)findViewById(R.id.tvCity) ;
        view_temp = (TextView)findViewById(R.id.tvTem);
        view_desc = (TextView)findViewById(R.id.tvDesc);

        view_city.setText("");
        view_temp.setText("");
        view_desc.setText("");

        view_weather = findViewById(R.id.ivImage);
        search = findViewById(R.id.etSearch);
        search_floating = findViewById(R.id.btnSearch);

        search_floating.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                InputMethodManager imn = (InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
                imn.hideSoftInputFromWindow(getCurrentFocus().getRootView().getWindowToken(), 0);
                api_key(String.valueOf(search.getText()));



            }


            @RequiresApi(api = Build.VERSION_CODES.O)
            private void api_key(final String City) {
                OkHttpClient client = new OkHttpClient();
                String http;
                Request request = new Request.Builder()
                        .url("http://api.openweathermap.org/data/2.5/weather?q=Moscow&appid=1948554b1dd29136e513210e1561d1d2")
                        .get()
                        .build();
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                try{
                    Response response = client.newCall(request).execute();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {


                        }


                        @Override
                        public void onResponse(Response response) throws IOException {
                            String responseData = response.body().string();
                            try {
                                JSONObject json = new JSONObject(responseData);
                                JSONArray array = json.getJSONArray("weather");
                                JSONObject object = array.getJSONObject(0);

                                String description = object.getString("description");
                                String icons = object.getString("icon");
                                JSONObject temp1 = json.getJSONObject("main");
                                Double Temperature = temp1.getDouble("temp");

                                setText(view_city, City);
                                String temps = (Math.round(Temperature) + " °С");
                                setText(view_temp, temps);
                                setText(view_desc, description);

                                setImage(view_weather, icons);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        private void setText(TextView view_city, String city) {

                        }

                        private AclEntry.Builder get() {
                            return null;
                        }

                        private void setImage(ImageView view_weather, String icons) {
                        }

                        private void setImage(ImageView view_weather, String value) {
                            switch (value) {
                                case "01d":
                                    view_weather.setImageDrawable(getResources().getDrawable(R.drawable.icon1));
                                    break;
                                case "01n":
                                    view_weather.setImageDrawable(getResources().getDrawable(R.drawable.icon1));
                                    break;
                                case "02d":
                                    view_weather.setImageDrawable(getResources().getDrawable(R.drawable.icon2));
                                    break;
                                case "02n":
                                    view_weather.setImageDrawable(getResources().getDrawable(R.drawable.icon2));
                                    break;
                                case "03d":
                                    view_weather.setImageDrawable(getResources().getDrawable(R.drawable.icon3));
                                    break;
                                case "03n":
                                    view_weather.setImageDrawable(getResources().getDrawable(R.drawable.icon3));
                                    break;
                                case "04d":
                                    view_weather.setImageDrawable(getResources().getDrawable(R.drawable.icon4));
                                    break;
                                case "04n":
                                    view_weather.setImageDrawable(getResources().getDrawable(R.drawable.icon4));
                                    break;
                                case "09d":
                                    view_weather.setImageDrawable(getResources().getDrawable(R.drawable.icon5));
                                    break;
                                case "09n":
                                    view_weather.setImageDrawable(getResources().getDrawable(R.drawable.icon5));
                                    break;
                                case "10d":
                                    view_weather.setImageDrawable(getResources().getDrawable(R.drawable.icon6));
                                    break;
                                case "10n":
                                    view_weather.setImageDrawable(getResources().getDrawable(R.drawable.icon6));
                                    break;
                                case "11d":
                                    view_weather.setImageDrawable(getResources().getDrawable(R.drawable.icon7));
                                    break;
                                case "11n":
                                    view_weather.setImageDrawable(getResources().getDrawable(R.drawable.icon7));
                                    break;

                                default:
                                    ImageView.setImageDrawable(getResources().getDrawable(R.drawable.weather));

                            private AclEntry.Builder get ()
                            
                        }
                    }









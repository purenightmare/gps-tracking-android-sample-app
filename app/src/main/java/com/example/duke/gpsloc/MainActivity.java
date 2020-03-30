package com.example.duke.gpsloc;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    Button btnShowLocation;


    GPSTracker gps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},1);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},2);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET},3);
        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);

        gps = new GPSTracker(MainActivity.this);
        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {


                Log.i("GPS","start");
                if (gps.canGetLocation()) {

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    Log.i("GPS","show locations");
                    Toast.makeText(MainActivity.this, "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                } else {


                    gps.showSettingsAlert();
                }

            }
        });

    }
}

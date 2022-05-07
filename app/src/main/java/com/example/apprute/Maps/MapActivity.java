package com.example.apprute.Maps;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.example.apprute.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.SphericalUtil;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback,TaskLoadedCallback {
    private GoogleMap mMap;
    private MarkerOptions markerOptions[];
    TextView distance_text;
    ArrayList<String> duration,Listdistance;
    ArrayList<LatLng> locationArrayList;
    private Polyline currentPolyline;
    private LatLng ori, dest, ori2, dest2;
    ArrayList<Double> ListDistance;
    private Double distance;
    TextView duration1,duration2,duration3, distance1, distance2, total_duration, total_distance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        duration = new ArrayList<>();
        ori = new LatLng(2.3337,99.0833);
        dest = new LatLng(2.6542,98.9340);
        ori2 = new LatLng(2.675076, 98.831009 );
        dest2 = new LatLng(2.6517853,98.86084579999999);
        locationArrayList = new ArrayList<>();
        Listdistance = new ArrayList<>();
        ListDistance = new ArrayList<>();
        duration1 = findViewById(R.id.text_duration1);
        duration2 = findViewById(R.id.text_duration2);
        duration3 = findViewById(R.id.text_duration3);
        distance1 = findViewById(R.id.distance1);
        distance2 = findViewById(R.id.distance2);
        total_distance = findViewById(R.id.total_distance);
        total_duration = findViewById(R.id.total_duration);
        locationArrayList.add(ori);
        locationArrayList.add(dest);
        locationArrayList.add(ori2);
        locationArrayList.add(dest2);
        List<String> urls = getDirectionsUrl(locationArrayList,"driving");
        if (urls.size() > 1) {
            for (int i = 0; i < urls.size(); i++) {
                if(i==1){
                    System.out.println("Berhasil");
                }else {
                    String url = urls.get(i);
                    System.out.println("Rute "+url);
                    FetchURL fetchURL = new FetchURL(MapActivity.this);
                    fetchURL.execute(url, "driving");
                }
            }
        }
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.mapNearBy);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Log.d("mylog", "Added Markers");
        for (int i = 0; i < locationArrayList.size(); i++) {
            mMap.addMarker(new MarkerOptions().position(locationArrayList.get(i)).title("Marker"+i));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(locationArrayList.get(i)));
        }
        for (int i=0; i<locationArrayList.size();i++){
            if(i%2==0){
                ListDistance.add(SphericalUtil.computeDistanceBetween(locationArrayList.get(i), locationArrayList.get(i+1)));
            }
        }
        CameraPosition googlePlex = CameraPosition.builder()
                .target(new LatLng(2.6274,98.7922))
                .zoom(10)
                .build();
        distance = SphericalUtil.computeDistanceBetween(ori, dest)  ;
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 5000, null);
    }

    @Override
    public void onTaskDone(Object... values) {
        currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onDistanceDuration(Object... values) {
        String output = values[0].toString();
        duration.add(output);
        for (int i = 0; i < duration.size(); i++) {
            if (duration.size() > 1) {
                String strHours1="",strHourstot="";
                int s1 = Integer.parseInt(duration.get(1));
                int min1 = (s1 / 60) % 60;
                int hours1 = (s1 / 60) / 60;
                System.out.println("Hours" + hours1);
                String strmin1 = (min1 < 10) ? "0" + Integer.toString(min1) : Integer.toString(min1);
                strHours1 = (hours1 < 10) ? "0" + Integer.toString(hours1) : Integer.toString(hours1);
                System.out.println("hours : "+strHours1);
                duration3.setText(strHours1 + " Jam " + strmin1 + " Menit");
                int total = Integer.parseInt(duration.get(0)) + Integer.parseInt(duration.get(1));
                System.out.println("total : "+total);
                int mintot = (total / 60) % 60;
                int hourstot = (total / 60) / 60;
                System.out.println("Hours2" + hourstot);
                String strmintot = (mintot < 10) ? "0" + Integer.toString(mintot) : Integer.toString(mintot);
                strHourstot = (hourstot < 10) ? "0" + Integer.toString(hourstot) : Integer.toString(hourstot);
                total_duration.setText(strHourstot + " Jam " + strmintot + " Menit");
            }
            int s2 = Integer.parseInt(duration.get(0));
            int min2 = (s2 / 60) % 60;
            int hours2 = (s2 / 60) / 60;
            String strHours2="";
            String strmin2 = (min2 < 10) ? "0" + Integer.toString(min2) : Integer.toString(min2);
            strHours2 = (hours2 < 10) ? "0" + Integer.toString(hours2) : Integer.toString(hours2);
            duration1.setText(strHours2 + " Jam " + strmin2 + " Menit");

        }
    }

    @Override
    public void onDistance(Object... values) {
        String value = values[0].toString();
        Listdistance.add(value);
        for (int i = 0; i < Listdistance.size(); i++) {
            if (Listdistance.size() > 1) {
                int s1 = Integer.parseInt(Listdistance.get(1))/1000;
                distance2.setText("  "+s1 + " KM");
                int total = Integer.parseInt(Listdistance.get(0)) + Integer.parseInt(Listdistance.get(1));
                total_distance.setText(""+total/1000 +" KM");
            }
                int s2 = Integer.parseInt(Listdistance.get(0))/1000;
                distance1.setText("  "+s2 +" KM");
        }

    }

    private List<String> getDirectionsUrl(ArrayList<LatLng> markerPoints, String directionMode) {
        List<String> mUrls = new ArrayList<>();
        if (markerPoints.size() > 1) {
            String str_origin = markerPoints.get(0).latitude + "," + markerPoints.get(0).longitude;
            String str_dest = markerPoints.get(1).latitude + "," + markerPoints.get(1).longitude;

            String mode = "mode="+directionMode;
            String parameters = "origin=" + str_origin + "&destination=" + str_dest + "&" + mode;
            String output = "json";
            String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters+ "&key=" + getString(R.string.google_maps_key);
            System.out.println(str_origin);
            mUrls.add(url);
            for (int i = 2; i < markerPoints.size(); i++)//loop starts from 2 because 0 and 1 are already printed
            {
                str_origin = str_dest;
                str_dest = markerPoints.get(i).latitude + "," + markerPoints.get(i).longitude;
                parameters = "origin=" + str_origin + "&destination=" + str_dest + "&" + mode;
                url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters+ "&key=" + getString(R.string.google_maps_key);
                mUrls.add(url);
            }
        }

        return mUrls;
    }
}
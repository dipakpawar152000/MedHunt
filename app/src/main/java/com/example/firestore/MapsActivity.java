package com.example.firestore;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private String name;
    private String phone;
    private double latt, lonn;
    public String lat[];
    private String lon[];
    private String nam[];
    public ArrayList<QueryDocumentSnapshot> list2;
    public ArrayList<String> med;
    public SearchView mySearchView;
    public String str;


    private int i = 0;
    private int j = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);


        mapFragment.getMapAsync(this);
        Intent intent = getIntent();
        str = intent.getStringExtra("nam");


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;





        list2 = new ArrayList<QueryDocumentSnapshot>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document :task.getResult()){
                                med =(ArrayList<String>) document.get("medicine");

                                for (String i: med){
                                    if(i.equals(str)){
                                        list2.add(document);
                                         Toast.makeText(getApplicationContext(),"Succcess",Toast.LENGTH_LONG).show();

                                    }

                                }
                            }
                            i=list2.size();

                            lat = new String[i];
                            lon = new String[i];
                            nam = new String[i];

                            for(QueryDocumentSnapshot q :list2){
                                lat[j] = String.valueOf(q.get("Latitide"));
                                lon[j] = String.valueOf(q.get("Longitude"));
                                nam[j] = String.valueOf(q.get("Name"));
                                j = j+1;

                            }
                            for (int k = 0; k<i; k++)
                           {
                                name = nam[k];
                                latt = Double.parseDouble(lat[k]);
                                lonn = Double.parseDouble(lon[k]);
                                LatLng sydneyy = new LatLng(latt, lonn);
                                mMap.addMarker(new MarkerOptions().position(sydneyy).title(name)).setDraggable(true);
                                mMap.animateCamera(CameraUpdateFactory.zoomTo(500.5f));
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydneyy));


                            }
                            mMap.animateCamera(CameraUpdateFactory.zoomTo(13.5f));



                        }else {
                            Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();


                        }
                    }
                });
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {
                String markertitle = marker.getTitle();
                Intent i = new Intent(MapsActivity.this,Medical.class);
                i.putExtra("title",markertitle);
                startActivity(i);

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {

            }
        });

















    }

}
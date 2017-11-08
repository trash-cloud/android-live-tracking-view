package io.hypertrack.livetrackingconsumer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.hypertrack.lib.HyperTrack;
import com.hypertrack.lib.internal.consumer.models.ActionListUpdateCallback;
import com.hypertrack.lib.models.Action;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.annotations.Polyline;
import com.mapbox.mapboxsdk.annotations.PolylineOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aman on 07/11/17.
 */

public class MapBoxActivity extends AppCompatActivity implements OnMapReadyCallback, ActionListUpdateCallback {
    private static final String TAG = MapBoxActivity.class.getSimpleName();
    private MapView mapView;
    Marker marker;
    MapboxMap mapboxMap;
    Polyline polyline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, MAP_BOX_KEY);
        setContentView(R.layout.map_box_activity);
        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        HyperTrack.setTrackActionCallback(this);

    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        HyperTrack.removeTrackActionCallback();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onMapReady(final MapboxMap mapboxMap) {
        this.mapboxMap = mapboxMap;
        HyperTrack.setTrackActionCallback(this);
    }


    @Override
    public void onActionUpdated(List<Action> actions) {
        Log.d(TAG, "onActionUpdated: " + actions.size()+"  URL:"+actions.get(0).getTrackingURL());
    }

    @Override
    public void onLocationUpdated(String actionId, LatLng lastLatLng, LatLng newLatLng, List<LatLng> updatedLatLngList) {

        com.mapbox.mapboxsdk.geometry.LatLng latLng = new com.mapbox.mapboxsdk.geometry.LatLng(newLatLng.latitude, newLatLng.longitude);

        List<com.mapbox.mapboxsdk.geometry.LatLng> mapboxLatLngs = new ArrayList<>();

        if (marker == null) {
            for (LatLng temp : updatedLatLngList) {
                mapboxLatLngs.add(new com.mapbox.mapboxsdk.geometry.LatLng(temp.latitude, temp.longitude));
            }
            polyline = mapboxMap.addPolyline(new PolylineOptions().addAll(mapboxLatLngs));
            marker = mapboxMap.addMarker(new MarkerOptions().position(latLng));

        } else {
            marker.setPosition(latLng);
            mapboxLatLngs.clear();
            for (LatLng temp : updatedLatLngList) {
                mapboxLatLngs.add(new com.mapbox.mapboxsdk.geometry.LatLng(temp.latitude, temp.longitude));
            }
            polyline.setPoints(mapboxLatLngs);

        }

        CameraPosition position = new CameraPosition.Builder()
                .target(latLng) // Sets the new camera position
                .zoom(15) // Sets the zoom
                .build(); // Creates a CameraPosition from the builder

        mapboxMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(position));

    }
}
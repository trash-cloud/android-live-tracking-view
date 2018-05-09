package io.hypertrack.livetrackingconsumer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hypertrack.lib.MapFragmentCallback;
import com.hypertrack.lib.tracking.MapProvider.HyperTrackMapFragment;
import com.hypertrack.lib.tracking.MapProvider.MapFragmentView;

/**
 * Created by Aman Jain on 06/03/17.
 */

//Refer here for more detail about live tracking view https://docs.hypertrack.com/usecases/livetracking/android/installing.html
public class YourMapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_track);

        initUI();
    }

    private void initUI() {

        /** Initialize Map Fragment added in Activity Layout to getMapAsync
         *  Once map is created onMapReady callback will be fire with GoogleMap object
         */
        HyperTrackMapFragment hyperTrackMapFragment = (HyperTrackMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.htMapfragment);

        /**
         * Call the method below to enable UI customizations for Live Tracking View,
         * an instance of HyperTrackMapAdapter needs to be set as depicted below
         */
        hyperTrackMapFragment.setMapAdapter(new MyMapAdapter(this));

         /*
         * Call the method below to register for any callbacks/updates on Live Tracking View/Map
         */
        hyperTrackMapFragment.setMapCallback(new MapFragmentCallback());

        hyperTrackMapFragment.setUseCaseType(MapFragmentView.Type.ORDER_TRACKING);
    }
}

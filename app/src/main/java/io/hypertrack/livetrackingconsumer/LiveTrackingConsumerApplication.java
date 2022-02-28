package io.hypertrack.livetrackingconsumer;

import android.app.Application;
import android.util.Log;

import com.hypertrack.lib.HyperTrack;

/**
 * Created by Aman Jain on 17/05/17.
 */

public class LiveTrackingConsumerApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        String publishableKey = "JrKn1rygp4lqdquyP-fdQ8YNdl_fIaTGJOWAJE52arhWh-izOaejKuTaY2nJnXbgJR2z1JjSUvURkdSVfyXfhw";
        // Initialize HyperTrack SDK with the Publishable Key
        // Refer to
        // documentation at https://docs.hypertrack.com/gettingstarted/authentication.html#publishable-key
        // @NOTE: Add **YOUR_PUBLISHABLE_KEY** here for SDK to be authenticated with HyperTrack Server
        HyperTrack.initialize(this,publishableKey);
    }
}

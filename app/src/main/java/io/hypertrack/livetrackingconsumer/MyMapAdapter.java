package io.hypertrack.livetrackingconsumer;

import android.content.Context;

import com.hypertrack.lib.HyperTrackMapAdapter;

/**
 * Created by Aman Jain on 30/05/17.
 */

//Refer here for more customization detail https://docs.hypertrack.com/usecases/livetracking/android/customizations/ui.html
public class MyMapAdapter extends HyperTrackMapAdapter {

    public MyMapAdapter(Context mContext) {
        super(mContext);
    }

    /*
    * Show the trailing polyline of driver
    * */
    @Override
    public boolean showTrailingPolyline() {
        return true;
    }

}

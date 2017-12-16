package resolution.ex6.vr.new_project;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

public class GPSListener implements LocationListener {
    @Override
    public void onLocationChanged(Location location) {
        double latitude=location.getLatitude();
        double longtitude=location.getLongitude();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}

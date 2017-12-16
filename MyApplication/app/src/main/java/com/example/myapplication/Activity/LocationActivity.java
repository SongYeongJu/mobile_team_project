package com.example.myapplication.Activity;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class LocationActivity implements MapView.CurrentLocationEventListener{

    @Override
    public void onCurrentLocationUpdate(MapView mapView, MapPoint mapPoint, float v) {
        final MapView.CurrentLocationTrackingMode TrackingModeOnWithoutHeading;
    }

    @Override
    public void onCurrentLocationDeviceHeadingUpdate(MapView mapView, float v) {

    }

    @Override
    public void onCurrentLocationUpdateFailed(MapView mapView) {

    }

    @Override
    public void onCurrentLocationUpdateCancelled(MapView mapView) {

    }
}

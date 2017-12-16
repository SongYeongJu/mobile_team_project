package resolution.ex6.vr.new_project;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class MainActivity extends AppCompatActivity implements MapView.MapViewEventListener, MapView.CurrentLocationEventListener, MapView.POIItemEventListener {

    private MapPOIItem mDefaultMarker;
    private MapPOIItem mMarker;
    public static final MapPoint DEFAULT_MARKER_POINT = MapPoint.mapPointWithGeoCoord(35.87222, 128.60250);
    public MapView mapView;
    GPSListener gpsListener=new GPSListener();
    long minTime=10000;
    float minDistance=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapView = new MapView(this);

        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapView.setDaumMapApiKey("9cd21f655ed0bbade4257691ce87f6ce");
        mapViewContainer.addView(mapView);
        mapView.setMapViewEventListener(this); //지도 이동/확대/축소, 지도 화면 터치 이벤트 통보
        mapView.setCurrentLocationEventListener(this);
        mapView.setMapCenterPoint(DEFAULT_MARKER_POINT, true);
        Log.i("되는가", "응");
        createMarkerByCoordinate(mapView, 35.9, 129.3726);
        startLocationService();
//        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
//        mapView.setCurrentLocationEventListener(this);

//        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);

    }

    //MapViewEventListener
    @Override
    public void onMapViewInitialized(MapView mapView) {

    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {
    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {

    }

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {
        createStartMarker(mapView, mapPoint);
    }

    //출발 마커 생성
    public void createStartMarker(MapView mapView, MapPoint mapPoint) {

        mDefaultMarker = new MapPOIItem();
        String name = "출발 위치";
        mDefaultMarker.setItemName(name);
        mDefaultMarker.setTag(0);
        mDefaultMarker.setMapPoint(mapPoint);
        mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        mDefaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mapView.addPOIItem(mDefaultMarker);
        mapView.selectPOIItem(mDefaultMarker, true);

        MapPoint mMapPoint = mDefaultMarker.getMapPoint();
        double latitude = mMapPoint.getMapPointGeoCoord().latitude;
        double longtitude = mMapPoint.getMapPointGeoCoord().longitude;

        Log.i("latitude : ", String.valueOf(latitude));
        Log.i("longtitude : ", String.valueOf(longtitude));
//        mapView.setMapCenterPoint(DEFAULT_MARKER_POINT, false);
    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

    }

    public void createMarkerByCoordinate(MapView mapView, double latitude, double longtitude) {
        mMarker = new MapPOIItem();
        MapPoint coordinate = MapPoint.mapPointWithGeoCoord(latitude, longtitude);

        String name = "좌표";
        mMarker.setItemName(name);
        mMarker.setTag(0);
        mMarker.setMapPoint(coordinate);
        mMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        mMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mapView.addPOIItem(mMarker);
        mapView.selectPOIItem(mMarker, false);
    }

    //도착 마커 생성
    public void createDestinationMarker(MapView mapView, MapPoint mapPoint) {
        mDefaultMarker = new MapPOIItem();
        String name = "도착 위치";
        mDefaultMarker.setItemName(name);
        mDefaultMarker.setTag(0);
        mDefaultMarker.setMapPoint(mapPoint);
        mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.YellowPin);
        mDefaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mapView.addPOIItem(mDefaultMarker);
        mapView.selectPOIItem(mDefaultMarker, true);
        MapPoint mMapPoint = mDefaultMarker.getMapPoint();
        double latitude = mMapPoint.getMapPointGeoCoord().latitude;
        double longtitude = mMapPoint.getMapPointGeoCoord().longitude;
        Log.i("latitude : ", String.valueOf(latitude));
        Log.i("longtitude : ", String.valueOf(longtitude));
//        destination.add(mDefaultMarker);
//        mapView.setMapCenterPoint(DEFAULT_MARKER_POINT, false);
    }

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {
        createDestinationMarker(mapView, mapPoint);
    }

    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onCurrentLocationUpdate(MapView mapView, MapPoint currentLocation, float accuracyInMeters) {
        MapPoint.GeoCoordinate mapPointGeo = currentLocation.getMapPointGeoCoord();
        Log.i("손대지마", String.format("MapView onCurrentLocationUpdate (%f,%f) accuracy (%f)", mapPointGeo.latitude, mapPointGeo.longitude, accuracyInMeters));

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

    public void startLocationService() {
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location lastLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(lastLocation!=null){
            double latitude=lastLocation.getLatitude();
            double longtitude=lastLocation.getLongitude();
            MapPoint currentCoordinate=MapPoint.mapPointWithGeoCoord(latitude, longtitude);
            mapView.setMapCenterPoint(currentCoordinate, true);
            Log.i("Latitude/Longtitude", String.valueOf(latitude)+String.valueOf(longtitude));
        }
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, gpsListener);
        Toast.makeText(getApplicationContext(), "위치 확인이 시작되었슴", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {
        Toast.makeText(this, "Clicked"+mapPOIItem.getItemName()+" Callout Ballon", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {

    }

    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {

    }
}
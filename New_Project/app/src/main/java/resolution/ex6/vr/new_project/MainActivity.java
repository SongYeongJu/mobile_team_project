package resolution.ex6.vr.new_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class MainActivity extends AppCompatActivity implements MapView.MapViewEventListener, MapView.CurrentLocationEventListener {

    public static final MapPoint DEFAULT_MARKER_POINT=MapPoint.mapPointWithGeoCoord(35.87222, 128.60250);
    public MapView mapView;
    MarkerActivity setMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapView=new MapView(this);

        ViewGroup mapViewContainer=(ViewGroup)findViewById(R.id.map_view);
        mapView.setDaumMapApiKey("9cd21f655ed0bbade4257691ce87f6ce");
        mapViewContainer.addView(mapView);
        mapView.setMapViewEventListener(this); //지도 이동/확대/축소, 지도 화면 터치 이벤트 통보
        mapView.setCurrentLocationEventListener(this);

        Log.i("되는가", "응");
//        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
//        onMapViewDoubleTapped(mapView, DEFAULT_MARKER_POINT);
        MapPOIItem marker=new MapPOIItem();
        marker.setItemName("출발 위치");
        marker.setTag(0);
        marker.setMapPoint(DEFAULT_MARKER_POINT);
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mapView.addPOIItem(marker);
    }

    //MapViewEventListener
    @Override
    public void onMapViewInitialized(MapView mapView) {

    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(35.87222, 128.60250), true);
    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {

    }

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {
        MapPoint.GeoCoordinate mapPointGeo=mapPoint.getMapPointGeoCoord();
        MapPoint.PlainCoordinate mapPointScreenLocation=mapPoint.getMapPointScreenLocation();
        MapPoint sMapPoint=MapPoint.mapPointWithGeoCoord(mapPointGeo.latitude, mapPointGeo.longitude);
//        setMarker.createStartMarker(mapView, sMapPoint);
    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {
        MapPoint.GeoCoordinate mapPointGeo=mapPoint.getMapPointGeoCoord();
        MapPoint.PlainCoordinate mapPointScreenLocation=mapPoint.getMapPointScreenLocation();
        MapPoint sMapPoint=MapPoint.mapPointWithGeoCoord(mapPointGeo.latitude, mapPointGeo.longitude);
//        setMarker.createDestinationMarker(mapView, sMapPoint);
    }

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

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
    public void onCurrentLocationUpdate(MapView mapView, MapPoint mapPoint, float v) {

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

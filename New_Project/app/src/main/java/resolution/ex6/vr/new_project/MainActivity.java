package resolution.ex6.vr.new_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class MainActivity extends AppCompatActivity implements MapView.MapViewEventListener, MapView.CurrentLocationEventListener, MapView.POIItemEventListener {

//    ArrayList<MapPOIItem> start=new ArrayList<MapPOIItem>();
//    ArrayList<MapPOIItem> destination=new ArrayList<MapPOIItem>();

    private MapPOIItem mDefaultMarker;
    public static final MapPoint DEFAULT_MARKER_POINT=MapPoint.mapPointWithGeoCoord(35.87222, 128.60250);
    public MapView mapView;
    public MapPOIItem[] start;
    public MapPOIItem[] destination;
    MarkerActivity setMarker;
    int flag=0;

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
        mapView.setMapCenterPoint(DEFAULT_MARKER_POINT, true);
        Log.i("되는가", "응");
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

    public void removeMarker(MapPOIItem poiItem){
        mapView.removePOIItem(poiItem);
    }

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
        start=new MapPOIItem[]{};

        flag=1;
//        mapView.setMapCenterPoint(DEFAULT_MARKER_POINT, false);
    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

    }

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

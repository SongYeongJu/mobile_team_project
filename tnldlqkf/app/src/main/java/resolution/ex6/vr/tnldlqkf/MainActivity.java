package resolution.ex6.vr.tnldlqkf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import net.daum.mf.map.api.CalloutBalloonAdapter;
import net.daum.mf.map.api.CameraUpdateFactory;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapPointBounds;
import net.daum.mf.map.api.MapView;

public class MainActivity extends AppCompatActivity implements MapView.MapViewEventListener{

    public static final MapPoint DEFAULT_MARKER_POINT=MapPoint.mapPointWithGeoCoord(35.87222, 128.60250);
    public MapView mapView;
//    MarkerActivity setMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapView=new MapView(this);

        ViewGroup mapViewContainer=(ViewGroup)findViewById(R.id.map_view);
        mapView.setDaumMapApiKey("068723c9e6c0fefc94eba78aedd1dc58");
        mapViewContainer.addView(mapView);
        mapView.setMapViewEventListener(this); //지도 이동/확대/축소, 지도 화면 터치 이벤트 통보
//        setMarker.createStartMarker(mapView, DEFAULT_MARKER_POINT);

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

}

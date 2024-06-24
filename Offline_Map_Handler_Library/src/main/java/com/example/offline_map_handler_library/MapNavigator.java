package com.example.offline_map_handler_library;

import android.content.Context;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;
import java.util.ArrayList;

public class MapNavigator {

    private Context context;
    private MapView mapView;
    private MyLocationNewOverlay locationOverlay;

    public MapNavigator(Context context, MapView mapView) {
        this.context = context;
        this.mapView = mapView;
        this.locationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(context), mapView);
        mapView.getOverlays().add(locationOverlay);
    }

    public void startNavigation(GeoPoint destination) {
        locationOverlay.enableMyLocation();
        // Add custom navigation logic here
    }

    public void addRoute(ArrayList<GeoPoint> waypoints) {
        Polyline line = new Polyline();
        line.setPoints(waypoints);
        mapView.getOverlays().add(line);
    }
}

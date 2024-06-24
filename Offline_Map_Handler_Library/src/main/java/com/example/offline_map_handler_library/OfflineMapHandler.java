package com.example.offline_map_handler_library;

import android.content.Context;
import org.osmdroid.views.MapView;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.Marker;

public class OfflineMapHandler {

    private Context context;
    private MapView mapView;
    private MapStateManager mapStateManager;
    private MapNavigator mapNavigator;

    public OfflineMapHandler(Context context, MapView mapView) {
        this.context = context;
        this.mapView = mapView;
        this.mapStateManager = new MapStateManager(context);
        this.mapNavigator = new MapNavigator(context, mapView);
        mapStateManager.initializeMap(mapView);
    }

    public void setMapCenter(double latitude, double longitude, double zoomLevel) {
        GeoPoint startPoint = new GeoPoint(latitude, longitude);
        mapView.getController().setZoom(zoomLevel);
        mapView.getController().setCenter(startPoint);
    }

    public void addMarker(double latitude, double longitude, String title) {
        Marker marker = new Marker(mapView);
        marker.setPosition(new GeoPoint(latitude, longitude));
        marker.setTitle(title);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        mapView.getOverlays().add(marker);
    }

    public void saveMapState() {
        mapStateManager.saveMapState();
    }

    public void loadMapState() {
        mapStateManager.loadMapState();
    }

    public void startNavigation(GeoPoint destination) {
        mapNavigator.startNavigation(destination);
    }

    public void addRoute(java.util.ArrayList<GeoPoint> waypoints) {
        mapNavigator.addRoute(waypoints);
    }

    public void downloadMapTiles(double north, double south, double east, double west, int zoomMin, int zoomMax) {
        MapTileDownloader.downloadMapTiles(context, north, south, east, west, zoomMin, zoomMax);
    }
}

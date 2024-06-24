package com.example.offline_map_handler_library;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.cachemanager.CacheManager;
import org.osmdroid.tileprovider.cachemanager.CacheManager.CacheManagerCallback;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.views.MapView;

import java.io.File;

public class MapTileDownloader {

    public static void downloadMapTiles(final Context context, final double north, final double south, final double east, final double west, final int zoomMin, final int zoomMax) {
        final MapView mapView = new MapView(context);
        mapView.setTileSource(TileSourceFactory.MAPNIK);

        final BoundingBox boundingBox = new BoundingBox(north, east, south, west);
        final CacheManager cacheManager = new CacheManager(mapView);

        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                cacheManager.downloadAreaAsync(context, boundingBox, zoomMin, zoomMax, new CacheManagerCallback() {
                    @Override
                    public void onTaskComplete() {
                        Log.i("MapTileDownloader", "Tiles downloaded successfully");
                    }

                    @Override
                    public void onTaskFailed(int errors) {
                        Log.e("MapTileDownloader", "Failed to download tiles, errors: " + errors);
                    }

                    @Override
                    public void updateProgress(int progress, int currentZoomLevel, int zoomMin, int zoomMax) {
                        // Update progress here if needed
                        Log.i("MapTileDownloader", "Progress: " + progress + "% at zoom level: " + currentZoomLevel);
                    }

                    @Override
                    public void downloadStarted() {
                        Log.i("MapTileDownloader", "Download started");
                    }

                    @Override
                    public void setPossibleTilesInArea(int total) {
                        Log.i("MapTileDownloader", "Total possible tiles: " + total);
                    }
                });
                return null;
            }
        };

        task.execute();
    }

    public static void initializeTilesOverlay(Context context, MapView mapView) {
        // Configure the local tile cache directory
        File osmdroidBasePath = new File(context.getCacheDir(), "osmdroid");
        File osmdroidTileCache = new File(osmdroidBasePath, "tiles");
        Configuration.getInstance().setOsmdroidBasePath(osmdroidBasePath);
        Configuration.getInstance().setOsmdroidTileCache(osmdroidTileCache);

        // Setup the tile source and overlay
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setUseDataConnection(false); // Set to false to use only offline tiles
    }
}

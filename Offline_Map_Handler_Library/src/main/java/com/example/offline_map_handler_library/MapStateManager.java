package com.example.offline_map_handler_library;

import android.content.Context;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.MapView;

public class MapStateManager {

    private Context context;

    public MapStateManager(Context context) {
        this.context = context;
        Configuration.getInstance().load(context, context.getSharedPreferences("osmdroid", Context.MODE_PRIVATE));
    }

    public void initializeMap(MapView mapView) {
        mapView.setTileSource(TileSourceFactory.MAPNIK);
    }

    public void saveMapState() {
        Configuration.getInstance().save(context, context.getSharedPreferences("osmdroid", Context.MODE_PRIVATE));
    }

    public void loadMapState() {
        Configuration.getInstance().load(context, context.getSharedPreferences("osmdroid", Context.MODE_PRIVATE));
    }
}

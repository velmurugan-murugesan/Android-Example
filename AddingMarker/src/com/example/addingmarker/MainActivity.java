package com.example.addingmarker;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MainActivity extends MapActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		  MapView mapView = (MapView) findViewById(R.id.mapview);
		    mapView.setBuiltInZoomControls(true);
		    MapController mc = mapView.getController();
		    double lat1 = Double.parseDouble("48.85827758964043"); // latitude
		    double lon1= Double.parseDouble("2.294543981552124"); // longitude
		    double lat2 = Double.parseDouble("8.85827758964043"); // latitude
		    double lon2 = Double.parseDouble("22.294543981552124"); // longitude
		    double lat3 = Double.parseDouble("83.85827758964043"); // latitude
		    double lon3 = Double.parseDouble("22.294543981552124"); // longitude
		    GeoPoint geoPoint = new GeoPoint((int)(lat1 * 1E6), (int)(lon1 * 1E6));
		    mc.animateTo(geoPoint);
		    mc.setZoom(15);
		    mapView.invalidate();
		    List<Overlay> mapOverlays = mapView.getOverlays();
		    Drawable drawable = this.getResources().getDrawable(R.drawable.maker);
		    AddItemizedOverlay itemizedOverlay =
		    new AddItemizedOverlay(drawable, this);
		     
		    OverlayItem overlayitem1 = new OverlayItem(geoPoint, "Hello", "Sample Overlay item");
		    OverlayItem overlayItem2=new OverlayItem(geoPoint, "two", "this is two");
		    
		    itemizedOverlay.addOverlay(overlayitem1);
		    itemizedOverlay.addOverlay(overlayItem2);
		    mapOverlays.add(itemizedOverlay);
		    mapOverlays.add(itemizedOverlay);
	}

	

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}

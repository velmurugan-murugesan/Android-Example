package com.example.addingmarker;
import java.util.ArrayList;
	 
	import android.content.Context;
	import android.graphics.drawable.Drawable;
	import android.util.Log;
	 
	import com.google.android.maps.ItemizedOverlay;
	import com.google.android.maps.OverlayItem;
	 
	public class AddItemizedOverlay extends ItemizedOverlay<OverlayItem> {
	 
	       private ArrayList<OverlayItem> mapOverlays = new ArrayList<OverlayItem>();
	 
	       private Context context;
	 
	       public AddItemizedOverlay(Drawable defaultMarker) {
	            super(boundCenterBottom(defaultMarker));
	       }
	 
	       public AddItemizedOverlay(Drawable defaultMarker, Context context) {
	            this(defaultMarker);
	            this.context = context;
	       }
	 
	       @Override
	       protected OverlayItem createItem(int i) {
	          return mapOverlays.get(i);
	       }
	 
	       @Override
	       public int size() {
	          return mapOverlays.size();
	       }
	 
	       @Override
	       protected boolean onTap(int index) {
	          Log.e("Tap", "Tap Performed");
	          return true;
	       }
	 
	       public void addOverlay(OverlayItem overlay) {
	          mapOverlays.add(overlay);
	           this.populate();
	       }
	 
	    }


package com.example.meetme;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.meetme.model.User;
import com.example.meetme.network.RequestTask;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class MapsActivity extends MapActivity {
	MapView mapView;
	MapController mapController;
	List<Overlay> mapOverlays;
	Drawable drawable;
	OverlayItems overlayItems;
	String myStatus;
	User me;
	boolean onetime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_layout);
		
		this.onetime=true;
		
		LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		LocationListener mlocListener = new MyLocationListener();
		mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
				mlocListener);

		mapView = (MapView) findViewById(R.id.mapview);
		mapController = mapView.getController();
		mapOverlays = mapView.getOverlays();
		drawable = this.getResources().getDrawable(R.drawable.androidmarker);
		
		me = new User(2, "1990", getIntent().getExtras().getString("MyStatus"),
				"TestUser", this.getResources().getDrawable(
						R.drawable.androidmarker));
		overlayItems = new OverlayItems(drawable, this, me);
		mapController.setZoom(20);
		
		getOtherUser();
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	/* Class My Location Listener */

	public class MyLocationListener implements LocationListener

	{
		public void onLocationChanged(Location loc) {

			loc.getLatitude();
			loc.getLongitude();
			String Text = "My current location is: " + "Latitud = "
					+ loc.getLatitude() + "Longitud = " + loc.getLongitude();

			int lat = (int) (loc.getLatitude() * 1E6);
			int lng = (int) (loc.getLongitude() * 1E6);
			GeoPoint userPosition = new GeoPoint(lat, lng);
			
			overlayItems.getMe().setLatitude((float)loc.getLatitude());
			overlayItems.getMe().setLongitude((float)loc.getLongitude());
			
			overlayItems.updateAllView();			
			mapOverlays.add(overlayItems);
			
			mapController.setCenter(userPosition);
			updateMyPosition();
			
		}

		public void onProviderDisabled(String provider) {
			Toast.makeText(getApplicationContext(), "Gps Disabled",
					Toast.LENGTH_SHORT).show();
		}

		public void onProviderEnabled(String provider) {
			Toast.makeText(getApplicationContext(), "Gps Enabled",
					Toast.LENGTH_SHORT).show();
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}

	}
	
	public void updateMyPosition(){
		if(onetime){
			new RequestTask(this, false).execute("http://martinmatysiak.de:10101/meetme/position?id=1&latitude="+me.getLatitude()+"&longitude="+me.getLongitude()+"&timestamp=21212&status="+me.getStatus()+"");
			onetime=false;
		}
	}
	
	public void getOtherUser(){
		new RequestTask(this, true).execute("http://martinmatysiak.de:10101/meetme/list?latitude=50.777&longitude=6.099&distance=5000");
	}

	public List<Overlay> getMapOverlays() {
		return mapOverlays;
	}

	public void setMapOverlays(List<Overlay> mapOverlays) {
		this.mapOverlays = mapOverlays;
	}

	public OverlayItems getOverlayItems() {
		return overlayItems;
	}

	public void setOverlayItems(OverlayItems overlayItems) {
		this.overlayItems = overlayItems;
	}   
    
}

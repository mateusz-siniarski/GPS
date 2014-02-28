package com.example.gps1;


import com.example.gps1.GPSService.MyLocationListener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	private String tag = "Livssyklus";
	LocationManager lm;
	LocationListener locationListener;
	Intent runServiceIntent;
	
	public static String latitude;
	public static String longitude;
	public static String altitude;
	public static String speed;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d(tag, "Inne i onCreate()");
		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationListener = new MyLocationListener();
		
	}
	
	public void startButton(View v) {
				//startActivity(new Intent("android.intent.action.secondActivity"));
				Log.d(tag, "Start knappen er trykket!");
				lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, locationListener);
				
	}
	
	public void stopButton(View v) {
				//startActivity(new Intent("android.intent.action.secondActivity"));
				Log.d(tag, "Stopp knappen er trykket!");
				lm.removeUpdates(locationListener);
	}
	
	public void startService(View v) {
		
		Log.d(tag, "Start Service knapp trykket");
		runServiceIntent = new Intent("com.example.gps1.action.LOG_POS");
		startService(runServiceIntent);
	}
	
	public void stopService(View v) {
		
		Log.d(tag, "Stop Service knapp trykket");
		
		if(runServiceIntent != null)	{
			stopService(runServiceIntent);
			runServiceIntent = null;
		}
	}
	
	protected void getLocation()	{
		
		LocationListener locationListener = new MyLocationListener();
		
	}
	/*
	public void doSomething(View view)
	{
		//startActivity(new Intent("com.capgemini.second_activity"));
		Log.d(tag, "Knappen er trykket!");
	}*/

	@Override
	protected void onStart()
	{
		super.onStart();
		Log.d(tag, "Inne i onStart()");
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		Log.d(tag, "Inne i onResume()");
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		Log.d(tag, "Inne i onPause()");
	}

	@Override
	protected void onStop()
	{
		super.onStop();
		Log.d(tag, "Inne i onStop()");
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		Log.d(tag, "Inne i onDestroy()");
	}
	
/*
	@Override
	/public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    private class MyLocationListener implements LocationListener {

    	@Override
    	public void onLocationChanged(Location arg0) {
    		
    		TextView t1 = (TextView) findViewById(R.id.textView_Latitude);
            t1.setText("Lat: "+ Double.toString(arg0.getLatitude()));
            
            TextView t2 = (TextView) findViewById(R.id.textView_Longitude);
            t2.setText("Lng: " +Double.toString(arg0.getLongitude()));
            
            TextView t3 = (TextView) findViewById(R.id.textView_Altitude);
            t3.setText("Alt: "+Double.toString(arg0.getAltitude()));
            
            TextView t4 = (TextView) findViewById(R.id.textView_Speed);
            double km = 3.6*arg0.getSpeed();
            t4.setText("Speed: "+Double.toString(km)+" km/h");
            
            

    	}

    	@Override
    	public void onProviderDisabled(String arg0) {
    		// TODO Auto-generated method stub

    	}

    	@Override
    	public void onProviderEnabled(String arg0) {
    		// TODO Auto-generated method stub

    	}

    	@Override
    	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
    		// TODO Auto-generated method stub

    	}

    }
    
}
package com.example.gps1;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;


public class GPSService extends Service {

	int _notificationId = 1;
	int _callCount = 0;
	private String tag = "Livssyklus";
	private String gps_data = "gps_data";
	
	LocationManager lm;
	LocationListener locationListener;
	Intent runServiceIntent;
	
	
	
	
	@Override
	public void onCreate() {
		Log.i(tag, "onCreate");
		_callCount = 0;
		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationListener = new MyLocationListener();
		startInForeground();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		_callCount++;
		Log.i(tag, "onStartCommand - call #" + _callCount);
		
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, locationListener);
		
		
		
		return START_NOT_STICKY;
	}
	
	private void startInForeground()
	{
		// Set basic notification information
		int notificationIcon = R.drawable.ic_launcher;
		String notificationTickerText = "Launching my service";
		long notificationTimeStamp = System.currentTimeMillis();
		
		// Describe what to do if the user clicks on the notification in the status bar
		String notificationTitleText = "My Service";
        String notificationBodyText = "Does non-UI processing";
        Intent notificationActivityIntent = new Intent(this, MainActivity.class);
        notificationActivityIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent startMyActivityPendingIntent = PendingIntent.getActivity(this, 0, notificationActivityIntent, 0);
		
        Notification foregroundNotification = null;
        
		NotificationCompat.Builder notificationbuilder = new NotificationCompat.Builder(this)
														.setSmallIcon(notificationIcon)
														.setTicker(notificationTickerText)
													    .setWhen(notificationTimeStamp);
			
			foregroundNotification = notificationbuilder.setContentTitle(notificationTitleText)
											.setContentText(notificationBodyText)
											.setContentIntent(startMyActivityPendingIntent).build();
		
        // ID to use w/ Notification Manager for _foregroundNotification
        // Set the service to foreground status and provide notification info
        startForeground(_notificationId, foregroundNotification);
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public class MyLocationListener implements LocationListener {

    	@Override
    	public void onLocationChanged(Location loc) {
    		
    		//TextView t1 = (TextView) findViewById(R.id.textView_Latitude);
    		String s_latitude = "Lat: "+ Double.toString(loc.getLatitude());
            MainActivity.latitude = (s_latitude);
            Log.i(gps_data,s_latitude);
            
            //TextView t2 = (TextView) findViewById(R.id.textView_Longitude);
            String s_longitude = "Lng: " +Double.toString(loc.getLongitude());
            MainActivity.longitude = (s_longitude);
            Log.i(gps_data,s_longitude);
            
            //TextView t3 = (TextView) findViewById(R.id.textView_Altitude);
            String s_altitude = "Alt: "+Double.toString(loc.getAltitude());
            MainActivity.altitude = (s_altitude);
            Log.i(gps_data,s_longitude);
            
            //TextView t4 = (TextView) findViewById(R.id.textView_Speed);
            double km = 3.6*loc.getSpeed();
            String s_speed = "Speed: "+Double.toString(km)+" km/h";
            MainActivity.speed = (s_speed);
            Log.i(gps_data,s_speed);
            
            Intent updateUI = new Intent("LOCATION_UPDATED");
            updateUI.putExtra("latitude", s_latitude);
            updateUI.putExtra("longitude", s_longitude);
            updateUI.putExtra("altitude", s_altitude);
            updateUI.putExtra("speed", s_speed);
            sendBroadcast(updateUI);
            
            

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



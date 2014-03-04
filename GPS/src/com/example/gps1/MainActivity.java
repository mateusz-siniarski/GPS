package com.example.gps1;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	private String tag = "Livssyklus";
	static Intent runServiceIntent;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d(tag, "Inne i onCreate()");
		registerReceiver(uiUpdated,new IntentFilter("LOCATION_UPDATED"));
		
	}
	
	public void startService(View v) {
		
		Log.d(tag, "Start Service knapp trykket");
		if(runServiceIntent == null)	{
			runServiceIntent = new Intent("com.example.gps1.action.LOG_POS");
			startService(runServiceIntent);
		}
	}
	
	public void stopService(View v) {
		
		Log.d(tag, "Stop Service knapp trykket");
		
		if(runServiceIntent != null)	{
			stopService(runServiceIntent);
			//unregisterReceiver(uiUpdated);
			runServiceIntent = null;
		}
	}
	
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
		unregisterReceiver(uiUpdated);
	}
	

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    BroadcastReceiver uiUpdated = new BroadcastReceiver()	{

		@Override
		public void onReceive(Context context, Intent intent) {
			TextView t1 = (TextView) findViewById(R.id.textView_Latitude);
            t1.setText( intent.getExtras().getString("latitude") );
            
            TextView t2 = (TextView) findViewById(R.id.textView_Longitude);
            t2.setText( intent.getExtras().getString("longitude") );
            
            TextView t3 = (TextView) findViewById(R.id.textView_Altitude);
            t3.setText( intent.getExtras().getString("altitude") );
            
            TextView t4 = (TextView) findViewById(R.id.textView_Speed);
            t4.setText( intent.getExtras().getString("speed") );
			
		}
    	
    };

}
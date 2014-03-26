package com.example.gps1;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Toast;

import com.example.gps1.database.ActivityDataSource;

public class Test extends Activity {
	
	private ActivityDataSource datasource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		
		datasource = new ActivityDataSource(this);

		datasource.open();
		
		
		float pi = (float) 3.14;
		int two = 2;
		
		//addToTable("Test", pi);
		addToTable("Trying", two);
		/*float distance = datasource.getDistance();
		Toast toast = new Toast(this);
		toast.setText(Float.toString( distance ));*/
	}

	
	public void addToTable(String type, int distance)	{
		datasource.createActivity(type, distance);
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test, menu);
		return true;
	}

}

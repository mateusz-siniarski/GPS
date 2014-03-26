package com.example.gps1.database;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ActivityDataSource {
	private SQLiteDatabase database;
	private SQLLiteHelper dbHelper;
	
	private String[] allActivityColumns = { Activities.COLUMN_ID,
			Activities.COLUMN_ACTIVITY_TYPE,
			Activities.COLUMN_ACTIVITY_DISTANCE,
			};
	
	public ActivityDataSource(Context context)	{
		dbHelper = new SQLLiteHelper(context);
	}
	
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	
	public void close()	{
		dbHelper.close();
	}
	
	public long createActivity(String type, int distance) {
		ContentValues values = new ContentValues();
		
		values.put(Activities.COLUMN_ACTIVITY_TYPE, type);
		values.put(Activities.COLUMN_ACTIVITY_DISTANCE, distance);
		
		/*
		values.put(A.COLUMN_TITLE, title);
		values.put(ShowTable.COLUMN_YEAR, year);
		values.put(ShowTable.COLUMN_IMDB_ID, imdbId);
		*/
		
		
		long insertId = database.insert(Activities.TABLE_ACTIVITIES, null, values);
		
		return insertId;
	}
	
	
	
	
	public float getDistance() {
		Cursor cursor = database.query(Activities.TABLE_ACTIVITIES,
				allActivityColumns, 
				null, null, null, null, null);
		cursor.moveToFirst();
		float distance = cursor.getFloat(1);
		cursor.close();
		
		return distance;
	}
	
	
	
	
}

package com.example.testApp;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
//import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.testApp.DatabaseContract.Users;

public class SearchActivity extends Activity {
	
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		// Show the Up button in the action bar.
		setupActionBar();	
		
		Intent intent = getIntent();
		String eyesColor = intent.getStringExtra(SearchRequestActivity.EYES);
		String skinType = intent.getStringExtra(SearchRequestActivity.SKIN);	
		String isCustomer = intent.getStringExtra(SearchRequestActivity.CUSTOMER);		
		
		
		ListView mainListView = (ListView) findViewById( R.id.searchListView );
		
		UsersDbHelper mDbHelper = new UsersDbHelper(this);
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
		String[] projection = {
			    Users.COLUMN_NAME_FNAME,
			    Users.COLUMN_NAME_LNAME,
			    Users.COLUMN_NAME_AGE			
			    };
		String sortOrder =
			    Users.COLUMN_NAME_FNAME + " DESC";
		String selection = Users.COLUMN_NAME_CUSTOMER +
				" = ? AND " + Users.COLUMN_NAME_EYESCOLOR + 
				" = ? AND " + Users.COLUMN_NAME_SKINTYPE + 
				" =  ? " ;
		
		String whereColumns[] = new String[]{isCustomer, eyesColor, skinType};
	

		Cursor cursor = db.query(
		    Users.TABLE_NAME,  					// The table to query
		    projection,                         // The columns to return
		    selection,		    			   // The columns for the WHERE clause
		    whereColumns,                      // The values for the WHERE clause
		    null,                              // don't group the rows
		    null,                              // don't filter by row groups
		    sortOrder                          // The sort order
		    );
		if (! cursor.moveToFirst()) return;
		ArrayList<String> textList = new ArrayList<String>();
		while (! cursor.isAfterLast()) {			
			textList.add( cursor.getString(cursor.getColumnIndex(Users.COLUMN_NAME_FNAME)) + 
					" " +
					cursor.getString(cursor.getColumnIndex(Users.COLUMN_NAME_LNAME)) +
					" - " +
					cursor.getString(cursor.getColumnIndex(Users.COLUMN_NAME_AGE))					
					);
			cursor.moveToNext();
		}
		//Create ArrayAdapter using the planet list.
		ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow , textList);  
        // Set the ArrayAdapter as the ListView's adapter.
        mainListView.setAdapter( listAdapter ); 		
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			//NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

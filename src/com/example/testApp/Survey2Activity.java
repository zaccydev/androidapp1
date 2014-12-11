package com.example.testApp;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.testApp.DatabaseContract.Users;

public class Survey2Activity extends Activity {	
	
	private long recordUserId = 0L;
	
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_survey2);
		// Show the Up button in the action bar.
		setupActionBar();	
			
		
		Intent intent = getIntent();
		String fname = intent.getStringExtra(MainActivity.FNAME);
		String lname = intent.getStringExtra(MainActivity.LNAME);
		String age = intent.getStringExtra(MainActivity.AGE);
		
		recordForm1Data(fname, lname, age);
		
		TextView formLabel = (TextView) findViewById(R.id.SurveyFormLabel);		
		formLabel.setText(this.getString(R.string.hello) + " " + fname + " " + lname + " " + this.getString(R.string.askuser) );
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
	
	public void validSurvey(View view) {
		//1 enregistrement (mise à jour)
		this.recordForm2Data();		
		//2 fin de l'enquête
		Intent intent = new Intent(this, DisplayMessageActivity.class);
    	startActivity(intent);
	}
	
	private void recordForm2Data() {
		String eyesColor = ((Spinner) findViewById(R.id.SpinnerEyesColor)).getSelectedItem().toString();
		String skinType = ((Spinner) findViewById(R.id.SpinnerSkinType)).getSelectedItem().toString();
		boolean isCustomer = ((CheckBox) findViewById(R.id.CheckBoxResponse)).isChecked();
		
		UsersDbHelper mDbHelper = new UsersDbHelper(this);
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(Users.COLUMN_NAME_EYESCOLOR, eyesColor);
		values.put(Users.COLUMN_NAME_SKINTYPE, skinType);
		values.put(Users.COLUMN_NAME_CUSTOMER, isCustomer ? 1 : 0);

		// Which row to update, based on the ID
		String selection = Users._ID + " LIKE ? ";
		String[] selectionArgs = { String.valueOf(recordUserId) };

		db.update(
		    Users.TABLE_NAME,
		    values,
		    selection,
		    selectionArgs);
		db.close();
	}
	
	private void recordForm1Data(String fname, String lname, String age) {
		UsersDbHelper mDbHelper = new UsersDbHelper(this);
		// Gets the data repository in write mode
		SQLiteDatabase db = mDbHelper.getWritableDatabase();

		// Create a new map of values, where column names are the keys
		ContentValues values = new ContentValues();
		//values.put(Users.COLUMN_NAME_ENTRY_ID, id);
		values.put(Users.COLUMN_NAME_FNAME, fname);
		values.put(Users.COLUMN_NAME_LNAME, lname);
		values.put(Users.COLUMN_NAME_AGE, age);

		// Insert the new row, returning the primary key value of the new row		
		recordUserId = db.insert(
					Users.TABLE_NAME,
					null,
		         values);
		db.close();
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

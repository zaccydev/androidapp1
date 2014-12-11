package com.example.testApp;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Spinner;

public class SearchRequestActivity extends Activity {


	public final static String SKIN = "com.example.testApp.SKIN";
	public final static String EYES = "com.example.testApp.EYES";
	public final static String CUSTOMER = "com.example.testApp.CUSTOMER";
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_request);
		// Show the Up button in the action bar.
		setupActionBar();	
			
	}
	
	public void validSearch(View view) {
		
		String eyesColor = ((Spinner) findViewById(R.id.SpinnerEyesColor)).getSelectedItem().toString();
		String skinType = ((Spinner) findViewById(R.id.SpinnerSkinType)).getSelectedItem().toString();
		String isCustomer = String.valueOf(((CheckBox) findViewById(R.id.CheckBoxResponse)).isChecked() ? 1 : 0);
		
		Intent intent = new Intent(this, SearchActivity.class);
		intent.putExtra(EYES, eyesColor);
		intent.putExtra(SKIN, skinType);
		intent.putExtra(CUSTOMER, isCustomer);
		
		startActivity(intent);
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
		getActionBar().hide();
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

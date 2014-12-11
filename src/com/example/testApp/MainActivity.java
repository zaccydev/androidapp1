package com.example.testApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity
{
	public final static String FNAME = "com.example.testApp.FNAME";
	public final static String LNAME = "com.example.testApp.LNAME";
	public final static String AGE = "com.example.testApp.AGE";
	
	private EditText etFname;
	private EditText etLname;
	private EditText etAge;
	private Button btnSubmit;
	private Intent intent;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.initListener();
    }
    
    private void initListener() {    	
    	//prenom
    	etFname = (EditText) findViewById(R.id.edit_fname);
    	// TextWatcher would let us check validation error on the fly
        etFname.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Form1Validation.isOnlyLetter(etFname, true);               
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
    	
    	//nom
    	etLname = (EditText) findViewById(R.id.edit_lname);      	
    	etLname.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Form1Validation.isOnlyLetter(etLname, true);              
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
    	
    	//age
    	etAge = (EditText) findViewById(R.id.edit_age);    	
    	etAge.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Form1Validation.isOnlyNumber(etAge, true);               
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
    	
    	btnSubmit = (Button) findViewById(R.id.button_send);
    	btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Validation class will check the error and display the error on respective fields
                but it won't resist the form submission, so we need to check again before submit
                 */
                if ( checkValidation () ) {                	
                    submitForm();
                }
                else
                    Toast.makeText(MainActivity.this, R.string.errorform, Toast.LENGTH_LONG).show();
            }
        });
    }
   
    private void prepareMessage() {
    
    	intent = new Intent(this, Survey2Activity.class);
    	//prenom
    	etFname = (EditText) findViewById(R.id.edit_fname);    	
    	String fname = etFname.getText().toString();   	
    	//nom
    	etLname = (EditText) findViewById(R.id.edit_lname);  
    	String  lname = etLname.getText().toString();
    	//age
    	etAge = (EditText) findViewById(R.id.edit_age);  
    	String age = etAge.getText().toString();
    	
    	intent.putExtra(FNAME, fname);
    	intent.putExtra(LNAME, lname);
    	intent.putExtra(AGE, age);    	
    }
    
    private void submitForm() {   
    	this.prepareMessage();       
        Toast.makeText(this, R.string.sendform, Toast.LENGTH_LONG).show();        
        startActivity(intent);
    }

    private boolean checkValidation() {
        boolean ret = true;

        if (!Form1Validation.isOnlyLetter(etFname, true)) ret = false;
        if (!Form1Validation.isOnlyLetter(etLname, true)) ret = false;
        if (!Form1Validation.isOnlyNumber(etAge, true)) ret = false;

        return ret;
    }
    
    public void searchUsers(View view) {
    	Intent intent = new Intent(this, SearchRequestActivity.class);
    	startActivity(intent);
    }  
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);  	
    
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_search:
                openSearch();
                return true;
            case R.id.action_settings:
                openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    public void openSearch() {
    	Intent intent = new Intent(this, SearchRequestActivity.class);
    	startActivity(intent);
    }
    
    public void openSettings() {
    	
    }
}

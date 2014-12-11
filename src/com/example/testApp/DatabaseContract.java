package com.example.testApp;

import android.provider.BaseColumns;

public class DatabaseContract {
	
	public DatabaseContract() {	}
	
	/* Inner class for table users */
    public static abstract class Users implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_FNAME = "fname";
        public static final String COLUMN_NAME_LNAME = "lname";
        public static final String COLUMN_NAME_AGE = "age";
        public static final String COLUMN_NAME_SKINTYPE = "skin";
        public static final String COLUMN_NAME_EYESCOLOR = "eyes";
        public static final String COLUMN_NAME_CUSTOMER = "customer";
        
    }
    

}

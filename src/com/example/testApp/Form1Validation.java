package com.example.testApp;

import java.util.regex.Pattern;

import android.widget.EditText;

public class Form1Validation {
	
    // Error Messages
    private static final String REQUIRED_MSG = "requis";  
    private static final String AGE_MSG = "chiffre uniquement";
    private static final String TXT_MSG = "lettre uniquement";

    private static final String TEXT_REGEX = "^[A-Za-z]+$";
    private static final String AGE_REGEX = "[0-9]+";
    

    public static boolean isOnlyNumber(EditText editText, boolean required) {
        return isValid(editText, AGE_REGEX, AGE_MSG, required);
    }
    
    public static boolean isOnlyLetter(EditText editText, boolean required) {
        return isValid(editText, TEXT_REGEX, TXT_MSG, required);
    }

    // return true if the input field is valid, based on the parameter passed
    public static boolean isValid(EditText editText, String regex, String errMsg, boolean required) {

        String text = editText.getText().toString().trim();
        // clearing the error, if it was previously set by some other values
        editText.setError(null);

        // text required and editText is blank, so return false
        if ( required && !hasText(editText) ) return false;

        // pattern doesn't match so returning false
        if (required && !Pattern.matches(regex, text)) {
            editText.setError(errMsg);
            return false;
        };

        return true;
    }

    // check the input field has any text or not
    // return true if it contains text otherwise false
    public static boolean hasText(EditText editText) {

        String text = editText.getText().toString().trim();
        editText.setError(null);

        // length 0 means there is no text
        if (text.length() == 0) {
            editText.setError(REQUIRED_MSG);
            return false;
        }

        return true;
    }
}

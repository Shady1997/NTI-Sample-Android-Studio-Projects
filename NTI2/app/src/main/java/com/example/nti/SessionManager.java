package com.example.nti;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context context;

    // Email address and password (public static to access from outside)
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    private static final String PREF_NAME = "UserPref";
    private static final String IS_LOGIN = "IsLoggedIn";

    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME,0);
        editor = pref.edit();
    }


    public void createLoginSession(String email, String pass) {

        editor.putBoolean(IS_LOGIN, true);
        // Storing email in pref
        editor.putString(KEY_EMAIL, email);
        // Storing password in pref
        editor.putString(KEY_PASSWORD, pass);
        editor.commit();
    }

    public boolean checkLogin() {
        return this.isLoggedIn();
    }

    //Get stored session data
    public HashMap<String, String> getUserDetails() {

        HashMap<String, String> user = new HashMap<>();
        // get email from pref
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        // get password from pref
        user.put(KEY_PASSWORD, pref.getString(KEY_PASSWORD, null));

        return user;
    }


    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
    }

    // Get Login State
    private boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }

}

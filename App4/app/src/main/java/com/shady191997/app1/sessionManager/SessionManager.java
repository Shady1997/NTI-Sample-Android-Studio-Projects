package com.shady191997.app1.sessionManager;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context context;

    public static final String KEY_EMAIL="email";
    public static final String KEY_PASSWORD="password";

    private static final String PREF_NAME="UserPref";
    private static final String IS_LOGIN="IsLoggedIn";

   public SessionManager(Context context)
   {
       this.context=context;
       pref=context.getSharedPreferences(PREF_NAME,0);
       editor=pref.edit();
   }

   public void createLoginSession(String email,String pass)
   {
       editor.putBoolean(IS_LOGIN,true);

       editor.putString(KEY_EMAIL,email);
       editor.putString(KEY_PASSWORD,pass);
       editor.commit();
   }

   public boolean checkLogin()
   {

       return this.isLoggedIn();
   }

   public HashMap<String,String >getUserDetails()
   {
       HashMap <String,String>user=new HashMap<>();
       user.put(KEY_EMAIL,pref.getString(KEY_EMAIL,null));
       user.put(KEY_PASSWORD,pref.getString(KEY_PASSWORD,null));

       return user;
   }

   public void LogoutUser()
   {
       editor.clear();
       editor.commit();
   }

   private boolean isLoggedIn()
   {
       return pref.getBoolean(IS_LOGIN,false);
   }




}

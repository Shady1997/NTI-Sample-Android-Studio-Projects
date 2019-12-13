package com.example.nti.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nti.R;
import com.example.nti.SessionManager;
import com.example.nti.activities.HomeActivity;
import com.example.nti.activities.RegistrationActivity;

public class SplashFragment extends Fragment {

    private final int SPLASH_DISPLAY_LENGTH = 3500;
    private SessionManager sessionManager;

    public SplashFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_splash, container, false);
        sessionManager = new SessionManager(getContext());
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                if(!sessionManager.checkUserSession()){
                    Log.println(Log.ERROR,"help","heeeeeeeeeeeeeeeey");
                    fragmentTransaction.replace(R.id.framelayout_registeration_view,new SigninFragment());
                    fragmentTransaction.commit();
                }
                else{
                    startActivity(new Intent(getContext(), HomeActivity.class));
                }

            }
        }, SPLASH_DISPLAY_LENGTH);
        return view;
    }

}

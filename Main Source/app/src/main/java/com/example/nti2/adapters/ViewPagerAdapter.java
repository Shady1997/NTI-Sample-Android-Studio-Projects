package com.example.nti2.adapters;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.nti2.fragments.LoginFragment;
import com.example.nti2.fragments.SignupFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter( FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new LoginFragment();
            case 1:
                return new SignupFragment();

        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0:
                return "Login";
            case 1:
                return "Sign up";

        }
        return super.getPageTitle(position);
    }
}

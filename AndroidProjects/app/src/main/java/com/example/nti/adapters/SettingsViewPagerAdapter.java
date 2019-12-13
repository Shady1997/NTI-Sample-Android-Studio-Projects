package com.example.nti.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.nti.fragments.AccountSettingsFragment;
import com.example.nti.fragments.AudioSettingsFragment;
import com.example.nti.fragments.ScreenSettingsFragment;
import com.example.nti.fragments.SigninFragment;
import com.example.nti.fragments.SignupFragment;

public class SettingsViewPagerAdapter extends FragmentPagerAdapter {

    public SettingsViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new AccountSettingsFragment();
            case 1:
                return new AudioSettingsFragment();
            case 2:
                return new ScreenSettingsFragment();

        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Account";
            case 1:
                return "Audio";
            case 2:
                return "Screen";

        }
        return super.getPageTitle(position);
    }
}

package com.example.nti.fragments;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nti.R;
import com.example.nti.adapters.SettingsViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        viewPager = view.findViewById(R.id.view_pager_settings);
        viewPager.setAdapter(new SettingsViewPagerAdapter(getChildFragmentManager()));

        tabLayout = view.findViewById(R.id.tabs_settings);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

}

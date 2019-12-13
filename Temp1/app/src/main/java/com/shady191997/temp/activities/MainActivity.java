package com.shady191997.temp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.shady191997.temp.R;
import com.shady191997.temp.adapters.ViewPagerAdapter;
import com.shady191997.temp.fragments.SplashFragment;

public class MainActivity extends AppCompatActivity {


    ViewPager viewPager;
    TabLayout tabLayout;
    ViewPagerAdapter viewPagerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container,new SplashFragment());
        fragmentTransaction.commit();



//        viewPager=findViewById(R.id.view_pager);
////        tabLayout=findViewById(R.id.tab_layout);
////        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
////
////        tabLayout.setupWithViewPager(viewPager);
////        viewPager.setAdapter(viewPagerAdapter);


    }
}

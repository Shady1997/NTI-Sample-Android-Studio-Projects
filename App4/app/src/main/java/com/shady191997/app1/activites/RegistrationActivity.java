package com.shady191997.app1.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.shady191997.app1.R;
import com.shady191997.app1.adapters.ViewPagerAdapter1;

public class RegistrationActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ViewPagerAdapter1 viewPagerAdapter1=new ViewPagerAdapter1(getSupportFragmentManager());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        mViewPager=findViewById(R.id.view_pager);
        mTabLayout=findViewById(R.id.tab_layout);


        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setAdapter(viewPagerAdapter1);








    }
}

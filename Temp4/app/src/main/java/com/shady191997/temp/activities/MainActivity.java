package com.shady191997.temp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.shady191997.temp.R;
import com.shady191997.temp.adapters.ViewPagerAdapter;
import com.shady191997.temp.fragments.LoginFragment;
import com.shady191997.temp.fragments.SplashFragment;

public class MainActivity extends AppCompatActivity {


    ViewPager viewPager;
    TabLayout tabLayout;
    ViewPagerAdapter viewPagerAdapter;

//    DrawerLayout drawerLayout;
//    NavigationView navigationView;

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                drawerLayout.openDrawer(GravityCompat.START);
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getSupportActionBar().hide();

        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container,new SplashFragment());
        fragmentTransaction.commit();



//        viewPager=findViewById(R.id.view_pager);
//        tabLayout=findViewById(R.id.tab_layout);
//        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());


//        tabLayout.setupWithViewPager(viewPager);
//        viewPager.setAdapter(viewPagerAdapter);



//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//
//                switch (menuItem.getItemId())
//                {
//                    case R.id.weather:
//                        Toast.makeText(MainActivity.this, "Weather", Toast.LENGTH_SHORT).show();
//                        break;
//
//                    case R.id.calender:
//                        Toast.makeText(MainActivity.this, "Calender", Toast.LENGTH_SHORT).show();
//                }
//                return true;
//            }
//        });
//




    }
}

package com.example.nti.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nti.R;
import com.example.nti.SessionManager;
import com.example.nti.adapters.HomeRvAdapter;
import com.example.nti.fragments.HomeFragment;
import com.example.nti.fragments.ProfileFragment;
import com.example.nti.fragments.SettingsFragment;
import com.example.nti.models.GithubUserModel;
import com.example.nti.webServices.ApiClient;
import com.example.nti.webServices.RetrofitFactory;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    RecyclerView rv;
    HomeRvAdapter rvAdapter;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    LinearLayoutManager layoutManager;
    SessionManager sessionManager;
    List<GithubUserModel> users = new ArrayList<>();
    ApiClient api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sessionManager = new SessionManager(this);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);




        Fragment homeFragment = new HomeFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.framelayout_view,homeFragment);
        ft.commit();



        View view = navigationView.getHeaderView(0);
        String email = sessionManager.getUserEmail();
        TextView textView = view.findViewById(R.id.tv_user_email);
        textView.setText(email);



        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_btn);




        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        swapFragment(R.id.action_home);
                        Toast.makeText(HomeActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_profile:
                        Toast.makeText(HomeActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                        swapFragment(R.id.action_profile);
                        break;
                    case R.id.action_settings:
                        Toast.makeText(HomeActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                        swapFragment(R.id.action_settings);
                        break;
                    case R.id.action_help:
                        Toast.makeText(HomeActivity.this, "Help", Toast.LENGTH_SHORT).show();
                         break;
                    case R.id.action_signout:
                        Toast.makeText(HomeActivity.this, "Signed Out", Toast.LENGTH_SHORT).show();
                        FirebaseAuth.getInstance().signOut();
                        sessionManager.clearSession();
                        startActivity(new Intent(HomeActivity.this,RegistrationActivity.class));
                }
                return true;
            }
        });


        //myRecyclerView();
        //getAllUsers();


    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!sessionManager.checkUserSession()){
            startActivity(new Intent(this, RegistrationActivity.class));
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                //Log.println(Log.INFO,"loggg","hello");
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void getAllUsers() {
        api = RetrofitFactory.getRetrofit().create(ApiClient.class);
        Call<List<GithubUserModel>> usersResponse = api.getusers();
        usersResponse.enqueue(new Callback<List<GithubUserModel>>() {
            @Override
            public void onResponse(Call<List<GithubUserModel>> call, Response<List<GithubUserModel>> response) {
                users.addAll(response.body());
                rvAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<GithubUserModel>> call, Throwable t) {

            }
        });
    }


    private void swapFragment(int id){
        Fragment f = null;
        if(id == R.id.action_home){
            f = new HomeFragment();
        }
        else if(id == R.id.action_profile){
            f = new ProfileFragment();
        }
        else if(id == R.id.action_settings){
            f = new SettingsFragment();
        }

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.framelayout_view,f);
        ft.commit();

    }
}

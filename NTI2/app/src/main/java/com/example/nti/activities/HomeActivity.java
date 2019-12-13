package com.example.nti.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nti.R;
import com.example.nti.SessionManager;
import com.example.nti.adapters.HomeRvAdapter;
import com.example.nti.models.GithubUsersModel;
import com.example.nti.models.GurdianResponse;
import com.example.nti.webservices.APIClient;
import com.example.nti.webservices.RetrofitFactory;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private HomeRvAdapter adapter;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private SessionManager sessionManager;


    List<GurdianResponse> studentsList = new ArrayList<>();
    private APIClient api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mRecyclerView = findViewById(R.id.rv_home);
        //adapter = new HomeRvAdapter(studentsList);

        setUpSideMenuButton();

        sessionManager = new SessionManager(HomeActivity.this);

//        Intent intent = getIntent();
//        String email = intent.getStringExtra("email");
        String email = sessionManager.getUserDetails().get(sessionManager.KEY_EMAIL);


        setUpNavDrawer(email);

        setRecyclerView();

        getAllUsers();
    }

    private void setUpNavDrawer(String email) {
        mNavigationView = findViewById(R.id.nav_view);
        mDrawerLayout = findViewById(R.id.drawer);
        View headerView = mNavigationView.getHeaderView(0);
        TextView tvEmail = headerView.findViewById(R.id.tv_header_name);
//        Log.d("test", email);
        tvEmail.setText(email);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        Toast.makeText(HomeActivity.this, "Home is clicked", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.action_help:
                        Toast.makeText(HomeActivity.this, "Help is clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_profile:
                        Toast.makeText(HomeActivity.this, "Profile is clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_settings:
                        Toast.makeText(HomeActivity.this, "Settings is clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_signout:
                        Toast.makeText(HomeActivity.this, "Signout is clicked", Toast.LENGTH_SHORT).show();
                        FirebaseAuth.getInstance().signOut();
                        ;
                        sessionManager.logoutUser();
                        Intent intent = new Intent(HomeActivity.this, RegisterationActivity.class);
                        startActivity(intent);

                }
                return true;
            }
        });
    }

    private void setUpSideMenuButton() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_button);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getAllUsers() {
        api = RetrofitFactory.getRetrofit().create(APIClient.class);

        Call<GurdianResponse> getAllUsers = api.getUsers();
        getAllUsers.enqueue(new Callback<GurdianResponse>() {
            @Override
            public void onResponse(Call<GurdianResponse> call, Response<GurdianResponse> response) {
                Toast.makeText(HomeActivity.this, "Done", Toast.LENGTH_SHORT).show();
                Log.d("Message ",String.valueOf(response.body().getResponse().get(0).getWebTitle()));
            }

            @Override
            public void onFailure(Call<GurdianResponse> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });

//        getAllUsers.enqueue(new Callback<GurdianResponse>() {
//            @Override
//            public void onResponse(Call<GurdianResponse> call, Response<GurdianResponse> response) {
//                Toast.makeText(HomeActivity.this, "Done", Toast.LENGTH_SHORT).show();
//               // studentsList.add(response.body().getResponse().get(0).getWebTitle());
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<GurdianResponse> call, Throwable t) {
//                Toast.makeText(HomeActivity.this, "Error", Toast.LENGTH_SHORT).show();
//                Log.d("error", t.toString());
//
//
//            }
//        });
    }

    private void setRecyclerView() {
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(),
                layoutManager.getOrientation()));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        checkUserLogin();
    }

    private void checkUserLogin() {
        boolean isLoggedIn = sessionManager.checkLogin();
        if (!isLoggedIn) {
            Intent intent = new Intent(HomeActivity.this, RegisterationActivity.class);
            startActivity(intent);
        }
    }
}

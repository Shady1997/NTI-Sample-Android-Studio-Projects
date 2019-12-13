package com.shady191997.app1.activites;

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
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.shady191997.app1.R;
import com.shady191997.app1.adapters.HomeRvAdapter;
import com.shady191997.app1.models.GitHubUsersModel;
import com.shady191997.app1.models.StudentModel;
import com.shady191997.app1.webservices.API;
import com.shady191997.app1.webservices.RetrofitFactory;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView rv_home;
    HomeRvAdapter homeRvAdapter;
    List<GitHubUsersModel> gitHubUsersModelList=new ArrayList<>();

    DrawerLayout drawerLayout;
    NavigationView navigationView;


    private API api;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        rv_home=findViewById(R.id.rv_home);



        homeRvAdapter=new HomeRvAdapter(this,gitHubUsersModelList);
//        StudentModel studentModel=new StudentModel("Shady","22");
//        studentModelList.add(studentModel);
//        studentModelList.add(studentModel);
//        studentModelList.add(studentModel);
//        studentModelList.add(studentModel);
//        studentModelList.add(studentModel);
//        studentModelList.add(studentModel);
//        studentModelList.add(studentModel);
//        studentModelList.add(studentModel);
//        studentModelList.add(studentModel);
//        studentModelList.add(studentModel);
//        studentModelList.add(studentModel);
//        studentModelList.add(studentModel);
//        studentModelList.add(studentModel);
//        studentModelList.add(studentModel);
//        studentModelList.add(studentModel);
//        studentModelList.add(studentModel);
//        studentModelList.add(studentModel);
//        studentModelList.add(studentModel);
//        studentModelList.add(studentModel);
//        studentModelList.add(studentModel);
//        studentModelList.add(studentModel);


        drawerLayout=findViewById(R.id.drawer);
        navigationView=findViewById(R.id.nav_view);

        String email=getIntent().getStringExtra("email");
        View headerView=navigationView.getHeaderView(0);
        TextView tvEmail=headerView.findViewById(R.id.tv_user);
        tvEmail.setText(email);




        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.action_help:
                        Toast.makeText(HomeActivity.this, "Welcome in help", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_home:
                        Toast.makeText(HomeActivity.this, "Welcome in home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_profile:
                        Toast.makeText(HomeActivity.this, "Welcome in Profile", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_setting:
                        Toast.makeText(HomeActivity.this, "Welcome in Settings", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_signout:
                        Toast.makeText(HomeActivity.this, "Welcome in sign Up", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });




//

        setUpRecyclerView();
        api= RetrofitFactory.getmRetrofit().create(API.class);
       Call<List<GitHubUsersModel>> getAllUsers=api.getUsers();
       getAllUsers.enqueue(new Callback<List<GitHubUsersModel>>() {
           @Override
           public void onResponse(Call<List<GitHubUsersModel>> call, Response<List<GitHubUsersModel>> response) {
               gitHubUsersModelList.addAll(response.body());
               homeRvAdapter.notifyDataSetChanged();
           }

           @Override
           public void onFailure(Call<List<GitHubUsersModel>> call, Throwable t) {

               Toast.makeText(HomeActivity.this,"Error",Toast.LENGTH_LONG).show();
               Log.d("Error",t.toString());
           }
       });

        rv_home.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            GestureDetector gestureDetector = new GestureDetector(HomeActivity.this, new GestureDetector.SimpleOnGestureListener() {

                @Override public boolean onSingleTapUp(MotionEvent motionEvent) {

                    return true;
                }

            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

                if(Recyclerview.findChildViewUnder(motionEvent.getX(), motionEvent.getY()) != null && gestureDetector.onTouchEvent(motionEvent)) {

                        //int loc =(int) rv_home.getChildLayoutPosition(Recyclerview);
                   // Toast.makeText(HomeActivity.this, "jhkjhkgk", Toast.LENGTH_LONG).show();
//                    Intent intent=new Intent(HomeActivity.this,RegistrationActivity.class);
//                    startActivity(intent);
                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

    }

    private void setUpRecyclerView() {
        LinearLayoutManager layoutManager=new LinearLayoutManager(HomeActivity.this,LinearLayoutManager.VERTICAL,false);
//
//
//        rv_home.setItemAnimator(new DefaultItemAnimator());
//        rv_home.addItemDecoration(new DividerItemDecoration(rv_home.getContext(),layoutManager.getOrientation()));
//
//
        rv_home.setLayoutManager(layoutManager);
        rv_home.setAdapter(homeRvAdapter);
    }
}

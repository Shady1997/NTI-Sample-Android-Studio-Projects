package com.example.nti2.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nti2.R;
import com.example.nti2.adapters.HomeRvAdapter;
import com.example.nti2.models.GithubUsersModel;
import com.example.nti2.webServices.APIClient;
import com.example.nti2.webServices.RetrofitFactory;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView homeRv;
    private APIClient api;
    HomeRvAdapter adapter;
    List<GithubUsersModel> studentsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        homeRv = findViewById(R.id.rv_home);
        adapter = new HomeRvAdapter(HomeActivity.this, studentsList);
        //setUpRecyclerView();
        api = RetrofitFactory.getRetrofit().create(APIClient.class);

        Call<List<GithubUsersModel>> getAllUsers = api.getUsers();

        getAllUsers.enqueue(new Callback<List<GithubUsersModel>>() {
            @Override
            public void onResponse(Call<List<GithubUsersModel>> call, Response<List<GithubUsersModel>> response) {
                studentsList.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<GithubUsersModel>> call, Throwable t) {

            }
        });
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.VERTICAL, false);
        homeRv.setItemAnimator(new DefaultItemAnimator());
        homeRv.addItemDecoration(new DividerItemDecoration(homeRv.getContext(), layoutManager.getOrientation()));
        homeRv.setLayoutManager(layoutManager);
        homeRv.setAdapter(adapter);
    }

}

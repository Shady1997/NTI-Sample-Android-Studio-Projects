package com.shady191997.weatherapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.shady191997.weatherapp.R;
import com.shady191997.weatherapp.adapters.HomeRvAdapter;
import com.shady191997.weatherapp.model.OpenWeatherModel;
import com.shady191997.weatherapp.model.WeatherModel;
import com.shady191997.weatherapp.webservices.API;
import com.shady191997.weatherapp.webservices.RetrofitFactory;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //Retrofit Interface and ListDataModel Declaration
    private API api;
    List<List<OpenWeatherModel>> weatherModelList=new ArrayList<>();

    //RecyclerView Declaration
    private RecyclerView rv_home;
    HomeRvAdapter homeRvAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //RecyclerView Connect
        rv_home = findViewById(R.id.rv_home);
        //homeRvAdapter = new HomeRvAdapter(MainActivity.this,weatherModelList);



        api = RetrofitFactory.getmRetrofit().create(API.class);
        Call<OpenWeatherModel> getAllWeather = api.getWeather();
        getAllWeather.enqueue(new Callback<OpenWeatherModel>() {
            @Override
            public void onResponse(Call<OpenWeatherModel> call, Response<OpenWeatherModel> response) {

                OpenWeatherModel x = response.body();

                Log.d("test", x.getList().get(0).getWeather().get(0).getDescription());
                Toast.makeText(MainActivity.this, "done", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<OpenWeatherModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
                Log.d("Error", t.toString());
            }
        });

    }
}

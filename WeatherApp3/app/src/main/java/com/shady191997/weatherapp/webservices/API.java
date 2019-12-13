package com.shady191997.weatherapp.webservices;



import com.shady191997.weatherapp.model.OpenWeatherModel;
import com.shady191997.weatherapp.model.WeatherModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {


 @GET("data/2.5/box/city?bbox=12,32,15,37,10&appid=b6907d289e10d714a6e88b30761fae22")
    Call <OpenWeatherModel> getWeather();
}

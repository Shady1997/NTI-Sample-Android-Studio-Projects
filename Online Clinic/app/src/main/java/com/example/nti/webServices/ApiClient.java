package com.example.nti.webServices;

import com.example.nti.models.GithubUserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiClient {

    @GET("users")
    Call<List<GithubUserModel>> getusers();


}

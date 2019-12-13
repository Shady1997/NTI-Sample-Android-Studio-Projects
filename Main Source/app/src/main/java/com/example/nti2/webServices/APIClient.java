package com.example.nti2.webServices;

import com.example.nti2.models.GithubUsersModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIClient {
    @GET("users")
    Call<List<GithubUsersModel>> getUsers();
}

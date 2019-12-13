package com.example.nti.webservices;

import com.example.nti.models.GithubUsersModel;
import com.example.nti.models.GurdianResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIClient {

    @GET("search?section=film&show-tags=contributor&format=json&lang=en&order-by=newest&show-fields=thumbnail&page-size=50&api-key=fc7e9c59-0ea7-496f-ac5c-2296be591711")
    Call<GurdianResponse> getUsers();




}

package com.shady191997.app1.webservices;


import com.shady191997.app1.models.GitHubUsersModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {


 @GET("users")
    Call <List<GitHubUsersModel>> getUsers();
}

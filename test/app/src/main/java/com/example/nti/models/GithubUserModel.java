package com.example.nti.models;

import com.google.gson.annotations.SerializedName;

public class GithubUserModel {


    @SerializedName("login")
    private String username;
    @SerializedName("html_url")
    private String userUrl;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }
}

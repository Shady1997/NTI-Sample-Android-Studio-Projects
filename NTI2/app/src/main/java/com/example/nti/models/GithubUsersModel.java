package com.example.nti.models;

import com.google.gson.annotations.SerializedName;

public class GithubUsersModel {

    @SerializedName("login")
    private String userName;

    @SerializedName("url")
    private String userUrl;

    @SerializedName("id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }


}

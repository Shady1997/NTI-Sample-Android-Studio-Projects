package com.shady191997.app1.models;

import com.google.gson.annotations.SerializedName;

public class GitHubUsersModel {
    @SerializedName("login")
    private String userName;

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

    @SerializedName("url")
    private String userUrl;
}

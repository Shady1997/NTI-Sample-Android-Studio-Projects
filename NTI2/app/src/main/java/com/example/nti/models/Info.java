package com.example.nti.models;

import java.util.List;

public class Info {
    public String getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public List<gif> getFields() {
        return fields;
    }

    public void setFields(List<gif> fields) {
        this.fields = fields;
    }

    String webTitle;
    String webUrl;
    List<gif> fields;


}

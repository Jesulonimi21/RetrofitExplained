package com.jesulonimi.user.retrofitexplained;

import com.google.gson.annotations.SerializedName;

public class Post {
    int id;
    int userId;
    String title;
    @SerializedName("body")
    String text;

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}

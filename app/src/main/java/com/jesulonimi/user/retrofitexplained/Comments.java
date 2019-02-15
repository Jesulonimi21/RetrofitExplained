package com.jesulonimi.user.retrofitexplained;

import com.google.gson.annotations.SerializedName;

public class Comments {
    private int postId;
    private int id;
    private String email;
    private String name;

    @SerializedName("body")
    private String text;

    public int getPostId() {
        return postId;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }
}

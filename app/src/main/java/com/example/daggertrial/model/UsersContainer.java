package com.example.daggertrial.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UsersContainer {

    @SerializedName("results")
    @Expose
    private List<User> results = null;

    public List<User> getUsers() {
        return results;
    }

}

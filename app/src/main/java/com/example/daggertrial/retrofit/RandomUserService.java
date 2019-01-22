package com.example.daggertrial.retrofit;

import com.example.daggertrial.model.UsersContainer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RandomUserService {

    @GET("api/")
    Call<UsersContainer> getRandomUsers(@Query("results") int numberOfUsers);
}

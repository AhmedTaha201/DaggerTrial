package com.example.daggertrial.di.modues;

import com.example.daggertrial.retrofit.RandomUserService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {

    @Provides
    static RandomUserService provideRandomUserService(Retrofit retrofit) {
        return retrofit.create(RandomUserService.class);
    }

    @Provides
    static Retrofit provideRetrofit() {
        return new Retrofit.Builder().baseUrl("https://randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}

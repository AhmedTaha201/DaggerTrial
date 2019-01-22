package com.example.daggertrial.di.components;

import com.example.daggertrial.di.modues.RetrofitModule;
import com.example.daggertrial.retrofit.RandomUserService;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = RetrofitModule.class)
public interface RetrofitComponent {

    RandomUserService randomUserService();
}

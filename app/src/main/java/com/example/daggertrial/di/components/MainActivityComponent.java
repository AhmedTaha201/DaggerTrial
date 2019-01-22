package com.example.daggertrial.di.components;

import com.example.daggertrial.MainActivity;
import com.example.daggertrial.di.modues.MainActivityModule;
import com.example.daggertrial.di.Scopes.MainActivityScope;


import dagger.Component;

@Component(modules = MainActivityModule.class, dependencies = RetrofitComponent.class)
@MainActivityScope
public interface MainActivityComponent {

    void injectActivity(MainActivity activity);
}

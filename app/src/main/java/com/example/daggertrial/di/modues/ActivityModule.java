package com.example.daggertrial.di.modues;

import android.app.Activity;
import android.content.Context;

import com.example.daggertrial.di.Qualifiers.ActivityContext;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final Context context;

    public ActivityModule(Activity context) {
        this.context = context;
    }

    @Provides
    @ActivityContext
    public Context provideActivityContext() {
        return context;
    }
}

package com.example.daggertrial;

import android.app.Activity;
import android.app.Application;

import com.example.daggertrial.di.components.DaggerRetrofitComponent;
import com.example.daggertrial.di.components.RetrofitComponent;

public class RandomUserApplication extends Application {

     private RetrofitComponent retrofitComponent;

    public static RandomUserApplication get(Activity activity) {
        return (RandomUserApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        retrofitComponent = DaggerRetrofitComponent.create();
    }

    public RetrofitComponent getRetrofitComponent() {
        return retrofitComponent;
    }
}

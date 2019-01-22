package com.example.daggertrial.di.modues;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.daggertrial.RandomUserAdapter;
import com.example.daggertrial.di.Qualifiers.ActivityContext;
import com.example.daggertrial.di.Scopes.MainActivityScope;

import dagger.Module;
import dagger.Provides;

@Module(includes = ActivityModule.class)
@MainActivityScope
public class MainActivityModule {

    @Provides
    @MainActivityScope
    RandomUserAdapter provideAdapter(@ActivityContext Context context) {
        return new RandomUserAdapter(context);
    }

    @Provides
    @MainActivityScope
    RecyclerView.LayoutManager providesLayoutManager(@ActivityContext Context context) {
        return new LinearLayoutManager(context);
    }
}

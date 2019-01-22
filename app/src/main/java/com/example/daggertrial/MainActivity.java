package com.example.daggertrial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.daggertrial.di.Scopes.MainActivityScope;
import com.example.daggertrial.di.modues.ActivityModule;
import com.example.daggertrial.di.modues.MainActivityModule;
import com.example.daggertrial.di.components.DaggerMainActivityComponent;
import com.example.daggertrial.di.components.MainActivityComponent;
import com.example.daggertrial.model.User;
import com.example.daggertrial.model.UsersContainer;
import com.example.daggertrial.retrofit.RandomUserService;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_users_recycler_view)
    RecyclerView recyclerView;

    @Inject
    @MainActivityScope
    RandomUserService randomUserService;

    @Inject
    @MainActivityScope
    RandomUserAdapter randomUserAdapter;

    @Inject
    @MainActivityScope
    RecyclerView.LayoutManager recyclerLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timber.plant(new Timber.DebugTree());
        ButterKnife.bind(this);

        MainActivityComponent component = DaggerMainActivityComponent.builder()
                .retrofitComponent(RandomUserApplication.get(this).getRetrofitComponent())
                .activityModule(new ActivityModule(this))
                .mainActivityModule(new MainActivityModule())
                .build();

        component.injectActivity(this);

        populateUsers();

    }

    void populateUsers() {
        recyclerView.setLayoutManager(recyclerLayoutManager);
        recyclerView.setAdapter(randomUserAdapter);

        //Fetching data
        Call<UsersContainer> usersContainerCall = randomUserService.getRandomUsers(15);
        Timber.d(randomUserService.toString());
        usersContainerCall.enqueue(new Callback<UsersContainer>() {
            @Override
            public void onResponse(Call<UsersContainer> call, Response<UsersContainer> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<User> userList = response.body().getUsers();
                    randomUserAdapter.setUserList(userList);
                }
            }

            @Override
            public void onFailure(Call<UsersContainer> call, Throwable t) {
                Timber.e("Failed to fetch random usres data");
                t.printStackTrace();
            }
        });
    }
}

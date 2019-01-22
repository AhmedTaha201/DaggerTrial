package com.example.daggertrial;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.daggertrial.di.Qualifiers;
import com.example.daggertrial.di.Scopes.MainActivityScope;
import com.example.daggertrial.model.User;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class RandomUserAdapter extends RecyclerView.Adapter<RandomUserAdapter.UserViewHolder> {

    private Context context;

    private List<User> userList;

    @Inject
    public RandomUserAdapter(@Qualifiers.ActivityContext Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.random_user_list_item, parent, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RandomUserAdapter.UserViewHolder holder, int position) {
        User currentUser = userList.get(position);

        Glide.with(context).load(currentUser.getPicture().getMedium())
                .into(holder.userImage);

        String userName = currentUser.getName().getTitle() + ". " + currentUser.getName().getFirst() + " " + currentUser.getName().getLast();
        holder.userName.setText(userName);

        holder.userAge.setText(String.valueOf(currentUser.getDob().getAge()));

        String location = currentUser.getLocation().getStreet() + ", " + currentUser.getLocation().getCity();
        holder.userLocation.setText(location);
    }

    @Override
    public int getItemCount() {
        return userList != null ? userList.size() : 0;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id._recycler_item_user_image)
        ImageView userImage;

        @BindView(R.id._recycler_item_user_name)
        TextView userName;

        @BindView(R.id._recycler_item_user_age)
        TextView userAge;

        @BindView(R.id._recycler_item_user_location)
        TextView userLocation;

        UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

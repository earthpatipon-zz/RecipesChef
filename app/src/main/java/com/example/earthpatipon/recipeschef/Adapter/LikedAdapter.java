package com.example.earthpatipon.recipeschef.Adapter;

import android.content.Context;

import com.example.earthpatipon.recipeschef.entity.User;

public class LikedAdapter {//extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {

    private Context context;
    private User user;

    public LikedAdapter(Context context, User user) {
        this.context = context;
        this.user = user;
    }
}

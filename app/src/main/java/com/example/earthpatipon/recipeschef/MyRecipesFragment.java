package com.example.earthpatipon.recipeschef;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyRecipesFragment extends Fragment {

    private View view;

    public MyRecipesFragment() {
        //Empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user_liked, container, false);

        return view;
    }
}

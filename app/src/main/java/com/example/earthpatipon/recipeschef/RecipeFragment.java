package com.example.earthpatipon.recipeschef;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.earthpatipon.recipeschef.database.AppDatabase;
import com.example.earthpatipon.recipeschef.entity.Recipe;
import com.example.earthpatipon.recipeschef.utils.RecipeAdapter;
import com.example.earthpatipon.recipeschef.utils.RecipeCard;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeFragment extends Fragment {

    private List<RecipeCard> recipeList;
    private Context context;

    private RecyclerView recyclerView;

    private RecipeAdapter recipeAdapter;

    public RecipeFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity().getApplicationContext();
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe, container, false);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        HomeActivity activity = (HomeActivity) getActivity();
        recipeAdapter = activity.getRecipeAdapter();
        recipeList = activity.getRecipeList();

        recyclerView = view.findViewById(R.id.RecipeCardView);
        recyclerView.setHasFixedSize(true);

        if (recipeList.size() > 0 & recyclerView != null) {
            recyclerView.setAdapter(new RecipeAdapter(context, recipeList));
        }
        recyclerView.setLayoutManager(MyLayoutManager);
        return view;
    }


}
package com.example.earthpatipon.recipeschef;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.earthpatipon.recipeschef.utils.HomeAdapter;
import com.example.earthpatipon.recipeschef.utils.RecipeCard;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private List<RecipeCard> recipeList;
    private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;

    public HomeFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        MainActivity activity = (MainActivity) getActivity();
        homeAdapter = activity.getHomeAdapter();
        recipeList = activity.getRecipeList();

        recyclerView = view.findViewById(R.id.homeRecycleView);
        recyclerView.setHasFixedSize(true);

        if (recipeList.size() > 0 & recyclerView != null) {
            recyclerView.setAdapter(homeAdapter);
        }
        recyclerView.setLayoutManager(MyLayoutManager);
        return view;
    }

}
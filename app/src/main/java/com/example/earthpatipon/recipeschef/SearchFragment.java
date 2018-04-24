package com.example.earthpatipon.recipeschef;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.earthpatipon.recipeschef.utils.RecipeCard;
import com.example.earthpatipon.recipeschef.utils.SearchAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private List<RecipeCard> recipeList;
    private Context context;
    private RecyclerView recyclerView;

    public SearchFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity().getApplicationContext();
        setHasOptionsMenu(true); // Add option menu
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        HomeActivity activity = (HomeActivity) getActivity();

        recyclerView = view.findViewById(R.id.cardView);
        recyclerView.setHasFixedSize(true);
        recipeList = activity.getRecipeList();
        //Log.d("size", Integer.toString(recipeList.size()));
        if (recipeList.size() > 0 & recyclerView != null) {
            recyclerView.setAdapter(new SearchAdapter(context, recipeList));
        }
        recyclerView.setLayoutManager(MyLayoutManager);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        //adapter.getFilter().filter(newText);
                        return true;
                    }
                }
        );

    }
}
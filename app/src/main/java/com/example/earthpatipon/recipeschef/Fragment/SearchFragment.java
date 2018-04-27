/* Group: Aoong Aoong
 * Members: Tanaporn 5888124, Kanjanaporn 5888178, Patipon 5888218
 */
package com.example.earthpatipon.recipeschef.Fragment;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.earthpatipon.recipeschef.MainActivity;
import com.example.earthpatipon.recipeschef.R;
import com.example.earthpatipon.recipeschef.entity.RecipeCard;
import com.example.earthpatipon.recipeschef.Adapter.SearchAdapter;

import java.util.List;

public class SearchFragment extends Fragment {

    private List<RecipeCard> recipeList;
    private RecyclerView recyclerView;
    private SearchView searchView;

    private SearchAdapter searchAdapter;

    public SearchFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        MainActivity activity = (MainActivity) getActivity();
        searchAdapter = activity.getSearchAdapter();
        recipeList = activity.getCardList();

        recyclerView = view.findViewById(R.id.searchRecycleView);
        recyclerView.setHasFixedSize(true);

        if (recipeList.size() > 0 & recyclerView != null) {
            recyclerView.setAdapter(searchAdapter);
        }
        recyclerView.setLayoutManager(MyLayoutManager);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.search_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                searchAdapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                searchAdapter.getFilter().filter(query);
                return true;
            }
        });
    }
}
/* Group: Aoong Aoong
 * Members: Tanaporn 5888124, Kanjanaporn 5888178, Patipon 5888218
 */
package com.example.earthpatipon.recipeschef.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.earthpatipon.recipeschef.Adapter.HomeAdapter;
import com.example.earthpatipon.recipeschef.MainActivity;
import com.example.earthpatipon.recipeschef.R;
import com.example.earthpatipon.recipeschef.entity.RecipeCard;

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
        recipeList = activity.getCardList();

        recyclerView = view.findViewById(R.id.homeRecycleView);
        recyclerView.setHasFixedSize(true);

        if (recipeList.size() > 0 & recyclerView != null) {
            recyclerView.setAdapter(homeAdapter);
        }
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        //recyclerView.setLayoutManager(MyLayoutManager);
        return view;
    }
}
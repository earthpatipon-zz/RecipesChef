package com.example.earthpatipon.recipeschef;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private TextView textView;
//    private List<RecipeCard> recipeList;
//    private RecyclerView recyclerView;
//    private HomeAdapter homeAdapter;

    public ProfileFragment() {
        //Empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    //    @Override
//    public void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
//    }

//    package com.example.earthpatipon.recipeschef.utils;
//
//public class RecipeCard {
//
//    private String cardName;
//    private int isFav;
//    private int isTurned;
//
//    public String getCardName() {
//        return cardName;
//    }
//
//    public void setCardName(String cardName) {
//        this.cardName = cardName;
//    }
//
//    public int getIsFav() {
//        return isFav;
//    }
//
//    public void setIsFav(int isFav) {
//        this.isFav = isFav;
//    }
//
//    public int getIsTurned() {
//        return isTurned;
//    }
//
//    public void setIsTurned(int isTurned) {
//        this.isTurned = isTurned;
//    }
//}


}
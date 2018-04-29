///* Group: Aoong Aoong
// * Members: Tanaporn 5888124, Kanjanaporn 5888178, Patipon 5888218
// */
package com.example.earthpatipon.recipeschef.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.earthpatipon.recipeschef.EditProfileActivity;
import com.example.earthpatipon.recipeschef.adapter.LikeAdapter;
import com.example.earthpatipon.recipeschef.MainActivity;
import com.example.earthpatipon.recipeschef.R;
import com.example.earthpatipon.recipeschef.entity.RecipeCard;

import java.util.List;

public class ProfileFragment extends Fragment {

    private List<RecipeCard> likeCardList;
    private RecyclerView recyclerView;
    private LikeAdapter likeAdapter;

    private TextView userNameText;
    private Button editProfileButton;


    public ProfileFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume(){

        MainActivity activity = (MainActivity) getActivity();
        likeAdapter = activity.getLikeAdapter();
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        userNameText = view.findViewById(R.id.user_name);
        editProfileButton = view.findViewById(R.id.edit_profile_button);
        recyclerView = view.findViewById(R.id.recycleView_like);

        MainActivity activity = (MainActivity) getActivity();
        userNameText.setText(activity.getUser().getUserName().toString());
        likeCardList = activity.getLikeCardList();
        likeAdapter = activity.getLikeAdapter();

        if (likeCardList.size() > 0 & recyclerView != null) {
            recyclerView.setAdapter(likeAdapter);
        }
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                intent.putExtra("SENDER_KEY", "ProfileFragment"); // put extra info
                intent.putExtra("NAME_KEY", userNameText.getText().toString());
                getActivity().startActivity(intent);
            }
        });

        return view;
    }
}
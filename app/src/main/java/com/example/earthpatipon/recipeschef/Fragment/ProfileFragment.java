
///* Group: Aoong Aoong
// * Members: Tanaporn 5888124, Kanjanaporn 5888178, Patipon 5888218
// */
//package com.example.earthpatipon.recipeschef.Fragment;
//
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.GridLayout;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import com.example.earthpatipon.recipeschef.Adapter.LikedAdapter;
//import com.example.earthpatipon.recipeschef.Adapter.ProfileAdapter;
//import com.example.earthpatipon.recipeschef.MainActivity;
//import com.example.earthpatipon.recipeschef.R;
//import com.example.earthpatipon.recipeschef.entity.User;
//
//
///**
// * A simple {@link Fragment} subclass.
// */
//public class ProfileFragment extends Fragment {
//
//    private TextView userNameText;
////    private Button editProfileButton;
//    private ProfileAdapter profileAdapter;
//    private LikedAdapter likedAdapter;
//    private ListView profileView;
//    private RecyclerView liked;
//    private User user;
//
//    public ProfileFragment() { }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//        View view = inflater.inflate(R.layout.fragment_profile, container, false);
//        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
//        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//
//        MainActivity activity = (MainActivity) getActivity();
//        profileAdapter = activity.getProfileAdapter();
//
//        profileView = view.findViewById(R.id.profileView);
////        profileView.setAdapter(ProfileAdapter);
//
////        userNameText = getView().findViewById(R.id.user_name);
////        editProfileButton = getView().findViewById(R.id.edit_profile_button);
////
////        userNameText.setText(profileAdapter.getUserName());
////        editProfileButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                // Change fragment to edit profile
////                EditProfileFragment editProfileFragment = new EditProfileFragment();
////                FragmentManager fragmentManager = getFragmentManager();
////                fragmentManager.beginTransaction().replace(R.id.edit_profile_fragment, editProfileFragment, editProfileFragment.getTag());
////            }
////        });
//
//        liked = view.findViewById(R.id.likedRecycleView);
//        likedAdapter = ((MainActivity) getActivity()).getLikedAdapter();
//        liked.setAdapter(LikedAdapter);
//
//        return view;
//    }
//}

        package com.example.earthpatipon.recipeschef.Fragment;

        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v7.widget.LinearLayoutManager;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.TextView;

        import com.example.earthpatipon.recipeschef.Adapter.ProfileAdapter;
        import com.example.earthpatipon.recipeschef.MainActivity;
        import com.example.earthpatipon.recipeschef.R;
        import com.example.earthpatipon.recipeschef.entity.User;

public class ProfileFragment extends Fragment {

    TextView userNameText;
    Button editProfileButton;
    ProfileAdapter profileAdapter;
    User user;

    public ProfileFragment() { }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        MainActivity activity = (MainActivity) getActivity();
        profileAdapter = activity.getProfileAdapter();

        userNameText = view.findViewById(R.id.user_name);
        editProfileButton = view.findViewById(R.id.edit_profile_button);

        userNameText.setText(user.getUserName());
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change fragment to edit profile
                EditProfileFragment editProfileFragment = new EditProfileFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.edit_profile_fragment, editProfileFragment, editProfileFragment.getTag());
            }
        });

        return view;
    }
}
package com.example.earthpatipon.recipeschef;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.earthpatipon.recipeschef.utils.ViewPagerAdapter;

public class ProfileActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        appBarLayout = (AppBarLayout) findViewById(R.id.profile_main);
        viewPager = (ViewPager) findViewById(R.id.user_function);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(new LikedFragment(), "liked_fragment");
        viewPagerAdapter.addFragment(new MyRecipesFragment(), "my_recipes_fragment");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}

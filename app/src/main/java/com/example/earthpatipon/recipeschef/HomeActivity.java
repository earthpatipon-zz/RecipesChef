/* Group: Aoong Aoong
 * Members: Tanaporn 5888124, Kanjanaporn 5888178, Patipon 5888218
 */
package com.example.earthpatipon.recipeschef;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.earthpatipon.recipeschef.database.AppDatabase;
import com.example.earthpatipon.recipeschef.entity.Recipe;
import com.example.earthpatipon.recipeschef.utils.HomeAdapter;
import com.example.earthpatipon.recipeschef.utils.RecipeCard;
import com.example.earthpatipon.recipeschef.utils.SearchAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawer;
    private Toolbar toolbar;

    private HomeAdapter homeAdapter;
    private SearchAdapter searchAdapter;

    private List<RecipeCard> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initializeRecipeList();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");

        drawer = findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this, drawer, R.string.action_open, R.string.action_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item) == true) {
            return true;
        }
        return onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.home:
                replaceFragment(HomeFragment.class);
                break;
            case R.id.search:
                replaceFragment(SearchFragment.class);
                break;
            case R.id.profile:
                //replaceFragment(ProfileFragment.class);
                break;
            case R.id.logout:
//                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
//                startActivity(intent);
                break;
            default:
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public List<RecipeCard> getRecipeList() {
        return recipeList;
    }

    public HomeAdapter getHomeAdapter() {
        return homeAdapter;
    }

    public SearchAdapter getSearchAdapter() {
        return searchAdapter;
    }

    private void initializeRecipeList() {
        recipeList = new ArrayList<>();
        List<Recipe> recipes = AppDatabase.getInstance(this).recipeDao().getAllRecipe();

        for (int i = 0; i < recipes.size(); i++) {
            RecipeCard card = new RecipeCard();
            card.setCardName(recipes.get(i).getRecipeName());
            card.setIsFav(0);
            card.setIsTurned(0);
            this.recipeList.add(card);
        }

        homeAdapter = new HomeAdapter(this, recipeList);
        searchAdapter = new SearchAdapter(this, recipeList);

        replaceFragment(HomeFragment.class);
    }


    public void replaceFragment(Class fragmentClass) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
    }
}


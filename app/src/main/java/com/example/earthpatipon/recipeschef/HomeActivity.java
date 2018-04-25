/* Group: Aoong Aoong
 * Members: Tanaporn 5888124, Kanjanaporn 5888178, Patipon 5888218
 */
package com.example.earthpatipon.recipeschef;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.earthpatipon.recipeschef.database.AppDatabase;
import com.example.earthpatipon.recipeschef.entity.Recipe;
import com.example.earthpatipon.recipeschef.utils.RecipeAdapter;
import com.example.earthpatipon.recipeschef.utils.RecipeCard;
import com.example.earthpatipon.recipeschef.utils.SearchAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawer;
    private Toolbar toolbar;

    private RecipeAdapter recipeAdapter;
    private SearchAdapter searchAdapter;

    private List<RecipeCard> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recipeList = new ArrayList<>();
        initializeRecipeList();

        recipeAdapter = new RecipeAdapter(this, recipeList);
        searchAdapter = new SearchAdapter(this, recipeList);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(HomeActivity.this, drawer, R.string.action_open, R.string.action_close);

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
                break;
            case R.id.recipe:
                replaceFragment(RecipeFragment.class);
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

    public RecipeAdapter getRecipeAdapter() {
        return recipeAdapter;
    }

    public SearchAdapter getSearchAdapter() {
        return searchAdapter;
    }

    private void initializeRecipeList() {
        List<Recipe> recipes = AppDatabase.getInstance(this).recipeDao().getAllRecipe();

        for (int i = 0; i < recipes.size(); i++) {
            RecipeCard card = new RecipeCard();
            card.setCardName(recipes.get(i).getRecipeName());
            card.setIsFav(0);
            card.setIsTurned(0);
            this.recipeList.add(card);
        }
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


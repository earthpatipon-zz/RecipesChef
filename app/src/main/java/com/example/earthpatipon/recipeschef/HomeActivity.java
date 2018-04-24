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
import android.view.Menu;
import android.view.MenuItem;

import com.example.earthpatipon.recipeschef.database.AppDatabase;
import com.example.earthpatipon.recipeschef.entity.Recipe;
import com.example.earthpatipon.recipeschef.utils.RecipeAdapter;
import com.example.earthpatipon.recipeschef.utils.RecipeCard;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawer;
    private RecipeAdapter recipeAdapter;
    private SearchView searchView;
    private Toolbar toolbar;

    private List<RecipeCard> recipeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initializeCard();

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

        if (fragment == null) {
            fragment = new RecipeFragment();
            fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
        }

        recipeAdapter = new RecipeAdapter(this, recipeList);

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
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                recipeAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                recipeAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.home) {

        }
        else if (id == R.id.recipe) {
            //Intent intent = new Intent(HomeActivity.this, RecipeActivity.class);
            //startActivity(intent);
        }
        else if (id == R.id.search) {
            //Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
            //startActivity(intent);
        }
        else if (id == R.id.profile) {
            //Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
            //startActivity(intent);
        }
        else if (id == R.id.logout) {
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initializeCard() {
        List<Recipe> recipeList = AppDatabase.getInstance(this).recipeDao().getAllRecipe();

        for (int i = 0; i < recipeList.size(); i++) {
            RecipeCard item = new RecipeCard();
            item.setCardName(recipeList.get(i).getRecipeName());
            item.setIsFav(0);
            item.setIsTurned(0);
            this.recipeList.add(item);
        }
    }

    public List<RecipeCard> getRecipeList(){
        return recipeList;
    }
}

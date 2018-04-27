/* Group: Aoong Aoong
 * Members: Tanaporn 5888124, Kanjanaporn 5888178, Patipon 5888218
 */
package com.example.earthpatipon.recipeschef;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.earthpatipon.recipeschef.Fragment.HomeFragment;
import com.example.earthpatipon.recipeschef.Fragment.SearchFragment;
import com.example.earthpatipon.recipeschef.database.AppDatabase;
import com.example.earthpatipon.recipeschef.entity.Recipe;
import com.example.earthpatipon.recipeschef.entity.User;
import com.example.earthpatipon.recipeschef.Adapter.HomeAdapter;
import com.example.earthpatipon.recipeschef.entity.RecipeCard;
import com.example.earthpatipon.recipeschef.Adapter.SearchAdapter;
import com.example.earthpatipon.recipeschef.entity.UserLike;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawer;
    private Toolbar toolbar;

    private HomeAdapter homeAdapter;
    private SearchAdapter searchAdapter;

    private List<RecipeCard> cardList;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getCurrentUser(); // get User session
        initCardList(); // get list of Recipe
        initToolbar(); // get Toolbar
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
                getSupportActionBar().setTitle("Search");
                replaceFragment(SearchFragment.class);
                break;
            case R.id.profile:
<<<<<<< HEAD
                getSupportActionBar().setTitle("Profile");
                replaceFragment(ProfileFragment.class);
=======
                //replaceFragment(ProfileFragment.class);
>>>>>>> 37ce86c0f574e039d12f5574bd181bd8385b0c7e
                break;
            case R.id.logout:
                Intent logout_intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(logout_intent);
                break;
            default:
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void getCurrentUser() {

        String userName = "";

        final String sender = this.getIntent().getExtras().getString("SENDER_KEY");
        if (sender != null) {
            Intent intent = getIntent();
            userName = intent.getStringExtra("NAME_KEY");
        }

        user = AppDatabase.getInstance(getApplicationContext()).userDao().findByName(userName);
    }

    private void initCardList() {

        cardList = new ArrayList<>();

        List<UserLike> ulList = AppDatabase.getInstance(this).userLikeDao().findByUserID(user.getUserID());
        List<Recipe> recipes = AppDatabase.getInstance(this).recipeDao().getAllRecipe();

        for (int i = 0; i < recipes.size(); i++) {
            RecipeCard card = new RecipeCard();
            card.setCardName(recipes.get(i).getRecipeName());
            card.setId(recipes.get(i).getRecipeID());
            card.setIsLiked(0);
            for(UserLike ul : ulList){
                if(ul.getRecipeID() == recipes.get(i).getRecipeID()){
                    card.setIsLiked(1);
                    break;
                }
            }
            this.cardList.add(card);
        }

        homeAdapter = new HomeAdapter(this, cardList, user);
        searchAdapter = new SearchAdapter(this, cardList);

        replaceFragment(HomeFragment.class);
    }

    private void initToolbar(){

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


    private void replaceFragment(Class fragmentClass) {

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

    public User getUser(){ return user; }

    public List<RecipeCard> getCardList() {
        return cardList;
    }

    public HomeAdapter getHomeAdapter() {
        return homeAdapter;
    }

    public SearchAdapter getSearchAdapter() {
        return searchAdapter;
    }
}


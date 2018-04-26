/* Group: Aoong Aoong
 * Members: Tanaporn 5888124, Kanjanaporn 5888178, Patipon 5888218
 */
package com.example.earthpatipon.recipeschef;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.earthpatipon.recipeschef.database.AppDatabase;
import com.example.earthpatipon.recipeschef.entity.Recipe;

public class RecipeActivity extends AppCompatActivity {

    private Recipe recipeCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        getRecipeOnClick();
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish(); // this method is to call the rest of android lifecycle component i.e, onDestroy
        //overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    private void getRecipeOnClick(){

        String recipeName = "";

        final String sender = this.getIntent().getExtras().getString("SENDER_KEY");
        if (sender != null) {
            Intent intent = getIntent();
            recipeName = intent.getStringExtra("NAME_KEY");
        }

        recipeCurrent = AppDatabase.getInstance(getApplicationContext()).recipeDao().getRecipe(recipeName);
    }
}

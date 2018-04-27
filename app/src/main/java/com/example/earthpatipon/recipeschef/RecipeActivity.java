/* Group: Aoong Aoong
 * Members: Tanaporn 5888124, Kanjanaporn 5888178, Patipon 5888218
 */
package com.example.earthpatipon.recipeschef;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.earthpatipon.recipeschef.database.AppDatabase;
import com.example.earthpatipon.recipeschef.entity.Recipe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeActivity extends AppCompatActivity {

    @BindView(R.id.recipe_image) ImageView recipeImage;
    @BindView(R.id.recipe_name) TextView recipeName;
    @BindView(R.id.recipe_description) TextView recipeDesciption;
    @BindView(R.id.recipe_difficulty) TextView recipeDifficulty;
    @BindView(R.id.recipe_time) TextView recipeTime;
    @BindView(R.id.recipe_serve) TextView recipeServe;
    @BindView(R.id.recipe_ingredient) TextView recipeIngredient;
    @BindView(R.id.recipe_instruction) TextView recipeInstruction;
    @BindView(R.id.recipe_category) TextView recipeCategory;

    private Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        ButterKnife.bind(this);

        getCurrentRecipe();

        File file = new File(getFilesDir().getPath() + File.separator + "RecipeImages",recipe.getRecipeName() + ".png");
        Uri imageUri = Uri.fromFile(file);
        Glide.with(this).load(imageUri).into(recipeImage);

        recipeName.setText(recipe.getRecipeName());
        recipeDesciption.setText(recipe.getDescription());
        recipeDifficulty.setText(recipe.getDifficulty());
        recipeTime.setText(Integer.toString(recipe.getTime()));
        recipeServe.setText(Integer.toString(recipe.getServe()));
        recipeIngredient.setText(recipe.getIngredient());
        recipeInstruction.setText(recipe.getInstruction());
        recipeCategory.setText(recipe.getCategory());
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish(); // this method is to call the rest of android lifecycle component i.e, onDestroy
        //overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    private void getCurrentRecipe(){

        String recipeName = "";

        final String sender = this.getIntent().getExtras().getString("SENDER_KEY");
        if (sender != null) {
            Intent intent = getIntent();
            recipeName = intent.getStringExtra("NAME_KEY");
        }

        recipe = AppDatabase.getInstance(getApplicationContext()).recipeDao().getRecipe(recipeName);
    }


}

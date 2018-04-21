/* Group: Aoong Aoong
 * Members: Tanaporn 5888124, Kanjanaporn 5888178, Patipon 5888218
 */
package com.example.earthpatipon.recipeschef.utils;

import android.content.Context;
import android.os.AsyncTask;

import com.example.earthpatipon.recipeschef.database.AppDatabase;
import com.example.earthpatipon.recipeschef.entity.Recipe;
import com.example.earthpatipon.recipeschef.entity.User;

import java.io.IOException;

// Init dataset into database class
public class DatabaseInitializer {

    private Context context;

    public DatabaseInitializer(Context context){
        this.context = context;
    }

    // A method to call AsyncTask (thread with being able to affect to UI)
    public void populateAsync(final AppDatabase db) {
        PopulateDbAsync task = new PopulateDbAsync(db, context);
        task.execute();
    }

    private void populateWithTestData(AppDatabase db) throws IOException {
        //db.recipeDao().deleteAll();
        //db.userDao().deleteAll();
        //addUser(db, "admin", "1234");
        ImageManager imgManager = new ImageManager(context);
        imgManager.copyFileOrDir("RecipeImages");
        // Initialize recipes on Recipe table

    }

    private void addUser(AppDatabase db, String username, String password) {
        User user = new User(username, password);
        db.userDao().insert(user);
    }

    private void addRecipe(AppDatabase db,
                           String recipeName, String description, String difficulty,
                           int time, int serve, String ingredient,
                           String instruction, String category, String image){
        Recipe recipe = new Recipe(recipeName, description, difficulty,
                time, serve, ingredient, instruction, category, image);
        db.recipeDao().insert(recipe);
    }

    // Sub-class to create that inherit AsyncTask class working as thread
    private class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;

        PopulateDbAsync(AppDatabase db, Context context) {
            this.mDb = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            try {
                populateWithTestData(mDb);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}

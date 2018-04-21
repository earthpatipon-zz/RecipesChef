/* Group: Aoong Aoong
 * Members: Tanaporn 5888124, Kanjanaporn 5888178, Patipon 5888218
 */
package com.example.earthpatipon.recipeschef.utils;

import android.content.Context;
import android.os.AsyncTask;

import com.example.earthpatipon.recipeschef.database.AppDatabase;
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
        ImageManager img = new ImageManager(context);
        //img.copyFileFromAssetManager("RecipeImages", "RecipeImages");
        img.copyFileOrDir("RecipeImages");
        // Recipe table
        //db.recipeDao().deleteAll();

        // User table
        //db.userDao().deleteAll();
        //addUser(db, "admin", "1234");

    }

    private void addUser(final AppDatabase db, final String username, final String password) {
        User user = new User(username, password);
        db.userDao().insert(user);
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

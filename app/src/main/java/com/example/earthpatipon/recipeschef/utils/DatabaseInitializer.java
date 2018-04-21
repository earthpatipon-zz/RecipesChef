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
        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }

    private void populateWithTestData(AppDatabase db) throws IOException {
        //db.recipeDao().deleteAll();
        //db.userDao().deleteAll();
        ImageManager imgManager = new ImageManager(context);
        imgManager.copyFileOrDir("RecipeImages");
        // Initialize recipes on Recipe table
//        addRecipe(db, "Bulgogi","Korean BBQ Beef ",
//                "Medium",15,4,
//                "\"800g (1.76 pounds) thinly sliced sirloin (or any tender prime beef cut)\n" +
//                        "1 large yellow onion, peeled (130g, 4.6 ounces)\n" +
//                        "2 stalks green onion (55g, 2 ounces)\n" +
//                        "1/2 medium carrot, peeled (55g, 2 ounces)\n" +
//                        "1 Tbsp sesame oil\n" +
//                        "1 Tbsp sesame seeds\n" +
//                        "1 Tbsp cooking oil (I used rice bran oil)\n" +
//                        "6 Tbsp soy sauce (I used Kikkoman soy sauce)\n" +
//                        "3 Tbsp brown sugar\n" +
//                        "2 Tbsp rice wine (mirin)\n" +
//                        "2 Tbsp grated yellow onion (This is equivalent to 1/2 large onion – 80g, 2.8 ounces)\n" +
//                        "1 Tbsp minced garlic\n" +
//                        "5 Tbsp grated red apple (This is equivalent to 1 medium apple – 155g, 5.5 ounces, I used royal gala. Pink lady or fuji apples are ok to use as well.)\n" +
//                        "1 tsp minced ginger\n" +
//                        "1/8 tsp ground black pepper\n" +
//                        "*1 Tbsp = 15 ml\n" +
//                        "\n",
//                "1. Remove the blood from the meat with kitchen paper. (This can be done by placing the kitchen paper on the cutting board. Then put one slice of meat and layer another piece of kitchen paper on top of the meat. Gently press it down to soak up the blood.) Place the meat into a large mixing bowl for marinating.\n" +
//                        "2. Pour the marinade sauce into the bowl and mix well with the meat. Add the sesame oil and gently mix it into the meat. (I prefer adding the sesame oil separately as opposed to mixing it in the marinade sauce. I read somewhere that the oil can prevent the other sauce getting absorbed effectively into the meat.) Cover the bowl with food wrap (or move the meat into a glass container with a lid) and marinate the meat for at least 4 hours in the fridge. (If you have more time, you can also marinate it overnight for an enhanced flavour).\n" +
//                        "3. Prepare the vegetables. Thinly slice the onion, carrots and  green onion.\n" +
//                        "4. Preheat the wok/skillet on the stove on medium high heat and once heated add the cooking oil and spread it well with a spatula.\n" +
//                        "5. Add the meat and vegetables and stir. Cook them on high heat for 3 to 5 mins until the meat is starting to turn brown. Stir occasionally (every 30 seconds) and reduce the heat gradually as the meat & vegetables cook.\n" +
//                        "6. When the meat is nearly cooked toss in the sesame seeds. By now the heat should be very low. Stir them quickly and turn off the heat.\n" +
//                        "7. Serve the Bulgogi onto a plate and enjoy.\n",
//                "Main dish", "Bulgogi");
        addUser(db, "admin", "1234");
        addRecipe(db,"recipeName","description","difficulty",1,2,
                "ingredient","instruction", "category", "Bulgogi.png");

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

        PopulateDbAsync(AppDatabase db) {
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

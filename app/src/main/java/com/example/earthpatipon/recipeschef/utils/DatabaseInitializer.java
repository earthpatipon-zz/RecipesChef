/* Group: Aoong Aoong
 * Members: Tanaporn 5888124, Kanjanaporn 5888178, Patipon 5888218
 */
package com.example.earthpatipon.recipeschef.utils;

import android.content.Context;
import android.os.AsyncTask;

import com.example.earthpatipon.recipeschef.database.AppDatabase;
import com.example.earthpatipon.recipeschef.entity.Recipe;
import com.example.earthpatipon.recipeschef.entity.User;

import java.io.File;
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
        imgManager.copyAssetsFromFolder("RecipeImages");
        // Initialize recipes on Recipe table

        addRecipe(db, "Bulgogi",
                "Korean BBQ Beef",
                "Medium",
                15,
                4,
                "800g (1.76 pounds) thinly sliced sirloin (or any tender prime beef cut)\n" + "1 large yellow onion, peeled (130g, 4.6 ounces)\n" +
                        "2 stalks green onion (55g, 2 ounces)\n" +
                        "1/2 medium carrot, peeled (55g, 2 ounces)\n" +
                        "1 Tbsp sesame oil\n" +
                        "1 Tbsp sesame seeds\n" +
                        "1 Tbsp cooking oil (I used rice bran oil)\n" +
                        "6 Tbsp soy sauce (I used Kikkoman soy sauce)\n" +
                        "3 Tbsp brown sugar\n" +
                        "2 Tbsp rice wine (mirin)\n" +
                        "2 Tbsp grated yellow onion (This is equivalent to 1/2 large onion – 80g, 2.8 ounces)\n" +
                        "1 Tbsp minced garlic\n" +
                        "5 Tbsp grated red apple (This is equivalent to 1 medium apple – 155g, 5.5 ounces, I used royal gala. Pink lady or fuji apples are ok to use as well.)\n" +
                        "1 tsp minced ginger\n" + "1/8 tsp ground black pepper\n" + "*1 Tbsp = 15 ml\n" +
                        "\n",
                "1. Remove the blood from the meat with kitchen paper. (This can be done by placing the kitchen paper on the cutting board. Then put one slice of meat and layer another piece of kitchen paper on top of the meat. Gently press it down to soak up the blood.) Place the meat into a large mixing bowl for marinating.\n" +
                        "2. Pour the marinade sauce into the bowl and mix well with the meat. Add the sesame oil and gently mix it into the meat. (I prefer adding the sesame oil separately as opposed to mixing it in the marinade sauce. I read somewhere that the oil can prevent the other sauce getting absorbed effectively into the meat.) Cover the bowl with food wrap (or move the meat into a glass container with a lid) and marinate the meat for at least 4 hours in the fridge. (If you have more time, you can also marinate it overnight for an enhanced flavour).\n" +
                        "3. Prepare the vegetables. Thinly slice the onion, carrots and  green onion.\n" +
                        "4. Preheat the wok/skillet on the stove on medium high heat and once heated add the cooking oil and spread it well with a spatula.\n" +
                        "5. Add the meat and vegetables and stir. Cook them on high heat for 3 to 5 minutes until the meat is starting to turn brown. Stir occasionally (every 30 seconds) and reduce the heat gradually as the meat & vegetables cook.\n" +
                        "6. When the meat is nearly cooked toss in the sesame seeds. By now the heat should be very low. Stir them quickly and turn off the heat.\n" +
                        "7. Serve the Bulgogi onto a plate and enjoy.\n",
                "Main dish",
                "Bulgogi.png");

        addRecipe(db,   "Avocado salmon toast",
                "Avocado on toast with smoked salmon",
                "Easy",
                15,
                2,
                "avocado 1\n" +
                          "fat-free yogurt 2 tbsp\n"+
                          "lemon 1/2, juiced\n" +
                          "rye bread 2 slices, toasted\n" +
                          "cayenne pepper a pinch\n" +
                          "smoked salmon 75g\n" +
                          "cucumber 1/4, ribboned with a veg peeler\n" +
                          "salad cress a handful\n" +
                          "For Dressing\n" +
                          "red chilli 1/2, seeded and finely diced\n" +
                          "mint finely chopped to make 1 tbsp\n" +
                          "lemon 1/2, zested and juiced\n" +
                          "tomato 1, finely diced\n" +
                          "white wine vinegar 1 tablespoons)\n" +
                        "\n",
                "1. Make the dressing by mixing all the ingredients together with some seasoning.\n" +
                      "2. Halve the avocado, remove the stone and scoop the flesh into a bowl.Add the yogurt and lemon juice and mash everything with a fork. Season well with salt and black pepper.\n" +
                          "3. Divide the avocado between the toasted rye bread, sprinkle over a little cayenne pepper and add the smoked salmon and cucumber. Spoon over the dressing, then top with the cress.\n",
                "Breakfast",
                "Avocado Salmon Toast.png");

        addRecipe(db, "Chicken katsu",
                "Japanese style fried chicken.",
                "Easy",
                20,
                4,
                "4 skinless, boneless chicken breast halves - pounded to 1/2 inch thickness\n" +
                          "salt and pepper to taste\n" +
                          "2 tablespoons all-purpose flour\n" +
                          "1 egg, beaten\n" +
                          "1 cup panko bread crumbs\n" +
                          "1 cup oil for frying, or as needed\n" +
                          "\n",
                "1. Season the chicken breasts on both sides with salt and pepper.\n" +
                          "2. Place the flour, egg and panko crumbs into separate shallow dishes.\n" +
                          "3. Coat the chicken breasts in flour, shaking off any excess.\n" +
                          "4. Dip them into the egg, and then press into the panko crumbs until well coated on both sides.\n" +
                          "5. Heat 1/4 inch of oil in a large skillet over medium-high heat.\n" +
                          "6. Place chicken in the hot oil, and cook 3 or 4 minutes per side, or until golden brown.\n",
                "Main dish",
                "Chicken katsu.png");

        addRecipe(db, "Creamy herb chicken",
                "Quick and easy creamy herb chicken, filled with so much flavour, ready and on your table in 15 minutes",
                "Easy" ,
                30,
                4,
                "For The Chicken:\n" +
                      "4 chicken breasts (pounded 1/2-inch thin)\n" +
                      "2 teaspoons each of onion powder and garlic powder\n" +
                      "1 teaspoon fresh chopped parsley\n" +
                      "1/2 teaspoon each of dried thyme and dried rosemary\n" +
                      "salt and pepper, to season\n" +
                  "For The Sauce:\n" +
                      "4 cloves garlic, minced (or 1 tablespoon minced garlic)\n" +
                      "1 teaspoon fresh chopped parsley\n" +
                      "1/2 teaspoon each of dried thyme and dried rosemary\n" +
                      "1 cup milk (or half and half)\n" +
                      "Salt and freshly ground black pepper, to taste\n" +
                      "1 teaspoon cornstarch mixed with 1 tablespoon water, until smooth\n" +
                  "\n",
                "1. Coat chicken breasts with the onion and garlic powders and herbs. Season generously with salt and pepper.\n" +
                      "2. Heat 1 tablespoon of oil a large pan or skillet over medium-high heat and cook chicken breasts until opaque and no longer pink inside (about 5 minutes each side, depending on thickness). Transfer to a plate; set aside.\n" +
                      "3. To the same pan or skillet, heat another 2 teaspoons of olive oil and sauté garlic, with parsley, thyme and rosemary, for about 1 minute, or until fragrant.\n" +
                      "4. Stir in milk (or cream); season with salt and pepper, to taste.\n" +
                      "5. Bring to a boil; add the cornstarch mixture to the centre of the pan, quickly stirring, until sauce has thickened slightly. Reduce heat and simmer gently for a further minute to allow the sauce to thicken more.\n" +
                      "6. Return chicken to the skillet. Sprinkle with extra herbs if desired. Serve immediately.\n" ,
                "Diner",
                "Creamy herb chicken.png");

        addRecipe(db, "Gyoza" ,
                "Dumplings filled with ground meat and vegetables and wrapped in a thin dough.",
                "Medium" ,
                45,
                8,
                "1 tablespoon sesame oil\n" +
                      "2 cups chopped cabbage\n" +
                      "1/4 cup chopped onion\n" +
                      "1 clove garlic, chopped\n" +
                      "1/4 cup chopped carrot\n" +
                      "1/2 pound ground pork\n" +
                      "1 egg\n" +
                      "1 tablespoon vegetable oil\n" +
                      "1 (10 ounce) package wonton wrappers\n" +
                      "1/4 cup water\n" +
                      "1/4 cup soy sauce\n" +
                      "2 tablespoons rice vinegar\n" +
                   "\n",
                "1.Heat sesame oil in a large skillet over medium high heat. Mix in cabbage, onion, garlic and carrot. Cook and stir until cabbage is limp. Mix in ground pork and egg. Cook until pork is evenly brown and egg is no longer runny.\n" +
                       "2.Preheat vegetable oil in a large skillet over medium high heat.\n" +
                       "3.Place approximately 1 tablespoon of the cabbage and pork mixture in the center of each wrapper. Fold wrappers in half over filling, and seal edges with moistened fingers.\n" +
                       "4.In the preheated vegetable oil, cook gyoza approximately 1 minute per side, until lightly browned. Place water into skillet and reduce heat. Cover and allow gyoza to steam until the water is gone. In a small bowl, mix soy sauce and rice vinegar. Use the mixture as a dipping sauce for the finished wrappers.\n",
                "Orderf",
                "Gyoza.png");

        addRecipe(db, "Red velvet cookies",
                "Inside out red velvet cookies",
                "Medium",
                45,
                4,
                "For Cream Cheese Filling\n" +
                      "12 oz. cream cheese, softened\n" +
                      "1/2 c. powdered sugar\n" +
                      "Pinch of kosher salt\n" +
                  "For Cookie Dough\n" +
                      "6 tbsp. butter, softened\n" +
                      "1/2 c. sugar\n" +
                      "1/4 c. brown sugar\n" +
                      "1 large egg\n" +
                      "1 tsp. pure vanilla extract\n" +
                      "1 1/4 all-purpose flour\n" +
                      "2 tbsp. unsweetened cocoa powder\n" +
                      "1/2 tsp. baking powder\n" +
                      "1 tbsp. red food coloring\n" +
                  "\n",
                "1.Preheat oven to 350° and line a large baking sheet with parchment paper.\n" +
                      "2.Make cheesecake filling: Combine cream cheese, powdered sugar, and salt in a small bowl. Mix well until mixture is smooth. Cover with plastic wrap and refrigerate until slightly firm, about 30 minutes.\n" +
                      "3.Make cookie dough: In a large bowl using a hand mixer, cream butter and sugars until light and fluffy. Beat in egg and vanilla. Add flour, cocoa powder, baking powder, food coloring, and salt. Mix until just combined.\n" +
                      "4.Make cookies: Scoop about a tablespoon of the cookie dough and flatten into a flat, pancake-like circle. Spoon about 2 teaspoons of the cream cheese filling in the center. Form another tablespoon of cookie dough into a flat circle and place on top of filling. Pinch the edges together to seal, then roll the filled dough into a ball. Space cookies about 3 apart on prepared baking sheet.\n"+
                      "5.Bake cookies 15 to 17 minutes. Let cool slightly and serve.\n",
                "Dessert",
                "Red velvet cookies.png");

        addRecipe(db, "Kimbap",
                "Korean dish made from cooked rice and other ingredients that are rolled in gim.",
                "Easy",
                35,
                4,
                "4 dried seaweed sheets (gim / sushi nori)\n" +
                      "120g / 4.2 ounces spinach (regular or baby spinach)\n" +
                      "2 eggs, beaten\n" +
                      "1/2 carrot (120g / 4.2 ounces), julienned\n" +
                      "2 to 4 imitation crab sticks (depending on the length), cut in half length ways\n" +
                      "4 sticks of BBQ Kimbap ham, cut into long strips if you didn’t buy the pre-cut version (available at a Korean grocery store)\n" +
                      "4 yellow radish pickle (danmuji), cut into long strips if you didn’t buy the pre-cut version (available at a Korean / Japanese grocery store)\n" +
                      "12 strips seasoned edible burdock root (available at a Korean grocery store. You can buy the one that’s packaged together with yellow radish pickle.\n" +
                      "2 1/2 cups cooked short grain rice\n" +
                      "2 Tbsp sesame oil, divided\n" +
                      "3/8 tsp fine sea salt (or more to taste), divided\n" +
                  "\n",
                "1. Rinse the spinach and parboil it in rapidly boiling water (30 seconds to 1 minute). Drain the water away and run some cold water over the spinach. Gently squeeze the spinach to remove any excess water. Put the spinach into a bowl. Add 1/8 tsp salt and 1/2 Tbsp sesame oil then mix them well.\n" +
                      "2. Pre-heat a pan/skillet. Add a small dash of cooking oil and spread it around the pan. Add the beaten egg and cook both sides well over medium heat. Place the egg omelette on a cutting board and cut it into long strips.\n" +
                      "3. In a heated pan, lightly cook the carrots, crab sticks and kimbap ham (about 1 min per ingredients), separately, and over medium heat. (Some people use these raw, but I prefer cooking them first for my kimbap.)\n" +
                      "4. Place the cooked rice into a mixing bowl then add 1/2 Tbsp sesame oil and 1/4 tsp fine sea salt (or more to taste). Mix them well with a rice spatula.\n" +
                      "5. Gather all the kimbap ingredients at the ‘kimbap rolling station’. This makes it easy to assemble.\n" +
                      "6. Place one dried seaweed sheet on the bamboo mat (shiny side down). Put the rice on the seaweed sheet then spread it evenly and thinly to cover about 2/3 of the seaweed. (Leave about 5cm / 2 inches gap at the top of the seaweed).\n" +
                      "7. Now fill the seaweed with the filing ingredients. Make sure you evenly use the ingredients so that you’re not short of them later.\n" +
                      "If the seaweed doesn’t stick, paste some water on the gap you left in step 6. You can also use a few grains of cooked rice in this gap. Set the rolled kimbap aside.\n" +
                      "8. Repeat the step 6 and 7 with the remaining ingredients.\n" +
                      "9. Line up all kimbap on a cutting board and paste some sesame oil (1 Tbsp) around the seaweed surface. Slice kimbap into bite size pieces. Serve.\n",
                "Lunch",
                "Kimbap.png");

        addRecipe(db,"Candied potatoes",
                "Korean candied sweet potatoes",
                "Easy",
                35,
                4,
                "500g / 1.1 pounds sweet potato, you can use any sweet potatoes that are suitable for deep-frying or roasting (e.g. beauregard‘ variety). FYI, I used Korean sweet potatoes.\n" +
                      "Cooking oil for deep-frying (enough to cover the sweet potatoes in a pot)\n" +
                        "Caramel sauce:\n" +
                      "3 Tbsp (raw) sugar\n" +
                      "1 Tbsp cooking oil (I used rice bran oil)\n" +
                      "(Optional) crushed nuts or roasted sesame seeds – to garnish\n" +
                      "*1 Tbsp = 15 ml\n" +
                  "\n",
                "1. Wash the sweet potato, peel the skin and cut it into small chunks (not too thick as it will take longer to cook through). Soak them in cold water for 30 minutes to reduce the starch.\n" +
                      "2. Drain away the water and thoroughly wipe off the water from the sweet potatoes with kitchen paper.\n" +
                      "3. Boil the deep-frying oil in a large pot until it reaches 180 degree C (356F) and add the sweet potatoes and cook until cooked through (about 5 to 8 minutes).\n" +
                      "4. Take out the fried sweet potatoes and place them onto some kitchen paper to soak away the excess oil.\n" +
                      "5. Add the cooking oil in a well heated pan and scatter the sugar around. Melt it over medium high heat until the sugar dissolves then quickly reduce the heat to low. Add the sweet potatoes into the pan and mix well with the sauce (for 1 to 2 minutes).\n" +
                      "6. Move the sweet potatoes onto non stick baking paper to cool down for 3 to 5 minutes. Serve. (You can garnish with some crushed nuts or sesame seeds for extra savory taste.)\n" ,
                "Snack",
                "Candied potatoes.png");

        addRecipe(db,"Raspberry oatmeal cookie bars",
                "The snack with benefit and easy to make it.",
                "Medium",
                55,
                9,
                "1/2 cup packed light brown sugar\n" +
                      "1 cup all-purpose flour\n" +
                      "1/4 teaspoon baking soda\n" +
                      "1/8 teaspoon salt\n" +
                      "1 cup rolled oats\n" +
                      "1/2 cup butter, softened\n" +
                      "3/4 cup seedless raspberry jam\n" +
                  "\n",
                "1.Preheat oven to 350 degrees F (175 degrees C). Grease one 8 inch square pan, and line with greased foil.\n" +
                      "2.Combine brown sugar, flour, baking soda, salt, and rolled oats. Rub in the butter using your hands or a pastry blender to form a crumbly mixture. Press 2 cups of the mixture into the bottom of the prepared pan. Spread the jam to within 1/4 inch of the edge. Sprinkle the remaining crumb mixture over the top, and lightly press it into the jam.\n" +
                      "3.Bake for 35 to 40 minutes in preheated oven, or until lightly browned. Allow to cool before cutting into bars.\n",
                "Dessert",
                "Raspberry oatmeal cookie bars.png");

        addRecipe(db,"Beef and broccoli",
                "Restaurant Style serving over rice",
                "Easy",
                60,
                4,
                "1/3 cup oyster sauce\n" +
                      "2 teaspoons Asian (toasted) sesame oil\n" +
                      "1/3 cup sherry\n" +
                      "1 teaspoon soy sauce\n" +
                      "1 teaspoon white sugar\n" +
                      "1 teaspoon cornstarch\n" +
                      "3/4 pound beef round steak, cut into 1/8-inch thick strips\n" +
                      "3 tablespoons vegetable oil, plus more if needed\n" +
                      "1 thin slice of fresh ginger root\n" +
                      "1 clove garlic, peeled and smashed\n" +
                      "1 pound broccoli, cut into florets\n" +
                        "\n",
                "1. Whisk together the oyster sauce, sesame oil, sherry, soy sauce, sugar, and cornstarch in a bowl, and stir until the sugar has dissolved. Place the steak pieces into a shallow bowl, pour the oyster sauce mixture over the meat, stir to coat well, and marinate for at least 30 minutes in refrigerator.\n" +
                      "2. Heat vegetable oil in a wok or large skillet over medium-high heat, and stir in the ginger and garlic. Let them sizzle in the hot oil for about 1 minute to flavor the oil, then remove and discard. Stir in the broccoli, and toss and stir in the hot oil until bright green and almost tender, 5 to 7 minutes. Remove the broccoli from the wok, and set aside.\n" +
                 "3. Pour a little more oil into the wok, if needed, and stir and toss the beef with the marinade until the sauce forms a glaze on the beef, and the meat is no longer pink, about 5 minutes. Return the cooked broccoli to the wok, and stir until the meat and broccoli are heated through, about 3 minutes.\n",
                "Main dish",
                "Beef and broccoli.png");

        addRecipe(db,"Pad krapow gai",
                "Spicy Thai basil chicken",
                "Easy",
                15,
                2,
                "1/3 cup chicken broth\n" +
                      "1 tablespoon oyster sauce\n" +
                      "1 tablespoon soy sauce, or as needed\n" +
                      "2 teaspoons fish sauce\n" +
                      "1 teaspoon white sugar\n" +
                      "1 teaspoon brown sugar\n" +
                      "2 tablespoons vegetable oil\n" +
                      "1 pound skinless, boneless chicken thighs, coarsely chopped\n" +
                      "1/4 cup sliced shallots\n" +
                      "4 cloves garlic, minced\n" +
                      "2 tablespoons minced Thai chilies, Serrano, or other hot pepper\n" +
                      "1 cup very thinly sliced fresh basil leaves\n" +
                      "2 cups hot cooked rice\n" +
                  "\n",
                "1. Whisk chicken broth, oyster sauce, soy sauce, fish sauce, white sugar, and brown sugar together in a bowl until well blended.\n" +
                      "2.Heat large skillet over high heat. Drizzle in oil. Add chicken and stir fry until it loses its raw color, 2 to 3 minutes. Stir in shallots, garlic, and sliced chilies. 3.Continue cooking on high heat until some of the juices start to caramelize in the bottom of the pan, about 2 or 3 more minutes. Add about a tablespoon of the sauce mixture to the skillet; cook and stir until sauce begins to caramelize, about 1 minute.\n" +
                      "3.Pour in the rest of the sauce. Cook and stir until sauce has deglazed the bottom of the pan. Continue to cook until sauce glazes onto the meat, 1 or 2 more minutes. Remove from heat. Stir in basil. Cook and stir until basil is wilted, about 20 seconds. Serve with rice.\n" ,
                "Main dish",
                "Pad krapow gai.png");

        addRecipe(db, "Strawberry cheesecake",
                "No-Bake Strawberry Cheesecake! Made with fresh berries, cashew butter, dates, and an easy almond crust. Creamy, dreamy, and actually pretty healthy.",
                "Hard",
                140,
                8,
                "1 (9 inch) unbaked pie crust\n" +
                      "3 cups rhubarb, sliced 1/4-inch thick\n" +
                      "1 cup fresh strawberries, quartered\n" +
                      "3 large eggs\n" +
                      "1 1/2 cups white sugar\n" +
                      "3 tablespoons milk\n" +
                      "3 tablespoons all-purpose flour\n" +
                      "1/4 teaspoon freshly grated nutmeg\n" +
                      "1 tablespoon butter, diced\n" +
                      "2 tablespoons strawberry jam\n" +
                      "1/4 teaspoon water\n" +
                  "\n",
                "1.Preheat oven to 350 degrees F (175 degrees C). Place rolled-out pie crust in a 9-inch pie plate and set on a baking sheet lined with parchment paper or a silicone baking mat.\n" +
                      "2.Combine rhubarb and strawberries in a bowl; transfer to the pie crust, distributing evenly.\n" +
                     "3.Whisk eggs, sugar, milk, flour, and nutmeg together in a medium bowl. Slowly pour filling over rhubarb mixture until it just reaches the top edge of the crust. Scatter diced butter evenly over the top of the filling. Lightly tap and shake the baking sheet to remove any air bubbles.\n" +
                     "4.Transfer pie to the preheated oven and bake, turning halfway through, until rhubarb is tender and custard is set, about 1 hour. Mix strawberry jam and water in a small bowl; heat in the microwave until warm, about 15 seconds. Glaze the top of the pie with the jam mixture and let cool. Refrigerate until ready to serve.\n",
                "Dessert",
                "Strawberry cheesecake.png");

//        addUser(db, "admin", "1234");
//        addRecipe(db,"recipeName","description","difficulty",1,2,
//                "ingredient","instruction", "category", "Bulgogi.png");

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

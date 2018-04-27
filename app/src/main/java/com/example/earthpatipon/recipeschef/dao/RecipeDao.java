/* Group: Aoong Aoong
 * Members: Tanaporn 5888124, Kanjanaporn 5888178, Patipon 5888218
 */
package com.example.earthpatipon.recipeschef.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.earthpatipon.recipeschef.entity.Recipe;

import java.util.ArrayList;
import java.util.List;

// This class is an interface to connect to database table "Recipe" with through Room
@Dao
public interface RecipeDao {

    @Query("SELECT * FROM Recipe")
    List<Recipe> getAllRecipe();

    @Query("SELECT * FROM Recipe WHERE Category = :category")
    List<Recipe> findByCategory(String category);

    @Query("SELECT * FROM Recipe WHERE RecipeName LIKE :keyword")
    List<Recipe> findByKeyword(String keyword);

    @Query("SELECT * FROM Recipe WHERE RecipeName = :recipeName")
    Recipe getRecipe(String recipeName);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Recipe recipe);

    @Query("DELETE FROM Recipe")
    void deleteAll();
}

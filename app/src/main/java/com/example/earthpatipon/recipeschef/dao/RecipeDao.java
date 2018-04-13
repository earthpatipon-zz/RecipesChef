package com.example.earthpatipon.recipeschef.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.earthpatipon.recipeschef.entity.Recipe;

import java.util.List;

@Dao
public interface RecipeDao {

    @Query("SELECT * FROM Recipe")
    List<Recipe> getAllRecipe();

    @Query("SELECT * FROM Recipe WHERE Category = category")
    List<Recipe> findByCategory(String category);

    @Query("SELECT * FROM Recipe WHERE Ingredient LIKE :keyword")
    List<Recipe> findByKeyword(String keyword);

}

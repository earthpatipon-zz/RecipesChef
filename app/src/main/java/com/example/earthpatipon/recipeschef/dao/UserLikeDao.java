/* Group: Aoong Aoong
 * Members: Tanaporn 5888124, Kanjanaporn 5888178, Patipon 5888218
 */
package com.example.earthpatipon.recipeschef.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.earthpatipon.recipeschef.entity.UserLike;
import com.example.earthpatipon.recipeschef.entity.Recipe;

import java.util.List;

// This class is an interface to connect to database table "UserLike" with through Room
@Dao
public interface UserLikeDao {

    @Query("SELECT * FROM UserLike")
    List<UserLike> getAllLike();

    @Query("SELECT * FROM UserLike WHERE UserID = :userID")
    List<UserLike> findByUserID(int userID);

    @Query("DELETE FROM UserLike WHERE UserID = :userID and RecipeID = :recipeID")
    void delete(int userID, int recipeID);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserLike like);

    @Query("DELETE FROM UserLike")
    void deleteAll();
}

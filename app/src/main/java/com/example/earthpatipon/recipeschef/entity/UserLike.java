/* Group: Aoong Aoong
 * Members: Tanaporn 5888124, Kanjanaporn 5888178, Patipon 5888218
 */
package com.example.earthpatipon.recipeschef.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = User.class,
                parentColumns = "UserID",
                childColumns = "UserID",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE
        ),
        @ForeignKey(
                entity = Recipe.class,
                parentColumns = "RecipeID",
                childColumns = "RecipeID",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE
        )
}, indices = {
        @Index("UserID"),
        @Index("RecipeID")
})
public class UserLike {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "LikeID")
    private int likeID;
    @ColumnInfo(name = "UserID")
    private int userID;
    @ColumnInfo(name = "RecipeID")
    private int recipeID;

    public UserLike(int userID, int recipeID) {
        this.userID = userID;
        this.recipeID = recipeID;
    }

    public int getLikeID() {
        return likeID;
    }

    public void setLikeID(int likeID) {
        this.likeID = likeID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }
}

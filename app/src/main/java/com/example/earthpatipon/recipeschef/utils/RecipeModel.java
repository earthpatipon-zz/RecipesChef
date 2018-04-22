package com.example.earthpatipon.recipeschef.utils;

public class RecipeModel {

    private String cardName;
    private String imageResourceId;
    private int isFav;
    private int isTurned;

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(String imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public int getIsFav() {
        return isFav;
    }

    public void setIsFav(int isFav) {
        this.isFav = isFav;
    }

    public int getIsTurned() {
        return isTurned;
    }

    public void setIsTurned(int isTurned) {
        this.isTurned = isTurned;
    }
}

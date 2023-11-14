package com.example.meal_planner.Model;

import java.io.Serializable;

public class Recipes implements Serializable, Comparable<Recipes> {
    private String mName;
    private String mIngredients;
    private int mServings;

    public Recipes(String name, String ingredients, int servings){
        mName = name;
        mIngredients = ingredients;
        mServings = servings;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getIngredients() {
        return mIngredients;
    }

    public void setIngredients(String ingredients) {
        mIngredients = ingredients;
    }

    public int getServings() {
        return mServings;
    }

    public void setServings(int servings) {
        mServings = servings;
    }

    @Override
    public String toString(){
        return mName + ": " + mServings + " Servings, Ingredients[" + mIngredients + "]";
    }

    public int compareTo(Recipes other){
        int nameComp = this.mName.compareTo(other.mName);
        return nameComp;
    }
}

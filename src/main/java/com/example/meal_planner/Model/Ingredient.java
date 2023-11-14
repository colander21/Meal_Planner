package com.example.meal_planner.Model;

import java.io.Serializable;

public class Ingredient implements Serializable, Comparable<Ingredient> {
    private String mName;

    public Ingredient(String name){
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    @Override
    public String toString(){
        return mName;
    }

    public int compareTo(Ingredient other){
        int nameComp = this.mName.compareTo(other.mName);
        return nameComp;
    }
}

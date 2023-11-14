package com.example.meal_planner.Controller;

import com.example.meal_planner.Model.Ingredient;
import com.example.meal_planner.Model.Model;
import com.example.meal_planner.Model.Recipes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Controller {

    private static Controller theInstance;
    private ObservableList<Recipes> mAllRecipesList;
    private ObservableList<Recipes> mFilteredRecipesList;
    private ObservableList<Ingredient> mAllIngredientsList;

    public static Controller getInstance(){
        if(theInstance == null) {
            theInstance = new Controller();

            theInstance.mAllRecipesList = Model.populateListFromBinaryFile1();

            FXCollections.sort(theInstance.mAllRecipesList);

            theInstance.mAllIngredientsList = Model.populateListFromBinaryFile2();
            //FXCollections.sort(theInstance.mAllIngredientsList);

            theInstance.mFilteredRecipesList = FXCollections.observableArrayList();
        }
        return theInstance;
    }

    public ObservableList<Recipes> getAllRecipes(){
        return mAllRecipesList;
    }

    public ObservableList<Ingredient> getAllIngredients() {return mAllIngredientsList; }

    public void addRecipe(Recipes newRecipe){
        mAllRecipesList.add(newRecipe);
        FXCollections.sort(mAllRecipesList);
    }

    public void addIngredient(Ingredient newIngredient){
        mAllIngredientsList.add(newIngredient);
    }

    public void removeIngredient (Ingredient oldIngredient){
        mAllIngredientsList.remove(oldIngredient);
    }

    public void saveData(){
        Model.writeDataToBinaryFile1(mAllRecipesList);
        Model.writeDataToBinaryFile2(mAllIngredientsList);
    }

    public ObservableList<Recipes> filter(String name) {
        mFilteredRecipesList.clear();
        for (Recipes v : mAllRecipesList)
        {
            if("".equals(name) || v.getName().toLowerCase().contains(name.toLowerCase()))
                mFilteredRecipesList.add(v);
        }
        return mFilteredRecipesList;
    }

}

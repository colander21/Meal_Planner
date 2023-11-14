package com.example.meal_planner.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

public class Model {

    // Creates binary file to store data of recipes
    public static final String BINARY_FILE1 = "Recipes.dat";

    // Creates second binary file to store data of shopping list
    public static final String BINARY_FILE2 = "Ingredients.dat";

    // Methods to check if both binary files have any data (is there data being persisted)
    public static boolean binaryFile1HasData() {
        File binaryFile1 = new File(BINARY_FILE1);
        return (binaryFile1.exists() && binaryFile1.length() > 5L);
    }
    public static boolean binaryFile2HasData() {
        File binaryFile2 = new File(BINARY_FILE2);
        return (binaryFile2.exists() && binaryFile2.length() > 5L);
    }

    // Puts the data from the first binary file created above into an ObservableList
    public static ObservableList<Recipes> populateListFromBinaryFile1(){
        ObservableList<Recipes> allRecipes = FXCollections.observableArrayList();
        File binaryFile1 = new File(BINARY_FILE1);
        if (binaryFile1HasData()) {
            try{
                ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(binaryFile1));
                Recipes[] tempArray = (Recipes[]) fileReader.readObject();
                allRecipes.addAll(tempArray);
                fileReader.close();
            } catch(Exception e) {
                System.err.println("Error opening file: " + BINARY_FILE1 + " for reading. \nCaused by: " + e.getMessage());
            }
        }
        return allRecipes;
    }

    // Puts the data from the second binary file created above into an ObservableList
    public static ObservableList<Ingredient> populateListFromBinaryFile2() {
        ObservableList<Ingredient> allIngredients = FXCollections.observableArrayList();
        File binaryFile2 = new File(BINARY_FILE2);
        if (binaryFile2HasData()) {
            try {
                ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(binaryFile2));
                Ingredient[] tempArray = (Ingredient[]) fileReader.readObject();
                allIngredients.addAll(tempArray);
                fileReader.close();
            } catch (Exception e) {
                System.err.println("Error opening file: " + BINARY_FILE2 + " for reading. \nCaused by: " + e.getMessage());
            }
        }
        return allIngredients;
    }


    // Writes new data to the first binary file(Recipes)
    public static boolean writeDataToBinaryFile1(ObservableList<Recipes> allRecipesList){
        File binaryFile1 = new File(BINARY_FILE1);
        try{
            ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(binaryFile1));
            Recipes[] tempArray = new Recipes[allRecipesList.size()];
            allRecipesList.toArray(tempArray);
            fileWriter.writeObject(tempArray);
            fileWriter.close();
            return true;
        } catch (Exception e) {
            System.err.println("Error writing binary file: " + BINARY_FILE1 + "\n" + e.getMessage());
            return false;
        }
    }

    // Writes new data to the second binary file(Ingredient list)
    public static boolean writeDataToBinaryFile2(ObservableList<Ingredient> allIngredientsList){
        File binaryFile2 = new File(BINARY_FILE2);
        try{
            ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(binaryFile2));
            Ingredient[] tempArray = new Ingredient[allIngredientsList.size()];
            allIngredientsList.toArray(tempArray);
            fileWriter.writeObject(tempArray);
            fileWriter.close();
            return true;
        } catch (Exception e) {
            System.err.println("Error writing binary file: " + BINARY_FILE2 + "\n" + e.getMessage());
            return false;
        }
    }
}

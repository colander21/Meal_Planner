package com.example.meal_planner.View;

import com.example.meal_planner.Controller.Controller;
import com.example.meal_planner.Model.Ingredient;
import com.example.meal_planner.Model.Recipes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.Arrays;

public class AllRecipesScene extends Scene{

    private Scene previousScene;
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 1000;
    private Controller controller = Controller.getInstance();

    private Button addRecipeButton = new Button("Add New Recipe");
    private Button removeRecipeButton = new Button("Remove Recipe");
    private Label filterRecipesLabel = new Label("Enter Name of Recipe to Filter Recipes");
    private TextField filterRecipesTF = new TextField();
    private ListView allRecipesLV = new ListView();
    private ObservableList<Recipes> AllRecipesList;
    private ObservableList<Recipes> FilteredRecipesList;
    private Button viewSpecificRecipeButton = new Button("View Recipe");

    private Button backButton = new Button("Back");

    private static Recipes selectedRecipe;

    public AllRecipesScene(Scene previousScene){
        super(new GridPane(), WIDTH, HEIGHT);
        this.previousScene = previousScene;

        AllRecipesList = controller.getAllRecipes();
        allRecipesLV.setItems(AllRecipesList);

        allRecipesLV.getSelectionModel().selectedItemProperty().addListener((obsVal, oldVal, newVal) -> updateSelectedRecipe((Recipes) newVal));

        addRecipeButton.setOnAction(e -> addRecipe());
        viewSpecificRecipeButton.setOnAction(e -> viewSpecificRecipe());
        viewSpecificRecipeButton.setDisable(true);
        removeRecipeButton.setDisable(true);
        removeRecipeButton.setOnAction(e -> removeRecipe());

        filterRecipesTF.setOnKeyPressed(e -> filter());

        GridPane pane = new GridPane();

        pane.setHgap(25);
        pane.setVgap(10);
        pane.setPadding(new Insets(10));

        HBox hBox = new HBox();
        pane.add(hBox,0,0);
        hBox.setSpacing(10);
        hBox.getChildren().add(filterRecipesLabel);
        hBox.getChildren().add(filterRecipesTF);
        hBox.getChildren().add(viewSpecificRecipeButton);

        pane.add(allRecipesLV,0,1);

        HBox hBox1 = new HBox();
        pane.add(hBox1,0,2);
        hBox1.setSpacing(10);
        hBox1.getChildren().add(addRecipeButton);
        hBox1.getChildren().add(removeRecipeButton);

        pane.add(backButton,10,0);
        backButton.setOnAction(e -> goBackToPrevScene());

        this.setRoot(pane);
    }

    private void updateSelectedRecipe(Recipes newVal) {
        selectedRecipe = newVal;
        viewSpecificRecipeButton.setDisable(selectedRecipe == null);
        removeRecipeButton.setDisable(selectedRecipe == null);
    }

    private void addRecipe() {
        ViewNavigator.loadScene("Add Recipe", new AddRecipeScene(this));
    }

    private void removeRecipe() {
        if(selectedRecipe == null)
            return;
        AllRecipesList.remove(selectedRecipe);
        controller.saveData();
        allRecipesLV.getSelectionModel().select(-1);
    }

    private void viewSpecificRecipe(){
        ViewNavigator.loadScene(selectedRecipe.getName(), new SpecificRecipeScene(this));
    }

    private void filter() {
        String name = filterRecipesTF.getText();
        FilteredRecipesList = controller.filter(name);
        allRecipesLV.setItems(FilteredRecipesList);
    }

    private void goBackToPrevScene() {
        ViewNavigator.loadScene("Meal Planner", previousScene);
    }

    public static String getSelectedRecipeName(){
        if(selectedRecipe != null)
            return selectedRecipe.getName();
        return "";
    }

    public static ObservableList getSelectedRecipeIngredients(){
        String[] ingredientsList = selectedRecipe.getIngredients().split(",");
        ObservableList<Ingredient> newIngredientsList = FXCollections.observableArrayList();
        Ingredient newIngredient;

        for (String s: ingredientsList){
            newIngredient = new Ingredient(s);
            newIngredientsList.add(newIngredient);
        }

        return newIngredientsList;
    }
}

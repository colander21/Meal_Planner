package com.example.meal_planner.View;

import com.example.meal_planner.Controller.Controller;
import com.example.meal_planner.Model.Ingredient;
import com.example.meal_planner.Model.Recipes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class AddRecipeScene extends Scene {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 1000;

    private Scene previousScene;

    private Label recipeNameLabel = new Label("Name of Recipe");
    private TextField recipeNameTF = new TextField();
    private Label ingredientsLabel = new Label("Ingredients");
    private TextField ingredientsTF = new TextField();
    private ComboBox<Integer> ServingsCB;
    private Label ServingLabel = new Label("Servings");
    private ListView<Ingredient> ingredientsLV = new ListView<>();
    private Button addIngredientToRecipeButton = new Button("Add Ingredient to Recipe");
    private Button removeSelectedIngredientFromRecipe = new Button("Remove Ingredient From Recipe");
    private Button backButton = new Button("Back");
    private Button saveButton = new Button("Save Recipe");
    private Ingredient selectedIngredient;
    private ObservableList<Ingredient> ingredientsInRecipe;
    private ObservableList<Ingredient> allIngredientsList;
    private ObservableList<Recipes> AllRecipesList;
    private Controller controller = Controller.getInstance();


    public AddRecipeScene(Scene previousScene){
        super(new GridPane(), WIDTH, HEIGHT);
        this.previousScene = previousScene;

        ObservableList <Integer> ServingsChoicesList = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10);

        ingredientsLV.getSelectionModel().selectedItemProperty().addListener((obsVal, oldVal, newVal) -> updateSelectedIngredient((Ingredient) newVal));
        removeSelectedIngredientFromRecipe.setDisable(true);
        addIngredientToRecipeButton.setDisable(ingredientsTF == null);

        allIngredientsList = controller.getAllIngredients();
        ingredientsInRecipe = FXCollections.observableArrayList();

        AllRecipesList = controller.getAllRecipes();

        ingredientsLV.setItems(ingredientsInRecipe);

        GridPane pane = new GridPane();
        pane.setHgap(10.0);
        pane.setVgap(5);
        pane.setPadding(new Insets(5));

        pane.add(backButton,0,10);

        ServingsCB = new ComboBox<>(ServingsChoicesList);
        HBox hBox = new HBox();
        pane.add(hBox,0,0);
        hBox.setSpacing(10);
        hBox.getChildren().add(recipeNameLabel);
        hBox.getChildren().add(recipeNameTF);
        hBox.getChildren().add(ServingLabel);
        hBox.getChildren().add(ServingsCB);

        HBox hBox1 = new HBox();
        pane.add(hBox1,0,1);
        hBox1.setSpacing(10);
        hBox1.getChildren().add(ingredientsLabel);
        hBox1.getChildren().add(ingredientsTF);
        hBox1.getChildren().add(addIngredientToRecipeButton);

        pane.add(ingredientsLV,0,2);
        pane.add(removeSelectedIngredientFromRecipe,0,4);
        pane.add(saveButton,0,5);

        addIngredientToRecipeButton.setOnAction(e -> addIngredientToRecipe());
        removeSelectedIngredientFromRecipe.setOnAction(e -> removeIngredient());
        backButton.setOnAction(e -> goBackToPrevScene());
        saveButton.setOnAction(e -> saveRecipe());


        this.setRoot(pane);
    }

    private void updateSelectedIngredient(Ingredient newVal) {
        selectedIngredient = newVal;
        removeSelectedIngredientFromRecipe.setDisable(selectedIngredient == null);
    }

    private void addIngredientToRecipe() {
        Ingredient newIngredient = new Ingredient(ingredientsTF.getText());
        ingredientsInRecipe.add(newIngredient);
        ingredientsTF.setText(null);
        ingredientsTF.requestFocus();
    }

    private void removeIngredient() {
        ingredientsInRecipe.remove(selectedIngredient);
    }

    private void goBackToPrevScene() {
        ViewNavigator.loadScene("Meal Planner", previousScene);
    }

    private void saveRecipe() {
        String ingredients = "";
        String name = recipeNameTF.getText();
        for(int i = 0; i < ingredientsInRecipe.size() -1; i++){
            ingredients += ingredientsInRecipe.get(i) + ", ";
        }
        ingredients += ingredientsInRecipe.get(ingredientsInRecipe.size()-1);
        Controller.getInstance().addRecipe(new Recipes(name, ingredients, ServingsCB.getValue()));

        goBackToPrevScene();
    }
}

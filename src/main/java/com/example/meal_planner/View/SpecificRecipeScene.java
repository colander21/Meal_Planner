package com.example.meal_planner.View;

import com.example.meal_planner.Controller.Controller;
import com.example.meal_planner.Model.Ingredient;
import com.example.meal_planner.Model.Recipes;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import static com.example.meal_planner.View.AllRecipesScene.getSelectedRecipeIngredients;
import static com.example.meal_planner.View.AllRecipesScene.getSelectedRecipeName;

public class SpecificRecipeScene extends Scene {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 1000;

    private Scene previousScene;

    private Controller controller = Controller.getInstance();

    private Label recipeName =  new Label(getSelectedRecipeName());
    private ListView ingredientsLV = new ListView<>();
    private ObservableList<String> ingredientsInRecipeList;
    private Button addToShoppingListButton = new Button("Add To Shopping List");
    private Button backButton = new Button("Back");

    private Ingredient selectedIngredient;



    public SpecificRecipeScene(Scene previousScene){
        super(new GridPane(), WIDTH, HEIGHT);
        this.previousScene = previousScene;

        ingredientsLV.getSelectionModel().selectedItemProperty().addListener((obsVal, oldVal, newVal) -> updateSelectedIngredient((Ingredient) newVal));

        ingredientsInRecipeList = getSelectedRecipeIngredients();
        ingredientsLV.setItems(ingredientsInRecipeList);

        addToShoppingListButton.setDisable(true);
        addToShoppingListButton.setOnAction(e -> addToShoppingList());
        backButton.setOnAction(e -> goBackToPrevScene());

        GridPane pane = new GridPane();

        pane.setHgap(25);
        pane.setVgap(10);
        pane.setPadding(new Insets(10));

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        pane.add(hBox,0,0);
        hBox.getChildren().add(recipeName);
        hBox.getChildren().add(addToShoppingListButton);

        pane.add(ingredientsLV,0,1);
        pane.add(backButton,10,0);

        this.setRoot(pane);
    }

    private void addToShoppingList() {
        controller.addIngredient(selectedIngredient);
    }

    private void updateSelectedIngredient(Ingredient newVal) {
        selectedIngredient = newVal;
        addToShoppingListButton.setDisable(selectedIngredient == null);
    }

    private void goBackToPrevScene() {
        ViewNavigator.loadScene("Meal Planner", previousScene);
    }
}

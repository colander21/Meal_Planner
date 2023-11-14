package com.example.meal_planner.View;

import com.example.meal_planner.Controller.Controller;
import com.example.meal_planner.Model.Ingredient;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class ShoppingListScene extends Scene {

    private Scene previousScene;
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 1000;
    private Controller controller = Controller.getInstance();

    private Button addIngredientButton = new Button("Add Ingredient");
    private TextField ingredientTF = new TextField();
    private Button backButton = new Button("Back");
    private Button removeIngredientButton = new Button("Remove Ingredient");
    private ListView allIngredientsLV = new ListView<>();
    private ObservableList<Ingredient> AllIngredientsList;
    private Ingredient selectedIngredient;

    public ShoppingListScene(Scene previousScene){
        super(new GridPane(), WIDTH, HEIGHT);
        this.previousScene = previousScene;

        AllIngredientsList = controller.getAllIngredients();
        allIngredientsLV.setItems(AllIngredientsList);

        allIngredientsLV.getSelectionModel().selectedItemProperty().addListener((obsVal, oldVal, newVal) -> updateSelectedIngredient((Ingredient) newVal));


        GridPane pane = new GridPane();
        pane.setHgap(25);
        pane.setVgap(10);
        pane.setPadding(new Insets(10));

        pane.add(backButton,15,0);

        HBox hBox = new HBox();
        pane.add(hBox,0,0);
        hBox.setSpacing(10);
        hBox.getChildren().add(ingredientTF);
        hBox.getChildren().add(addIngredientButton);

        pane.add(allIngredientsLV,0,2);
        pane.add(removeIngredientButton,0,3);

        backButton.setOnAction(e -> goBackToPrevScene());
        addIngredientButton.setOnAction(e -> addIngredient());
        removeIngredientButton.setDisable(true);
        removeIngredientButton.setOnAction(e -> removeIngredient());

        this.setRoot(pane);

    }

    private void updateSelectedIngredient(Ingredient newVal) {
        selectedIngredient = newVal;
        removeIngredientButton.setDisable(selectedIngredient == null);
    }

    private void addIngredient(){
        String name = ingredientTF.getText();
        Controller.getInstance().addIngredient(new Ingredient(name));
        ingredientTF.setText(null);
        ingredientTF.requestFocus();
    }

    private void removeIngredient() {
        if (selectedIngredient == null)
            return;
        AllIngredientsList.remove(selectedIngredient);
        controller.saveData();
        allIngredientsLV.getSelectionModel().select(0);

    }

    private void goBackToPrevScene() {
        ViewNavigator.loadScene("Meal Planner", previousScene);
    }
}

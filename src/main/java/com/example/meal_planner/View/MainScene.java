package com.example.meal_planner.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class MainScene extends Scene {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 1000;

    private ImageView MainSceneIV = new ImageView();
    private Button allRecipesButton = new Button("View All Recipes");
    private Button shoppingListButton = new Button("View Shopping List");

    public MainScene(){
        super(new GridPane(), WIDTH, HEIGHT);

        MainSceneIV.setImage(new Image("HomemadeBreadcopy.jpg"));
        MainSceneIV.setFitHeight(400);
        MainSceneIV.setFitWidth(600);

        allRecipesButton.setOnAction(e -> viewAllRecipes());
        shoppingListButton.setOnAction(e -> viewShoppingList());

        GridPane pane = new GridPane();
        pane.setHgap(25);
        pane.setVgap(10);
        pane.setPadding(new Insets(10));

        allRecipesButton.setPrefSize(300,75);
        shoppingListButton.setPrefSize(300, 75);

        pane.add(MainSceneIV,7,2);
        

        HBox hBox = new HBox();
        pane.add(hBox,7,7);
        hBox.setSpacing(25);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(allRecipesButton);
        hBox.getChildren().add(shoppingListButton);

        this.setRoot(pane);
    }

    private void viewAllRecipes(){
        ViewNavigator.loadScene("All Recipes", new AllRecipesScene(this));
    }

    private void viewShoppingList(){
        ViewNavigator.loadScene("Shopping List", new ShoppingListScene(this));
    }
}

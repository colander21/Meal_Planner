package com.example.meal_planner.View;


import com.example.meal_planner.Controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class View extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Changing the icon (top left of stage) to custom icon (saved in resources folder)
        //primaryStage.getIcons().add(new Image("wave.jpg"));
        ViewNavigator.setStage(primaryStage);
        ViewNavigator.loadScene("Meal Planner", new MainScene());
    }

    @Override
    public void stop() throws Exception {
       Controller.getInstance().saveData();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }


}

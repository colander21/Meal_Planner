module com.example.meal_planner {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.meal_planner to javafx.fxml;
    exports com.example.meal_planner.View;
}
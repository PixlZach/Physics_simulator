module com.example.gravityphysicssimulator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gravityphysicssimulator to javafx.fxml;
    exports com.example.gravityphysicssimulator;
}
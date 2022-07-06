module com.tsp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.tsp to javafx.fxml;
    exports com.tsp;
    exports com.tsp.controller;
    opens com.tsp.controller to javafx.fxml;
}
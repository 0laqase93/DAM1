module parque.dinosaurios {
    requires javafx.controls;
    requires javafx.fxml;


    opens parque.dinosaurios to javafx.fxml;
    exports parque.dinosaurios;
}
module parque.dinosaurios {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens parque.dinosaurios to javafx.fxml;
    exports parque.dinosaurios;
    opens parque.dinosaurios.Clases to javafx.fxml;
    exports parque.dinosaurios.Clases;
}
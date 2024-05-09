module hotel.cajero {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens hotel.cajero to javafx.fxml;
    exports hotel.cajero;
    opens hotel.cajero.Clases to javafx.fxml;
    exports hotel.cajero.Clases;
}
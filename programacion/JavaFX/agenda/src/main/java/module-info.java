module agenda {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens agenda to javafx.fxml;
    exports agenda;
}

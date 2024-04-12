module agenda {
    requires javafx.controls;
    requires javafx.fxml;

    opens agenda to javafx.fxml;
    exports agenda;
}

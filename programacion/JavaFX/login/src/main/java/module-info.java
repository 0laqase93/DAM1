module login {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens login to javafx.fxml;
    exports login;
}

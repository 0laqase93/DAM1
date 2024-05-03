module task.protrack {
    requires javafx.controls;
    requires javafx.fxml;


    opens task.protrack to javafx.fxml;
    exports task.protrack;
}
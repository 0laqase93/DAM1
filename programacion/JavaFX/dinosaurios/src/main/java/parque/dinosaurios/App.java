package parque.dinosaurios;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class App extends Application {
    private static Scene scene = null;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("principal.fxml"));
        scene = new Scene(fxmlLoader.load(), 539, 400);
        stage.setTitle("DinoÑAM");
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    static void setScene(String nombre) throws IOException {
        scene.setRoot(new FXMLLoader(App.class.getResource(nombre + ".fxml")).load());
    }

    public static void main(String[] args) {
        launch();
    }
}
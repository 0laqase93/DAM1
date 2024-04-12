import javax.swing.text.LabelView;

import javafx.application.Application;
import javafx.event.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.layout.*;


public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        // La raiz de la escena es un StackPane (ventana)
        StackPane root = new StackPane();
        // Crea una etiqueta
        Label label = new Label("Hola mundo");
        // Crea un boton
        Button btn = new Button("Pulsar aquí");
        // Asocia la accion del boton
        btn.setOnAction(e -> {
            // Abre un cuadro de dialogo de Informacion
            Alert alert = new Alert(AlertType.INFORMATION, "Hola Mundo!!");
            alert.showAndWait();
        });
        // Añade el boton a la estructura raiz
        root.getChildren().addAll(label,btn);
        Scene scene = new Scene(root, 400, 200);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Mi primer proyecto javaFX");
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Cuando se inicia la aplicación se ejecuta el método start
        Application.launch(args);
    }
}
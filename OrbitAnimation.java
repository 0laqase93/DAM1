import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.util.Duration;

public class OrbitAnimation extends Application {
    private PathTransition transition; // Declarar como campo de clase para que sea accesible en todo el método

    @Override
    public void start(Stage primaryStage) {
        // Crear el nodo central alrededor del cual se orbitará
        Circle centro = new Circle(200, 200, 10);
        centro.setFill(javafx.scene.paint.Color.RED);

        // Crear el nodo que orbitará alrededor del centro
        Circle orbitador = new Circle(10);
        orbitador.setFill(javafx.scene.paint.Color.BLUE);

        // Crear la trayectoria de la órbita (elipse)
        Ellipse orbita = new Ellipse(200, 150, 100, 50);

        // Crear la animación de la órbita
        transition = new PathTransition();
        transition.setNode(orbitador);
        transition.setPath(orbita);
        transition.setInterpolator(Interpolator.LINEAR); // Puedes cambiar esto para una animación más suave
        transition.setDuration(Duration.seconds(4)); // Duración de un ciclo completo (en segundos)
        transition.setCycleCount(Animation.INDEFINITE); // Repetir indefinidamente
        transition.play(); // Iniciar la animación

        // Botón para pausar/reanudar la animación
        Button btnPauseResume = new Button("Pausar");
        btnPauseResume.setOnAction(e -> {
            if (transition.getStatus() == Animation.Status.RUNNING) {
                transition.pause();
                btnPauseResume.setText("Reanudar");
            } else {
                transition.play();
                btnPauseResume.setText("Pausar");
            }
        });

        // Configurar la escena
        Group root = new Group();
        root.getChildren().addAll(centro, orbitador, btnPauseResume);
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Efecto de órbita en JavaFX");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

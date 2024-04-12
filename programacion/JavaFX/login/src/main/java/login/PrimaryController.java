package login;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;

public class PrimaryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button enviar;

    @FXML
    private ImageView imagen;

    @FXML
    private PasswordField password;

    @FXML
    private TextField user;

    @FXML
    void validarLogin(MouseEvent event) {
        String usuario = user.getText();
        String passwd = password.getText();
        try {
            File f = new File("src/main/java/login/passwd.secret");
            boolean valido = false;
            InputStream stream = new FileInputStream("src/main/resources/login/images/pepe.png");
            imagen.setImage(new Image(stream));
            FileReader fr = new FileReader(f);
            BufferedReader out = new BufferedReader(fr);
            String linea = out.readLine();

            while ((linea != null) && (!valido)) {
                String[] data = linea.split(":");
                if (data[0].equals(usuario) && data[1].equals(passwd)) {
                    stream = new FileInputStream("src/main/resources/login/images/log.png");
                    imagen.setImage(new Image(stream));
                    imagen.setLayoutX(201);
                    imagen.setLayoutY(82);
                    valido = true;
                }
                linea = out.readLine();
            }
            if (!valido) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Usuario no encontrado");
                alert.setHeaderText("Ususario no encontrado");
                alert.setContentText("Parece ser que ha insertado un usuario o contraseña inexsistente.");
                alert.showAndWait();
            }
            out.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @FXML
    void initialize() {
        assert enviar != null : "fx:id=\"enviar\" was not injected: check your FXML file 'primary.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'primary.fxml'.";
        assert user != null : "fx:id=\"user\" was not injected: check your FXML file 'primary.fxml'.";

    }

}

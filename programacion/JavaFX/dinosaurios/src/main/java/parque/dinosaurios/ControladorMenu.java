package parque.dinosaurios;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class ControladorMenu {

    private static Connection con;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button botonAtraccion;

    @FXML
    private Button botonListar;

    @FXML
    private Button botonListarZonas;

    @FXML
    private Button salir;

    @FXML
    void crearAtraccion(MouseEvent event) throws IOException {
        App.setScene("atraccion");
    }

    @FXML
    void listarDinosaurios(MouseEvent event) throws IOException {
        App.setScene("listarDinosaurios");
    }

    @FXML
    void listarZonas(MouseEvent event) throws IOException {
        App.setScene("listarZona");
    }

    @FXML
    void salirPrograma(MouseEvent event) {
        try {
            con.close();
        } catch (Exception e) {
            System.out.println("[!]Error: " + e.getMessage());
        }
        System.exit(0);
    }

    @FXML
    void initialize() {
        assert botonAtraccion != null : "fx:id=\"botonAtraccion\" was not injected: check your FXML file 'principal.fxml'.";
        assert botonListar != null : "fx:id=\"botonListar\" was not injected: check your FXML file 'principal.fxml'.";
        assert botonListarZonas != null : "fx:id=\"botonListarZonas\" was not injected: check your FXML file 'principal.fxml'.";
        assert salir != null : "fx:id=\"salir\" was not injected: check your FXML file 'principal.fxml'.";

        try {
            con = crearConexion("33006", "JurassicPark", "root", "root");
        } catch (Exception e) {
            try {
                con.close();
            } catch (Exception e1) {
                System.out.println("[!]Error: " + e1.getMessage());
            }
            System.out.println("[!]Error: " + e.getMessage());
        }
    }

    public static Connection crearConexion(String puerto, String baseDatos, String usuario, String passwd) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:" + puerto + "/" + baseDatos, usuario, passwd);
            return con;
        } catch (Exception e) {
            return null;
        }
    }

    public static Connection getCon() {
        return con;
    }

}

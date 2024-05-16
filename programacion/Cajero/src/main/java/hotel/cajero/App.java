package hotel.cajero;

import hotel.cajero.Clases.Cliente;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

public class App extends Application {
    private static Scene scene = null;
    private static Connection con = null;
    private static Cliente cliente = null;

    public static Connection getCon() {
        return con;
    }

    public static void setCon(Connection con) {
        App.con = con;
    }

    public static Cliente getCliente() {
        return cliente;
    }

    public static void setCliente(Cliente cliente) {
        App.cliente = cliente;
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("iniciarSesion.fxml"));
        this.scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("CajeroNOVA");
        stage.setScene(scene);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    static void setScene(String nombre) throws IOException {
        scene.setRoot(new FXMLLoader(App.class.getResource(nombre + ".fxml")).load());
    }

    public static void main(String[] args) {
        App.con = crearConexion("33006", "CajeroNOVA", "root", "root");
        launch();
    }

    public static Connection crearConexion(String puerto, String baseDatos, String usuario, String passwd) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:" + puerto + "/" + baseDatos, usuario, passwd);
            return con;
        } catch (Exception e) {
            return null;
        }
    }
}
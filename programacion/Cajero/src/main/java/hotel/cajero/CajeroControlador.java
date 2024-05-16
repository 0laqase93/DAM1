package hotel.cajero;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import hotel.cajero.Clases.Cliente;
import hotel.cajero.Exceptions.UsuarioNoEncontrado;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class CajeroControlador {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField NIF;

    @FXML
    private PasswordField claveAcceso;

    @FXML
    void IniciarSesion(MouseEvent event) {
        if (NIF.getText().equals("") || claveAcceso.getText() == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText(null);
            alerta.setContentText("Por favor ingrese todos los campos.");
            alerta.showAndWait();
        } else {
            String sql = "SELECT * FROM Cliente WHERE NIF LIKE ? AND clave LIKE ?;";
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                ps = App.getCon().prepareStatement(sql);
                ps.setString(1, NIF.getText());
                ps.setString(2, claveAcceso.getText());
                rs = ps.executeQuery();
                if (rs.next()) {
                    App.setCliente(new Cliente(rs.getString("NIF"), rs.getString("clave"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("movil")));
                    App.setScene("acceso");
                } else {
                    throw new UsuarioNoEncontrado("NIF o Clave incorrecta");
                }
            } catch (UsuarioNoEncontrado e) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("ERROR DE ACCESO");
                alerta.setHeaderText("Error");
                alerta.setContentText(e.getMessage());
                alerta.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    ps.close();
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    void initialize() {
        assert NIF != null : "fx:id=\"NIF\" was not injected: check your FXML file 'iniciarSesion.fxml'.";
        assert claveAcceso != null : "fx:id=\"claveAcceso\" was not injected: check your FXML file 'iniciarSesion.fxml'.";
    }
}

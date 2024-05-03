package parque.dinosaurios;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ControladorAtraccion {

    private Connection con;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField capacidad;

    @FXML
    private ChoiceBox<String> dinosaurio;

    @FXML
    private TextField edadMinima;

    @FXML
    private TextField nombre;

    @FXML
    private Button salir;

    @FXML
    private Button volver;

    @FXML
    private ChoiceBox<String> zona;

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
    void volverMenu(MouseEvent event) throws IOException {
        App.setScene("principal");
    }

    @FXML
    void crearAtraccion(MouseEvent event) {
        if (nombre.getText().equals("") || edadMinima.getText().equals("") || zona.getValue().equals("") || capacidad.getText().equals("") || dinosaurio.getValue().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Por favor ingrese todos los campos");
            alert.showAndWait();
        } else {
            String sql = "INSERT INTO Atraccion (id_zona, id_dino, nombre, capacidad, edad_minima) VALUES (?,?,?,?,?)";
            String sqlDino = "SELECT id_dino FROM Dinosaurio WHERE nombre LIKE ?";
            String sqlZona = "SELECT id_zona FROM Zona WHERE nombre LIKE ?";
            ResultSet rs = null;
            int idDino = 0;
            int idZona = 0;
            PreparedStatement ps = null;
            try {
                // Recoger el id del dinosaurio
                ps = con.prepareStatement(sqlDino);
                ps.setString(1, dinosaurio.getValue());
                rs = ps.executeQuery();
                if (rs.next()) {
                    idDino = rs.getInt("id_dino");
                }

                // Recoger el id de al zona
                ps = con.prepareStatement(sqlZona);
                ps.setString(1, zona.getValue());
                rs = ps.executeQuery();
                if (rs.next()) {
                    idZona = rs.getInt("id_zona");
                }

                // Agregar datos
                ps = con.prepareStatement(sql);
                ps.setInt(1, idZona);
                ps.setInt(2, idZona);
                ps.setString(3, nombre.getText());
                ps.setInt(4, Integer.parseInt(capacidad.getText()));
                ps.setInt(5, Integer.parseInt(edadMinima.getText()));
                ps.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Salida");
                alert.setHeaderText(null);
                alert.setContentText("Se ha agregado la Atraccion");
                alert.showAndWait();

                App.setScene("principal");
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("ERROR: Datos no válidos");
                alert.showAndWait();
            }
        }
    }

    @FXML
    void initialize() {
        assert capacidad != null : "fx:id=\"capacidad\" was not injected: check your FXML file 'atraccion.fxml'.";
        assert dinosaurio != null : "fx:id=\"dinosaurio\" was not injected: check your FXML file 'atraccion.fxml'.";
        assert edadMinima != null : "fx:id=\"edadMinima\" was not injected: check your FXML file 'atraccion.fxml'.";
        assert nombre != null : "fx:id=\"nombre\" was not injected: check your FXML file 'atraccion.fxml'.";
        assert salir != null : "fx:id=\"salir\" was not injected: check your FXML file 'atraccion.fxml'.";
        assert volver != null : "fx:id=\"volver\" was not injected: check your FXML file 'atraccion.fxml'.";
        assert zona != null : "fx:id=\"zona\" was not injected: check your FXML file 'atraccion.fxml'.";

        con = ControladorMenu.getCon();
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT nombre FROM Dinosaurio;";
        ArrayList<String> dinos = new ArrayList<>();
        ArrayList<String> zon = new ArrayList<>();
        try {
            // Listar los dinos
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                dinos.add(rs.getString("nombre"));
            }

            // Listar las zonas
            sql = "SELECT nombre FROM Zona;";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                zon.add(rs.getString("nombre"));
            }

            // Mostrar datos
            dinosaurio.setItems(FXCollections.observableArrayList(dinos));
            zona.setItems(FXCollections.observableArrayList(zon));
        } catch (Exception e) {
            System.out.println("[!]Error: " + e.getMessage());
        } finally {
            try {
                st.close();
                rs.close();
            } catch (Exception e) {
                System.out.println("[!]Error: " + e.getMessage());
            }
        }
    }
}

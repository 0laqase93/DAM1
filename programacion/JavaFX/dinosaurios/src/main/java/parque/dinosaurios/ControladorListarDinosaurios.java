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
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import parque.dinosaurios.Clases.Dinosaurio;

public class ControladorListarDinosaurios {

    private Connection con;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> alimentacion;

    @FXML
    private TableView<Dinosaurio> contenido;

    @FXML
    private TableColumn<Dinosaurio, String> alimentacionCol;

    @FXML
    private TableColumn<Dinosaurio, String> nombreCol;

    @FXML
    private TableColumn<Dinosaurio, String> tamanyoCol;

    @FXML
    private TableColumn<Dinosaurio, String> tipoCol;

    @FXML
    private Button lupa;

    @FXML
    private Button salir;

    @FXML
    private ChoiceBox<String> tamanyo;

    @FXML
    private ChoiceBox<String> tipo;

    @FXML
    private Button volver;

    @FXML
    void Buscar(MouseEvent event) {
        if (alimentacion.getValue().isEmpty() || tamanyo.getValue().isEmpty() || tipo.getValue().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Por favor ingrese todos los campos");
            alert.showAndWait();
        } else {
            String sql = "SELECT * FROM Dinosaurio WHERE tamanyo LIKE ? AND alimentacion LIKE ? AND tipo LIKE ?;";
            PreparedStatement st = null;
            ResultSet rs = null;
            ObservableList<Dinosaurio> listaDinosaurios = FXCollections.observableArrayList();
            try {
                st = con.prepareStatement(sql);
                st.setString(1, tamanyo.getValue());
                st.setString(2, alimentacion.getValue());
                st.setString(3, tipo.getValue());
                rs = st.executeQuery();
                while (rs.next()) {
                    Dinosaurio dinosaurio = new Dinosaurio(rs.getString("nombre"), rs.getString("tamanyo"), rs.getString("alimentacion"), rs.getString("tipo"));
                    listaDinosaurios.add(dinosaurio);
                }
                contenido.setItems(listaDinosaurios);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                try {
                    st.close();
                    rs.close();
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
    }

    @FXML
    void salirPrograma(MouseEvent event) {
        try {
            con.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.exit(0);
    }

    @FXML
    void volverMenu(MouseEvent event) throws IOException {
        App.setScene("principal");
    }

    @FXML
    void initialize() {
        assert alimentacion != null : "fx:id=\"alimentacion\" was not injected: check your FXML file 'listarDinosaurios.fxml'.";
        assert contenido != null : "fx:id=\"contenido\" was not injected: check your FXML file 'listarDinosaurios.fxml'.";
        assert lupa != null : "fx:id=\"lupa\" was not injected: check your FXML file 'listarDinosaurios.fxml'.";
        assert salir != null : "fx:id=\"salir\" was not injected: check your FXML file 'listarDinosaurios.fxml'.";
        assert tamanyo != null : "fx:id=\"tamanyo\" was not injected: check your FXML file 'listarDinosaurios.fxml'.";
        assert tipo != null : "fx:id=\"tipo\" was not injected: check your FXML file 'listarDinosaurios.fxml'.";
        assert volver != null : "fx:id=\"volver\" was not injected: check your FXML file 'listarDinosaurios.fxml'.";

        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT DISTINCT(tamanyo) FROM Dinosaurio;";
        ArrayList<String> tamanyos = new ArrayList<>();
        ArrayList<String> tipos = new ArrayList<>();
        ArrayList<String> alimentacion = new ArrayList<>();
        try {
            con = ControladorMenu.getCon();

            // Recoger tamaños
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                tamanyos.add(rs.getString("tamanyo"));
            }

            // Recoger alimentación
            sql = "SELECT DISTINCT(alimentacion) FROM Dinosaurio;";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                alimentacion.add(rs.getString("alimentacion"));
            }

            // Recoger tipos
            sql = "SELECT DISTINCT(tipo) FROM Dinosaurio;";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                tipos.add(rs.getString("tipo"));
            }

            // Mostrar datos
            this.alimentacion.setItems(FXCollections.observableArrayList(alimentacion));
            this.tamanyo.setItems(FXCollections.observableArrayList(tamanyos));
            this.tipo.setItems(FXCollections.observableArrayList(tipos));

            // Asignar valor a las columnas
            nombreCol.setCellValueFactory(new PropertyValueFactory<Dinosaurio, String>("nombre"));
            tamanyoCol.setCellValueFactory(new PropertyValueFactory<Dinosaurio, String>("tamanyo"));
            alimentacionCol.setCellValueFactory(new PropertyValueFactory<Dinosaurio, String>("alimentacion"));
            tipoCol.setCellValueFactory(new PropertyValueFactory<Dinosaurio, String>("tipo"));

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                st.close();
                rs.close();
            } catch (Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

}

package parque.dinosaurios;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import parque.dinosaurios.Clases.Zona;

public class ControladorListarZonas {

    private ArrayList<Zona> listaZonas;

    private Connection con;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ZonaNoreste;

    @FXML
    private Button salir;

    @FXML
    private Button volver;

    @FXML
    private Button zonaNoroeste;

    @FXML
    private Button zonaSureste;

    @FXML
    private Button zonaSuroeste;

    @FXML
    void noreste(MouseEvent event) {
        for (Zona zona : listaZonas) {
            if (zona.getUbicacion().equals("noreste")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Noreste");
                alert.setHeaderText(null);
                alert.setContentText("Nombre: " + zona.getNombre() + "\nUbicación: " + zona.getUbicacion() + "\nDinosaurios:\n" + zona.listarDinos());
                alert.show();
                break;
            }
        }
    }

    @FXML
    void noroeste(MouseEvent event) {
        for (Zona zona : listaZonas) {
            if (zona.getUbicacion().equals("noroeste")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("noroeste");
                alert.setHeaderText(null);
                alert.setContentText("Nombre: " + zona.getNombre() + "\nUbicación: " + zona.getUbicacion() + "\nDinosaurios:\n" + zona.listarDinos());
                alert.show();
                break;
            }
        }
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
    void sureste(MouseEvent event) {
        for (Zona zona : listaZonas) {
            if (zona.getUbicacion().equals("sureste")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("sureste");
                alert.setHeaderText(null);
                alert.setContentText("Nombre: " + zona.getNombre() + "\nUbicación: " + zona.getUbicacion() + "\nDinosaurios:\n" + zona.listarDinos());
                alert.show();
                break;
            }
        }
    }

    @FXML
    void suroeste(MouseEvent event) {
        for (Zona zona : listaZonas) {
            if (zona.getUbicacion().equals("suroeste")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("suroeste");
                alert.setHeaderText(null);
                alert.setContentText("Nombre: " + zona.getNombre() + "\nUbicación: " + zona.getUbicacion() + "\n" + zona.listarDinos());
                alert.show();
                break;
            }
        }
    }

    @FXML
    void volverMenu(MouseEvent event) throws IOException {
        App.setScene("principal");
    }

    @FXML
    void initialize() {
        assert ZonaNoreste != null : "fx:id=\"ZonaNoreste\" was not injected: check your FXML file 'listarZona.fxml'.";
        assert salir != null : "fx:id=\"salir\" was not injected: check your FXML file 'listarZona.fxml'.";
        assert volver != null : "fx:id=\"volver\" was not injected: check your FXML file 'listarZona.fxml'.";
        assert zonaNoroeste != null : "fx:id=\"zonaNoroeste\" was not injected: check your FXML file 'listarZona.fxml'.";
        assert zonaSureste != null : "fx:id=\"zonaSureste\" was not injected: check your FXML file 'listarZona.fxml'.";
        assert zonaSuroeste != null : "fx:id=\"zonaSuroeste\" was not injected: check your FXML file 'listarZona.fxml'.";


        ResultSet rs = null;
        Statement st = null;
        String sql = "SELECT * FROM Zona;";
        try {
            con = ControladorMenu.getCon();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            listaZonas = new ArrayList<>();
            while (rs.next()) {
                listaZonas.add(new Zona(rs.getString("nombre"), rs.getString("ubicacion")));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}

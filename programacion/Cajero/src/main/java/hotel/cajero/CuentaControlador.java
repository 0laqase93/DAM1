package hotel.cajero;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import hotel.cajero.Clases.Cliente;
import hotel.cajero.Clases.Cuenta;
import hotel.cajero.Clases.Factura;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class CuentaControlador {

    private Cliente cliente;
    private ArrayList<Cuenta> cuentasArray;
    private ArrayList<Factura> facturasArray;
    private ArrayList<String> numerosCuenta;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label Nombre;

    @FXML
    private ChoiceBox<String> cuentas;

    @FXML
    private Button pagarFacturaBoton;

    @FXML
    private Button sacarDineroBoton;

    @FXML
    void cerrarSesion(MouseEvent event) throws IOException {
        App.setCliente(null);
        App.setScene("iniciarSesion");
    }

    @FXML
    void pagarFactura(MouseEvent event) {

    }

    @FXML
    void sacarDinero(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert Nombre != null : "fx:id=\"Nombre\" was not injected: check your FXML file 'acceso.fxml'.";
        assert cuentas != null : "fx:id=\"cuentas\" was not injected: check your FXML file 'acceso.fxml'.";
        assert pagarFacturaBoton != null : "fx:id=\"pagarFacturaBtono\" was not injected: check your FXML file 'acceso.fxml'.";
        assert sacarDineroBoton != null : "fx:id=\"sacarDineroBoton\" was not injected: check your FXML file 'acceso.fxml'.";

        this.cliente = App.getCliente();
        Nombre.setText(cliente.getNombre() + " " + cliente.getApellidos());

        cuentasArray = new ArrayList<>();
        facturasArray = new ArrayList<>();
        numerosCuenta = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Cuenta WHERE NIF LIKE ?;\n";
        try {
            ps = App.getCon().prepareStatement(sql);
            ps.setString(1, cliente.getNIF());
            rs = ps.executeQuery();
            while (rs.next()) {
                Cuenta cuenta = new Cuenta(rs.getString("num_cta"), rs.getString("NIF"), rs.getDouble("saldo"));
                cuentasArray.add(cuenta);
                numerosCuenta.add(cuenta.getNumeroCuenta());
            }

            sql = "SELECT * FROM Factura WHERE NIF LIKE ?;";
            ps = App.getCon().prepareStatement(sql);
            ps.setString(1, cliente.getNIF());
            rs = ps.executeQuery();
            while (rs.next()) {
                Factura factura = new Factura(rs.getInt("num_fra"), rs.getString("NIF"), rs.getInt("num_habitacion"), rs.getString("importe"));
                facturasArray.add(factura);
            }

            cuentas.setItems(FXCollections.observableArrayList(numerosCuenta));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

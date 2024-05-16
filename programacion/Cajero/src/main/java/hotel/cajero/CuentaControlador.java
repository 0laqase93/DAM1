package hotel.cajero;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import hotel.cajero.Clases.*;
import hotel.cajero.Exceptions.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class CuentaControlador {

    private Cliente cliente;
    private ArrayList<Cuenta> cuentasArray;
    private Cuenta cuenta;
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
        List<Integer> choices = new ArrayList<>();
        for (Factura factura : facturasArray) {
            choices.add(factura.getNumeroFactura());
        }

        ChoiceDialog<Integer> dialog = new ChoiceDialog<>(null, choices);
        dialog.setTitle("Pagar factura");
        dialog.setHeaderText("Saldo disponible: " + cuenta.getSaldo()  + "\nElija la factura a pagar");
        dialog.setContentText("Facturas:");
        Optional<Integer> result = dialog.showAndWait();
        if (result.isPresent()) {
            Factura facturaPagar = null;
            for (Factura factura : facturasArray) {
                if (factura.getNumeroFactura().equals(result.get())) {
                    facturaPagar = factura;
                    break;
                }
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Pagar factura");
            alert.setHeaderText("Saldo disponible: " + cuenta.getSaldo() + "\nLa factura num: " + facturaPagar.getNumeroFactura() + "\nTiene un imporde de: " + facturaPagar.getImporte());
            alert.setContentText("¿Desea pagar la factura?");
            ButtonType pagar = new ButtonType("PAGAR");
            ButtonType cancelar = new ButtonType("CANCELAR", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(pagar, cancelar);
            try {
                Optional<ButtonType> result2 = alert.showAndWait();
                if (result2.isPresent()) {
                    if (result2.get() == pagar) {
                        if (facturaPagar.getImporte() <= cuenta.getSaldo()) {
                            // Pagamos factura
                            restarDineroDeLaCuenta(facturaPagar.getImporte());

                            // Eliminamos la factura de la base de datos y del array
                            facturasArray.remove(facturaPagar);
                            String sql = "DELETE FROM Factura WHERE num_fra = ?;";
                            PreparedStatement ps = null;
                            try {
                                ps = App.getCon().prepareStatement(sql);
                                ps.setInt(1, facturaPagar.getNumeroFactura());
                                ps.executeUpdate();
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                try {
                                    ps.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                            alert2.setTitle("Pagar factura");
                            alert2.setHeaderText("Mensaje");
                            alert2.setContentText("Operación realizada\nSu nuevo saldo es: " + cuenta.getSaldo());
                            alert2.showAndWait();
                        } else {
                            throw new SaldoInsuficienteException("No tienes suficiente saldo para pagar esa factura");
                        }
                    }
                }
            } catch (SaldoInsuficienteException e) {
                Alert alert2 = new Alert(Alert.AlertType.WARNING);
                alert2.setTitle("Advertencia");
                alert2.setHeaderText("Demasiado");
                alert2.setContentText(e.getMessage());
                alert2.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No hay factura");
            alert.setContentText("Se deben seleccionar una factura");
            alert.showAndWait();
        }
    }

    @FXML
    void sacarDinero(MouseEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Sacar dinero");
        dialog.setHeaderText("Saldo disponible: " + cuenta.getSaldo());
        dialog.setContentText("Introduce el dinero:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            try {
                if (Integer.parseInt(result.get()) <= 1000) {
                    if (Integer.parseInt(result.get()) <= cuenta.getSaldo()) {
                        restarDineroDeLaCuenta(Double.parseDouble(result.get()));

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Sacar dinero");
                        alert.setHeaderText("Mensaje");
                        alert.setContentText("OPERACIÓN REALIZADA\nSu nuevo saldo es: " + cuenta.getSaldo());
                        alert.showAndWait();
                    } else {
                        throw new SaldoInsuficienteException("No tiene suficiente saldo\nSolo puede retirar " + cuenta.getSaldo());
                    }
                } else {
                    throw new MaximoPermitidoException("Excede la cantidad máxima permitida: 1000€");
                }
            } catch (MaximoPermitidoException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText("Demasiado");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            } catch (SaldoInsuficienteException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Advertencia");
                alert.setHeaderText("Demasiado");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Valores no válidos");
                alert.setContentText("Se deben introducir números");
                alert.showAndWait();
            }
        }
    }

    void detectarCuenta() {
        sacarDineroBoton.setDisable(false);
        pagarFacturaBoton.setDisable(false);
        for (Cuenta cuenta : cuentasArray) {
            if (cuentas.getValue().equals(cuenta.getNumeroCuenta())) {
                this.cuenta = cuenta;
                break;
            }
        }
    }

    void restarDineroDeLaCuenta(Double cantidad) {
        cuenta.restarSaldo(cantidad);
        String sql = "UPDATE Cuenta SET saldo = ? WHERE num_cta LIKE ?;";
        PreparedStatement ps = null;
        try {
            ps = App.getCon().prepareStatement(sql);
            ps.setDouble(1, cuenta.getSaldo());
            ps.setString(2, cuenta.getNumeroCuenta());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
                Factura factura = new Factura(rs.getInt("num_fra"), rs.getString("NIF"), rs.getInt("num_habitacion"), rs.getDouble("importe"));
                facturasArray.add(factura);
            }

            cuentas.setItems(FXCollections.observableArrayList(numerosCuenta));
            cuentas.setOnAction(event -> detectarCuenta());
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

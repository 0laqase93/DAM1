package agenda;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.util.Duration;

public class Controlador {

    private int idEmpleado = 1;
    private boolean editMode = false;
    private boolean addMode = false;
    private ArrayList<Empleado> listaEmpleados;
    private ArrayList<Empleado> empleadosBorrados;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField id;

    @FXML
    private TextField name;

    @FXML
    private TextField lastname;

    @FXML
    private TextField phone;

    @FXML
    private DatePicker date;

    @FXML
    private TextField work;

    @FXML
    private Button inicio;

    @FXML
    private Button previous;

    @FXML
    private Button last;

    @FXML
    private Button next;

    @FXML
    private Button save;

    @FXML
    private ImageView lock;

    @FXML
    private ImageView trash;

    @FXML
    private ImageView add;

    @FXML
    private Button salir;

    @FXML
    void initialize() {
        this.listaEmpleados = new ArrayList<>();
        this.empleadosBorrados = new ArrayList<>();
        Connection con = crearConexion("33006", "empleados", "root", "root");
        String sql = "SELECT * FROM empleados;";
        PreparedStatement pt = null;
        ResultSet out = null;
        try {
            pt = con.prepareStatement(sql);
            out = pt.executeQuery();
            while (out.next()) {
                int idEmpleado = out.getInt("idEmpleado");
                String nombre = out.getString("Nombre");
                String apellidos = out.getString("Apellidos");
                String telefono = out.getString("Telefono");
                LocalDate fechaNacimiento = out.getDate("FechaNacimiento").toLocalDate();
                String cargo = out.getString("Cargo");
                this.listaEmpleados.add(new Empleado(idEmpleado, nombre, apellidos, telefono, fechaNacimiento, cargo));
            }
            mostrarDatos(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                pt.close();
                con.close();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void primerEmpleado(MouseEvent event) {
        if (editMode && !addMode) {
            listaEmpleados.get(idEmpleado-1).actualizarEmpleado(name.getText(), lastname.getText(), phone.getText(), date.getValue(), work.getText());
        }
        if (addMode) {
            addMode = false;
            listaEmpleados.add(new Empleado(Integer.parseInt(id.getText()), name.getText(), lastname.getText(), phone.getText(), date.getValue(), work.getText()));
        }
        this.idEmpleado = 1;
        mostrarDatos(idEmpleado);
    }

    @FXML
    void siguienteEmpleado(MouseEvent event) {
        if (editMode) {
            listaEmpleados.get(idEmpleado-1).actualizarEmpleado(name.getText(), lastname.getText(), phone.getText(), date.getValue(), work.getText());
        }
        this.idEmpleado++;
        mostrarDatos(this.idEmpleado);
    }

    @FXML
    void ultimoEmpleado(MouseEvent event) {
        if (editMode) {
            listaEmpleados.get(idEmpleado-1).actualizarEmpleado(name.getText(), lastname.getText(), phone.getText(), date.getValue(), work.getText());
        }
        this.idEmpleado = listaEmpleados.size();
        mostrarDatos(this.idEmpleado);
    }

    @FXML
    void anteriorEmpleado(MouseEvent event) {
        if (editMode && !addMode) {
            listaEmpleados.get(idEmpleado-1).actualizarEmpleado(name.getText(), lastname.getText(), phone.getText(), date.getValue(), work.getText());
        }
        if (addMode) {
            addMode = false;
            listaEmpleados.add(new Empleado(Integer.parseInt(id.getText()), name.getText(), lastname.getText(), phone.getText(), date.getValue(), work.getText()));
        }
        this.idEmpleado -= 1;
        mostrarDatos(idEmpleado);
    }

    @FXML
    void mostrarBotonGuardar(MouseEvent event) throws java.io.FileNotFoundException {
        if (!editMode) {
            editMode = true;

            int x = 50;
            int mili = 400;
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(mili),
                    new KeyValue(inicio.translateXProperty(), -x)));
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(mili),
                    new KeyValue(last.translateXProperty(), x)));
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(mili),
                    new KeyValue(previous.translateXProperty(), -x)));
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(mili),
                    new KeyValue(next.translateXProperty(), x)));

            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(mili*2),
                    new KeyValue(save.opacityProperty(), 1)));

            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(mili*2),
                    new KeyValue(trash.opacityProperty(), 1)));

            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(mili*2),
                    new KeyValue(add.opacityProperty(), 1)));

            timeline.play();

            save.setDisable(false);

            trash.setDisable(false);

            add.setDisable(false);

            lock.setImage(new Image(new FileInputStream("src/main/resources/agenda/img/candadoAbierto.png")));

            name.setDisable(false);
            lastname.setDisable(false);
            phone.setDisable(false);
            date.setDisable(false);
            work.setDisable(false);
        }
    }

    @FXML
    void ocultarBotonGuardar(MouseEvent event) throws java.io.FileNotFoundException {
        editMode = false;

        int x = 0;
        int mili = 400;
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(mili),
                new KeyValue(inicio.translateXProperty(), -x)));
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(mili),
                new KeyValue(last.translateXProperty(), x)));
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(mili),
                new KeyValue(previous.translateXProperty(), -x)));
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(mili),
                new KeyValue(next.translateXProperty(), x)));

        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(mili),
                new KeyValue(save.opacityProperty(), 0)));

        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(mili),
                new KeyValue(trash.opacityProperty(), 0)));

        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(mili),
                new KeyValue(add.opacityProperty(), 0)));

        timeline.play();

        save.setDisable(true);

        trash.setDisable(true);

        add.setDisable(true);

        lock.setImage(new Image(new FileInputStream("src/main/resources/agenda/img/candadoCerrado.png")));

        name.setDisable(true);
        lastname.setDisable(true);
        phone.setDisable(true);
        date.setDisable(true);
        work.setDisable(true);
    }

    @FXML
    void guardarDatos(MouseEvent event) throws java.io.FileNotFoundException {
        if (editMode && !addMode) {
            listaEmpleados.get(idEmpleado-1).actualizarEmpleado(name.getText(), lastname.getText(), phone.getText(), date.getValue(), work.getText());
        }
        if (addMode) {
            addMode = false;
            listaEmpleados.add(new Empleado(Integer.parseInt(id.getText()), name.getText(), lastname.getText(), phone.getText(), date.getValue(), work.getText()));
        }

        ocultarBotonGuardar(null);

        Connection con = crearConexion("33006", "empleados", "root", "root");
        ResultSet out = null;
        String sql = "";
        String sqlUltimoId = "SELECT idEmpleado FROM empleados ORDER BY idEmpleado DESC LIMIT 1;";
        int ultimoId = 0;
        PreparedStatement ps = null;
        try {
            int idSumar = listaEmpleados.size(); // Esta variable es solo para ordenar los IDs

            // Se busca el último ID para añadir más adelante
            ps = con.prepareStatement(sqlUltimoId);
            out = ps.executeQuery();
            if (out.next()) {
                ultimoId = out.getInt("idEmpleado");
            }

            // Eliminamos los empleados eliminados
            sql = "DELETE FROM empleados WHERE idEmpleado = ?;";
            ps = con.prepareStatement(sql);
            for (Empleado e : empleadosBorrados) {
                if (idSumar > e.getIdEmpleado()) {
                    idSumar = e.getIdEmpleado();
                }
                ps.setInt(1, e.getIdEmpleado());

                ps.executeUpdate();
            }

            // Actualiza todos los empleados
            for (Empleado e : listaEmpleados) {
                if (e.getIdEmpleado() > ultimoId) { // Este caso es para cuando hay nuevos empleados
                    sql = "INSERT INTO empleados VALUES (?, ?, ?, ?, ?, ?);";
                    ps = con.prepareStatement(sql);

                    ps.setInt(1, e.getIdEmpleado());
                    ps.setString(2, e.getNombre());
                    ps.setString(3, e.getApellidos());
                    ps.setString(4, e.getTelefono());
                    ps.setString(5, e.getFecha().toString());
                    ps.setString(6, e.getCargo());

                    ps.executeUpdate();
                } else {
                    sql = "UPDATE empleados SET idEmpleado = ?, Nombre = ?, Apellidos = ?, Telefono = ?, FechaNacimiento = ?, Cargo = ? WHERE idEmpleado = ?;";
                    ps = con.prepareStatement(sql);

                    ps.setInt(1, e.getIdEmpleado());
                    ps.setString(2, e.getNombre());
                    ps.setString(3, e.getApellidos());
                    ps.setString(4, e.getTelefono());
                    ps.setString(5, e.getFecha().toString());
                    ps.setString(6, e.getCargo());
                    if (idSumar <= e.getIdEmpleado()) { // Esto es para reorganizar los IDs
                        ps.setInt(7, e.getIdEmpleado() + empleadosBorrados.size());
                    } else {
                        ps.setInt(7, e.getIdEmpleado());
                    }
                    ps.executeUpdate();
                }
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                out.close();
                con.close();
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void borrarEmpleado(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminación de empleado");
        alert.setHeaderText("Eliminación de empleado");
        alert.setContentText("¿Seguro que quiere eliminar a " + listaEmpleados.get(idEmpleado-1).getNombre() + "?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            idEmpleado--;
            empleadosBorrados.add(listaEmpleados.remove(idEmpleado));
            for (int i = idEmpleado; i < listaEmpleados.size(); i++) {
                Empleado e = listaEmpleados.get(i);
                e.actualizarIdEmpleado(i+1);
            }
            idEmpleado++;
            mostrarDatos(idEmpleado);
        }
        alert.close();
    }

    @FXML
    void addEmpleado(MouseEvent event) {
        idEmpleado = listaEmpleados.size() + 1;
        addMode = true;

        // Desactivar y activar los botones
        next.setDisable(true);
        last.setDisable(true);

        if (previous.isDisable()) {
            previous.setDisable(false);
            inicio.setDisable(false);
        }

        // Vaciar datos
        id.setText(idEmpleado + "");
        name.setText("");
        lastname.setText("");
        phone.setText("");
        date.setValue(null);
        work.setText("");
    }

    @FXML
    void salirPrograma(MouseEvent event) {
        if (editMode) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Salir");
            alert.setHeaderText("Salir");
            alert.setContentText("Hay cambios sin guardar ¿Seguro que quiere salir?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                alert.close();
                System.exit(0);
            }
        } else {
            System.exit(0);
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

    public void mostrarDatos(int idEmpleado){
        idEmpleado -= 1; //Esto es para que se ajuste al array
        int idActual = listaEmpleados.get(idEmpleado).getIdEmpleado();
        String nombre = listaEmpleados.get(idEmpleado).getNombre();
        String apellidos = listaEmpleados.get(idEmpleado).getApellidos();
        String telefono = listaEmpleados.get(idEmpleado).getTelefono();
        LocalDate fechaNacimiento = listaEmpleados.get(idEmpleado).getFecha();
        String cargo = listaEmpleados.get(idEmpleado).getCargo();
        id.setText(idActual + "");
        name.setText(nombre);
        lastname.setText(apellidos);
        phone.setText(telefono);
        date.setValue(fechaNacimiento);
        work.setText(cargo);
        // Desactivar botones en X casos
        if (idEmpleado == 0) {
            inicio.setDisable(true);
            previous.setDisable(true);
        } else {
            inicio.setDisable(false);
            previous.setDisable(false);
        }
        if (idEmpleado == listaEmpleados.size() - 1) {
            last.setDisable(true);
            next.setDisable(true);
        } else {
            last.setDisable(false);
            next.setDisable(false);
        }
    }
}
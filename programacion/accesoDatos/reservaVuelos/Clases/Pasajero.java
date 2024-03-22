package reservaVuelos.Clases;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Pasajero {
    private int idPasajero;
    private String numPasaporte;
    private String nombre;

    public Pasajero() {};

    public Pasajero(int idPasajero, String numPasaporte, String nombre) {
        this.idPasajero = idPasajero;
        this.numPasaporte = numPasaporte;
        this.nombre = nombre;
    }

    public Pasajero(String numPasaporte, String nombre) {
        this.numPasaporte = numPasaporte;
        this.nombre = nombre;
    }

    public int getIdPasajero() {
        return idPasajero;
    }

    public void setIdPasajero(int idPasajero) {
        this.idPasajero = idPasajero;
    }

    public String getNumPasaporte() {
        return numPasaporte;
    }

    public void setNumPasaporte(String numPasaporte) {
        this.numPasaporte = numPasaporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void guardarDatos(Connection con) {
        String sql = "INSERT INTO Pasajeros VALUES (null, ?, ?)";
        PreparedStatement in = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            // Guardar los datos en la query
            in = con.prepareStatement(sql);
            in.setString(1, this.numPasaporte);
            in.setString(2, this.nombre);

            // Guardar los datos en la base de datos
            in.executeUpdate();
            System.out.print(ConsoleColors.GREEN + "[+] Datos guardados con éxito" + ConsoleColors.RESET);
            br.readLine();

            // Cerrar conexión
            in.close();
        } catch (SQLException e) {
            System.out.println(ConsoleColors.RED + "[!] Fallo en la inserción SLQ: " + e.getMessage() + ConsoleColors.RESET);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

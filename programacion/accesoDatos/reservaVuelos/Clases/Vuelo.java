package reservaVuelos.Clases;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.mysql.cj.PreparedQuery;

public class Vuelo {
    private int idVuelo;
    private String origen;
    private String destino;
    private String fecha;
    private int capacidad;
    private HashMap<Integer, Pasajero> asientos;

    public Vuelo() {};
    
    public Vuelo(int idVuelo, String origen, String destino, String fecha, int capacidad) {
        this.idVuelo = idVuelo;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.capacidad = capacidad;
        this.asientos = new HashMap<>(capacidad);
        for (int i = 1; i <= capacidad; i++) {
            this.asientos.put(i, null);
        }
    }

    public Vuelo(String origen, String destino, String fecha, int capacidad) {
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.capacidad = capacidad;
        this.asientos = new HashMap<>(capacidad);
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getFecha() {
        return fecha;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public void guardarDatos(Connection con) {
        String sql = "INSERT INTO Vuelos VALUES (null, ?, ?, ?, ?)";
        PreparedStatement in = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            // Guardar los datos en la query
            in = con.prepareStatement(sql);
            in.setString(1, this.origen);
            in.setString(2, this.destino);
            in.setString(3, this.fecha);
            in.setInt(4, this.capacidad);

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

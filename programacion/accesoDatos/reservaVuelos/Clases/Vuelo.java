package reservaVuelos.Clases;

import java.beans.Statement;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Vuelo {
    private int idVuelo;
    private String origen;
    private String destino;
    private String fecha;
    private int capacidad;
    private ArrayList<Integer> asientos;

    public Vuelo() {};
    
    public Vuelo(int idVuelo, String origen, String destino, String fecha, int capacidad) {
        this.idVuelo = idVuelo;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.capacidad = capacidad;
        this.asientos = rellenarAsientos();
    }

    public Vuelo(String origen, String destino, String fecha, int capacidad) {
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.capacidad = capacidad;
        this.asientos = rellenarAsientos();
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


    public ArrayList<Integer> getAsientos() {
        return asientos;
    }

    public void setAsientos(ArrayList<Integer> asientos) {
        this.asientos = asientos;
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

    public ArrayList<Integer> rellenarAsientos() {
        ArrayList<Integer> asientos = new ArrayList<>();
        String sql = "SELECT n_asiento FROM Vuelos_Pasajeros WHERE id_vuelo = " + idVuelo + ";";
        try {
            Connection con = crearConexion("33006", "reservaVuelos", "root", "root");
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet out = st.executeQuery();
            while (out.next()) {
                asientos.add(out.getInt("n_asiento"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return asientos;
    }

    public static Connection crearConexion(String puerto, String baseDatos, String usuario, String passwd) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:" + puerto + "/" + baseDatos, usuario, passwd);
            return con;
        } catch (Exception e) {
            return null;
        }
    }

    public void ImprimirAsientosDisponibles() {
        int contador = 0;
        System.out.println("[+] " + ConsoleColors.PURPLE + "Asientos" + ConsoleColors.RESET + " disponibles:");
        for (int i = 1; i <= this.capacidad; i++) {
            if (contador == 10) {
                contador = 0;
                System.out.println();
            }
            contador++;
            if (asientos.contains(i)) {
                System.out.print(ConsoleColors.BLACK + " X " + ConsoleColors.RESET);
            } else {
                System.out.print(ConsoleColors.BLUE + " " + i + " " + ConsoleColors.RESET);
            }
        }
        System.out.println();
    }

    public void asignarAsiento(int asiento){
        String sql = "UPDATE Vuelos_Pasajeros SET n_asiento = ? WHERE id_vuelo = ?;";
        try {
            Connection con = crearConexion("33006", "reservaVuelos", "root", "root");
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, asiento);
            st.setInt(2, idVuelo);
            st.executeUpdate();
            asientos.add(asiento);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

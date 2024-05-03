package parque.dinosaurios.Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Zona {
    private String nombre;
    private String ubicacion;
    private ArrayList<Dinosaurio> dinosaurios;

    public Zona(String nombre, String ubicacion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.dinosaurios = setDinosaurios();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public ArrayList<Dinosaurio> setDinosaurios() {
        ArrayList<Dinosaurio> dinosaurios = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = """
                SELECT d.*
                FROM Dinosaurio AS d
                INNER JOIN Atraccion AS a
                    ON d.id_dino = a.id_dino
                INNER JOIN Zona AS z
                    ON a.id_zona = z.id_zona
                WHERE z.nombre LIKE ?;""";
        try {
            con = crearConexion("33006", "JurassicPark", "root", "root");
            ps = con.prepareStatement(sql);
            ps.setString(1, this.nombre);
            rs = ps.executeQuery();
            while (rs.next()) {
                Dinosaurio dinosaurio = new Dinosaurio(rs.getString("nombre"), rs.getString("tamanyo"), rs.getString("alimentacion"), rs.getString("tipo"));
                dinosaurios.add(dinosaurio);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return dinosaurios;
    }

    public String listarDinos() {
        String dinos = "";
        for (Dinosaurio dinosaurio : dinosaurios) {
            dinos += dinosaurio.toString() + "\n";
        }
        return dinos;
    }

    public static Connection crearConexion(String puerto, String baseDatos, String usuario, String passwd) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:" + puerto + "/" + baseDatos, usuario, passwd);
            return con;
        } catch (Exception e) {
            return null;
        }
    }
}
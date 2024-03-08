import java.io.*;

// Clase para representar un Circuito
class Circuito implements Serializable {
    private Integer ncircuito;  // Numero del circuito según el Gran Premio
    private String nombre;      // Nombre del circuito
    private String ciudad;      // Ciudad del circuito
    private Integer m;          // Longitud en metros del circuito

    public Circuito(Integer ncircuito, String nombre, String ciudad, Integer m) {
        this.ncircuito = ncircuito;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.m = m;
    }

    public Integer getNcircuito() {
        return ncircuito;
    }
    public void setNcircuito(Integer ncircuito) {
        this.ncircuito = ncircuito;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Integer getKm() {
        return m;
    }
    public void setKm(Integer m) {
        this.m = m;
    }

}

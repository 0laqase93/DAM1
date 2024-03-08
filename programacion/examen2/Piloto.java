import java.io.*;

// Clase para representar un Piloto
public class Piloto implements Serializable {
    private String nombre;          // Nombre del Piloto
    private String codigo;          // Código del Piloto 
    private String nacionalidad;    // Nacionalidad del Piloto


    public Piloto(String nombre, String codigo, String nacionalidad) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.nacionalidad = nacionalidad;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

}


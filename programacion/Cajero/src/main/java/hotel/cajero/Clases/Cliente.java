package hotel.cajero.Clases;

public class Cliente {
    private String NIF;
    private String clave;
    private String nombre;
    private String apellidos;
    private String movil;

    public Cliente(String NIF, String clave, String nombre, String apellidos, String movil) {
        this.NIF = NIF;
        this.clave = clave;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.movil = movil;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }
}

package parque.dinosaurios.Clases;

public class Zona {
    private String nombre;
    private String ubicacion;
    private Integer num_atracciones;

    public Zona(String nombre, String ubicacion, Integer num_atracciones) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.num_atracciones = num_atracciones;
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

    public Integer getNum_atracciones() {
        return num_atracciones;
    }

    public void setNum_atracciones(Integer num_atracciones) {
        this.num_atracciones = num_atracciones;
    }
}
package parque.dinosaurios.Clases;

public class Dinosaurio {
    private String nombre;
    private String tamanyo;
    private String alimentacion;
    private String tipo;

    public Dinosaurio(String nombre, String tamanyo, String alimentacion, String tipo) {
        this.nombre = nombre;
        this.tamanyo = tamanyo;
        this.alimentacion = alimentacion;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTamanyo() {
        return tamanyo;
    }

    public void setTamanyo(String tamanyo) {
        this.tamanyo = tamanyo;
    }

    public String getAlimentacion() {
        return alimentacion;
    }

    public void setAlimentacion(String alimentacion) {
        this.alimentacion = alimentacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Nombre: " + this.nombre + "\n\t tamaño: " + this.tamanyo + "\n\t alimentación: " + this.alimentacion + "\n\t tipo: " + this.tipo;
    }
}
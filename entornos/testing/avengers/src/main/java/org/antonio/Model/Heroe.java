package org.antonio.Model;

public class Heroe {
    private String nombre;
    private String superpoderes;
    private String biografia;
    private String descripcion;

    public Heroe(String nombre, String superpoderes, String biografia, String descripcion) {
        this.nombre = nombre;
        this.superpoderes = superpoderes;
        this.biografia = biografia;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getSuperpoderes() {
        return superpoderes;
    }

    public String getBiografia() {
        return biografia;
    }

    public String getDescripcion()
    {
        return  descripcion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSuperpoderes(String superpoderes) {
        this.superpoderes = superpoderes;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

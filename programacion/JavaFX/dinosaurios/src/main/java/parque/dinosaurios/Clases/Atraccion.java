package parque.dinosaurios.Clases;

import parque.dinosaurios.*;

public class Atraccion {
    private String nombre;
    private Dinosaurio dinosaurio;
    private Integer capacidad;
    private Integer edad;
    private Zona zona;

    public Atraccion(String nombre, Dinosaurio dinosaurio, Integer capacidad, Integer edad, Zona zona) {
        this.nombre = nombre;
        this.dinosaurio = dinosaurio;
        this.capacidad = capacidad;
        this.edad = edad;
        this.zona = zona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Dinosaurio getDinosaurio() {
        return dinosaurio;
    }

    public void setDinosaurio(Dinosaurio dinosaurio) {
        this.dinosaurio = dinosaurio;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public void asignarDino(Dinosaurio dinosaurio) {
        setDinosaurio(dinosaurio);
    }

    public void asignarZona(Zona zona) {
        setZona(zona);
    }
}
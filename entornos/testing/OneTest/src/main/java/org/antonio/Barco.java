package org.antonio;

public class Barco {
    private String nombre;
    private int capacidad;

    public Barco(String nombre, int capacidad) {
        this.nombre = nombre;
        this.capacidad = capacidad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public boolean agregarTripulante() {
        if (capacidad < 10) {
            capacidad++;
            return true;
        }
        return false;
    }
}

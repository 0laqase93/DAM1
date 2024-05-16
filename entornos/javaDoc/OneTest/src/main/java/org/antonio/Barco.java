package org.antonio;

/**
 * Clase de Barco con métodos para añadir un tripulante.
 * Tiene los atributos: nombre y capacidad.
 * @version 1.0
 */
public class Barco {
    private String nombre;
    private int capacidad;

    /**
     * Constructor básico del barco.
     * @param nombre Nombre del barco.
     * @param capacidad Capacidad máxima de tripulantes.
     */
    public Barco(String nombre, int capacidad) {
        this.nombre = nombre;
        this.capacidad = capacidad;
    }

    /**
     * Retorna el valor del nombre del barco.
     * @return Nombre de la fruta.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Retorna el valor de la capacidad del barco.
     * @return Capacidad del barco
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * Añade un tripulante a la capacidad del barco. Si el número es mayor o igual a 10
     * entonces no añadirá más tripulantes.
     * @return True si se ha añadido correctamente y false en caso de que ya esté lleno el barco.
     */
    public boolean agregarTripulante() {
        if (capacidad < 10) {
            capacidad++;
            return true;
        }
        return false;
    }
}

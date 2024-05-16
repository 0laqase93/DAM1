package org.antonio;

/**
 * Clase de Luffy con métodos para atacar y recibir daño.
 * Tiene los atributos: nombre y poder.
 * Utiliza la interfaz Personaje.
 * @version 1.0
 * @see Personaje
 */
public class Luffy implements Personaje {
    private String nombre;
    private int poder;

    /**
     * Constructor base del personaje Luffy.
     * @param nombre Nombre del personaje.
     * @param poder Poder del personaje.
     */
    public Luffy(String nombre, int poder) {
        this.nombre = nombre;
        this.poder = poder;
    }

    /**
     * Retorna el nombre del Luffy.
     * @return Nombre del personaje.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Retorna el poder de Luffy.
     * @return Poder del personaje.
     */
    public int getPoder() {
        return poder;
    }

    /**
     * Reescribe la función de recibir daño de la interfaz personaje.
     * @param cantidadDanio Cantidad de daño a recibir.
     * @see Personaje
     */
    @Override
    public void recibirDanio(int cantidadDanio) {
        this.poder -= cantidadDanio;
    }

    /**
     * Método para atacar a otro personaje.
     * @param enemigo Personaje al que atacar.
     */
    // Método para atacar a otro personaje
    public void atacar(Personaje enemigo) {
        enemigo.recibirDanio(poder);
    }
}
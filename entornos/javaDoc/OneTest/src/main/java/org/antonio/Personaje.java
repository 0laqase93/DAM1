package org.antonio;

/**
 * Interfaz que se utiliza para crear personajes
 * Tiene los métodos: getNombre(), getPoder(), recibirDanio().
 */
public interface Personaje {
    /**
     * Retorna el nombre del personaje.
     * @return Nombre del personaje.
     */
    String getNombre();

    /**
     * Retorna el poder del personaje.
     * @return Poder del personaje.
     */
    int getPoder();

    /**
     * Función que busca restar el poder con la cantidad de daño pasado.
     * @param cantidadDanio Cantidad de daño a recibir.
     */
    void recibirDanio(int cantidadDanio);
}

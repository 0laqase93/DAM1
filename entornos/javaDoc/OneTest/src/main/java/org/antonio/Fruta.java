package org.antonio;

/**
 * Clase de Fruta con métodos para saber si una fruta es deliciosa, se puede comer o si es fruta del diablo.
 * Tiene los atributos: nombre y esDeliciosa.
 * @version 1.0
 */
public class Fruta {
    private String nombre;
    private boolean esDeliciosa;

    /**
     * Constructor base de la fruta.
     * @param nombre Nombre de la fruta ("Fruta del Diablo" para que sea la fruta del diablo).
     * @param esDeliciosa Determina si se puede comer la fruta (True para la fruta del diablo).
     */
    public Fruta(String nombre, boolean esDeliciosa) {
        this.nombre = nombre;
        this.esDeliciosa = esDeliciosa;
    }

    /**
     * Retorna el valor del nombre de la fruta.
     * @return Nombre de la fruta.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Retorna el valor del valor esDeliciosa.
     * @return Sí es deliciosa o no.
     */
    public boolean esDeliciosa() {
        return esDeliciosa;
    }

    /**
     * Verifica si la fruta se puede comer y si es la fruta del diablo.
     * Si se llama "Fruta del diablo" y es comestible entonces devuelve verdadero.
     * @return Verdadero si es la fruta del diablo y falso si no lo es.
     */
    public boolean puedoComer() {
        return esDeliciosa && !nombre.equals("Fruta del Diablo");
    }

    /**
     * Verifica si la fruta se puede comer y si es la fruta del diablo.
     * Si se llama "Fruta del diablo" y es comestible entonces devuelve verdadero.
     * @return Verdadero si es la fruta del diablo y falso si no lo es.
     * @deprecated reemplazado por {@link #puedoComer()}.
     */
    @Deprecated
    public boolean sePuedeComer() {
        return esDeliciosa && !nombre.equals("Fruta del Diablo");
    }
}

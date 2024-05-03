package org.antonio;

public class Zoro implements Personaje {
    private String nombre;
    private int poder;

    public Zoro(String nombre, int poder)  {
        this.nombre = nombre;
        this.poder = poder;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPoder() {
        return poder;
    }

    @Override
    public void recibirDanio(int cantidadDanio) {
        this.poder -= cantidadDanio;
    }

    // Método para atacar a otro personaje
    public void atacar(Personaje enemigo) {
        enemigo.recibirDanio(poder);
    }
}

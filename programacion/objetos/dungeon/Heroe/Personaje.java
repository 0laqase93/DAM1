package Heroe;

import Armas.*;
import java.util.Random;

public class Personaje {

    Random numRandom = new Random();

    protected String nombre;
    protected String tipo;
    protected int vida;
    protected Arma arma;
    protected int x = 0;

    public Personaje() { }

    public Personaje(String nombre, String tipo, int minVida, int maxVida) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.vida = numRandom.nextInt(minVida, maxVida);
    }

    public Arma getArma() {
        return arma;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }

    public int getPosicion() {
        return x;
    }

    public void avanzar() {
        this.x += 1;
        if (this.tipo.equals("Mago")) {
            this.vida += 2;
        } else {
            this.vida++;
        }
    }

    public int getVida() {
        return this.vida;
    }

    public void reciveDanyo(int danyo) {
        this.vida -= danyo;
    }

    @Override
    public String toString() {
        return "nombre: " + this.nombre + "\ntipo: " + this.tipo + "\nvida: " + this.vida + "\narma: " + this.arma + "\nposición: " + this.x;
    }
}

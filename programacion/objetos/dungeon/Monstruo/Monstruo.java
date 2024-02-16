package Monstruo;

import java.util.Random;
import Armas.*;

public abstract class Monstruo {

    Random numRandom = new Random();

    protected String tipo;
    protected int vida;
    protected Arma arma;
    protected int x;

    public Monstruo(String tipo, int x) {
        this.tipo = tipo;
        this.vida = numRandom.nextInt(5, 20);
        this.x = x;
    }

    public Arma getArma() {
        return arma;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }

    public int getVida() {
        return this.vida;
    }

    public void reciveDanyo(int danyo) {
        this.vida -= danyo;
    }

    public int getPosicion() {
        return this.x;
    }

    @Override
    public String toString() {
        return "tipo: " + this.tipo + " | vida: " + this.vida + " | arma: " + this.arma + " | x: " + this.x;
    }
}

package Armas;

import java.util.Random;

public abstract class Arma {

    Random numRandom = new Random();

    protected String tipo;
    private final int danyomin = 1;
    protected int danyomax;
    private double critico; // Va por pocentaje

    public Arma(String tipo, int danyomax, double critico) {
        this.tipo = tipo;
        this.danyomax = danyomax;
        this.critico = critico;
    }

    public int danyoArma(boolean critico) {
        if (critico) {
            int d = this.numRandom.nextInt(danyomin, danyomax);
            if (d == 1) {
                d++;
            }
            return d*2;
        } else {
            return this.numRandom.nextInt(danyomin, danyomax);
        }
    }

    public boolean isCritico() {
        double rango = Math.random();
        if (rango <= critico) {
            return true;
        } else {
            return false;
        }
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return this.tipo;
    }
}

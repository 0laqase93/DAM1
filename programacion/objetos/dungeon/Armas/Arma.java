package Armas;

import java.util.Random;

public abstract class Arma {

    Random numRandom = new Random();

    protected String tipo;
    private final int danyomin = 1;
    protected int danyomax;

    public Arma(String tipo, int danyomax) {
        this.tipo = tipo;
        this.danyomax = danyomax;
    }

    public int danyoArma() {
        return this.numRandom.nextInt(danyomin, danyomax);
    }

    @Override
    public String toString() {
        return this.tipo;
    }
}

package Armas;

public class Espada extends Arma {

    private final int danyoMax = 10;
    private final double critico = 0.1; 

    public Espada() {
        super("Espada", 10, 0.1);
    }

    public int getDanyoMax() {
        return danyoMax;
    }

    public double getCritico() {
        return critico;
    }
}

package Armas;

public class Espada extends Arma {

    private final static int danyoMax = 10;
    private final static double critico = 0.15; 

    public Espada() {
        super("Espada", danyoMax, critico);
    }

    public static int getDanyoMax() {
        return danyoMax;
    }

    public static double getCritico() {
        return critico;
    }
}

package Armas;

public class Arco extends Arma {

    private final static int danyoMax = 5;
    private final static double critico = 0.3; 

    public Arco() {
        super("Arco", danyoMax, critico);
    }

    public static int getDanyoMax() {
        return danyoMax;
    }

    public static double getCritico() {
        return critico;
    }
}
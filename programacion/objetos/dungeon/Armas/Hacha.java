package Armas;

public class Hacha extends Arma {

    private final static int danyoMax = 8;
    private final static double critico = 0.3;

    public Hacha() {
        super("Hacha", danyoMax, critico);
    }

    public static int getDanyoMax() {
        return danyoMax;
    }

    public static double getCritico() {
        return critico;
    }
}

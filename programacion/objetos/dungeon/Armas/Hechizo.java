package Armas;

public class Hechizo extends Arma {

    private final static int danyoMax = 10;
    private final static double critico = 0.3;

    public Hechizo() {
        super("Hechizo", danyoMax, critico);
    }

    public static int getDanyoMax() {
        return danyoMax;
    }

    public static double getCritico() {
        return critico;
    }
}

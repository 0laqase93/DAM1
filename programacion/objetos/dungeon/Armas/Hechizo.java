package Armas;

public class Hechizo extends Arma {

    private final static int danyoMax = 9;
    private final static double critico = 0.2;

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

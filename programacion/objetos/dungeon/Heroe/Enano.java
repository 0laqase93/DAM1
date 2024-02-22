package Heroe;

public class Enano extends Personaje {

    private final static int minVida = 5;
    private final static int maxVida = 10;
    private final static int vidaXpiso = 1;

    public Enano(String nombre) {
        super(nombre, "Enano", 5, 10);
    }

    public static int getMinVida() {
        return minVida;
    }

    public static int getMaxVida() {
        return maxVida;
    }

    public static int getVidaXpiso() {
        return vidaXpiso;
    }
}

package Heroe;

public class Mago extends Personaje {

    private final static int minVida = 3;
    private final static int maxVida = 8;
    private final static int vidaXpiso = 2;

    public Mago(String nombre) {
        super(nombre, "Mago", 3, 8);
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

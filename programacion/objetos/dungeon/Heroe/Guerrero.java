package Heroe;

public class Guerrero extends Personaje {

    private final static int minVida = 9;
    private final static int maxVida = 15;
    private final static int vidaXpiso = 1;
    
    public Guerrero(String nombre) {
        super(nombre, "Guerrero", 9, 15);
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

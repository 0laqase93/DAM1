import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

class Cartas {
    private int num;
    private String color;

    public Cartas() {}

    public Cartas(int num, String color) {
        this.num = num;
        this.color = color;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "(" + this.num + " " + this.color + ")";
    }
}

class Baraja {
    ArrayList<Cartas> cartas = new ArrayList<>();

    public Baraja(ArrayList<Cartas> cartas) {
        this.cartas = cartas;
    }

    public void barajar() {
        Collections.shuffle(cartas);
    }

    public Cartas repartirCarta() {
        return cartas.remove(0);
    }

    @Override
    public String toString() {
        String output = "";
        for (Cartas valor : cartas) {
            output += valor + " ";
        }
        return output;
    }
}

class Jugador {
    ArrayList<Cartas> mano;

    public void recibeCarta(Cartas carta) {
        mano.add(carta);
    }

    @Override
    public String toString() {
        return mano.toString();
    }
}

public class uno {
    
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static void main(String[] args) {
        Jugador user1 = new Jugador();
        ArrayList<Cartas> cartas = new ArrayList<>();
        String[] colores = { RED+"rojo"+RESET, GREEN+"verde"+RESET, YELLOW+"amarillo"+RESET, BLUE+"azul"+RESET };
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                cartas.add(new Cartas(j, colores[i]));
            }
        }
        Baraja b = new Baraja(cartas);
        System.out.println(b);
        b.barajar();
        System.out.println(b);
        b.barajar();
        System.out.println(b);
        b.barajar();
        System.out.println(b);
        b.barajar();
        System.out.println(b);
        b.barajar();
        System.out.println(b);
        user1.recibeCarta(b.repartirCarta());
        user1.recibeCarta(b.repartirCarta());
        user1.recibeCarta(b.repartirCarta());
        System.out.println(user1);
    }
}
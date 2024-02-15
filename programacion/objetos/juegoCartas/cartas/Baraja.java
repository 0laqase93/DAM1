package cartas;

import cartas.*;
import java.util.ArrayList;
import java.util.Collections;

public class Baraja {
    ArrayList<Carta> cartas = new ArrayList<>();

    public Baraja(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }

    public void barajar() {
        Collections.shuffle(cartas);
        while ((cartas.get(0).getNum() == 13) || (cartas.get(0).getNum() == 14)) {
            Collections.shuffle(cartas);
        }
    }

    public Carta repartirCarta() {
        return cartas.remove(0);
    }

    @Override
    public String toString() {
        String output = "";
        for (Carta valor : cartas) {
            output += valor + " ";
        }
        return output;
    }
}

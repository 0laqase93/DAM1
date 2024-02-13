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

    // Esta función es para añadir una carta al final de la baraja
    public void añadirFinal(Carta carta) {
        cartas.add(carta);
    }
}

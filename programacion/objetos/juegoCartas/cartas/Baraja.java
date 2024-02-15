package cartas;

import cartas.*;
import java.util.ArrayList;
import java.util.Collections;

public class Baraja {
    ArrayList<Carta> cartas = new ArrayList<>();

    public Baraja() {}

    public Baraja(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }

    public void barajar() {
        Collections.shuffle(cartas);
        while ((cartas.get(0).getNum() >= 10)) { // Esto es para que no haya especiales al principio de la partida
            Collections.shuffle(cartas);
        }
    }

    public Carta repartirCarta() {
        return cartas.remove(0);
    }

    /**
     * Esta función agrega una carta a la baraja
     * @param carta La carta que se quiere añadir
     */
    public void agregarCarta(Carta carta) {
        cartas.add(carta);
    }

    /**
     * Esta función elimina la primera carta una carta a la baraja
     */
    public void eliminarCarta() {
        cartas.remove(0);
    }

    public int size() {
        return cartas.size();
    }

    @Override
    public String toString() {
        String output = "";
        for (Carta valor : cartas) {
            output += valor + " ";
        }
        return output;
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }
}

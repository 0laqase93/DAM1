package cartas;

import cartas.*;
import java.util.ArrayList;

public class Jugador {
    ArrayList<Carta> mano = new ArrayList<>();
    String nombre;

    public Jugador() {}

    public Jugador(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void recibeCarta(Carta carta) {
        mano.add(carta);
    }

    public ArrayList<Carta> getMano() {
        return mano;
    }
    
    public void setMano(ArrayList<Carta> mano) {
        this.mano = mano;
    }

    @Override
    public String toString() {
        String output = "";
        int indice = 1;
        for (Carta carta : mano) {
            output += (Colores.PURPLE + indice + Colores.RESET + ":" + carta + " ");
            indice++;
        }
        return output;
    }

    public int numCartas() {
        return this.mano.size();
    }
}

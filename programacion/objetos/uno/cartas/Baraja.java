package cartas;
import cartas.Cartas;
import java.util.ArrayList;

public class Baraja {
    ArrayList<Cartas> cartas;

    public Baraja(ArrayList<Cartas> cartas) {
        this.cartas = cartas;
    }

    public void Barajar() {
        int veces = (int)(Math.random()*100);

        for (int i = 0; i < veces; i++) {
            int indiceRandom = (int)(Math.random()*cartas.size());
            cartas.add(indiceRandom, cartas.remove(i));
        }
    }
}

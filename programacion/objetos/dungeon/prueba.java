import Sprites.*;

public class prueba {
    public static void main(String[] args) throws InterruptedException {
        Pantalla p = new Pantalla(23, 100);
        String historia = "En las antiguas crónicas de tiempos olvidados se nos cuenta el relato de ¬aOlaqase93"  + "¬, un héroe cuyo despertar resonó en las paredes de una arcaica mazmorra.\n\nLleno de confusión, ¬aOlaqase93" + "¬ se despertó de su sueño para encontrarse perdido en un pasillo lúgubre, cuyas baldosas desgastadas marcaban el inicio de un laberinto.\n\n Todavía somnoliento, se sentía embrujado por un poder ancestral: La capacidad de hacer ¬rHechizos¬, una habilidad existente desde tiempos remotos y cuyo origen parecía ser un regalo de los dioses ya olvidados.\n\n Con determinación en su pecho, ¬aOlaqase93" + "¬ se embarcó en la osada aventura de atravesar las 40 habitaciones, desafiando los enigmas y peligros que acechaban en cada pasillo. A través de la penumbra y del misterio, cada paso hacia adelante era un paso hacia la libertad, cada enfrentamiento una prueba de su valor y coraje.\n\n Así, armado con la magia de los dioses y el anhelo de la libertad, ¬aOlaqase93" + "¬ se adentró en las profundidades de la mazmorra con esperanza, decidido a desafiar las sombras y emerger triunfante bajo el resplandor del sol.\n";
        p.marco('b');
        Letras.imprimirFrase(historia, p, 2, 2, 200);
        p.mostrarPantalla();
    }
}

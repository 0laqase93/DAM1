import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import cartas.*;

public class uno {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Declaración de variables
        ListaCircular jugadores = new ListaCircular();
        Baraja b = new Baraja(rellenarMazo()); // Creación del mazo de robar
        b.barajar();
        Carta central = b.repartirCarta();
        int numJugadores = 0;
        int numCartarRepatir = 7; // Esto es el número de cartas con la que empizan la partida

        // Muestra el logo de UNO
        imprimirLogo();

        // Comprobar que se introduce un número válido de jugadores
        do {
            System.out.print( Colores.BLUE + "[+]" + Colores.RESET + " ¿Cuántos jugadores van a jugar? (2/4): ");
            numJugadores = sc.nextInt(); sc.nextLine();
            if ((numJugadores < 2) || (numJugadores > 4)) {
                System.out.println( Colores.RED + "[!] Número de jugadores no válido." + Colores.RESET);
            }
        } while ((numJugadores < 2) || (numJugadores > 4));

        // Meter los jugadores en la lista
        for (int i = 0; i < numJugadores; i++) {
            System.out.print( Colores.BLUE + "[+]" + Colores.RESET + " Introduce el nombre del jugador " + (i+1) + ": ");
            String nombre = sc.nextLine();
            jugadores.add(new Jugador(nombre));
            // Les reparte las cartas
            Jugador jugador = jugadores.get(i);
            for (int j = 0; j < numCartarRepatir; j++) {
                jugador.recibeCarta(b);
            }
            Collections.sort(jugador.getMano());
        }

        jugadores.get(0).juegaCarta(central);
    }

    /**
     * Este método se encarga de devolver una arrayList de cartas que contiene todas las cartas del UNO.
     * @return ArrayList<Carta>
     */
    public static ArrayList<Carta> rellenarMazo() {
        ArrayList<Carta> cartas = new ArrayList<>();
        char[] colores = { 'r', 'v', 'a', 'b' };
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                // Van de los número normales (0-9) hasta un par de especiales (+2/reverse/saltar):
                //  -> reverse: index 10
                //  -> +2:      index 11
                //  -> saltar:  index 12
                cartas.add(new Carta(j, colores[i]));
            }
        }
        // Las cartas especiales sin color (+4/Cambiar color):
        //  -> +4:            index 13
        //  -> Cambiar color: index 14
        for (int i = 0; i < 4; i++) {
            cartas.add(new Carta(13, 'x'));
        }
        for (int i = 0; i < 4; i++) {
            cartas.add(new Carta(14, 'x'));
        }
        return cartas;
    }

    /**
     * Este método se encarga de imprimir en pantalla el logo de UNO en color
     * @return void
     */
    public static void imprimirLogo() {
        //Limpiar pantalla
        System.out.print("\033[H\033[2J");  
        System.out.flush();

        System.out.println("\n" + //
        "                                     "+Colores.RED+"▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓"+Colores.RESET+"\n" + //
        "                               "+Colores.RED+"▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓"+Colores.RESET+"▓"+Colores.YELLOW+"░░░░░░░░░"+Colores.RESET+"\n" + //
        "                           "+Colores.RED+"▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓"+Colores.RESET+"▒░▒█"+Colores.YELLOW+"░░░░░░░░░"+Colores.RESET+"█▓░\n" + //
        "                        "+Colores.RED+"▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓"+Colores.RESET+"░░█"+Colores.YELLOW+"░░░░░░░░░░░░░░░"+Colores.RESET+"█░\n" + //
        "                     "+Colores.RED+"▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓"+Colores.RESET+"░░░█░"+Colores.RED+"▓"+Colores.RESET+"░░██"+Colores.YELLOW+"░░░░░░░░░░░░░░░░░"+Colores.RESET+"█░░\n" + //
        "                   "+Colores.RED+"▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓"+Colores.RESET+"░░██"+Colores.YELLOW+"░░░░"+Colores.RESET+"█░██▓"+Colores.YELLOW+"░░░░░"+Colores.RESET+"█████████"+Colores.YELLOW+"░░░░░░"+Colores.RESET+"█░\n" + //
        "                 "+Colores.RED+"▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓"+Colores.RESET+"░░█▓█"+Colores.YELLOW+"░░░░░"+Colores.RESET+"████"+Colores.YELLOW+"░░░░░"+Colores.RESET+"███████▓▓▓█"+Colores.YELLOW+"░░░░░"+Colores.RESET+"█░\n" + //
        "               "+Colores.RED+"▓▓▓▓▓▓▓▓▓▓"+Colores.RESET+"░░░██░░"+Colores.RED+"▓▓"+Colores.RESET+"░█▓▓▓█"+Colores.YELLOW+"░░░░░"+Colores.RESET+"███"+Colores.YELLOW+"░░░░"+Colores.RESET+"█░"+Colores.RED+"▓▓▓▓"+Colores.RESET+"░░█▓▓██"+Colores.YELLOW+"░░░░░"+Colores.RESET+"█░\n" + //
        "             "+Colores.RED+"▓▓▓▓▓▓▓"+Colores.RESET+"░░░██"+Colores.YELLOW+"░░░░░░"+Colores.RESET+"██░░░█▓█▓█"+Colores.YELLOW+"░░░░░"+Colores.RESET+"██"+Colores.YELLOW+"░░░░░"+Colores.RESET+"░"+Colores.RED+"▓▓▓▓▓▓"+Colores.RESET+"░█▓▓█"+Colores.YELLOW+"░░░░░"+Colores.RESET+"█░\n" + //
        "            "+Colores.RED+"▓▓▓▓"+Colores.RESET+"░░██"+Colores.YELLOW+"░░░░"+Colores.RESET+"█"+Colores.YELLOW+"░░░░░░░░░"+Colores.RESET+"█▓░█▓██"+Colores.YELLOW+"░░░░░░"+Colores.RESET+"█"+Colores.YELLOW+"░░░░░"+Colores.RESET+"█░"+Colores.RED+"▓▓▓▓▓"+Colores.RESET+"░████"+Colores.YELLOW+"░░░░░"+Colores.RESET+"█░\n" + //
        "          "+Colores.RED+"▓▓▓▓"+Colores.RESET+"░░█▓█"+Colores.YELLOW+"░░░░░"+Colores.RESET+"██"+Colores.YELLOW+"░░░░░░░░░░░"+Colores.RESET+"█████"+Colores.YELLOW+"░░░░░"+Colores.RESET+"█"+Colores.YELLOW+"░░░░░░"+Colores.RESET+"█░"+Colores.RED+"▓▓▓▓▓"+Colores.RESET+"░██"+Colores.YELLOW+"░░░░░░"+Colores.RESET+"█░\n" + //
        "     ░░░██░"+Colores.RED+"▓▓▓"+Colores.RESET+"░█▓▓▓█"+Colores.YELLOW+"░░░░░"+Colores.RESET+"██"+Colores.YELLOW+"░░░░░░░░░░░░░"+Colores.RESET+"███"+Colores.YELLOW+"░░░░░"+Colores.RESET+"█"+Colores.YELLOW+"░░░░░░░"+Colores.RESET+"█"+Colores.YELLOW+"░░░░"+Colores.RESET+"██"+Colores.YELLOW+"░░░░░░"+Colores.RESET+"█░\n" + //
        "  ░▒█"+Colores.YELLOW+"░░░░░"+Colores.RESET+"█░"+Colores.RED+"▓▓▓"+Colores.RESET+"░█▓█▓█"+Colores.YELLOW+"░░░░░"+Colores.RESET+"██"+Colores.YELLOW+"░░░░░"+Colores.RESET+"█"+Colores.YELLOW+"░░░░░░░░░░░░░░░"+Colores.RESET+"█"+Colores.YELLOW+"░░░░░░░░░░░░░░░░░░"+Colores.RESET+"█░\n" + //
        "░░████"+Colores.YELLOW+"░░░░░"+Colores.RESET+"█░"+Colores.RED+"▓▓"+Colores.RESET+"░░█▓██"+Colores.YELLOW+"░░░░░▒"+Colores.RESET+"█"+Colores.YELLOW+"░░░░░"+Colores.RESET+"▓███"+Colores.YELLOW+"░░░░░░░░░░░░"+Colores.RESET+"███"+Colores.YELLOW+"░░░░░░░░░░░░░░"+Colores.RESET+"██░\n" + //
        "░░████"+Colores.YELLOW+"░░░░░░"+Colores.RESET+"░░"+Colores.RED+"▓▓"+Colores.RESET+"░█████"+Colores.YELLOW+"░░░░░"+Colores.RESET+"██"+Colores.YELLOW+"░░░░░"+Colores.RESET+"██████"+Colores.YELLOW+"░░░░░░░░░░"+Colores.RESET+"██████"+Colores.YELLOW+"░░░░░░░"+Colores.RESET+"███░\n" + //
        " ░█████"+Colores.YELLOW+"░░░░░"+Colores.RESET+"█░"+Colores.RED+"▓▓▓"+Colores.RESET+"░█████"+Colores.YELLOW+"░░░░░"+Colores.RESET+"██"+Colores.YELLOW+"░░░░░"+Colores.RESET+"████████"+Colores.YELLOW+"░░░░░░░░"+Colores.RESET+"█████████████░\n" + //
        "  ░█████"+Colores.YELLOW+"░░░░░"+Colores.RESET+"█░"+Colores.RED+"▓▓"+Colores.RESET+"░░████"+Colores.YELLOW+"░░░░░"+Colores.RESET+"▓█"+Colores.YELLOW+"░░░░░░"+Colores.RESET+"░████████"+Colores.YELLOW+"░░░░░░▒"+Colores.RESET+"░███████░░░\n" + //
        "  ░░██▓▓"+Colores.YELLOW+"░░░░░░"+Colores.RESET+"█░"+Colores.RED+"▓▓"+Colores.RESET+"░█████"+Colores.YELLOW+"░░░░░"+Colores.RESET+"██"+Colores.YELLOW+"░░░░░"+Colores.RESET+"█░░░███████████░░"+Colores.RED+"▓▓▓▓▓▓▓"+Colores.RESET+"\n" + //
        "   ░█▓▓▓█"+Colores.YELLOW+"░░░░░"+Colores.RESET+"█░"+Colores.RED+"▓▓▓"+Colores.RESET+"░████"+Colores.YELLOW+"░░░░░"+Colores.RESET+"███"+Colores.YELLOW+"░░░░░"+Colores.RESET+"█░"+Colores.RED+"▓▓"+Colores.RESET+"░███████░░"+Colores.RED+"▓▓▓▓▓▓▓▓"+Colores.RESET+"\n" + //
        "    ░███▓█"+Colores.YELLOW+"░░░░░░"+Colores.RESET+"█░░░██"+Colores.YELLOW+"▒░░░░░▒"+Colores.RESET+"███"+Colores.YELLOW+"░░░░░"+Colores.RESET+"██░"+Colores.RED+"▓▓▓"+Colores.RESET+"░░░░"+Colores.RED+"▓▓▓▓▓▓▓▓▓▓"+Colores.RESET+"\n" + //
        "     ░█▓███"+Colores.YELLOW+"░░░░░░░░░░░░░░░░░"+Colores.RESET+"█████████░"+Colores.RED+"▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓"+Colores.RESET+"\n" + //
        "     ░░█████"+Colores.YELLOW+"░░░░░░░░░░░░░░"+Colores.RESET+"█░░█████░░"+Colores.RED+"▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓"+Colores.RESET+"\n" + //
        "      ░░██████▓"+Colores.YELLOW+"░░░░░░░"+Colores.RESET+"███░░"+Colores.RED+"▓▓"+Colores.RESET+"░░░"+Colores.RED+"▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓"+Colores.RESET+"\n" + //
        "        ░██████████████░░"+Colores.RED+"▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓"+Colores.RESET+"\n" + //
        "          ░░████████░░"+Colores.RED+"▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓"+Colores.RESET+"\n" + //
        "              "+Colores.RED+"▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓"+Colores.RESET+"\n" + //
        "                   "+Colores.RED+"▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓"+Colores.RESET+"\n");
    }
}

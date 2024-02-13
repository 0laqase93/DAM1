import java.util.ArrayList;
import java.util.Scanner;
import cartas.*;

public class uno {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Declaración de variables
        ListaCircular jugadores = new ListaCircular();
        Baraja b = new Baraja(rellenarMazo()); // Creación del mazo de robar
        b.barajar();
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
            for (int j = 0; j < numCartarRepatir; j++) {
                Jugador jugador = jugadores.get(i);
                jugador.recibeCarta(b.repartirCarta());
            }
        }

        System.out.println(CartasASCII.getCartaAscii(0));
        System.out.println(CartasASCII.getCartaAscii(1));
    }

    /**
     * Este método se encarga de devolver una arrayList de cartas que contiene todas las cartas del UNO.
     * @return ArrayList<Carta>
     */
    public static ArrayList<Carta> rellenarMazo() {
        ArrayList<Carta> cartas = new ArrayList<>();
        String[] colores = { Colores.RED, Colores.BLUE, Colores.YELLOW, Colores.GREEN };
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                // Van de los número normales (0-9) hasta un par de especiales (+2/reverse/saltar):
                //  -> +2:      index 10
                //  -> reverse: index 11
                //  -> saltar:  index 12
                cartas.add(new Carta(j, colores[i]));
            }
        }
        // Las cartas especiales sin color (+4/Cambiar color):
        //  -> +4:            index 14
        //  -> Cambiar color: index 15
        for (int i = 0; i < 4; i++) {
            cartas.add(new Carta(14, Colores.BLACK_BACKGROUND));
        }
        for (int i = 0; i < 4; i++) {
            cartas.add(new Carta(15, Colores.BLACK_BACKGROUND));
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

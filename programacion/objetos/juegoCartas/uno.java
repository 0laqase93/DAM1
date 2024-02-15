import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import cartas.*;

public class uno {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Declaración de variables
        ListaCircular jugadores = new ListaCircular();
        JuegoUNO uno = new JuegoUNO();
        Baraja b = new Baraja(rellenarMazo()); // Creación del mazo de robar
        Baraja aux = new Baraja();
        b.barajar();
        Carta central = b.repartirCarta();
        int numJugadores = 0;
        int numCartarRepatir = 20; // Esto es el número de cartas con la que empizan la partida
        boolean direccion = true; // True sentido normal, false sentido al revés
        boolean funcionEjecutada = false; // Esto es para evitar que una carta especial se ejecute varias veces

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
            Jugador jugador = jugadores.get(i).getValor();
            for (int j = 0; j < numCartarRepatir; j++) {
                jugador.recibeCarta(b);
            }
            Collections.sort(jugador.getMano());
        }
        Nodo nodoActual = jugadores.get(0);
        Jugador jugadorActual = nodoActual.getValor();

        do {
            rellenarBaraja(b, aux); // Esto es para saber si la baraja está vacía y si lo está pues la rellena
            if ((central.getNum() == 10) && (!funcionEjecutada)) { // Función para reverse (cambiar orden)
                funcionEjecutada = true;
                System.out.println(Colores.BLUE + "[+]" + Colores.RESET + " Orden invertido");
                if (direccion) {
                    direccion = false;
                } else {
                    direccion = true;
                }
                nodoActual = nodoActual.getSiguienteJugador(direccion);
                jugadorActual = nodoActual.getValor();
                nodoActual = nodoActual.getSiguienteJugador(direccion);
                jugadorActual = nodoActual.getValor();
                System.out.println(Colores.BLUE + "[+]" + Colores.RESET + " Orden invertido, le toca ha " + Colores.RED + nodoActual.getValor().getNombre() + Colores.RESET);
                System.out.print(Colores.BLUE + "[+]" + Colores.RESET + " Presiona enter para ver las cartas.");
                sc.nextLine();
            }

            if ((central.getNum() == 11) && (!funcionEjecutada)) { // Función de +2
                funcionEjecutada = true;
                System.out.println(Colores.BLUE + "[+]" + Colores.RESET + " Te han hecho robar " + Colores.RED + "2" + Colores.RESET + " cartas.");
                System.out.print(Colores.BLUE + "[+]" + Colores.RESET + " Presiona enter para ver las cartas.");
                sc.nextLine();
                for (int i = 0; i < 2; i++) {
                    jugadorActual.recibeCarta(b);
                    rellenarBaraja(b, aux);
                }
                Collections.sort(jugadorActual.getMano());
            }

            if ((central.getNum() == 12) && (!funcionEjecutada)) { // Función saltar jugador
                funcionEjecutada = true;
                System.out.println(Colores.BLUE + "[+]" + Colores.RESET + " Jugador " + Colores.RED + jugadorActual.getNombre() + Colores.RESET + " saltado.");
                nodoActual = nodoActual.getSiguienteJugador(direccion);
                jugadorActual = nodoActual.getValor();
                System.out.println(Colores.RED + "[+]" + Colores.RESET + " Le toca ha " + Colores.RED + nodoActual.getValor().getNombre() + Colores.RESET);
                System.out.print(Colores.BLUE + "[+]" + Colores.RESET + " Presiona enter para ver las cartas.");
                sc.nextLine();
            }

            if ((central.getNum() == 13) && (!funcionEjecutada)) { // Función de +4
                funcionEjecutada = true;
                System.out.println(Colores.BLUE + "[+]" + Colores.RESET + " Te han hecho robar " + Colores.RED + "4" + Colores.RESET + " cartas.");
                System.out.print(Colores.BLUE + "[+]" + Colores.RESET + " Presiona enter para ver las cartas.");
                sc.nextLine();
                for (int i = 0; i < 4; i++) {
                    jugadorActual.recibeCarta(b);
                    rellenarBaraja(b, aux);
                }
                Collections.sort(jugadorActual.getMano());
            }

            Carta cartaJugada = jugadorActual.juegaCarta(central);
            if (cartaJugada == null) {
                jugadorActual.recibeCarta(b);
            } else {
                funcionEjecutada = false;
                aux.agregarCarta(cartaJugada);
                central = cartaJugada;
            }
            //Limpiar pantalla
            System.out.print("\033[H\033[2J");
            System.out.flush();

            if (!uno.ganaMano(jugadores)) {
                nodoActual = nodoActual.getSiguienteJugador(direccion);
                jugadorActual = nodoActual.getValor();
                System.out.println(Colores.BLUE + "[+]" + Colores.RESET + " Ya has jugado, le toca ha " + Colores.RED + nodoActual.getValor().getNombre() + Colores.RESET);
                System.out.print(Colores.BLUE + "[+]" + Colores.RESET + " Presiona enter para ver las cartas.");
                sc.nextLine();
            }
        } while (!uno.ganaMano(jugadores));

        // Imprimir el jugador que ha ganado
        System.out.println(
            Colores.GREEN +
            "  ___    ___ ________  ___  ___          ___       __   ___  ________      \n" + 
            " |\\  \\  /  /|\\   __  \\|\\  \\|\\  \\        |\\  \\     |\\  \\|\\  \\|\\   ___  \\    \n" + 
            " \\ \\  \\/  / | \\  \\|\\  \\ \\  \\\\\\  \\       \\ \\  \\    \\ \\  \\ \\  \\ \\  \\\\ \\  \\   \n" + 
            "  \\ \\    / / \\ \\  \\\\\\  \\ \\  \\\\\\  \\       \\ \\  \\  __\\ \\  \\ \\  \\ \\  \\\\ \\  \\  \n" + 
            "   \\/  /  /   \\ \\  \\\\\\  \\ \\  \\\\\\  \\       \\ \\  \\|\\__\\_\\  \\ \\  \\ \\  \\\\ \\  \\ \n" + 
            " __/  / /      \\ \\_______\\ \\_______\\       \\ \\____________\\ \\__\\ \\__\\\\ \\__\\\n" + 
            "|\\___/ /        \\|_______|\\|_______|        \\|____________|\\|__|\\|__| \\|__|\n" + 
            "\\|___|/                                                                    \n" + 
            Colores.RESET);
        System.out.println(Colores.GREEN + "[++] Ha ganado " + jugadorActual.getNombre() + Colores.RESET);
    }


    /**
     * Esta Función comprueba si la baraja está vacía y la rellena si lo está
     * @param principal Baraja con la que se juega
     * @param aux Baraja donde se han ido jugando las cartas
     * @return La baraja ya mezclada
     */
    public static Baraja rellenarBaraja(Baraja principal, Baraja aux) {
        if (principal.size() == 0) {
            int index = 0;
            for (Carta valor : aux.getCartas()) {
                principal.agregarCarta(valor);
                aux.eliminarCarta();
            }
            principal.barajar();
        }
        return principal;
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

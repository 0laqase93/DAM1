import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Nodo {
    private Jugador valor;
    private Nodo anterior;
    private Nodo siguiente;

    public Nodo() {
        this.valor = new Jugador();
        this.anterior = null;
        this.siguiente = null;
    }

    public Nodo(Jugador valor, Nodo anterior, Nodo siguiente) {
        this.valor = valor;
        this.anterior = anterior;
        this.siguiente = siguiente;
    }

    public Jugador getValor() {
        return valor;
    }

    public void setValor(Jugador valor) {
        this.valor = valor;
    }

    public Nodo getAnterior() {
        return anterior;
    }

    public void setAnterior(Nodo anterior) {
        this.anterior = anterior;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}

class ListaCircular {
    private Nodo inicio;
    private Nodo ultimo;
    private int size;
    
    public ListaCircular() {
        this.inicio = null;
        this.ultimo = null;
        this.size = 0;
    }

    public ListaCircular(Nodo inicio, Nodo ultimo, int size) {
        this.inicio = inicio;
        this.ultimo = ultimo;
        this.size = size;
    }

    public boolean esVacia() {
        return inicio == null;
    }

    public void add(Jugador valor) {
        Nodo nuevo = new Nodo();
        nuevo.setValor(valor);
        if (esVacia()) {
            this.inicio = nuevo;
            this.ultimo = nuevo;
            this.ultimo.setAnterior(nuevo);
            this.ultimo.setSiguiente(nuevo);
        } else {
            this.ultimo.setSiguiente(nuevo);
            nuevo.setSiguiente(this.inicio);
            this.ultimo = nuevo;
        }
        size++;
    }

    public Jugador get(int index) {
        int aux = 0;
        Nodo nodo = this.inicio;
        while (aux != index) {
            nodo = nodo.getSiguiente();
            aux++;
        }
        return nodo.getValor();
    }
}

class Colores {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
}

class Carta implements Comparable<Carta> {
    private char num;
    private String color;
    private Map<String, Integer> colores = new HashMap<>(); 

    public Carta() {
        num = '-';
    }

    public Carta(char num, String color) {
        this.num = num;
        this.color = color;
        colores.put(Colores.RED, 1);
        colores.put(Colores.GREEN, 2);
        colores.put(Colores.BLUE, 3);
        colores.put(Colores.YELLOW, 4);
        colores.put("", 5); //cartas especiales
    }

    public int getNum() {
        return num;
    }

    public void setNum(char num) {
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
        return "[" + this.color + this.num + Colores.RESET + "]";
    }

    @Override
    public int compareTo(Carta carta) {
        return this.colores.get(this.color) - carta.colores.get(carta.getColor());
    }
}

class Baraja {
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


}

class Jugador {
    ArrayList<Carta> mano = new ArrayList<>();

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
}

public class uno {

    public static Scanner sc = new Scanner(System.in);
    public static int turno = 0;
    
    public static void main(String[] args) throws InterruptedException {
        int numJugadores = 0;
        ListaCircular jugadores = new ListaCircular();
        imprimirLogo();
        do {
            System.out.print("[+] ¿Cuántos jugadores van a jugar? (2/4): ");
            numJugadores = sc.nextInt(); sc.nextLine();
            if (numJugadores < 2 || numJugadores > 4) {
                 System.out.println(Colores.RED + "[!] Opción no válida." + Colores.RESET);
            }
        } while (numJugadores < 2 || numJugadores > 4);

        for (int i = 0; i < numJugadores; i++) {
            jugadores.add(new Jugador());
        }

        Baraja b = new Baraja(llenarBaraja());
        b.barajar();
        Carta central = b.repartirCarta();
        //Repartir cartas
        for (int i = 0; i < 7; i++) {
            jugadores.get(turno).recibeCarta(b.repartirCarta());
        }
        Collections.sort(jugadores.get(turno).getMano());
        do {
            mostrarMenú(1, central, jugadores.get(turno));
            int opcion = elegirOpcion();
            if (opcion == 1) {
                Carta jugada = elegirCarta(jugadores.get(turno), central);
                if (jugada.getNum() != '-') { //Si es '-' el jugador no ha elegido carta
                    central = jugada;
                }
            } else if (opcion == 2) {
                jugadores.get(turno).recibeCarta(b.repartirCarta());
                System.out.println("[+] Has robado la carta " + jugadores.get(turno).getMano().get(jugadores.get(turno).getMano().size() - 1) + ". Presione enter para continuar...");
                Collections.sort(jugadores.get(turno).getMano());
                sc.nextLine();
            } else {
                System.out.println("UNO");
                Thread.sleep(3000);
            }
        } while (true);
    }

    public static ArrayList<Carta> llenarBaraja() {
        ArrayList<Carta> cartas = new ArrayList<>();
        String[] colores = { Colores.RED, Colores.GREEN, Colores.YELLOW, Colores.BLUE };
        // Añadir los numeros (0-9) con sus colores
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                cartas.add(new Carta((char)(j + 48), colores[i]));
            }
            // Añadir cartas especiales
            cartas.add(new Carta('↔', colores[i])); //Reverse
            cartas.add(new Carta('Ø', colores[i])); //pasar
            cartas.add(new Carta('═', colores[i])); //+2
        }
        // Añadir cartas especiales
        for (int i = 0; i < 4; i++) {
            cartas.add(new Carta('C', "")); //Cambiar color
            cartas.add(new Carta('╬', "")); //+4
        }
        return cartas;
    }

    public static void mostrarMenú(int menu, Carta central, Jugador user) {
        //Limpiar pantalla
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 

        System.out.println(Colores.YELLOW + "-----Central-----" + Colores.RESET);
        System.out.println("       " + central);
        System.out.println(Colores.YELLOW + "-----------------" + Colores.RESET);
        switch (menu) {
            case 1:
                System.out.println(Colores.PURPLE + "-->" + Colores.RESET + Colores.CYAN + " 1." + Colores.RESET + " Jugar cartas");
                System.out.println(Colores.PURPLE + "-->" + Colores.RESET + Colores.CYAN + " 2." + Colores.RESET + " Robar carta");
                System.out.println(Colores.PURPLE + "-->" + Colores.RESET + Colores.CYAN + " 3." + Colores.RESET + " Decir UNO");
                break;
        
            case 2:
                System.out.println("Usuario: " + user);
                break;
        }
    }

    public static int elegirOpcion() {
        int input = 0;
        System.out.print("[+] Elija la opción que quiere hacer: ");
        input = sc.nextInt(); sc.nextLine();
        return input;
    }

    public static Carta elegirCarta(Jugador jugador, Carta central) {
        int index = 1;
        String input = "";
        Carta cartaJugada = new Carta();
        boolean valida = true;

        System.out.println(jugador); //Muestra las cartas del usuario
        //Leer opción
        do {
            do {
                System.out.print("[+] Elija la posición de la carta que quiere jugar(1-" + jugador.getMano().size() + ")(\"volver\" para volver): ");
                input.toLowerCase();
                input = sc.nextLine();
                if (!input.equals("volver")) {
                    index = Integer.parseInt(input);
                    if ((index < 1) || (index > jugador.getMano().size())) {
                        System.out.println(Colores.RED + "[!] Opción no válida." + Colores.RESET);
                    }
                }
            } while ((index < 1) || (index > jugador.getMano().size()) && (!input.equals("volver")));
            if (!input.equals("volver")) {
                index--; // Esto es para que se ajuste a los índices del arraylist
                cartaJugada = jugador.getMano().get(index);
        
                // Esta condición es para ver si la carta tiene el mismo color o el mismo número
                // "" es una carta especial
                if (cartaJugada.getColor() != "") {
                    if ((cartaJugada.getColor() != central.getColor()) && (cartaJugada.getNum() != central.getNum())) {
                        System.out.println(Colores.RED + "[+] La carta sececionada no se puede jugar" + Colores.RESET);
                        valida = false;
                    } else {
                        valida = true;
                    }
                } else {
                    valida = true;
                    //Esto es para cambiar el color de la carta especial al deseado
                    boolean colorValido = false;
                    String color = "";
                    //Comprueba que la opción esté entre las posibles opciones
                    do {
                        colorValido = false;
                        System.out.print("[+] ¿A cuál color quieres cambiar? (rojo/verde/amarillo/azul): ");
                        color = sc.nextLine();
                        color.toLowerCase();
                        if (color.equals("rojo") || color.equals("verde") || color.equals("amarillo") || color.equals("azul")) {
                            colorValido = true;
                        } else {
                            System.out.println("[+] Opción no válida");
                        }
                    } while (!colorValido);
                    //Cambia el color al color deseado
                    switch (color) {
                        case "rojo":
                            jugador.getMano().get(index).setColor(Colores.RED);
                            break;
                    
                        case "verde":
                            jugador.getMano().get(index).setColor(Colores.GREEN);
                            break;
    
                        case "amarillo":
                            jugador.getMano().get(index).setColor(Colores.YELLOW);
                            break;
    
                        case "azul":
                            jugador.getMano().get(index).setColor(Colores.BLUE);
                            break;
                    }
                }
            }
        } while (!valida);
        if (!input.equals("volver")) {
            return jugador.getMano().remove(index);
        } else {
            return new Carta();
        }
    }

    public static void imprimirLogo() {
        //Limpiar pantalla
        System.out.print("\033[H\033[2J");  
        System.out.flush();

        System.out.println("\n" + //
        "                                     "+Colores.RED+"▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓"+Colores.RESET+"                   \n" + //
        "                               "+Colores.RED+"▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓"+Colores.RESET+"▓"+Colores.YELLOW+"░░░░░░░░░"+Colores.RESET+"         \n" + //
        "                           "+Colores.RED+"▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓"+Colores.RESET+"▒░▒█"+Colores.YELLOW+"░░░░░░░░░"+Colores.RESET+"█▓░      \n" + //
        "                        "+Colores.RED+"▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓"+Colores.RESET+"░░█"+Colores.YELLOW+"░░░░░░░░░░░░░░░"+Colores.RESET+"█░    \n" + //
        "                     ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓"+Colores.RESET+"░░░█░▓░░██"+Colores.YELLOW+"░░░░░░░░░░░░░░░░░"+Colores.RESET+"█░░  \n" + //
        "                   ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓░░██░░░░█░██▓"+Colores.YELLOW+"░░░░░"+Colores.RESET+"█████████"+Colores.YELLOW+"░░░░░░"+Colores.RESET+"█░ \n" + //
        "                 ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓░░█▓█░░░░░████"+Colores.YELLOW+"░░░░░"+Colores.RESET+"███████▓▓▓█"+Colores.YELLOW+"░░░░░"+Colores.RESET+"█░ \n" + //
        "               ▓▓▓▓▓▓▓▓▓▓░░░██░░▓▓░█▓▓▓█░░░░░███"+Colores.YELLOW+"░░░░"+Colores.RESET+"█░"+Colores.RED+"▓▓▓▓"+Colores.RESET+"░░█▓▓██"+Colores.YELLOW+"░░░░░"+Colores.RESET+"█░\n" + //
        "             ▓▓▓▓▓▓▓░░░██░░░░░░██░░░█▓█▓█░░░░░██"+Colores.YELLOW+"░░░░░"+Colores.RESET+"░"+Colores.RED+"▓▓▓▓▓▓"+Colores.RESET+"░█▓▓█"+Colores.YELLOW+"░░░░░"+Colores.RESET+"█░\n" + //
        "            ▓▓▓▓░░██░░░░█░░░░░░░░░█▓░█▓██░░░░░░█"+Colores.YELLOW+"░░░░░"+Colores.RESET+"█░"+Colores.RED+"▓▓▓▓▓"+Colores.RESET+"░████"+Colores.YELLOW+"░░░░░"+Colores.RESET+"█░\n" + //
        "          ▓▓▓▓░░█▓█░░░░░██░░░░░░░░░░░█████░░░░░█"+Colores.YELLOW+"░░░░░░"+Colores.RESET+"█░"+Colores.RED+"▓▓▓▓▓"+Colores.RESET+"░██"+Colores.YELLOW+"░░░░░░"+Colores.RESET+"█░\n" + //
        "     ░░░██░▓▓▓░█▓▓▓█░░░░░██░░░░░░░░░░░░░███░░░░░█"+Colores.YELLOW+"░░░░░░░"+Colores.RESET+"█"+Colores.YELLOW+"░░░░"+Colores.RESET+"██"+Colores.YELLOW+"░░░░░░"+Colores.RESET+"█░ \n" + //
        "  ░▒█░░░░░█░▓▓▓░█▓█▓█░░░░░██░░░░░█░░░░░░░░░░░░░░░█"+Colores.YELLOW+"░░░░░░░░░░░░░░░░░░"+Colores.RESET+"█░  \n" + //
        "░░████░░░░░█░▓▓░░█▓██░░░░░▒█░░░░░▓███░░░░░░░░░░░░███"+Colores.YELLOW+"░░░░░░░░░░░░░░"+Colores.RESET+"██░   \n" + //
        "░░████░░░░░░░░▓▓░█████░░░░░██░░░░░██████░░░░░░░░░░██████"+Colores.YELLOW+"░░░░░░░"+Colores.RESET+"███░     \n" + //
        " ░█████░░░░░█░▓▓▓░█████░░░░░██░░░░░████████░░░░░░░░█████████████░       \n" + //
        "  ░█████░░░░░█░▓▓░░████░░░░░▓█░░░░░░░████████░░░░░░▒░███████░░░         \n" + //
        "  ░░██▓▓░░░░░░█░▓▓░█████░░░░░██░░░░░█░░░███████████░░▓▓▓▓▓▓▓"+Colores.RESET+"            \n" + //
        "   ░█▓▓▓█░░░░░█░▓▓▓░████░░░░░███░░░░░█░▓▓░███████░░▓▓▓▓▓▓▓▓"+Colores.RESET+"             \n" + //
        "    ░███▓█░░░░░░█░░░██▒░░░░░▒███░░░░░██░▓▓▓░░░░▓▓▓▓▓▓▓▓▓▓"+Colores.RESET+"               \n" + //
        "     ░█▓███░░░░░░░░░░░░░░░░░█████████░▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓"+Colores.RESET+"                 \n" + //
        "     ░░█████░░░░░░░░░░░░░░█░░█████░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓"+Colores.RESET+"                   \n" + //
        "      ░░██████▓░░░░░░░███░░▓▓░░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓"+Colores.RESET+"                      \n" + //
        "        ░██████████████░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓"+Colores.RESET+"                        \n" + //
        "          ░░████████░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓"+Colores.RESET+"                            \n" + //
        "              ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓"+Colores.RESET+"                                \n" + //
        "                   ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓"+Colores.RESET+"                                      \n");
    }
}
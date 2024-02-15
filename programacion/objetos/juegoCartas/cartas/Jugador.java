package cartas;

import cartas.*;
import java.util.ArrayList;
import java.util.Scanner;

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

    public ArrayList<Carta> getMano() {
        return mano;
    }
    
    public void setMano(ArrayList<Carta> mano) {
        this.mano = mano;
    }

    public void recibeCarta(Baraja b) {
        mano.add(b.repartirCarta());
    }

    public int numCartas() {
        return this.mano.size();
    }

    public Carta juegaCarta(Carta central) {
        Scanner sc = new Scanner(System.in);
        int extensorVertical = 12*(mano.size()/5); 
        Pantalla screen = new Pantalla(150, 93 + extensorVertical);
        Carta jugada = new Carta(0, 'x');
        int x = 0, y = 0;
        String textoCentral =
      "   █████████  ██████████ ██████   █████ ███████████ ███████████     █████████    █████\n" +
      "  ███░░░░░███░░███░░░░░█░░██████ ░░███ ░█░░░███░░░█░░███░░░░░███   ███░░░░░███ ░░███\n" +
      " ███     ░░░  ░███  █ ░  ░███░███ ░███ ░   ░███  ░  ░███    ░███  ░███    ░███  ░███\n" +
      "░███          ░██████    ░███░░███░███     ░███     ░██████████   ░███████████  ░███\n" +
      "░███          ░███░░█    ░███ ░░██████     ░███     ░███░░░░░███  ░███░░░░░███  ░███\n" +
      "░░███     ███ ░███ ░   █ ░███  ░░█████     ░███     ░███    ░███  ░███    ░███  ░███      █\n" +
      " ░░█████████  ██████████ █████  ░░█████    █████    █████   █████ █████   █████ ███████████\n" +
      "  ░░░░░░░░░  ░░░░░░░░░░ ░░░░░    ░░░░░    ░░░░░    ░░░░░   ░░░░░ ░░░░░   ░░░░░ ░░░░░░░░░░░";

        screen.situa(30, 0, textoCentral, 'l');
        screen.situa(50, 10, CartasASCII.getCartaAscii(central.getNum()), central.getColor());
        screen.situa(0, 51, "████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████\n", 'x');
        
        y = 53;

        int contador = 0; // Esto es para contar si hay 5 cartas en una fila
        // Situamos las cartas en la pantalla
        for (Carta valor : mano) {
            screen.situa(x, y, CartasASCII.getCartaAscii(valor.getNum()), valor.getColor());
            x += 20;
            if (contador == 5) {
                x = 0;
                y += 12;
                contador = -1;
            }
            contador++;
        }

        screen.mostra();// Muestra las cartas


        //Muestra el formato del input
        System.out.println(Colores.RED + "\n[!] FORMATO:" + Colores.RESET);
        System.out.println(Colores.PURPLE + "\t--> Carta normal:" + Colores.RESET + " \"8 rojo\" | \"8 azul\" | \"8 amarillo \" | \"8 verde\"");
        System.out.println(Colores.PURPLE + "\t--> Carta especial:" + Colores.RESET + " \"reverse\" | \"saltar\" | \"+2\" | \"+4\" | \"cambiar color\"");
        System.out.println(Colores.PURPLE + "\t--> Robar carta:" + Colores.RESET + " \"robar\"");

        //Le pide al jugador que carta va escoger
        String input = "";
        boolean valido = true;
        do {
            System.out.print(Colores.CYAN + "\n[+]" + Colores.RESET + " Introduce la carta que quiera jugar: ");
            valido = true;
            input = sc.nextLine();
            input.toLowerCase();
            if (!(input.equals("reverse")) || !(input.equals("saltar")) || !(input.equals("+2")) || !(input.equals("+4")) || !(input.equals("robar")) || !(input.equals("cambiar color"))) {
                int numero = Integer.parseInt(input.split(" ")[0]);
                String colorString = input.split(" ")[1];
                char color = 'x';
                switch (colorString) { // Pasar color al formato carácter
                    case "rojo":
                        color = 'r';
                        break;
                
                    case "azul":
                        color = 'b';
                        break;

                    case "amarillo":
                        color = 'a';
                        break;

                    case "verde":
                        color = 'v';
                        break;
                    default:
                        System.out.println(Colores.RED + "[!] Opción no válida" + Colores.RESET);
                        valido = false;
                        break;
                }
                if (valido) {
                    valido = false;
                    for (Carta valor : mano) {
                        if ((valor.getNum() == numero) && (valor.getColor() == color)) {
                            jugada = valor;
                            valido = true;
                            break; // Lo siento por el break
                        }
                    }
                    if (!valido) {
                        System.out.println(Colores.RED + "[!] No tienes esta carta" + Colores.RESET);
                    }
                    if ((central.getColor() != jugada.getColor()) && (central.getNum() != jugada.getNum())) {
                        valido = false;
                        System.out.println(Colores.RED + "[!] Esta carta no se puede jugar." + Colores.RESET);
                    }
                }
            } else {
                jugada = mano.get(0);
            }
        } while (!valido);
        return jugada;
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

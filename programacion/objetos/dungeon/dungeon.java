import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import Armas.*;
import Heroe.*;
import Monstruo.*;
import Sprites.*;

public class dungeon {
    
    static boolean[] pasillo = new boolean[40];
    static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) throws InterruptedException {
        Personaje jugador = new Personaje();
        ArrayList<Monstruo> monstruos = new ArrayList<>();
        monstruos = creacionEnemigos(3);
        String nick;
        int option;

        //Inserción de datos del jugador
        //Limpiar pantalla
        System.out.print("\033[H\033[2J");  
        System.out.flush();

        System.out.print(Colors.BLUE + "[+]" + Colors.RESET + " Nombre del personaje: ");
        nick = sc.nextLine();

        // Crear tipo de jugador
        MostrarMenu(1);
        option = sc.nextInt(); sc.nextLine();
        switch (option) {
            case 1: jugador = new Mago(nick); break;
            case 2: jugador = new Guerrero(nick); break;
            case 3: jugador = new Enano(nick); break;
            default:
                break;
        }

        // Añadir arma al jugador
        MostrarMenu(2);
        option = sc.nextInt(); sc.nextLine();
        switch (option) {
            case 1: jugador.setArma(new Arco()); break;
            case 2: jugador.setArma(new Espada()); break;
            case 3: jugador.setArma(new Hacha()); break;
            case 4: jugador.setArma(new Hechizo()); break;
            default:
                break;
        }

        System.out.println(Colors.RED + "[!!] ¡¡Minimiza la pantalla al mínimo!!" + Colors.RESET);
        System.out.println("[+] Lo único que tienes que hacer es presionar enter...");
        sc.nextLine();

        ////////////////////////////////////////////////////

        //Aquí empieza el juego
        while (jugador.getPosicion() < pasillo.length) { //Comprueba si ha llegado al final
            boolean enemigo = false;
            int jugadorAtaca = 1;
            Monstruo monstruo = monstruos.get(0);
            int danyo = 0;

            if (pasillo[jugador.getPosicion()]) { // Aquí detecta que la habitación está ocupada
                enemigo = true;
                danyo = 0;

                for (Monstruo valor : monstruos) {
                    if (valor.getPosicion() == jugador.getPosicion()) {
                        monstruo = valor;
                        break;
                    }
                }

                imprimirGraficos(jugador, true);
                sc.nextLine();
                imprimirGraficos(jugador, monstruo, 0, danyo);
                sc.nextLine();
                
                jugadorAtaca = 1;

                do {
                    if (jugadorAtaca == 1) {
                        danyo = jugador.getArma().danyoArma();
                        monstruo.recibeDanyo(danyo);
                        if (monstruo.getVida() <= 0) {
                            monstruo.setVida(0);
                        }
                        jugadorAtaca = -1;
                    } else {
                        danyo = monstruo.getArma().danyoArma();
                        jugador.recibeDanyo(danyo);
                        if (jugador.getVida() <= 0) {
                            jugador.setVida(0);
                            imprimirGraficos(jugador, monstruo, jugadorAtaca, danyo);
                            System.exit(0);
                        }
                        jugadorAtaca = 1;
                    }
                    imprimirGraficos(jugador, monstruo, jugadorAtaca, danyo);
                    sc.nextLine();
                } while ((monstruo.getVida() > 0) && (jugador.getVida() > 0));
            } else {
                imprimirGraficos(jugador, false);
                sc.nextLine();
            }
            jugador.avanzar();
        }
        System.out.println("[++] Felicidades, has ganado el juego.");
    }

    public static void MostrarMenu(int menu) {
        //Limpiar pantalla
        System.out.print("\033[H\033[2J");  
        System.out.flush();

        switch (menu) {
            case 1:
                System.out.println(Colors.BLUE + "[+]" + Colors.RESET + " Elige el tipo de heroe que deseas utilizar: ");
                System.out.println("\t--> " + Colors.PURPLE + "1" + Colors.RESET + ")" + Colors.YELLOW + "Mago" + Colors.RESET);
                System.out.println("\t--> " + Colors.PURPLE + "2" + Colors.RESET + ")" + Colors.YELLOW + "Guerrero" + Colors.RESET);
                System.out.println("\t--> " + Colors.PURPLE + "3" + Colors.RESET + ")" + Colors.YELLOW + "Enano" + Colors.RESET);
                break;
        
            case 2:
                System.out.println(Colors.BLUE + "[+]" + Colors.RESET + " Elige el arma que deseas utilizar: ");
                System.out.println("\t--> " + Colors.PURPLE + "1" + Colors.RESET + ")" + Colors.YELLOW + "Arco" + Colors.RESET);
                System.out.println("\t--> " + Colors.PURPLE + "2" + Colors.RESET + ")" + Colors.YELLOW + "Espada" + Colors.RESET);
                System.out.println("\t--> " + Colors.PURPLE + "3" + Colors.RESET + ")" + Colors.YELLOW + "Hacha" + Colors.RESET);
                System.out.println("\t--> " + Colors.PURPLE + "4" + Colors.RESET + ")" + Colors.YELLOW + "Hechizo" + Colors.RESET);
                break;
        }
    }

    public static ArrayList<Monstruo> creacionEnemigos(int numEnemigos){
        ArrayList<Monstruo> monstruos = new ArrayList<>();
        for (int i = 0; i < numEnemigos; i++) {
            int tipo = (int)(Math.random()*3)+1;
            int x = 0;
            do {
                x = (int)(Math.random()*38)+1; // Posición aleatoria (1-39)
                if (!pasillo[x]) {
                    pasillo[x] = true;
                }
            } while (!pasillo[x]); // Esto es para evitar que estén en la misma casilla
            switch (tipo) {
                case 1: monstruos.add(new Zombie(x)); break;
                case 2: monstruos.add(new Orco(x)); break;
                case 3: monstruos.add(new Dragon(x)); break;
            }
            // Le doy el arma
            tipo = (int)(Math.random()*4)+1;
            Monstruo monstruo = monstruos.get(i);
            switch (tipo) {
                case 1: monstruo.setArma(new Arco()); break;
                case 2: monstruo.setArma(new Espada()); break;
                case 3: monstruo.setArma(new Hacha()); break;
                case 4: monstruo.setArma(new Hechizo()); break;
            }
        }
        return monstruos;
    }

    public static void imprimirGraficos(Personaje jugador, Monstruo monstruo, int jugadorAtaca, int danyo) {
        Pantalla screen = new Pantalla(86, 280);
        Pantalla info = new Pantalla(25, 276);
        Pantalla armaPantalla = new Pantalla(21, 34);
        screen.marco('b');
        info.marco('b');
        info.dividirH();
        armaPantalla.marco('b');
        Sprite sprites = new Sprite();

        screen.posiciona(sprites.getFondo(), 'n', 2, 2);

        //Contador de habitación
        int habitacion = jugador.getPosicion();

        screen.posiciona(sprites.getNumero(habitacion/10), 'b', 5, 3);
        screen.posiciona(sprites.getNumero(habitacion%10), 'b', 10, 3);

        if (monstruo.getTipo() == "Dragón") {
            screen.posiciona(sprites.getDragon(),'r', 85, 10);
            screen.posiciona(sprites.getEnemigo(0),'a', 160, 62);
        } else if (monstruo.getTipo() == "Orco") {
            screen.posiciona(sprites.getOrco(), 'v', 80, 10);
            screen.posiciona(sprites.getEnemigo(1),'a', 160, 62);
        } else if (monstruo.getTipo() == "Zombie") {
            screen.posiciona(sprites.getZombie(), 'v', 110,25);
            screen.posiciona(sprites.getEnemigo(2),'a', 160, 62);
        }

        screen.posiciona(info.toString(), 'x', 2, 59, true);

        /////////////////////////////////////////// Jugador
        //Imprimir arma
        screen.posiciona(armaPantalla.toString(), 'x', 4, 61);
        switch (jugador.getArma().getTipo()) {
            case "Espada": screen.posiciona(sprites.getEspada(), 'z', 6, 63); break;
            case "Hacha": screen.posiciona(sprites.getHacha(), 'z', 6, 63); break;
            case "Hechizo": screen.posiciona(sprites.getHechizo(),'z', 6, 63); break;
            case "Arco": screen.posiciona(sprites.getArco(),'z', 6, 63); break;
        }

        if (jugador.getVida() != 0) {
            screen.posiciona(sprites.getCorazon(), 'r', 40, 62);
            screen.posiciona(sprites.getNumero(jugador.getVida()/100), 'r', 47, 76);
            screen.posiciona(sprites.getNumero(jugador.getVida()/10), 'r', 52, 76);
            screen.posiciona(sprites.getNumero(jugador.getVida()%10), 'r', 57, 76);
        } else {
            screen.posiciona(sprites.getCalavera(), 'r', 41, 62);
        }
        Map<String, Integer> valoresTipo = new HashMap<>();
        valoresTipo.put("Enano", 0);
        valoresTipo.put("Guerrero", 1);
        valoresTipo.put("Mago", 2);
        screen.posiciona(sprites.getTipo(valoresTipo.get(jugador.getTipo())), 'a', 80, 62);
        if (jugadorAtaca == -1) {
            screen.posiciona(sprites.getCorte(), 'a', 70, 30);
            screen.posiciona(sprites.getGolpe(), 'a', 80, 68);
            //Daño hecho
            screen.posiciona(sprites.getNumero(danyo/10), 'b', 96, 72);
            screen.posiciona(sprites.getNumero(danyo%10), 'b', 101, 72);
        }

        /////////////////////////////////////////// Enemigo
        if (monstruo.getTipo() == "Dragón") {
            screen.posiciona(sprites.getEnemigo(0),'a', 160, 62);
        } else if (monstruo.getTipo() == "Orco") {
            screen.posiciona(sprites.getEnemigo(1),'a', 160, 62);
        } else if (monstruo.getTipo() == "Zombie") {
            screen.posiciona(sprites.getEnemigo(2),'a', 160, 62);
        }

        screen.posiciona(armaPantalla.toString(), 'x', 242, 61);
        switch (monstruo.getArma().getTipo()) {
            case "Espada": screen.posiciona(sprites.getEspada(), 'z', 244, 63); break;
            case "Hacha": screen.posiciona(sprites.getHacha(), 'z', 244, 63); break;
            case "Hechizo": screen.posiciona(sprites.getHechizo(),'z', 244, 63); break;
            case "Arco": screen.posiciona(sprites.getArco(),'z', 244, 63); break;
        }

        if (monstruo.getVida() != 0) {
            screen.posiciona(sprites.getCorazon(), 'r', 212, 62);
            screen.posiciona(sprites.getNumero(monstruo.getVida()/100), 'r', 219, 76);
            screen.posiciona(sprites.getNumero(monstruo.getVida()/10), 'r', 224, 76);
            screen.posiciona(sprites.getNumero(monstruo.getVida()%10), 'r', 229, 76);
        } else {
            screen.posiciona(sprites.getCalavera(), 'r', 213, 62);
        }

        if (jugadorAtaca == 1) {
            screen.posiciona(sprites.getGolpe(), 'a', 155, 68);
            screen.posiciona(sprites.getNumero(danyo/10), 'b', 171, 72);
            screen.posiciona(sprites.getNumero(danyo%10), 'b', 176, 72);
        }
        ///////////////////////////////////////////
        screen.mostrarPantalla();
    }

    public static void imprimirGraficos(Personaje jugador, boolean alerta) {
        Pantalla screen = new Pantalla(86, 280);
        Pantalla info = new Pantalla(25, 276);
        Pantalla armaPantalla = new Pantalla(21, 34);
        screen.marco('b');
        info.marco('b');
        armaPantalla.marco('b');
        Sprite sprites = new Sprite();

        screen.posiciona(sprites.getFondo(), 'n', 2, 2);
        screen.posiciona(info.toString(), 'x', 2, 59, true);

        //Contador de habitación
        int habitacion = jugador.getPosicion();

        screen.posiciona(sprites.getNumero(habitacion/10), 'b', 5, 3);
        screen.posiciona(sprites.getNumero(habitacion%10), 'b', 10, 3);

        /////////////////////////////////////////// Jugador
        //Imprimir arma
        screen.posiciona(armaPantalla.toString(), 'x', 4, 61);
        switch (jugador.getArma().getTipo()) {
            case "Espada": screen.posiciona(sprites.getEspada(), 'z', 6, 63); break;
            case "Hacha": screen.posiciona(sprites.getHacha(), 'z', 6, 63); break;
            case "Hechizo": screen.posiciona(sprites.getHechizo(),'z', 6, 63); break;
            case "Arco": screen.posiciona(sprites.getArco(),'z', 6, 63); break;
        }

        screen.posiciona(sprites.getCorazon(), 'r', 40, 62);
        screen.posiciona(sprites.getNumero(jugador.getVida()/100), 'r', 47, 76);
        screen.posiciona(sprites.getNumero(jugador.getVida()/10), 'r', 52, 76);
        screen.posiciona(sprites.getNumero(jugador.getVida()%10), 'r', 57, 76);
        
        Map<String, Integer> valoresTipo = new HashMap<>();
        valoresTipo.put("Enano", 0);
        valoresTipo.put("Guerrero", 1);
        valoresTipo.put("Mago", 2);
        screen.posiciona(sprites.getTipo(valoresTipo.get(jugador.getTipo())), 'a', 80, 62);

        if(alerta) {
            screen.posiciona(sprites.getDanger(), 'r', 90, 8);
        }

        screen.mostrarPantalla();
    }
}
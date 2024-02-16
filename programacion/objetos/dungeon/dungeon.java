import java.util.ArrayList;
import java.util.Scanner;
import Armas.*;
import Heroe.*;
import Monstruo.*;
import Sprites.*;

public class dungeon {
    
    static boolean[] pasillo = new boolean[40];
    
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        Sprite sprites = new Sprite();
        Personaje jugador = new Personaje();
        ArrayList<Monstruo> monstruos = new ArrayList<>();
        monstruos = creacionEnemigos(3);
        String nick;
        int option;

        //Inserción de datos del jugador
        //Limpiar pantalla
        System.out.print("\033[H\033[2J");  
        System.out.flush();

        System.out.print("[+] Nombre del personaje: ");
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

        //Aquí empieza el juego
        /*System.out.println(jugador);
        System.out.println("----");
        System.out.println(monstruos);
        for (int i = 0; i < pasillo.length; i++) {
            if (pasillo[i]) {
                System.out.print("█ ");
            } else {
                System.out.print(i + " ");
            }
        }*/
        while (jugador.getPosicion() < pasillo.length) {
            //Limpiar pantalla
            System.out.print("\033[H\033[2J");  
            System.out.flush();

            System.out.println(jugador);
            System.out.println("[+] Estás en la habitación " + jugador.getPosicion());
            if (pasillo[jugador.getPosicion()]) {
                System.out.println("[!] Cuidado, Aquí hay un enemigo");
                Thread.sleep(3000);
                Monstruo monstruo = monstruos.get(0);
                int danyo = 0;
                for (Monstruo valor : monstruos) {
                    if (valor.getPosicion() == jugador.getPosicion()) {
                        monstruo = valor;
                        break;
                    }
                }

                boolean jugadorAtaca = true;

                do {
                    //Limpiar pantalla
                    System.out.print("\033[H\033[2J");  
                    System.out.flush();

                    System.out.println("[+] Jugador");
                    System.out.println(jugador);
                    System.out.println("----");
                    System.out.println("[-] Monstruo:");
                    System.out.println(monstruo);
                    System.out.println("----");
                    if (jugadorAtaca) {
                        danyo = jugador.getArma().danyoArma();
                        System.out.println("[+] El jugador hace " + danyo + " daño al monstruo");
                        Thread.sleep(3000);
                        monstruo.reciveDanyo(danyo);
                        if (monstruo.getVida() <= 0) {
                            System.out.println("[+] Felicidades, has ganado.");
                        } else {
                            System.out.println("[+] El monstruo ahora tiene " + monstruo.getVida() + " de vida.");
                        }
                        jugadorAtaca = false;
                    } else {
                        danyo = monstruo.getArma().danyoArma();
                        System.out.println("[+] El Monstruo hace " + danyo + " daño al jugador");
                        Thread.sleep(3000);
                        jugador.reciveDanyo(danyo);
                        if (jugador.getVida() <= 0) {
                            System.out.println("[+] No te queda vida, has perdido.");
                            System.exit(0);
                        } else {
                            System.out.println("[+] El jugador ahora tiene " + jugador.getVida() + " de vida.");
                        }
                        jugadorAtaca = true;
                    }
                    Thread.sleep(3000);
                } while ((monstruo.getVida() > 0) && (jugador.getVida() > 0));
                sc.nextLine();
            } else {
                System.out.println("[+] Es una habitación libre");
            }
            System.out.println("[+] Presiona enter para avanzar a la siguiente habitación...");
            sc.nextLine();
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
                System.out.println("[+] Elige el tipo de heroe que deseas utilizar: ");
                System.out.println("\t--> 1)Mago");
                System.out.println("\t--> 2)Guerrero");
                System.out.println("\t--> 3)Enano");
                break;
        
            case 2:
                System.out.println("[+] Elige el arma que deseas utilizar: ");
                System.out.println("\t--> 1)Arco");
                System.out.println("\t--> 2)Espada");
                System.out.println("\t--> 3)Hacha");
                System.out.println("\t--> 4)Hechizo");
                break;
            default:
                break;
        }
    }

    public static ArrayList<Monstruo> creacionEnemigos(int numEnemigos){
        ArrayList<Monstruo> monstruos = new ArrayList<>();
        for (int i = 0; i < numEnemigos; i++) {
            int tipo = (int)(Math.random()*3)+1;
            int x = 0;
            do {
                x = (int)(Math.random()*39)+1; // Posición aleatoria
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
                case 3: monstruo.setArma(new Arco()); break;
                case 4: monstruo.setArma(new Hechizo()); break;
            }
        }
        return monstruos;
    }
}
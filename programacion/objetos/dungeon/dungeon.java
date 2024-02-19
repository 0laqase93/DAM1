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

        contarHistoria(jugador);

        System.out.println(Colors.RED + "[!!] ¡¡Minimiza la pantalla al mínimo!!" + Colors.RESET);
        System.out.println("[+] Lo único que tienes que hacer es presionar enter...");
        sc.nextLine();

        ////////////////////////////////////////////////////

        //Aquí empieza el juego
        while (jugador.getPosicion() < pasillo.length) { //Comprueba si ha llegado al final
            boolean enemigo = false;
            int jugadorAtaca = 1; //1 = ataca el jugar | 0 = no ataca nadie | -1 = ataca el enemigo
            Monstruo monstruo = monstruos.get(0); //Inicializo el mosntruo
            int danyo = 0;

            if (pasillo[jugador.getPosicion()]) { // Aquí detecta que la habitación está ocupada
                enemigo = true;
                danyo = 0;

                //Aquí busco el enemigo en el arraylist ya no está ordenados por su posición
                for (Monstruo valor : monstruos) {
                    if (valor.getPosicion() == jugador.getPosicion()) {
                        monstruo = valor;
                        break;
                    }
                }

                //Si hay un enemigo en la misma sala pues salta una alerta
                imprimirGraficos(jugador, true);
                sc.nextLine();
                imprimirGraficos(jugador, monstruo, 0, danyo);
                sc.nextLine();
                
                //Aquí empieza el combate
                jugadorAtaca = 1;
                do {
                    //Ataca el jugador
                    if (jugadorAtaca == 1) {
                        danyo = jugador.getArma().danyoArma();
                        monstruo.recibeDanyo(danyo);
                        if (monstruo.getVida() <= 0) {
                            monstruo.setVida(0);
                        }
                        jugadorAtaca = -1;
                    //Ataca el monstruo
                    } else {
                        danyo = monstruo.getArma().danyoArma();
                        jugador.recibeDanyo(danyo);
                        if (jugador.getVida() <= 0) {
                            jugador.setVida(0);
                            imprimirGraficos(jugador, monstruo, jugadorAtaca, danyo);
                            Thread.sleep(1000);
                            acabarJuego(false);
                            System.exit(0);
                        }
                        jugadorAtaca = 1;
                    }
                    //Se imprime todo después de cada turno
                    imprimirGraficos(jugador, monstruo, jugadorAtaca, danyo);
                    sc.nextLine();
                } while ((monstruo.getVida() > 0) && (jugador.getVida() > 0));
            } else {
                //Si no encuentra nada pues solo imprime el pasillo
                imprimirGraficos(jugador, false);
                sc.nextLine();
            }
            jugador.avanzar();
        }
        acabarJuego(true);
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

    public static void acabarJuego(boolean victoria) throws InterruptedException {
        Pantalla screen = new Pantalla(86, 280);
        Sprite sprites = new Sprite();
        int tiempoFade = 500;

        screen.rellenarPantalla('▓');
        screen.marco('b');
        screen.mostrarPantalla();
        Thread.sleep(tiempoFade);
        screen.rellenarPantalla('▒');
        screen.marco('b');
        screen.mostrarPantalla();
        Thread.sleep(tiempoFade);
        screen.rellenarPantalla('░');
        screen.marco('b');
        screen.mostrarPantalla();
        Thread.sleep(tiempoFade);

        screen.limpiarPantalla();
        screen.marco('b');

        if (victoria) {
            screen.posiciona(sprites.getPaisaje(), 'v', 2, 2);
            screen.posiciona(sprites.getYouWin(), 'a', 70, 20);
        } else {
            screen.posiciona(sprites.getYouLose(), 'r', 80, 40);
        }

        screen.mostrarPantalla();
    }

    public static void contarHistoria(Personaje jugador) throws InterruptedException {
        String historia = "";

        if (jugador.getArma().getTipo() == "Hechizo") {
            historia = 
                "En las antiguas crónicas de tiempos olvidados se nos cuenta el relato de " + Colors.YELLOW + jugador.getNombre() + Colors.RESET + ", un héroe cuyo despertar resonó en las paredes de una arcaica mazmorra.\n\n" +
                "Lleno de confusión, " + Colors.YELLOW + jugador.getNombre() + Colors.RESET + " se despertó de su sueño para encontrarse perdido en un pasillo lúgubre, cuyas baldosas desgastadas marcaban el inicio de un laberino.\n\n" +
                "Todavía somnoliento, se sentía embrujado por un poder ancestral: La capacidad de hacer "+ Colors.RED +"hechizos" + Colors.RESET + ", una habilidad existente desde tiempos remotos y cuyo origen parecía ser un regalo de los dioses ya olvidados.\n\n" +
                "Con determinación en su pecho, " + Colors.YELLOW + jugador.getNombre() + Colors.RESET + " se embarcó en la osada aventura de atravesar las 40 habitaciones, desafiando los enigmas y peligros que acechaban en cada pasillo. A través de la penumbra y del misterio, cada paso hacia adelante era un paso hacia la libertad, cada enfrentamiento una prueba de su valor y coraje.\n\n" +
                "Así, armado con la magia de los dioses y el anhelo de la libertad, " + Colors.YELLOW + jugador.getNombre() + Colors.RESET + " se adentró en las profundidades de la mazmorra con esperanza, decidido a desafiar las sombras y emerger triunfante bajo el resplandor del sol.\n";
        } else {
            historia =
                "En las crónicas de tiempos olvidados se entrelaza el relato de " + Colors.YELLOW + jugador.getNombre() + Colors.RESET + ", un héroe cuyo despertar resonó en las paredes pétreas de una mazmorra ancestral. Envuelto en un velo de confusión, " + Colors.YELLOW + jugador.getNombre() + Colors.RESET + " se alzó de su letargo para encontrarse perdido en un pasillo lúgubre, cuyas baldosas desgastadas marcaban el inicio de un laberinto de 40 habitaciones. En el umbral de la conciencia, su mano se cerró en torno a un objeto antiguo y poderoso: su fiel " + Colors.RED + jugador.getArma() + Colors.RESET + ", un tesoro de tiempos remotos que reposaba en el suelo de piedra como un regalo de los dioses olvidados.\n\n" +
                "Con el arma en mano y la determinación ardiente en su pecho, " + Colors.YELLOW + jugador.getNombre() + Colors.RESET + " se embarcó en la osada empresa de atravesar las 40 habitaciones, desafiando a los enigmas y peligros que acechaban en cada umbral. A través de la penumbra y el misterio, cada paso hacia adelante era un paso hacia la libertad, cada enfrentamiento una prueba de su valor y coraje.\n\n" +
                "Así, armado con la fuerza de su " + Colors.RED + jugador.getArma() + Colors.RESET + " y el anhelo de la libertad, " + Colors.YELLOW + jugador.getNombre() + Colors.RESET + " se adentró en las profundidades de la mazmorra con la esperanza ardiente en su corazón, decidido a desafiar las sombras y emerger triunfante bajo el resplandor del sol.\n";
        }


        //Limpiar pantalla
        System.out.print("\033[H\033[2J");  
        System.out.flush();

        for (int i = 0; i < historia.length(); i++) {
            System.out.print(historia.charAt(i));
            if ((historia.charAt(i) == ',') || (historia.charAt(i) == '\n')) {
                Thread.sleep(400);
            } else if ((historia.charAt(i) == '.') || (historia.charAt(i) == ':')) {
                Thread.sleep(800);
            } else {
                Thread.sleep(60);
            }
        }
    }
}
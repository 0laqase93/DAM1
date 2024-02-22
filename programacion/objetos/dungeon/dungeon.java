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

        // Inserción de datos del jugador
        jugador = seleccionarPersonajeArma();
        contarHistoria(jugador);

        ////////////////////////////////////////////////////

        // Aquí empieza el juego
        while (jugador.getPosicion() < pasillo.length) { // Comprueba si ha llegado al final
            boolean enemigo = false;
            int jugadorAtaca = 1; // 1 = ataca el jugar | 0 = no ataca nadie | -1 = ataca el enemigo
            Monstruo monstruo = monstruos.get(0); // Inicializo el mosntruo
            int danyo = 0;

            if (pasillo[jugador.getPosicion()]) { // Aquí detecta que la habitación está ocupada
                enemigo = true;
                danyo = 0;

                // Aquí busco el enemigo en el arraylist ya no está ordenados por su posición
                for (Monstruo valor : monstruos) {
                    if (valor.getPosicion() == jugador.getPosicion()) {
                        monstruo = valor;
                        break;
                    }
                }

                // Si hay un enemigo en la misma sala pues salta una alerta
                imprimirGraficos(jugador, true);
                sc.nextLine();
                imprimirGraficos(jugador, monstruo, 0, danyo, false);
                sc.nextLine();

                // Aquí empieza el combate
                jugadorAtaca = 1;
                do {
                    boolean critico = false;
                    // Ataca el jugador
                    if (jugadorAtaca == 1) {
                        critico = jugador.getArma().isCritico();
                        danyo = jugador.getArma().danyoArma(critico);
                        monstruo.recibeDanyo(danyo);
                        if (monstruo.getVida() <= 0) {
                            monstruo.setVida(0);
                        }
                        jugadorAtaca = -1;
                        // Ataca el monstruo
                    } else {
                        critico = monstruo.getArma().isCritico();
                        danyo = monstruo.getArma().danyoArma(critico);
                        jugador.recibeDanyo(danyo);
                        if (jugador.getVida() <= 0) {
                            jugador.setVida(0);
                            imprimirGraficos(jugador, monstruo, jugadorAtaca, danyo, critico);
                            Thread.sleep(1000);
                            acabarJuego(false);
                            System.exit(0);
                        }
                        jugadorAtaca = 1;
                    }
                    // Se imprime todo después de cada turno
                    imprimirGraficos(jugador, monstruo, jugadorAtaca, danyo, critico);
                    sc.nextLine();
                } while ((monstruo.getVida() > 0) && (jugador.getVida() > 0));
            } else {
                // Si no encuentra nada pues solo imprime el pasillo
                imprimirGraficos(jugador, false);
                sc.nextLine();
            }
            jugador.avanzar();
        }
        acabarJuego(true);
    }

    public static ArrayList<Monstruo> creacionEnemigos(int numEnemigos) {
        ArrayList<Monstruo> monstruos = new ArrayList<>();
        for (int i = 0; i < numEnemigos; i++) {
            int tipo = (int) (Math.random() * 3) + 1;
            int x = 0;
            do {
                x = (int) (Math.random() * 38) + 1; // Posición aleatoria (1-39)
                if (!pasillo[x]) {
                    pasillo[x] = true;
                }
            } while (!pasillo[x]); // Esto es para evitar que estén en la misma casilla
            switch (tipo) {
                case 1:
                    monstruos.add(new Zombie(x));
                    break;
                case 2:
                    monstruos.add(new Orco(x));
                    break;
                case 3:
                    monstruos.add(new Dragon(x));
                    break;
            }
            // Le doy el arma
            tipo = (int) (Math.random() * 4) + 1;
            Monstruo monstruo = monstruos.get(i);
            switch (tipo) {
                case 1:
                    monstruo.setArma(new Arco());
                    break;
                case 2:
                    monstruo.setArma(new Espada());
                    break;
                case 3:
                    monstruo.setArma(new Hacha());
                    break;
                case 4:
                    monstruo.setArma(new Hechizo());
                    break;
            }
        }
        return monstruos;
    }

    public static void imprimirGraficos(Personaje jugador, Monstruo monstruo, int jugadorAtaca, int danyo, boolean critico) {
        Pantalla screen = new Pantalla(86, 280);
        Pantalla info = new Pantalla(25, 276);
        Pantalla armaPantalla = new Pantalla(21, 34);
        screen.marco('b');
        info.marco('b');
        info.dividirH();
        armaPantalla.marco('b');
        Sprite sprites = new Sprite();

        screen.posiciona(sprites.getFondo(), 'n', 2, 2);

        // Contador de habitación
        int habitacion = jugador.getPosicion();

        screen.posiciona(sprites.getNumero(habitacion / 10), 'b', 5, 3);
        screen.posiciona(sprites.getNumero(habitacion % 10), 'b', 10, 3);

        if (monstruo.getTipo() == "Dragón") {
            screen.posiciona(sprites.getDragon(), 'r', 85, 10);
            screen.posiciona(sprites.getEnemigo(0), 'a', 160, 62);
        } else if (monstruo.getTipo() == "Orco") {
            screen.posiciona(sprites.getOrco(), 'v', 80, 10);
            screen.posiciona(sprites.getEnemigo(1), 'a', 160, 62);
        } else if (monstruo.getTipo() == "Zombie") {
            screen.posiciona(sprites.getZombie(), 'v', 110, 25);
            screen.posiciona(sprites.getEnemigo(2), 'a', 160, 62);
        }

        screen.posiciona(info.toString(), 'x', 2, 59, true);

        /////////////////////////////////////////// Jugador
        // Imprimir arma
        screen.posiciona(armaPantalla.toString(), 'x', 4, 61);
        switch (jugador.getArma().getTipo()) {
            case "Espada":
                screen.posiciona(sprites.getEspada(), 'z', 6, 63);
                break;
            case "Hacha":
                screen.posiciona(sprites.getHacha(), 'z', 6, 63);
                break;
            case "Hechizo":
                screen.posiciona(sprites.getHechizo(), 'z', 6, 63);
                break;
            case "Arco":
                screen.posiciona(sprites.getArco(), 'z', 6, 63);
                break;
        }

        if (jugador.getVida() != 0) {
            screen.posiciona(sprites.getCorazon(), 'r', 40, 62);
            screen.posiciona(sprites.getNumero(jugador.getVida() / 100), 'r', 47, 76);
            screen.posiciona(sprites.getNumero(jugador.getVida() / 10), 'r', 52, 76);
            screen.posiciona(sprites.getNumero(jugador.getVida() % 10), 'r', 57, 76);
        } else {
            screen.posiciona(sprites.getCalavera(), 'r', 41, 62);
        }
        Map<String, Integer> valoresTipo = new HashMap<>();
        valoresTipo.put("Enano", 0);
        valoresTipo.put("Guerrero", 1);
        valoresTipo.put("Mago", 2);
        screen.posiciona(sprites.getTipo(valoresTipo.get(jugador.getTipo())), 'a', 80, 62);
        if (jugadorAtaca == -1) {
            if (critico) {
                screen.posiciona(sprites.getCorte(), 'a', 70, 25);
                screen.posiciona(sprites.getCorte(), 'a', 70, 35);
                screen.posiciona(sprites.getGolpe(), 'r', 80, 68);
            } else {
                screen.posiciona(sprites.getCorte(), 'a', 70, 30);
                screen.posiciona(sprites.getGolpe(), 'a', 80, 68);
            }
            // Daño hecho
            screen.posiciona(sprites.getNumero(danyo / 10), 'x', 96, 72);
            screen.posiciona(sprites.getNumero(danyo % 10), 'x', 101, 72);
        }

        /////////////////////////////////////////// Enemigo
        if (monstruo.getTipo() == "Dragón") {
            screen.posiciona(sprites.getEnemigo(0), 'a', 160, 62);
        } else if (monstruo.getTipo() == "Orco") {
            screen.posiciona(sprites.getEnemigo(1), 'a', 160, 62);
        } else if (monstruo.getTipo() == "Zombie") {
            screen.posiciona(sprites.getEnemigo(2), 'a', 160, 62);
        }

        screen.posiciona(armaPantalla.toString(), 'x', 242, 61);
        switch (monstruo.getArma().getTipo()) {
            case "Espada":
                screen.posiciona(sprites.getEspada(), 'z', 244, 63);
                break;
            case "Hacha":
                screen.posiciona(sprites.getHacha(), 'z', 244, 63);
                break;
            case "Hechizo":
                screen.posiciona(sprites.getHechizo(), 'z', 244, 63);
                break;
            case "Arco":
                screen.posiciona(sprites.getArco(), 'z', 244, 63);
                break;
        }

        if (monstruo.getVida() != 0) {
            screen.posiciona(sprites.getCorazon(), 'r', 212, 62);
            screen.posiciona(sprites.getNumero(monstruo.getVida() / 100), 'r', 219, 76);
            screen.posiciona(sprites.getNumero(monstruo.getVida() / 10), 'r', 224, 76);
            screen.posiciona(sprites.getNumero(monstruo.getVida() % 10), 'r', 229, 76);
        } else {
            screen.posiciona(sprites.getCalavera(), 'r', 213, 62);
        }

        if (jugadorAtaca == 1) {
            if (critico) {
                screen.posiciona(sprites.getGolpe(), 'r', 155, 68);
            } else {
                screen.posiciona(sprites.getGolpe(), 'a', 155, 68);
            }
            screen.posiciona(sprites.getNumero(danyo / 10), 'x', 171, 72);
            screen.posiciona(sprites.getNumero(danyo % 10), 'x', 176, 72);
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

        // Contador de habitación
        int habitacion = jugador.getPosicion();

        screen.posiciona(sprites.getNumero(habitacion / 10), 'b', 5, 3);
        screen.posiciona(sprites.getNumero(habitacion % 10), 'b', 10, 3);

        /////////////////////////////////////////// Jugador
        // Imprimir arma
        screen.posiciona(armaPantalla.toString(), 'x', 4, 61);
        switch (jugador.getArma().getTipo()) {
            case "Espada":
                screen.posiciona(sprites.getEspada(), 'z', 6, 63);
                break;
            case "Hacha":
                screen.posiciona(sprites.getHacha(), 'z', 6, 63);
                break;
            case "Hechizo":
                screen.posiciona(sprites.getHechizo(), 'z', 6, 63);
                break;
            case "Arco":
                screen.posiciona(sprites.getArco(), 'z', 6, 63);
                break;
        }

        screen.posiciona(sprites.getCorazon(), 'r', 40, 62);
        screen.posiciona(sprites.getNumero(jugador.getVida() / 100), 'r', 47, 76);
        screen.posiciona(sprites.getNumero(jugador.getVida() / 10), 'r', 52, 76);
        screen.posiciona(sprites.getNumero(jugador.getVida() % 10), 'r', 57, 76);

        Map<String, Integer> valoresTipo = new HashMap<>();
        valoresTipo.put("Enano", 0);
        valoresTipo.put("Guerrero", 1);
        valoresTipo.put("Mago", 2);
        screen.posiciona(sprites.getTipo(valoresTipo.get(jugador.getTipo())), 'a', 80, 62);

        if (alerta) {
            screen.posiciona(sprites.getDanger(), 'r', 90, 8);
        }

        screen.mostrarPantalla();
    }

    public static void acabarJuego(boolean victoria) throws InterruptedException {
        Pantalla screen = new Pantalla(86, 280);
        Sprite sprites = new Sprite();
        int tiempoFade = 200;

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
        screen.rellenarPantalla(':');
        screen.marco('b');
        screen.mostrarPantalla();
        Thread.sleep(tiempoFade);
        screen.rellenarPantalla('.');
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
        Pantalla screen = new Pantalla(86, 280);
        screen.marco('b');
        Letras.imprimirFrase("¿Leer historia? [y/n]:", screen, 2, 2, 40);
        String input = sc.nextLine();
        if (input.equals("y")) {
            String historia = "";
    
            if (jugador.getArma().getTipo() == "Hechizo") {
                historia = "En las antiguas crónicas de tiempos olvidados se nos cuenta el relato de " + jugador.getNombre() + ", un héroe cuyo despertar resonó en las paredes de una arcaica mazmorra.\n\nLleno de confusión, " + jugador.getNombre() + " se despertó de su sueño para encontrarse perdido en un pasillo lúgubre, cuyas baldosas desgastadas marcaban el inicio de un laberinto.\n\nTodavía somnoliento, se sentía embrujado por un poder ancestral: La capacidad de hacer hechizos, una habilidad existente desde tiempos remotos y cuyo origen parecía ser un regalo de los dioses ya olvidados.\n\nCon determinación en su pecho, "+ jugador.getNombre()+ " se embarcó en la osada aventura de atravesar las 40 habitaciones, desafiando los enigmas y peligros que acechaban en cada pasillo. A través de la penumbra y del misterio, cada paso hacia adelante era un paso hacia la libertad, cada enfrentamiento una prueba de su valor y coraje.\n\nAsí, armado con la magia de los dioses y el anhelo de la libertad, " + jugador.getNombre() + " se adentró en las profundidades de la mazmorra con esperanza, decidido a desafiar las sombras y emerger triunfante bajo el resplandor del sol.\n";
            } else {
                historia = "En las crónicas de tiempos olvidados se entrelaza el relato de " + Colors.YELLOW
                        + jugador.getNombre() + Colors.RESET
                        + ", un héroe cuyo despertar resonó en las paredes pétreas de una mazmorra ancestral. Envuelto en un velo de confusión, "
                        + Colors.YELLOW + jugador.getNombre() + Colors.RESET
                        + " se alzó de su letargo para encontrarse perdido en un pasillo lúgubre, cuyas baldosas desgastadas marcaban el inicio de un laberinto de 40 habitaciones. En el umbral de la conciencia, su mano se cerró en torno a un objeto antiguo y poderoso: su fiel "
                        + Colors.RED + jugador.getArma() + Colors.RESET
                        + ", un tesoro de tiempos remotos que reposaba en el suelo de piedra como un regalo de los dioses olvidados.\n\n"
                        +
                        "Con el arma en mano y la determinación ardiente en su pecho, " + Colors.YELLOW
                        + jugador.getNombre() + Colors.RESET
                        + " se embarcó en la osada empresa de atravesar las 40 habitaciones, desafiando a los enigmas y peligros que acechaban en cada umbral. A través de la penumbra y el misterio, cada paso hacia adelante era un paso hacia la libertad, cada enfrentamiento una prueba de su valor y coraje.\n\n"
                        +
                        "Así, armado con la fuerza de su " + Colors.RED + jugador.getArma() + Colors.RESET
                        + " y el anhelo de la libertad, " + Colors.YELLOW + jugador.getNombre() + Colors.RESET
                        + " se adentró en las profundidades de la mazmorra con la esperanza ardiente en su corazón, decidido a desafiar las sombras y emerger triunfante bajo el resplandor del sol.\n";
            }
            screen.limpiarPantalla();
            Letras.imprimirFrase(historia, screen, 2, 2, 40);
            sc.nextLine();
        }
    }

    public static Personaje seleccionarPersonajeArma() {
        Pantalla screen = new Pantalla(86, 280);
        Pantalla opciones = new Pantalla(35, 276);
        Pantalla stats = new Pantalla(46, 60);
        opciones.marco('b');
        stats.marco('b');
        screen.marco('b');
        Sprite sprites = new Sprite();

        Personaje jugador = new Personaje();
        String nick = "";
        int option = 1;

        // Input del nombre
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.print(Colors.BLUE + "[+]" + Colors.RESET + " Nombre del personaje: ");
        nick = sc.nextLine();

        System.out.println(Colors.RED + "[!!] ¡¡Minimiza la pantalla al mínimo!!" + Colors.RESET);
        System.out.println("[+] Presionar enter para continuar...");
        sc.nextLine();
 
        boolean elegido = false;

        //Elección de personaje
        do {
            screen.limpiarPantalla();
            screen.marco('b');

            if (option > 3) {
                option = 1;
            }
            if (option < 1) {
                option = 3;
            }

            screen.posiciona(sprites.getFondoSeleccion(), 'n', 2, 2);
            screen.posiciona(stats.toString(), 'x', 2, 2, true);
            
            if (option == 1) { //Imprimir enano
                screen.posiciona(sprites.getEnano(), 'z', 120, 4);

                screen.posiciona(opciones.toString(), 'x', 2, 49, true);

                screen.posiciona(sprites.getTipo(option-1), 'x', 4, 4);
        
                screen.posiciona(sprites.getStatsHeroe(), 'a', 4, 10);
        
                screen.posiciona(sprites.getNumero(Enano.getMinVida()), 'r', 30, 10);
                screen.posiciona(sprites.getNumero(Enano.getMaxVida()/10), 'v', 41, 10);
                screen.posiciona(sprites.getNumero(Enano.getMaxVida()%10), 'v', 47, 10);
                screen.posiciona(sprites.getNumero(Enano.getVidaXpiso()), 'v', 36, 16);
            } else if (option == 2) { //Imprimir guerrero
                screen.posiciona(sprites.getGuerrero(), 'z', 120, 2);
                
                screen.posiciona(opciones.toString(), 'x', 2, 49, true);
                screen.posiciona(stats.toString(), 'x', 2, 2, true);
                
                screen.posiciona(sprites.getTipo(option-1), 'x', 4, 4);
                
                screen.posiciona(sprites.getStatsHeroe(), 'a', 4, 10);
                
                screen.posiciona(sprites.getNumero(Guerrero.getMinVida()), 'r', 30, 10);
                screen.posiciona(sprites.getNumero(Guerrero.getMaxVida()/10), 'v', 41, 10);
                screen.posiciona(sprites.getNumero(Guerrero.getMaxVida()%10), 'v', 47, 10);
                screen.posiciona(sprites.getNumero(Guerrero.getVidaXpiso()), 'v', 36, 16);
            } else if (option == 3) { //Imprimir mago
                screen.posiciona(sprites.getMago(), 'z', 120, 2);
                
                screen.posiciona(opciones.toString(), 'x', 2, 49, true);
                screen.posiciona(stats.toString(), 'x', 2, 2, true);
                
                screen.posiciona(sprites.getTipo(option-1), 'x', 4, 4);
                
                screen.posiciona(sprites.getStatsHeroe(), 'a', 4, 10);
                
                screen.posiciona(sprites.getNumero(Mago.getMinVida()), 'r', 30, 10);
                screen.posiciona(sprites.getNumero(Mago.getMaxVida()), 'v', 41, 10);
                screen.posiciona(sprites.getNumero(Mago.getVidaXpiso()), 'v', 36, 16);
            }
            
            screen.posiciona(sprites.getA(), 'x', 4, 51);
            screen.posiciona(sprites.getS(), 'x', 113, 51);
            screen.posiciona(sprites.getD(), 'x', 219, 51);
    
            screen.mostrarPantalla();
            
            try {
                String input = sc.nextLine();
                
                if (input.equals("d")) {
                    option++;
                } else if (input.equals("a")) {
                    option--;
                } else if (input.equals("s")) {
                    elegido = true;
                }
            } catch (Exception e) {
                System.out.println("[!] Opción no válida");
            }
        } while (!elegido);
        
        switch (option) {
            case 1:
                jugador = new Enano(nick);
                break;
            case 2:
                jugador = new Guerrero(nick);
                break;
            case 3:
                jugador = new Mago(nick);
                break;
        }

        elegido = false;
        option = 1;

        //Elección de arma
        do {
            screen.limpiarPantalla();
            screen.marco('b');

            if (option > 4) {
                option = 1;
            }
            if (option < 1) {
                option = 4;
            }

            screen.posiciona(sprites.getFondoSeleccion(), 'n', 2, 2);
            screen.posiciona(stats.toString(), 'x', 2, 2, true);
            
            if (option == 1) { //Imprimir espada
                screen.posiciona(sprites.getEspadaEleccion(), 'z', 80, 15);

                screen.posiciona(opciones.toString(), 'x', 2, 49, true);

                screen.posiciona(sprites.getTipoArma(option-1), 'x', 4, 4);
        
                screen.posiciona(sprites.getStatsArma(), 'a', 4, 10);
        
                screen.posiciona(sprites.getNumero(Espada.getDanyoMax()/10), 'r', 30, 11);
                screen.posiciona(sprites.getNumero(Espada.getDanyoMax()%10), 'r', 35, 11);
                screen.posiciona(sprites.getNumero((int)(Espada.getCritico()*10)%10), 'v', 4, 24);
                screen.posiciona(sprites.getNumero((int)(Espada.getCritico()*100)%10), 'v', 9, 24);
            } else if (option == 2) { //Imprimir arco
                screen.posiciona(sprites.getArcoEleccion(), 'z', 90, 20);
                
                screen.posiciona(opciones.toString(), 'x', 2, 49, true);

                screen.posiciona(sprites.getTipoArma(option-1), 'x', 4, 4);
        
                screen.posiciona(sprites.getStatsArma(), 'a', 4, 10);
        
                screen.posiciona(sprites.getNumero(Arco.getDanyoMax()/10), 'r', 30, 11);
                screen.posiciona(sprites.getNumero(Arco.getDanyoMax()%10), 'r', 35, 11);
                screen.posiciona(sprites.getNumero((int)(Arco.getCritico()*10)%10), 'v', 4, 24);
                screen.posiciona(sprites.getNumero((int)(Arco.getCritico()*100)%10), 'v', 9, 24);
            } else if (option == 3) { //Imprimir hacha
                screen.posiciona(sprites.getHachaEleccion(), 'z', 90, 10);
                
                screen.posiciona(opciones.toString(), 'x', 2, 49, true);

                screen.posiciona(sprites.getTipoArma(option-1), 'x', 4, 4);
        
                screen.posiciona(sprites.getStatsArma(), 'a', 4, 10);
        
                screen.posiciona(sprites.getNumero(Hacha.getDanyoMax()/10), 'r', 30, 11);
                screen.posiciona(sprites.getNumero(Hacha.getDanyoMax()%10), 'r', 35, 11);
                screen.posiciona(sprites.getNumero((int)(Hacha.getCritico()*10)%10), 'v', 4, 24);
                screen.posiciona(sprites.getNumero((int)(Hacha.getCritico()*100)%10), 'v', 9, 24);
            } else if (option == 4) { //Imprimir hechizo
                screen.posiciona(sprites.getHechizoEleccion(), 'z', 110, 4);
                
                screen.posiciona(opciones.toString(), 'x', 2, 49, true);

                screen.posiciona(sprites.getTipoArma(option-1), 'x', 4, 4);
        
                screen.posiciona(sprites.getStatsArma(), 'a', 4, 10);
        
                screen.posiciona(sprites.getNumero(Hechizo.getDanyoMax()/10), 'r', 30, 11);
                screen.posiciona(sprites.getNumero(Hechizo.getDanyoMax()%10), 'r', 35, 11);
                screen.posiciona(sprites.getNumero((int)(Hechizo.getCritico()*10)%10), 'v', 4, 24);
                screen.posiciona(sprites.getNumero((int)(Hechizo.getCritico()*100)%10), 'v', 9, 24);
            }
            
            screen.posiciona(sprites.getA(), 'x', 4, 51);
            screen.posiciona(sprites.getS(), 'x', 113, 51);
            screen.posiciona(sprites.getD(), 'x', 219, 51);
    
            screen.mostrarPantalla();
            
            try {
                String input = sc.nextLine();
                
                if (input.equals("d")) {
                    option++;
                } else if (input.equals("a")) {
                    option--;
                } else if (input.equals("s")) {
                    elegido = true;
                }
            } catch (Exception e) {
                System.out.println("[!] Opción no válida");
            }
        } while (!elegido);

        switch (option) {
            case 1:
                jugador.setArma(new Arco());
                break;
            case 2:
                jugador.setArma(new Espada());
                break;
            case 3:
                jugador.setArma(new Hacha());
                break;
            case 4:
                jugador.setArma(new Hechizo());
                break;
        }

        return jugador;
    }
}
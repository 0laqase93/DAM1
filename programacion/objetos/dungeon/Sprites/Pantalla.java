package Sprites;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import Sprites.*;

public class Pantalla {

    private Character[][] pantalla;
    private Character[][] colores;
    private Map<Character, String> formatoColores = new HashMap<>();
    private int altura;
    private int ancho;

    public Pantalla(int altura, int ancho) {
        if (altura < 2) {
            altura = 2;
        }
        if (ancho < 2) {
            ancho = 2;
        }
        this.altura = altura;
        this. ancho = ancho;
        pantalla = new Character[altura][ancho];
        colores = new Character[altura][ancho];
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < ancho; j++) {
                pantalla[i][j] = ' ';
                colores[i][j] = 'x';
            }
        }
        formatoColores.put('x', Colors.RESET); //RESET
        formatoColores.put('n', Colors.BLACK); //NEGRO
        formatoColores.put('v', Colors.GREEN); //VERDE
        formatoColores.put('r', Colors.RED); //ROJO
        formatoColores.put('a', Colors.YELLOW); //AMARILLO
        formatoColores.put('z', Colors.BLUE); //AZUL
        formatoColores.put('b', Colors.WHITE); //BLANCO
    }

    public void marco(char input) {
        final Map<Character,char[]> tipos = new HashMap<>();
        tipos.put('b', new char[]{'█', '█', '█', '█', '█', '█'}); // bloque
        tipos.put('l', new char[]{'┌', '┐', '┘', '└','│', '─',}); // linea
        tipos.put('d', new char[]{'╔', '╗', '╝', '╚','║', '═',}); // doble

        char[] opciones = tipos.get(input);
        String bordeSuperior = opciones[0] + "";
        String bordeInferior = opciones[3] + "";
        for (int i = 1; i < ancho - 1; i++) {
            bordeSuperior += opciones[5];
            bordeInferior += opciones[5];
        }
        bordeSuperior += opciones[1];
        bordeInferior += opciones[2];

        for (int x = 0; x < altura; x++) {
            for (int y = 0; y < ancho; y++) {
                if (x == 0) {
                    pantalla[x][y] = bordeSuperior.charAt(y);
                } else if (x == altura-1) {
                    pantalla[x][y] = bordeInferior.charAt(y);
                } else if ((y == 0) || (y == ancho-1)) {
                    pantalla[x][y] = opciones[4];
                }
            }
        }
    }

    public void marco() {
        marco('d');
    }

    public void limpiarPantalla() {
        pantalla = new Character[altura][ancho];
        colores = new Character[altura][ancho];
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < ancho; j++) {
                pantalla[i][j] = ' ';
                colores[i][j] = 'x';
            }
        }
    }

    protected void clear() {
        try {
                String os = System.getProperty("os.name").toLowerCase();
                ProcessBuilder processBuilder;
                if (os.contains("win")) { // Windows
                    processBuilder = new ProcessBuilder("cmd", "/c", "cls");
                } else { // Unix/Linux/Mac
                    processBuilder = new ProcessBuilder("clear");
                }
                Process process = processBuilder.inheritIO().start();
                process.waitFor();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
        }
    }
    
    public void posiciona(String texto, char color, int x, int y, boolean ignorarEspacios) {
        int xInicial = x;
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) == '\n') {
                y++;
                x = xInicial-1;
            } else if ((texto.charAt(i) != ' ') || (ignorarEspacios)) {
                colores[y][x] = color;
                pantalla[y][x] = texto.charAt(i);
            }
            x++;
        }
    }

    public void posiciona(String texto, char color, int x, int y) {
        posiciona(texto, color, x, y, false);
    }

    public void rellenarPantalla() {
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < ancho; j++) {
                pantalla[i][j] = '▒';
            }
        }
    }

    public void dividirH() {
        for (int i = 0; i < altura; i++) {
            pantalla[i][ancho/2] = '█';
        }
    }
    
    @Override
    public String toString() {
        String pantalla = "";
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < ancho; j++) {
                pantalla += this.pantalla[i][j];
            }
            pantalla += "\n";
        }
        return pantalla;
    }

    public void mostrarPantalla() {
        this.clear();
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < ancho; j++) {
                System.out.print(formatoColores.get(this.colores[i][j]) + this.pantalla[i][j]);
            }
            System.out.println();
        }
    }
}
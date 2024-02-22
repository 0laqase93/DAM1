package Sprites;

import java.util.Scanner;

public class Letras {
    private static final String[] letras = 
    {
        "█████\n" +
        "█   █\n" +
        "█████\n" +
        "█   █\n" +
        "█   █\n",

        "█████\n" +
        "█  ██\n" +
        "████ \n" +
        "█  ██\n" +
        "█████\n",

        "█████\n" +
        "█    \n" +
        "█    \n" +
        "█    \n" +
        "█████\n",

        "████ \n" +
        "█   █\n" +
        "█   █\n" +
        "█   █\n" +
        "████ \n",

        "█████\n" +
        "█    \n" +
        "███  \n" +
        "█    \n" +
        "█████\n",

        "█████\n" +
        "█    \n" +
        "███  \n" +
        "█    \n" +
        "█    \n",

        "█████\n" +
        "█    \n" +
        "█ ███\n" +
        "█   █\n" +
        "█████\n",

        "█   █\n" +
        "█   █\n" +
        "█████\n" +
        "█   █\n" +
        "█   █\n",

        "█████\n" +
        "  █  \n" +
        "  █  \n" +
        "  █  \n" +
        "█████\n",

        "  ███ \n" +
        "   █ \n" +
        "   █ \n" +
        "█  █ \n" +
        "█████\n",

        "█  ██\n" +
        "█ █  \n" +
        "██   \n" +
        "█ █  \n" +
        "█  ██\n",

        "█    \n" +
        "█    \n" +
        "█    \n" +
        "█    \n" +
        "█████\n",

        "██ ██\n" +
        "█████\n" +
        "█ █ █\n" +
        "█   █\n" +
        "█   █\n",

        "█   █\n" +
        "██  █\n" +
        "█ █ █\n" +
        "█  ██\n" +
        "█   █\n",

        "█████\n" +
        "█   █\n" +
        "█   █\n" +
        "█   █\n" +
        "█████\n",

        "█████\n" +
        "█   █\n" +
        "█████\n" +
        "█    \n" +
        "█    \n",

        "█████\n" +
        "█   █\n" +
        "█ █ █\n" +
        "█  ██\n" +
        "█████\n",

        "█████\n" +
        "█   █\n" +
        "█████\n" +
        "█  █ \n" +
        "█   █\n",

        "█████\n" +
        "█    \n" +
        "█████\n" +
        "    █\n" +
        "█████\n",

        "█████\n" +
        "  █  \n" +
        "  █  \n" +
        "  █  \n" +
        "  █  \n",

        "█   █\n" +
        "█   █\n" +
        "█   █\n" +
        "█   █\n" +
        "█████\n",

        "█   █\n" +
        "█   █\n" +
        "█   █\n" +
        " █ █ \n" +
        "  █  \n",

        "█   █\n" +
        "█   █\n" +
        "█ █ █\n" +
        "█████\n" +
        "██ ██\n",

        "█   █\n" +
        " █ █ \n" +
        "  █  \n" +
        " █ █ \n" +
        "█   █\n",

        "█   █\n" +
        " █ █ \n" +
        "  █  \n" +
        "  █  \n" +
        "  █  \n",

        "█████\n" +
        "   █ \n" +
        "  █  \n" +
        " █   \n" +
        "█████\n"
    };

    private static final String[] letrasEspeciales = 
    {
        "  ██ \n" +
        "█████\n" +
        "█   █\n" +
        "█████\n" +
        "█   █\n" +
        "█   █\n",

        "  ██ \n" +
        "█████\n" +
        "█    \n" +
        "███  \n" +
        "█    \n" +
        "█████\n",

        "  ██ \n" +
        "█████\n" +
        "  █  \n" +
        "  █  \n" +
        "  █  \n" +
        "█████\n",

        "  ██ \n" +
        "█████\n" +
        "█   █\n" +
        "█   █\n" +
        "█   █\n" +
        "█████\n",

        "  ██ \n" +
        "█   █\n" +
        "█   █\n" +
        "█   █\n" +
        "█   █\n" +
        "█████\n",

        " ███ \n" +
        "█   █\n" +
        "██  █\n" +
        "█ █ █\n" +
        "█  ██\n" +
        "█   █\n",

        " ███ \n" +
        " ███ \n" +
        "     \n" +
        " ███ \n" +
        " ███ \n",

        "     \n" +
        "     \n" +
        "     \n" +
        "  ██ \n" +
        " ██  \n",

        "     \n" +
        "     \n" +
        "     \n" +
        "     \n" +
        " ███ \n",

        "█████\n" +
        "██ ██\n" +
        "   ██\n" +
        "     \n" +
        "   ██\n",

        "██   \n" +
        "     \n" +
        "██   \n" +
        "██ ██\n" +
        "█████\n",

        " ███ \n" +
        " █   \n" +
        " █   \n" +
        " █   \n" +
        " ███ \n",

        " ███ \n" +
        "   █ \n" +
        "   █ \n" +
        "   █ \n" +
        " ███ \n",

        "    █\n" +
        "   █ \n" +
        "  █  \n" +
        " █   \n" +
        "█    \n",
    };

    private static final String[] numeros = {
        "█████\n" +
        "█   █\n" +
        "█   █\n" +
        "█   █\n" +
        "█████",

        "███  \n" +
        "  █  \n" + 
        "  █  \n" +
        "  █  \n" +
        "█████",

        "█████\n" +
        "    █\n" +
        "█████\n" +
        "█    \n" +
        "█████",
        
        "█████\n" +
        "    █\n" +
        "  ███\n" +
        "    █\n" +
        "█████",

        "█   █\n" +
        "█   █\n" +
        "█████\n" +
        "    █\n" +
        "    █",

        "█████\n" +
        "█    \n" +
        "█████\n" +
        "    █\n" +
        "█████",

        "█████\n" +
        "█    \n" +
        "█████\n" +
        "█   █\n" +
        "█████",

        "█████\n" +
        "    █\n" +
        "    █\n" +
        "    █\n" +
        "    █",

        "█████\n" +
        "█   █\n" +
        "█████\n" +
        "█   █\n" +
        "█████",

        "█████\n" +
        "█   █\n" +
        "█████\n" +
        "    █\n" +
        "█████"
    };

    public static void imprimirLetra(char l, Pantalla p, int x, int y) {
        char letra = (char)(l - 97);
        if (l != ' ') {
            if ((l >= '0') && (l <= '9')) { // Números
                letra = (char)(l - '0');
                p.posiciona(numeros[letra], 'b', x, y);
            } else if ((l <= 'z') && (l >= 'a')) { // Letras normales
                letra = (char)(l - 'a');
                p.posiciona(letras[letra], 'b', x, y);
            } else { // Casos especiales
                switch (l) {
                    case 'á':
                        p.posiciona(letrasEspeciales[0], 'b', x, y-1);
                        break;
                    
                    case 'é':
                        p.posiciona(letrasEspeciales[1], 'b', x, y-1);
                        break;
    
                    case 'í':
                        p.posiciona(letrasEspeciales[2], 'b', x, y-1);
                        break;
    
                    case 'ó':
                        p.posiciona(letrasEspeciales[3], 'b', x, y-1);
                        break;
    
                    case 'ú':
                        p.posiciona(letrasEspeciales[4], 'b', x, y-1);
                        break;
    
                    case 'ñ':
                        p.posiciona(letrasEspeciales[5], 'b', x, y-1);
                        break;

                    case ':':
                        p.posiciona(letrasEspeciales[6], 'b', x, y);
                        break;

                    case ',':
                        p.posiciona(letrasEspeciales[7], 'b', x, y);
                        break;

                    case '.':
                        p.posiciona(letrasEspeciales[8], 'b', x, y);
                        break;

                    case '?':
                        p.posiciona(letrasEspeciales[9], 'b', x, y);
                        break;

                    case '¿':
                            p.posiciona(letrasEspeciales[10], 'b', x, y);
                            break;

                    case '[':
                        p.posiciona(letrasEspeciales[11], 'b', x, y);
                        break;

                    case ']':
                        p.posiciona(letrasEspeciales[12], 'b', x, y);
                        break;

                    case '/':
                        p.posiciona(letrasEspeciales[13], 'b', x, y);
                        break;
                
                    default:
                        break;
                }
            }
        }
    }

    public static void imprimirFrase(String s, Pantalla p, int x, int y, int tiempo) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        String palabra = "";
        s = s.toLowerCase();
        int xInicial = x;
        int yInicial = y;
        for (int i = 0; i < s.length(); i++) {
            if ((x >= p.getAncho() - 6) || (s.charAt(i) == '\n')) {
                x = xInicial;
                if ((s.charAt(i) == '\n') || (s.charAt(i) == ' ')) {
                    x -= 6;
                }
                y += 6;
            }
            if ((y >= p.getAltura() - 6)) {
                sc.nextLine();
                p.limpiarPantalla();
                x = xInicial;
                y = yInicial;
            }
            imprimirLetra(s.charAt(i), p, x, y);
            p.mostrarPantalla();
            x += 6;
            Thread.sleep(tiempo);
        }
    }
}

import Sprites.*;

public class prueba {
    public static void main(String[] args) {
        Pantalla p = new Pantalla(20, 100);
        int prueba = "\\033[0;30m".length();
        System.out.println(Colors.GREEN+'v'+Colors.RESET);
        p.marco();
        p.posiciona("caramelo prueba\n    <- 4 espacios",'v' , 1, 15);
        p.posiciona("123", 'x', 1, 16);
        p.mostrarPantalla();
    }
}

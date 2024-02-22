import Sprites.*;

public class prueba {
    public static void main(String[] args) throws InterruptedException {
        Pantalla p = new Pantalla(20, 100);
        p.marco('b');
        Letras.imprimirFrase("Probando salto línea\nHola", p, 2, 2, 200);
        p.mostrarPantalla();
    }
}

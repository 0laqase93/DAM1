import cartas.*;

public class prueba {
    public static void main(String[] args) {
        Pantalla screen = new Pantalla(150, 50);
        screen.marc();

        
        screen.situa(CartasASCII.getCartaAscii(0));
        screen.situa(20, 0, CartasASCII.getCartaAscii(9), 'v');
        screen.situa(40, 0, CartasASCII.getCartaAscii(10), 'r');
        screen.situa(60, 0, CartasASCII.getCartaAscii(11), 'b');
        screen.situa(80, 0, CartasASCII.getCartaAscii(12), 'g');
        screen.situa(100, 0, CartasASCII.getCartaAscii(13), 'x');
        
        screen.mostra();
    }
}

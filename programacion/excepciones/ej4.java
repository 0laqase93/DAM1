import java.util.Scanner;

class ElementoNoExistente extends Exception {
    public ElementoNoExistente(String mensaje) {
        super(mensaje);
    }
}

class ValidaLista {
    static Scanner sc = new Scanner(System.in);

    public static final String[] COMPOSITORES = {"Bach", "Haydn", "Mozart", "Beethoven", "Brahms", "Mahler", "Bartok"};

    public static int compositores() throws Exception {
        int contador = -1;
        try {
            System.out.print("[+] Introduce un autor: ");
            String compositor = sc.nextLine();
            boolean esta = false;
            while ((!esta) && (contador < COMPOSITORES.length-1)) {
                contador++;
                if (compositor.equals(COMPOSITORES[contador])) {
                    esta = true;
                }
            }
            if (!esta) {
                throw new ElementoNoExistente("[!] El autor no está en la lista");
            }
        } catch (ElementoNoExistente e) {
            throw new ElementoNoExistente(e.getMessage());
        }
        return contador;
    }
}

public class ej4 {
    public static void main(String[] args) throws Exception {
        try {       
            int compositorIndex = ValidaLista.compositores();
            System.out.println(compositorIndex);
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }
}

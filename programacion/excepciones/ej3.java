import java.util.InputMismatchException;
import java.util.Scanner;

class InputOK {

    static Scanner sc = new Scanner(System.in);

    public static int LeeInt() throws InputMismatchException{
        int input = 0;
        try {
            System.out.print("[+] Introduce un número entero: ");
            input = sc.nextInt(); sc.nextLine();
        } catch (InputMismatchException e) {
            throw new InputMismatchException("[!] No es un entero.");
        }
        return input;
    }

    public static int LeeIntPos() throws InputMismatchException{
        int input = 0;
        try {
            System.out.print("[+] Introduce un número entero: ");
            input = sc.nextInt(); sc.nextLine();
            if (input < 0) {
                throw new Exception("[!] El número tiene que ser entero");
            }
        } catch (InputMismatchException e) {
            throw new InputMismatchException("[!] No es un entero.");
        } catch (Exception e) {
            throw new InputMismatchException();
        }
        return input;
    }
}

public class ej3 {
    public static void main(String[] args) {
        try {
            int valor = InputOK.LeeInt();
            System.out.println(valor);

            valor = InputOK.LeeInt();
            System.out.println(valor);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

import java.util.InputMismatchException;
import java.util.Scanner;

class ExcepcionRango extends Exception {
    public ExcepcionRango(String mensaje) {
        super(mensaje);
    }
}

class InputOK {

    static Scanner sc = new Scanner(System.in);

    public static int LeeInt() throws InputMismatchException{
        int input = 0;
        try {
            System.out.print("[+] Introduce un número entero: ");
            input = sc.nextInt(); sc.nextLine();
        } catch (InputMismatchException e) {
            throw new InputMismatchException("[!] No es un número válido.");
        }
        return input;
    }

    public static int LeeIntPos() throws Exception{
        int input = 0;
        try {
            System.out.print("[+] Introduce un número entero positivo: ");
            input = sc.nextInt(); sc.nextLine();
            if (input < 0) {
                throw new ExcepcionRango("[!] El número tiene que ser positivo");
            }
        } catch (InputMismatchException e){
            throw new InputMismatchException("[!] No es un número válido.");
        } catch (ExcepcionRango e) {
            throw new ExcepcionRango(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e);
        }
        return input;
    }

    public static int LeeIntRango(int min, int max) throws Exception{
        int input = 0;
        try {
            System.out.print("[+] Introduce un número entero entre "+ min +" y " + max + " (incluidos): ");
            input = sc.nextInt(); sc.nextLine();
            if ((input < min) || (input > max)) {
                throw new ExcepcionRango("[!] El número tiene que estar entre " + min + " y " + max + " (incluidos)");
            }
        } catch (InputMismatchException e){
            throw new InputMismatchException("[!] No es un número válido.");
        } catch (ExcepcionRango e) {
            throw new ExcepcionRango(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e);
        }
        return input;
    }

    public static double LeeDou() throws Exception{
        double input = 0;
        try {
            System.out.print("[+] Introduce un número real: ");
            input = sc.nextDouble(); sc.nextLine();
        } catch (InputMismatchException e){
            throw new InputMismatchException("[!] No es un número válido.");
        } catch (Exception e) {
            throw new Exception(e);
        }
        return input;
    }

    public static double LeeDouRango(double min, double max) throws Exception{
        double input = 0;
        try {
            System.out.print("[+] Introduce un número real entre "+ min +" y " + max + " (incluidos): ");
            input = sc.nextDouble(); sc.nextLine();
            if ((input < min) || (input > max)) {
                throw new ExcepcionRango("[!] El número tiene que estar entre " + min + " y " + max + " (incluidos)");
            }
        } catch (InputMismatchException e){
            throw new InputMismatchException("[!] No es un número válido.");
        } catch (ExcepcionRango e) {
            throw new ExcepcionRango(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e);
        }
        return input;
    }
}

public class ej3 {
    public static void main(String[] args) {
        try {
            int valor = InputOK.LeeInt();
            System.out.println(valor);

            valor = InputOK.LeeIntPos();
            System.out.println(valor);

            valor = InputOK.LeeIntRango(0, 10);
            System.out.println(valor);

            double valor2 = InputOK.LeeDou();
            System.out.println(valor2);

            valor2 = InputOK.LeeDouRango(0.5, 10.5);
            System.out.println(valor2);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

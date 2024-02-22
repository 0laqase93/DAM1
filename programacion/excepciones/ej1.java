import java.util.InputMismatchException;
import java.util.Scanner;

public class ej1{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int divisor = 0, dividendo = 0;
        double resultado = 0.0;

        try {
            System.out.print("[+] Ingrese el dividendo: ");
            dividendo = sc.nextInt(); sc.nextLine();

            System.out.print("[+] Ingrese el divisor: ");
            dividendo = sc.nextInt(); sc.nextLine();

            resultado = dividendo / divisor;
            System.out.println(resultado);
        } catch (InputMismatchException e) {
            System.out.println("[!] Inserte un número.");
        } catch (ArithmeticException e) {
            System.out.println("[!] El divisor no puede ser igual a 0.");
        } catch (Exception e) {
            System.out.println("[!] Problemas con la división.");
        } finally {
            sc.close();
        }
    }
}
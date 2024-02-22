import java.util.*;

public class ej2 {
    public static void main(String[] args){
        double resultado = 0;

        try {
            resultado = dividir();
            System.out.println(resultado);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static double dividir() throws Exception {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("[+] Ingrese el dividendo: ");
            double dividendo = sc.nextDouble(); sc.nextLine();

            System.out.print("[+] Ingrese el divisor: ");
            double divisor = sc.nextDouble(); sc.nextLine();
            if (divisor == 0) {
                throw new ArithmeticException("[!] El divisor no puede ser igual a 0.");
            }
            double resultado = dividendo / divisor;
            return resultado;
        } catch (InputMismatchException e) {
            throw new InputMismatchException("[!] Inserte un número válido.");
        } finally {
            sc.close();
        }
    }
}

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

class FaltanArgumentos extends Exception {
    public FaltanArgumentos(String e){
        super(e);
    }
}

public class grepj {
    
    final static String RESET = "\u001B[0m";
    final static String VERDE = "\u001B[32m";

    public static void main(String[] args) {
        int numLinea = 1;
        String linea = "";
        try {
            if (args.length != 2) {
                throw new FaltanArgumentos("Faltan o hay demasiados argumentos.");
            }
            BufferedReader out = new BufferedReader(new FileReader(args[0]));
            String palabra = args[1];
            linea = out.readLine();
            while (linea != null) {
                if (linea.contains(palabra)) {          
                    linea = linea.replace(palabra, VERDE + palabra + RESET);          
                    System.out.println(numLinea + ": " + linea);
                }
                numLinea++;
                linea = out.readLine();
            }
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: No se encuentra el archivo.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
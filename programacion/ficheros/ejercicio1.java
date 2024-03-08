import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class ejercicio1 {
    public static void main(String[] args) {
        String linea;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            FileWriter in = new FileWriter("fuente.txt", true);
            BufferedWriter out = new BufferedWriter(in);
            do {
                System.out.print("[+] Escriba lo que quieres añadir al fichero (\"salir\" para salir del programa): ");
                linea = br.readLine();
                if (!linea.equals("salir")) {
                    out.write(linea + "\n");
                    out.flush();
                }
            } while (!linea.equals("salir"));
            br.close();
            in.close();
            out.close();
        } catch (FileNotFoundException e1) {
            System.out.println("[!] Error: No se encuentra el fichero.");
        } catch (Exception e2) {
            System.out.println("[! ]Error leyendo/escribiendo fichero.");
        }
    }
}

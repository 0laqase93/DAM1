import java.io.File;

public class rmj {
    public static void main(String[] args) {
        try {
            File fichero = new File(args[0]);
            if (fichero.exists()) {
                if (fichero.isFile()) {
                    fichero.delete();
                    System.out.println("[+] Archivo eliminado correctamente");
                } else {
                    throw new Exception("[!] No se pueden borrar los directorios, solo archivos.");
                }
            } else {
                throw new Exception("[!] El archivo no existe");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: [!] Tiene que pasar un argunmento con el formato: \"java rmj archivo\"");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

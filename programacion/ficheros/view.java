import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Set;
import java.util.TreeSet;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFilePermission;

public class view {

    public static final String RESET = "\033[0m";  // Text Reset
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED

    public static void main(String[] args) {
        try {
            File archivo = new File(args[0]);
            DecimalFormat formatoSize = new DecimalFormat("0.0");
            SimpleDateFormat formatoFecha = new SimpleDateFormat("EEE MMM d hh:mm:ss yyyy");
            PosixFileAttributeView fileAttributeView = Files.getFileAttributeView(Path.of(archivo.getAbsolutePath()), PosixFileAttributeView.class);
            Set<PosixFilePermission> permissions = fileAttributeView.readAttributes().permissions();
            TreeSet<PosixFilePermission> sortedPermissions = new TreeSet<>(permissions);
            int numPermisos = 3;

            
            if (archivo.exists()) {
                if (archivo.isFile()) {
                    System.out.print(BLUE_BOLD_BRIGHT + "." + RESET);
                } else {
                    System.out.print(BLUE_BOLD_BRIGHT + "d" + RESET);
                }
                
                for (PosixFilePermission permission : sortedPermissions) {
                    String permiso = permission.toString().substring(6);
                    String permisoOtros = permission.toString().substring(7);
                    boolean puesto = false;
                    while (!puesto) {
                        if (numPermisos == 3) {
                            if (permiso.equals("READ") || permisoOtros.equals("READ")) {
                                System.out.print(GREEN_BOLD_BRIGHT + "r" + RESET);
                                puesto = true;
                            } else {
                                System.out.print("-");
                            }
                            numPermisos = 2;
                        } else if (numPermisos == 2) {
                            if (permiso.equals("WRITE") || permisoOtros.equals("WRITE")) {
                                System.out.print(YELLOW_BOLD_BRIGHT + "w" + RESET);
                                puesto = true;
                            } else {
                                System.out.print("-");
                            }
                            numPermisos = 1;
                        } else {
                            if (permiso.equals("EXECUTE") || (permisoOtros.equals("EXECUTE"))) {
                                System.out.print(RED_BOLD_BRIGHT + "x" + RESET);
                                puesto = true;
                            } else {
                                System.out.print("-");
                            }
                            numPermisos = 3;
                        }
                    }
                }

                System.out.println(" " + YELLOW_BOLD_BRIGHT + formatoSize.format(archivo.length()/1024.0) + " Kb " + RESET + GREEN_BOLD_BRIGHT + formatoFecha.format(archivo.lastModified()) + RESET + " " + BLUE_BOLD_BRIGHT + archivo.getName() + RESET);
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

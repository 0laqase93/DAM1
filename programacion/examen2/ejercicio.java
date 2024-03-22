import java.io.*;
import java.util.*;
import clases.*;

public class ejercicio {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        List<Piloto> pilotos = LeerPilotos("pilotos.txt");
        List<Circuito> circuitos = LeerCircuitos("circuito.txt");
        int valor = menu();
        while (valor != 3) {
            if (valor == 1) {
                try {
                    ArrayList<ResultadoCarrera> rc = new ArrayList<>();
                    FileWriter f = new FileWriter("./clases/resultados.txt", true);
                    BufferedWriter in = new BufferedWriter(f);
                    Circuito c = null;
                    Integer posicion;
                    
                    System.out.print("Introduce el número de circuito: ");
                    Integer input = Integer.parseInt(sc.nextLine());
                    if ((input < 1) || (input > 24)) {
                        throw new IOException();
                    }
                    // Encuentro el cirucito
                    for (Circuito circuito : circuitos) {
                        if (input == circuito.getNcircuito()) {
                            c = circuito;
                            break;
                        }
                    }
                    System.out.println(">> Has introducido el circuito: " + c.getNombre());

                    // Imprimo los pilotos y guardo los resultados
                    for (Piloto p : pilotos) {
                        System.out.print("Introduce la posición de " + p.getNombre() + ": ");
                        posicion = Integer.parseInt(sc.nextLine());
                        rc.add(new ResultadoCarrera(c, p, posicion));
                        in.write(c.getNcircuito() + ":" + p.getNombre() + ":" + posicion + "\n");
                        in.flush();
                        
                    }

                } catch (NumberFormatException e){
                    System.out.println("Error: Solo se permiten números");
                } catch (IOException e) {
                    System.out.println("Error: Opción no válida");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (valor == 2) {
                ArrayList<PuntuacionPiloto> puntuaciones = new ArrayList<>();
                for (Piloto p : pilotos) {
                    puntuaciones.add(new PuntuacionPiloto(p));
                }
                //Clasificacion clasificacion = new Clasificacion(puntuaciones);
                //clasificacion.ordenaClasificación();
                //clasificacion.imprimirClasificacion();
                System.out.println("Presione enter para continuar...");
                sc.nextLine();
            }
            valor = menu();
        }
        System.out.println("Saliendo del programa...");
    }

    public static List<Piloto> LeerPilotos(String fichero){
        ArrayList<Piloto> pilotos = new ArrayList<>();
        Piloto p;
        String linea = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("pilotos.txt"));
            linea = br.readLine();
            while (linea != null) {
                String[] valores = linea.split(":");
                p = new Piloto(valores[0], valores[1], valores[2]);
                pilotos.add(p);
                linea = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e){
            System.out.println("Error: No se encuentra el archivo");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pilotos;
    }

    public static List<Circuito> LeerCircuitos(String fichero){
        ArrayList<Circuito> circuitos = new ArrayList<>();
        Circuito c;
        String linea = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("circuitos.txt"));
            linea = br.readLine();
            while (linea != null) {
                String[] valores = linea.split(":");
                c = new Circuito(Integer.parseInt(valores[0]), valores[1], valores[2], Integer.parseInt(valores[3]));
                circuitos.add(c);
                linea = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e){
            System.out.println("Error: No se encuentra el archivo");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return circuitos;
    }

    public static int menu(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();

        int input = 0;
        try {
            System.out.println("1.\tAgregar resultados\n2.\tClasificación\n3.\tSalir");
            input = Integer.parseInt(sc.nextLine());
            if ((input < 1) || (input > 3)) {
                throw new IOException();
            }
        } catch (NumberFormatException e){
            System.out.println("Error: Solo se permiten números");
        } catch (IOException e) {
            System.out.println("Error: Opción no válida");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return input;
    } 
}

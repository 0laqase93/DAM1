import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class mostrarTodo{

    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:33006/examen_ev2", "root", "123");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM PREGUNTA");
            Scanner sc = new Scanner(System.in);

            String respuesta = "";
            String opciones = "";
            int numPregunta = 1;
            while (rs.next()) {
                opciones = "";
                boolean verificar;
                String pregunta = rs.getString("Enunciado");
                ResultSet respuestas = st.executeQuery("SELECT * FROM RESPUESTA WHERE IdRespuesta LIKE \"" + numPregunta +"._\";");
                while (respuestas.next()) {
                    opciones += "\t" + YELLOW + respuestas.getString("IdRespuesta").substring(2) + RESET + " -> " + GREEN + respuestas.getString("Respuesta") + RESET + "\n";
                }
                do {
                    verificar = false;

                    //Limpiar pantalla
                    System.out.print("\033[H\033[2J");  
                    System.out.flush();
    
                    //Imprimo la pregunta
                    System.out.println("Pregunta " + RED + numPregunta + RESET + ":");
                    System.out.println("\t" + BLUE + pregunta + RESET);
                    System.out.println("Respuestas:");
                    //Imprimo las respuestas
                    System.out.println(opciones);
                    //Leo la respuesta y la meto dentro de la bases de datos
                    System.out.print(CYAN + "Respuesta pregunta: " + RESET);
                    respuesta = numPregunta + "." + sc.nextLine();
                    System.out.println("¿Seguro que quieres responder de está forma?");
                    ResultSet preguntaSeleccionada =  st.executeQuery("SELECT Respuesta FROM RESPUESTA WHERE IdRespuesta LIKE '" + respuesta + "';");
                    if(preguntaSeleccionada.next()) {
                        respuesta = preguntaSeleccionada.getString("Respuesta");
                    } else {
                        System.out.println("No se encontraron resultados para la consulta.");
                    }
                    System.out.println(BLUE + pregunta + RESET + " -> " + GREEN + respuesta + RESET);
                    System.out.print(CYAN + "Respuesta Validación [S/N]: " + RESET);
                    if (sc.nextLine().toLowerCase().equals("s")) {
                        verificar = true;
                    }
                } while (!verificar);
                int numfilas = st.executeUpdate("INSERT INTO PREGUNTA (IdRespuesta) VALUES ('" + respuesta + "')");
                System.out.println("Se han insertado " + numfilas + " filas en la tabla");
                System.out.println("Presione enter para continuar...");
                sc.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void imprimirTodo(ResultSet rs) {
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int numColumnas = rsmd.getColumnCount();

            while (rs.next()) {
                for (int i = 0; i < numColumnas; i++) {
                    switch (rsmd.getColumnType(i+1)) {
                        case -6:
                            System.out.print(rs.getInt(rsmd.getColumnName(i+1)) + " | ");
                            break;
                    
                        case 4:
                            System.out.print(rs.getInt(rsmd.getColumnName(i+1)) + " | ");
                            break;

                        case 12:
                            System.out.print(rs.getString(rsmd.getColumnName(i+1)) + " | ");
                            break;

                        default:
                            System.out.print("Nada | ");
                            break;
                    }
                }
                System.err.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
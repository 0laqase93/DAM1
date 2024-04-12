package reservaVuelos;

import java.io.*;
import java.sql.*;
import java.util.*;
import reservaVuelos.Clases.*;

public class ReservaVuelos {
    public static void main(String[] args) {
        int opcion = 0;
        Connection con = null;
        try {
            con = crearConexion("33006", "reservaVuelos", "root", "root");
            opcion = mostrarMenu();
            while (opcion != 6) {
                switch (opcion) {
                    case 1: darAltaVuelo(con); break;
                    case 2: darAltaPasajero(con); break;
                    case 3: reservarVuelo(con); break;
                    case 4: modificarReserva(con); break;
                    case 5: darBajaReserva(con); break;
                }
                opcion = mostrarMenu();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Cerrar conexión y terminar programa
        try {
            con.close();
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "[!] Fallo en la desconexión de la base de datos" + ConsoleColors.RESET);
        }
        System.out.println(ConsoleColors.RED + "[!] Saliendo del programa... :[" + ConsoleColors.RESET);
    }   

    public static int mostrarMenu() {
        int opcion = 0;

        // Limpar pantalla
        System.out.println("\33[H\033[2J");
        System.out.flush();
        
        System.out.println(ConsoleColors.YELLOW + "MENU" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.GREEN + "1." + ConsoleColors.RESET + ConsoleColors.BLUE + " Alta Vuelo" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.GREEN + "2." + ConsoleColors.RESET + ConsoleColors.BLUE + " Alta Pasajero" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.GREEN + "3." + ConsoleColors.RESET + ConsoleColors.BLUE + " Reserva Vuelo" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.GREEN + "4." + ConsoleColors.RESET + ConsoleColors.BLUE + " Modificar reserva" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.GREEN + "5." + ConsoleColors.RESET + ConsoleColors.BLUE + " Baja reserva" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.GREEN + "6." + ConsoleColors.RESET + ConsoleColors.RED + " Salir (Nadie querría salir de un programa tan chulo)" + ConsoleColors.RESET);
        
        do {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("[+] Opción: ");
                opcion = Integer.parseInt(br.readLine());
                if ((opcion < 1) || (opcion > 6)) {
                    System.out.println(ConsoleColors.RED + "[!] Opción no válida" + ConsoleColors.RESET);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while ((opcion < 1) || (opcion > 6));

        return opcion;
    }

    public static Connection crearConexion(String puerto, String baseDatos, String usuario, String passwd) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:" + puerto + "/" + baseDatos, usuario, passwd);
            return con;
        } catch (Exception e) {
            return null;
        }
    }

    public static void darAltaVuelo(Connection con) {
        String origen = "", destino = "", fecha = "";
        int capacidad = 0;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            // Limpar pantalla
            System.out.println("\33[H\033[2J");
            System.out.flush();

            System.out.println(ConsoleColors.YELLOW + "DAR ALTA VUELO" + ConsoleColors.RESET);

            // Pedir origen
            System.out.print("[+] Inserte el " + ConsoleColors.PURPLE + "origen" + ConsoleColors.RESET + ": ");
            origen = br.readLine();

            // Pedir destino
            System.out.print("[+] Inserte el " + ConsoleColors.PURPLE + "destino" + ConsoleColors.RESET + ": ");
            destino = br.readLine();

            // Pedir fecha
            boolean fechaValida = false;
            do {
                System.out.print("[+] Inserte el " + ConsoleColors.PURPLE + "fecha" + ConsoleColors.RESET + ConsoleColors.RED + " (AAAA-MM-DD)" + ConsoleColors.RESET + ": ");
                fecha = br.readLine();
                try {
                    String[] valores = fecha.split("-");
                    int year = Integer.parseInt(valores[0]);
                    int month = Integer.parseInt(valores[1]);
                    int day = Integer.parseInt(valores[2]); 
                    if (valores.length == 3) {
                        if ((valores[0].length() == 4) && (valores[1].length() == 2) && (valores[2].length() == 2)) {
                            if (((month > 12) || (month == 0)) || ((day > 31) || (day == 0)) || (year == 0)) {
                                throw new Exception();
                            }
                            fechaValida = true;
                        } else {
                            throw new Exception();
                        }
                    } else {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    System.out.println(ConsoleColors.RED + "[!] Fecha no válida" + ConsoleColors.RESET);
                }
            } while (!fechaValida);

            // Pedir capacidad
            try {
                System.out.print("[+] Inserte el " + ConsoleColors.PURPLE + "capacidad" + ConsoleColors.RESET + ": ");
                capacidad = Integer.parseInt(br.readLine());
                if (capacidad <= 0) {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println(ConsoleColors.RED + "[!] Capacidad no válida" + ConsoleColors.RESET);
            }

            // Guardar los datos en la base de datos
            Vuelo vuelo = new Vuelo(origen, destino, fecha, capacidad);
            vuelo.guardarDatos(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void darAltaPasajero(Connection con) {
        String sql = "";
        ResultSet out = null;
        PreparedStatement st = null;
        String pasaporte = "", nombre = "";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            // Limpar pantalla
            System.out.println("\33[H\033[2J");
            System.out.flush();

            System.out.println(ConsoleColors.YELLOW + "DAR ALTA PASAJERO" + ConsoleColors.RESET);

            // Pedir pasaporte
            boolean pasaporteValido = false;
            do {
                System.out.print("[+] Inserte el " + ConsoleColors.PURPLE + "pasaporte" + ConsoleColors.RESET + ": ");
                pasaporte = br.readLine();
                
                // Comprobar si existe
                try {
                    sql = "SELECT numero_pasaporte FROM Pasajeros WHERE numero_pasaporte LIKE ?";
                    st = con.prepareStatement(sql);
                    st.setString(1, pasaporte);
                    out = st.executeQuery();
                    if (out.next()) { // Si hay datos entonces ya existe
                        throw new SQLException("El usuario con ese pasaporte ya existe en la base de datos");
                    } else {
                        pasaporteValido = true;
                    }
                } catch (SQLException e) {
                    System.out.println(ConsoleColors.RED + "[!] Error: " + e.getMessage() + ConsoleColors.RESET);
                }
            } while (!pasaporteValido);

            // Pedir nombre
            System.out.print("[+] Inserte el " + ConsoleColors.PURPLE + "nombre" + ConsoleColors.RESET + ": ");
            nombre = br.readLine();
            
            // Guardar los datos en la base de datos
            Pasajero pasajero = new Pasajero(pasaporte, nombre);
            pasajero.guardarDatos(con);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerramos las conexiones
            try {
                out.close();
                st.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void reservarVuelo(Connection con) {
        String sql = "";
        PreparedStatement st = null;
        ResultSet out = null;
        String pasaporte = "", origen = "", destino = "", fecha = "";
        Vuelo vuelo = null;
        Pasajero pasajero = null;
        int asiento = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Limpar pantalla
        System.out.println("\33[H\033[2J");
        System.out.flush();

        System.out.println(ConsoleColors.YELLOW + "RESERVAR VUELO" + ConsoleColors.RESET);

        // Pedir ID de la reserva
        boolean reservaValida = false;
        do {
            try {
                boolean pasajeroValido = false;
                do {
                    System.out.print("[+] Inserte el " + ConsoleColors.PURPLE + "pasaporte" + ConsoleColors.RESET + ": ");
                    pasaporte = br.readLine();

                    // Comprobamos si el pasajero no está en la base de datos
                    try {
                        sql = "SELECT numero_pasaporte FROM Pasajeros WHERE numero_pasaporte LIKE ?";
                        st = con.prepareStatement(sql);
                        st.setString(1, pasaporte);
                        out = st.executeQuery();
                        if (!out.next()) { // Si no hay datos no existe el pasajero registrado
                            System.out.println(ConsoleColors.RED + "[!] Este pasaporte no está registrado" + ConsoleColors.RESET);
                            System.out.print(ConsoleColors.BLUE + "[?] ¿Desea registrarse?" + ConsoleColors.RESET + ConsoleColors.RED + "(S/N)" + ConsoleColors.RESET + ": ");
                            String input = br.readLine().toUpperCase();
                            if (input.equals("S")) {
                                darAltaPasajero(con);
                            } else {
                                throw new SQLException("Este usuario no existe");
                            }
                        } else {
                            pasajeroValido = true;
                            int id = out.getInt("id_pasajero");
                            pasaporte = out.getString("numero_pasaporte");
                            String nombre = out.getString("nombre_pasajero");
                            pasajero = new Pasajero(id, pasaporte, nombre);
                        }
                    } catch (SQLException e) {
                        System.out.println(ConsoleColors.RED + "[!] Error: " + e.getMessage() + ConsoleColors.RESET);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } while (!pasajeroValido);

                boolean vueloValido = false;
                do {
                    // Pedir origen
                    System.out.print("[+] Inserte el " + ConsoleColors.PURPLE + "origen" + ConsoleColors.RESET + ": ");
                    origen = br.readLine();
    
                    // Pedir destino
                    System.out.print("[+] Inserte el " + ConsoleColors.PURPLE + "destino" + ConsoleColors.RESET + ": ");
                    destino = br.readLine();
    
                    // Pedir fecha
                    boolean fechaValida = false;
                    do {
                        System.out.print("[+] Inserte el " + ConsoleColors.PURPLE + "fecha" + ConsoleColors.RESET + ConsoleColors.RED + " (AAAA-MM-DD)" + ConsoleColors.RESET + ": ");
                        fecha = br.readLine();
                        try {
                            String[] valores = fecha.split("-");
                            int year = Integer.parseInt(valores[0]);
                            int month = Integer.parseInt(valores[1]);
                            int day = Integer.parseInt(valores[2]); 
                            if (valores.length == 3) {
                                if ((valores[0].length() == 4) && (valores[1].length() == 2) && (valores[2].length() == 2)) {
                                    if (((month > 12) || (month == 0)) || ((day > 31) || (day == 0)) || (year == 0)) {
                                        throw new Exception();
                                    }
                                    fechaValida = true;
                                } else {
                                    throw new Exception();
                                }
                            } else {
                                throw new Exception();
                            }
                        } catch (Exception e) {
                            System.out.println(ConsoleColors.RED + "[!] Fecha no válida" + ConsoleColors.RESET);
                        }
                    } while (!fechaValida);
                    
                    // Comprobamos que le vuelo existe
                    try {
                        sql = "SELECT id_vuelo FROM Vuelos WHERE origen LIKE ? AND destino LIKE ? AND fecha LIKE ?";
                        st = con.prepareStatement(sql);
                        st.setString(1, origen);
                        st.setString(2, destino);
                        st.setString(3, fecha);
                        out = st.executeQuery();
                        if (!out.next()) { // Si no hay datos no existe el vuelo
                            throw new SQLException("Este vuelo no existe");
                        } else {
                            vueloValido = true;
                            int id = out.getInt("id_vuelo");
                            int capacidad = out.getInt("capacidad");
                            vuelo = new Vuelo(id, origen, destino, fecha, capacidad);
                        }
                        

                    } catch (SQLException e) {
                        System.out.println(ConsoleColors.RED + "[!] Error: " + e.getMessage() + ConsoleColors.RESET);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } while (!vueloValido);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // Cerramos las conexiones
                try {
                    out.close();
                    st.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } while (!reservaValida);
    }

    public static void modificarReserva(Connection con) {
        int idReserva = 0, idPasajero = 0, idVuelo = 0, asiento = 0, nuevoAsiento = 0;
        String sql = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            // Limpar pantalla
            System.out.println("\33[H\033[2J");
            System.out.flush();

            System.out.println(ConsoleColors.YELLOW + "RESERVAR VUELO" + ConsoleColors.RESET);

            // Pedir ID de la reserva
            boolean reservaValida = false;
            do {
                try {
                    System.out.print("[+] Inserte el " + ConsoleColors.PURPLE + "ID de la reserva" + ConsoleColors.RESET + ": ");
                    idReserva = Integer.parseInt(br.readLine());

                    // Comprobamos si existe
                    sql = "SELECT * FROM Vuelos_Pasajeros WHERE id_reserva = ?";
                    PreparedStatement st = con.prepareStatement(sql);
                    st.setInt(1, idReserva);
                    ResultSet out = st.executeQuery();
                    if (out.next()) { // Si hay datos entonces hay una reserva y guardamos los datos
                        reservaValida = true;
                        idVuelo = out.getInt("id_vuelo");
                        idPasajero = out.getInt("id_pasajero");
                        asiento = out.getInt("n_asiento");
                        // Se guarda el vuelo y el pasajero en objetos
                        sql = "SELECT * FROM Vuelos WHERE id_vuelo = ?;";
                        st = con.prepareStatement(sql);
                        st.setInt(1, idVuelo);
                        out = st.executeQuery();
                        Vuelo vuelo = new Vuelo(out.getInt(1), out.getString(2), out.getString(3), out.getString(4), out.getInt(5));
                        


                        System.out.print("[?] ¿Desea ver la información del" + ConsoleColors.PURPLE + " vuelo " +idVuelo + ConsoleColors.RESET + "? Su " + ConsoleColors.PURPLE + "asiento" + ConsoleColors.RESET + " es el " + ConsoleColors.PURPLE + asiento + ConsoleColors.RESET + ". " + ConsoleColors.RED + "(S/N)" + ConsoleColors.RESET + ":");
                        String input = br.readLine().toUpperCase();
                        if (input.equals("S")) { // Si es necesario se lee el vuelo
                            sql = "SELECT * FROM Vuelos WHERE id_vuelo = ?;";
                            st = con.prepareStatement(sql);
                            st.setInt(1, idVuelo);
                            out = st.executeQuery();
                            if (out.next()) {
                                System.out.println(ConsoleColors.BLUE + "Vuelo: " + ConsoleColors.RESET + ConsoleColors.PURPLE + idVuelo + ConsoleColors.RESET + ConsoleColors.BLUE + "\n\tTrayecto: " + ConsoleColors.RESET + ConsoleColors.RED + out.getString("origen") + ConsoleColors.RESET + " -> " + ConsoleColors.GREEN + out.getString("destino") + ConsoleColors.RESET + ConsoleColors.BLUE + "\n\tFecha: " + ConsoleColors.RESET + ConsoleColors.YELLOW + out.getString("fecha") + ConsoleColors.RESET);
                            }
                        }
                        System.out.println("[?] ¿A cuál asiento desea cambiar su reserva?: ");
                        nuevoAsiento = Integer.parseInt(br.readLine());
                        if (reservaValida) {
                            
                        }
                    } else {
                        throw new SQLException("No existe una reserva con ese ID en la base de datos");
                    }
                } catch (SQLException e) {
                    System.out.println(ConsoleColors.RED + "[!] Error: " + e.getMessage() + ConsoleColors.RESET);
                } catch (IOException e) {
                    System.out.println(ConsoleColors.RED + "[!] Error: Formato no válido" + ConsoleColors.RESET);
                } catch (Exception e) {
                    System.out.println(ConsoleColors.RED + "[!] Error:" + e.getMessage() + ConsoleColors.RESET);
                }
            } while (!reservaValida);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void darBajaReserva(Connection con) {

    }
}
